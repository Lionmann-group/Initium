package de.lw.game.objects.players;

import de.lw.game.core.ImageRepository;
import de.lw.game.core.Lifebar;
import de.lw.game.objects.projectiles.AimedProjectile;
import de.lw.game.objects.projectiles.FollowProjectile;
import de.lw.game.objects.projectiles.HostileProjectile;

import java.awt.Graphics2D;
import java.awt.Image;

public class EnemyBoss extends EnemyShip {

    private Lifebar lifebar;
    private int phase = 1;

    public EnemyBoss(final double posX, final double posY) {
        super(posX, posY);

        setSizeX(100);
        setSizeY(100);
        setHp(100);
        setSpeedX(-1.5);
        setSpeedY(-5);

        img = ImageRepository.getInstance().loadImage("/Enemies/EnemyBoss.png");
        img = img.getScaledInstance((int) getSizeX(), (int) getSizeY(), Image.SCALE_SMOOTH);
        lifebar = new Lifebar(getPosX(), getPosY(), getSizeX(), getHp());
    }

    @Override
    public void draw(final Graphics2D g2d) {
        g2d.drawImage(img, (int) getPosX(), (int) getPosY(), null);
        lifebar.draw(g2d);
    }

    @Override
    public void shoot() {
        // turn means the timer where the ship doesn't move
        if (turn < 500) {
            shootDelay++;

            if (shootDelay % 50 == 0) {
                shotsFired.add(new HostileProjectile(getPosX(), getPosY()));
                shotsFired.add(new HostileProjectile(getPosX(), getPosY() + 40));
                shotsFired.add(new HostileProjectile(getPosX(), getPosY() + 80));
            }

            if ((phase == 3 && shootDelay % 32 == 0) || (phase == 2 && shootDelay % 20 == 0)) {
                shotsFired.add(new AimedProjectile(getPosX(), getPosY() + 40));
            }

            if (phase == 3) {
                if (shootDelay % 101 == 0) {
                    shotsFired.add(new FollowProjectile(getPosX(), getPosY() + 40));
                }
            } else if (phase == 1) {
                if (shootDelay == 249 || shootDelay == 499) {
                    shotsFired.add(new FollowProjectile(getPosX(), getPosY() + 40));
                }
            }

            if (shootDelay == 500) {
                shootDelay = 0;
            }
        }
    }

    @Override
    public void isHit() {
        lifebar.lifebarLoseHp(1);
        if (getHp() == 60) {
            phase = 2;
        } else if (getHp() == 30) {
            phase = 3;
        }
    }

    @Override
    public void onDeath() {
        lifebar = null;
    }

    @Override
    public void move() {
        lifebar.setPosition(getPosX(), getPosY());

        posX = posX + getSpeedX();
        posY = posY + getSpeedY();

        turn++;

        if (turn > 600) {
            setSpeedY(-5);
            turn = 0;
        } else if (turn > 500)
            setSpeedY(0);
        if (getPosX() <= 1000) {
            setSpeedX(0);
        }

        if (outOfArea(0, 21, 2000, 548)) {
            setSpeedY(getSpeedY() * (-1));
        }
    }

}
