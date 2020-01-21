package de.lw.game.core;

import de.lw.game.objects.players.BaseEnemy;
import de.lw.game.objects.players.PlayerShip;
import de.lw.game.objects.projectiles.BaseProjectile;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class DrawMainCanvas extends JFrame {

    public static final int CANVAS_WIDTH = 1200;
    public static final int CANVAS_HEIGHT = 700;

    private int measuredFps;
    private double shootTimer;
    private int updateRate = 60;
    private int score;

    private GameOverlay gameOverlay;
    private EnemySpawner enemySpawner;
    private PlayerShip playerShip;
    private List<BaseEnemy> enemies;

    private DrawCanvas canvas;

    private boolean spawnProjectileOnNextCycle;

    public DrawMainCanvas() {
        playerShip = new PlayerShip(200, 300);
        gameOverlay = new GameOverlay();
        enemySpawner = new EnemySpawner(gameOverlay.gamePanel.getPosX(), gameOverlay.gamePanel.getPosY(), gameOverlay.gamePanel.getSizeX(), gameOverlay.gamePanel.getSizeY());
        enemies = new ArrayList<>(64);

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

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();

        canvas.createBufferStrategy(2);

        setLocationRelativeTo(null);
        setTitle("Not Space Invaders");
        setVisible(true);

        canvas.requestFocus();
    }

    public void run() {
        final BufferStrategy strategy = canvas.getBufferStrategy();

        final Thread th1 = new Thread() {
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

                    move();

                    if (spawnProjectileOnNextCycle) {
                        playerShip.shoot();

                        spawnProjectileOnNextCycle = false;
                    }

                    gameOverlay.setOverchargeTexture(playerShip.getAmmo());

                    ammoDelay();
                    enemySpawn();
                    enemyShoots();
                    collision();

                    /*for (BaseEnemy baseEnemy : baseEnemies) {
                        System.out.println(baseEnemy.getHp());
                    }*/

                    canvas.paint(strategy.getDrawGraphics());

                    while (System.nanoTime() < lastLoopTime + 1_000_000_000 / updateRate) {
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

        if (enemySpawner.spawnAsteroidEnemy() != null) {
            enemies.add(enemySpawner.spawnAsteroidEnemy());
        }

        if (enemySpawner.spawnLvL1() != null && !enemySpawner.lvl1Finished) {
            enemies.addAll(Arrays.asList(enemySpawner.spawnLvL1()));
        }
    }

    private void move() {
        if (enemies.size() > 0) {
            final Iterator<BaseEnemy> enemyIterator = enemies.iterator();

            while (enemyIterator.hasNext()) {
                final BaseEnemy enemy = enemyIterator.next();

                moveProjectiles(enemy.getShotsFired());

                enemy.move();

                if (enemy.leftOutOfMap(gameOverlay.gamePanel.getPosX())) {
                    enemyIterator.remove();
                }
            }
        }

        moveProjectiles(playerShip.getShotsFired());
        playerShip.move();

        playerShipOutOfBounds();
    }

    private void moveProjectiles(final List<BaseProjectile> projectiles) {
        if (projectiles.size() > 0) {
            final Iterator<BaseProjectile> projectileIterator = projectiles.iterator();

            while (projectileIterator.hasNext()) {
                final BaseProjectile projectile = projectileIterator.next();

                projectile.shipPosition(playerShip.getPosXMiddle(), playerShip.getPosYMiddle());
                projectile.move();

                if (projectile.outOfMap(gameOverlay.gamePanel.getPosX(),
                        gameOverlay.gamePanel.getPosY(),
                        gameOverlay.gamePanel.getSizeX(),
                        gameOverlay.gamePanel.getSizeY())
                ) {
                    projectileIterator.remove();
                }
            }
        }
    }

    private void playerShipOutOfBounds() {
        int gamePanelPosX = (int) gameOverlay.gamePanel.getPosX();
        int gamePanelPosY = (int) gameOverlay.gamePanel.getPosY();
        int gamePanelSizeX = (int) gameOverlay.gamePanel.getSizeX();
        int gamePanelSizeY = (int) gameOverlay.gamePanel.getSizeY();

        if (playerShip.outOfAreaLeft(gamePanelPosX)) {
            playerShip.setPosX(gamePanelPosX + 1);
        }

        if (playerShip.outOfAreaRight(gamePanelPosX, gamePanelSizeX)) {
            playerShip.setPosX(gamePanelPosX + gamePanelSizeX - playerShip.getSizeX() - 1);
        }

        if (playerShip.outOfAreaTop(gamePanelPosY)) {
            playerShip.setPosY(gamePanelPosY + 1);
        }

        if (playerShip.outOfAreaBottom(gamePanelPosY, gamePanelSizeY)) {
            playerShip.setPosY(gamePanelPosY + gamePanelSizeY - playerShip.getSizeY() - 1);
        }
    }

    private void collision() {
        final Iterator<BaseEnemy> enemyIterator = enemies.iterator();

        enemyCheck:
        while (enemyIterator.hasNext()) {
            final BaseEnemy enemy = enemyIterator.next();

            final Iterator<BaseProjectile> playerProjectileIterator = playerShip.getShotsFired().iterator();

            while (playerProjectileIterator.hasNext()) {
                final BaseProjectile projectile = playerProjectileIterator.next();

                if (enemy.hitByProjectile(projectile)) {
                    playerProjectileIterator.remove();

                    enemy.isHit();
                    enemy.setHp(enemy.getHp() - 1);
                    if (enemy.getHp() <= 0) {
                        enemy.onDeath();

                        enemyIterator.remove();

                        score += 10;

                        continue enemyCheck;
                    }
                }
            }

            final Iterator<BaseProjectile> enemyProjectileIterator = enemy.getShotsFired().iterator();

            while (enemyProjectileIterator.hasNext()) {
                final BaseProjectile projectile = enemyProjectileIterator.next();

                if (playerShip.hitByProjectile(projectile)) {
                    enemyProjectileIterator.remove();

                    playerIsHit();
                }
            }

            if (playerShip.hitByEnemy(enemy)) {
                enemyIterator.remove();

                playerIsHit();
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
        for (BaseEnemy enemy : enemies) {
            enemy.shoot();
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

            for (final BaseProjectile projectile : playerShip.getShotsFired()) {
                projectile.draw(g2d);
            }

            for (BaseEnemy enemy : enemies) {
                enemy.draw(g2d);

                for (final BaseProjectile projectile : enemy.getShotsFired()) {
                    projectile.draw(g2d);
                }
            }

            g2d.setColor(Color.WHITE);
            g2d.drawString(Integer.toString(measuredFps), 10, CANVAS_HEIGHT - 20);

            getBufferStrategy().show();
        }

    }

}
