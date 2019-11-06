package de.lw.game.core;

import de.lw.game.objects.players.BaseEnemy;
import de.lw.game.objects.players.PlayerShip;
import de.lw.game.objects.projectiles.BaseProjectile;
import de.lw.game.objects.projectiles.FriendlyProjectile;
import de.lw.game.objects.projectiles.HostileProjectile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class DrawMainCanvas extends JFrame {

    ImageRepository repository = ImageRepository.getInstance();

    public static final int CANVAS_WIDTH = 1200;
    public static final int CANVAS_HEIGHT = 700;

    private int measuredFps;
    private double shootTimer;
    private int gamespeed = 60;
    private int score;

    private GameOverlay gameOverlay;
    private EnemySpawner enemySpawner;
    private PlayerShip playerShip;
    private List<HostileProjectile> hostileProjectiles;
    private List<FriendlyProjectile> friendlyProjectiles;
    private List<BaseEnemy> baseEnemies;

    private DrawCanvas canvas;

    private boolean spawnProjectileOnNextCycle;

    public DrawMainCanvas() {
        gameOverlay = new GameOverlay();
        enemySpawner = new EnemySpawner(gameOverlay.gamePanel.getPosX(), gameOverlay.gamePanel.getPosY(),
                gameOverlay.gamePanel.getSizeX(), gameOverlay.gamePanel.getSizeY());
        playerShip = new PlayerShip(200, 300);
        hostileProjectiles = new LinkedList<>();
        friendlyProjectiles = new LinkedList<>();
        baseEnemies = new LinkedList<>();

        setResizable(false);

        canvas = new DrawCanvas();
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        add(canvas);

        canvas.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D) {
                    playerShip.setSpeedX(0);
                }
                if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S) {
                    playerShip.setSpeedY(0);
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    playerShip.setSpeedY(-8);
                } else if (e.getKeyCode() == KeyEvent.VK_S) {
                    playerShip.setSpeedY(8);
                }
                if (e.getKeyCode() == KeyEvent.VK_A) {
                    playerShip.setSpeedX(-5);
                } else if (e.getKeyCode() == KeyEvent.VK_D) {
                    playerShip.setSpeedX(5);
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (playerShip.getAmmo() > 0) {
                        spawnProjectileOnNextCycle = true;
                        playerShip.setAmmo(playerShip.getAmmo() - 1);
                    }
                }
            }
        });

//		canvas.addMouseListener(new MouseAdapter() {
//
//			@Override
//			public void mouseReleased(MouseEvent e) {
//
//			}
//		});

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        canvas.createBufferStrategy(2);
        setLocationRelativeTo(null);
        setTitle("Not Space Invaders");
        setVisible(true);
        canvas.requestFocus();
    }

    public void run() {
        BufferStrategy strategy = canvas.getBufferStrategy();

        Thread th1 = new Thread() {
            @Override
            public void run() {
                long lastLoopTime = System.nanoTime();
                int fps = 0;
                long lastFpsMeasure = System.nanoTime();

                while (!isInterrupted()) {
                    if (!playerShip.isAlive()) {
                        interrupt();
                        System.out.println("You lost");
                        System.exit(0);
                    }
                    moving();
                    if (spawnProjectileOnNextCycle) {
                        friendlyProjectiles.add(playerShip.shoot());
                        spawnProjectileOnNextCycle = false;
                    }
                    gameOverlay.overchargebarAnimation(playerShip.getAmmo());
                    ammoDelay();
                    enemySpawn();
                    enemyShoots();
                    collision();
                    for (int i = 0; i < baseEnemies.size(); i++) {
                        System.out.println(baseEnemies.get(i).getHp());
                    }
                    canvas.paint(strategy.getDrawGraphics());

                    while (System.nanoTime() < lastLoopTime + 1_000_000_000 / gamespeed) {
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    fps++;
                    if (System.nanoTime() - lastFpsMeasure > 1_000_000_000) {
                        measuredFps = fps;
                        fps = 0;
                        lastFpsMeasure = System.nanoTime();
                    }

                    lastLoopTime = System.nanoTime();
                }
            }
        };
        th1.start();
    }

    private void ammoDelay() {
        shootTimer++;
        if (playerShip.getAmmo() < 10 && shootTimer >= 45) {
            playerShip.setAmmo(playerShip.getAmmo() + 1);
            shootTimer = 0;
        }
    }

    private void enemySpawn() {
        enemySpawner.setEnemyTimer(enemySpawner.getEnemyTimer() + 1);
        if (enemySpawner.spawnAsteriodEnemy() != null) {
            baseEnemies.add(enemySpawner.spawnAsteriodEnemy());
        }
        if (enemySpawner.spawnLvL1() != null && !enemySpawner.lvl1Finished) {
            baseEnemies.addAll(Arrays.asList(enemySpawner.spawnLvL1()));
        }
    }

    private void moving() {
        if (!friendlyProjectiles.isEmpty()) {
            for (int i = 0; i < friendlyProjectiles.size(); i++) {
                BaseProjectile p = friendlyProjectiles.get(i);
                p.move();
                if (p.outOfMap(gameOverlay.gamePanel.getPosX(), gameOverlay.gamePanel.getPosY(),
                        gameOverlay.gamePanel.getSizeX(), gameOverlay.gamePanel.getSizeY())) {
                    friendlyProjectiles.remove(p);
                    i--;
                }
            }
        }
        if (!hostileProjectiles.isEmpty()) {
            for (int i = 0; i < hostileProjectiles.size(); i++) {
                BaseProjectile p = hostileProjectiles.get(i);
                p.shipPosition(playerShip.getPosXMiddle(), playerShip.getPosYMiddle());
                p.move();
                if (p.outOfMap(gameOverlay.gamePanel.getPosX(), gameOverlay.gamePanel.getPosY(),
                        gameOverlay.gamePanel.getSizeX(), gameOverlay.gamePanel.getSizeY())) {
                    hostileProjectiles.remove(p);
                    i--;
                }
            }
        }
        if (!baseEnemies.isEmpty()) {
            for (int i = 0; i < baseEnemies.size(); i++) {
                BaseEnemy b = baseEnemies.get(i);
                b.move();
                if (b.leftOutOfMap(gameOverlay.gamePanel.getPosX())) {
                    baseEnemies.remove(b);
                    i--;
                }
            }
        }
        playerShip.move();
        playerShipOutOfBounds();
    }

    public void playerShipOutOfBounds() {

        int gamepanelPosX = (int) gameOverlay.gamePanel.getPosX();
        int gamepanelPosY = (int) gameOverlay.gamePanel.getPosY();
        int gamepanelSizeX = (int) gameOverlay.gamePanel.getSizeX();
        int gamepanelSizeY = (int) gameOverlay.gamePanel.getSizeY();

        if (playerShip.outOfAreaLeft(gamepanelPosX)) {
            playerShip.setPosX(gamepanelPosX + 1);
        }
        if (playerShip.outOfAreaRight(gamepanelPosX, gamepanelSizeX)) {
            playerShip.setPosX(gamepanelPosX + gamepanelSizeX - playerShip.getSizeX() - 1);
        }
        if (playerShip.outOfAreaTop(gamepanelPosY)) {
            playerShip.setPosY(gamepanelPosY + 1);
        }
        if (playerShip.outOfAreaBottom(gamepanelPosY, gamepanelSizeY)) {
            playerShip.setPosY(gamepanelPosY + gamepanelSizeY - playerShip.getSizeY() - 1);
        }
    }

    private void collision() {
        for (int i = 0; i < baseEnemies.size(); i++) {
            BaseEnemy p = baseEnemies.get(i);
            for (int j = 0; j < friendlyProjectiles.size(); j++) {
                BaseProjectile b = friendlyProjectiles.get(j);
                if (p.hitByProjectile(b)) {
                    p.isHit();
                    p.setHp(p.getHp() - 1);
                    if (p.getHp() <= 0) {
                        p.onDeath();
                        baseEnemies.remove(i);
                        score += 10;
                    }
                    friendlyProjectiles.remove(j);
                    i--;
                    break;
                }
            }
        }
        for (int i = 0; i < baseEnemies.size(); i++) {
            BaseEnemy p = baseEnemies.get(i);
            if (playerShip.hitByEnemy(p)) {
                baseEnemies.remove(i);
                playerIsHit();
                i--;
            }
        }
        for (int i = 0; i < hostileProjectiles.size(); i++) {
            HostileProjectile p = hostileProjectiles.get(i);
            if (playerShip.hitByProjectile(p)) {
                hostileProjectiles.remove(i);
                playerIsHit();
                i--;
            }
        }
    }

    private void playerIsHit() {
        if (playerShip.getShield() > 0) {
            playerShip.setShield(playerShip.getShield() - 1);
        } else {
            playerShip.setHp(playerShip.getHp() - 1);
            gameOverlay.lifeAnimation(playerShip.getHp());
        }
    }

    private void enemyShoots() {
        HostileProjectile[] spawnedProjectiles;
        for (BaseEnemy p : baseEnemies) {
            spawnedProjectiles = p.shoot();
            if (spawnedProjectiles != null) {
                hostileProjectiles.addAll(Arrays.asList(spawnedProjectiles));
            }
        }
    }

    private class DrawCanvas extends Canvas {

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.WHITE);
            gameOverlay.draw(g2d);
            playerShip.draw(g2d);
            // g2d.drawString("LVL: " + Integer.toString(enemySpawner.getLvl()), 100, 35);
            g2d.setColor(Color.WHITE);
            g2d.drawString("Score: " + score, 25, 35);
            for (BaseEnemy p : baseEnemies) {
                p.draw(g2d);
            }
            for (FriendlyProjectile p : friendlyProjectiles) {
                p.draw(g2d);
            }
            for (HostileProjectile p : hostileProjectiles) {
                p.draw(g2d);
            }
            g2d.setColor(Color.WHITE);
            g2d.drawString(Integer.toString(measuredFps), 10, CANVAS_HEIGHT - 20);
            getBufferStrategy().show();

        }

    }

}