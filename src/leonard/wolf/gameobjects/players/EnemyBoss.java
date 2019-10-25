package leonard.wolf.gameobjects.players;


import leonard.wolf.gamecore.ImageRepository;
import leonard.wolf.gamecore.Lifebar;
import leonard.wolf.gameobjects.projectiles.AimedProjectile;
import leonard.wolf.gameobjects.projectiles.FollowProjectile;
import leonard.wolf.gameobjects.projectiles.HostileProjectile;

import java.awt.*;

public class EnemyBoss extends EnemyShip {

    Lifebar lifebar;
    int phase = 1;

    public EnemyBoss(double posX, double posY) {
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
    public void draw(Graphics2D g2d) {
        g2d.drawImage(img, (int) getPosX(), (int) getPosY(), null);
        lifebar.draw(g2d);
    }

    @Override
    public HostileProjectile[] shoot() {
        //turn means the timer where the ship doesnt move
        if (turn < 500) {
            shootDelay++;
            if(phase == 3) {
                if (shootDelay % 50 == 0) {
                    return new HostileProjectile[] { new HostileProjectile(getPosX(), getPosY()),
                            new HostileProjectile(getPosX(), getPosY() + 40),
                            new HostileProjectile(getPosX(), getPosY() + 80) };
                }
                if(shootDelay % 32 == 0) {
                    return new HostileProjectile[] { new AimedProjectile(getPosX(), getPosY() + 40) };
                }
                if(shootDelay % 101 == 0) {
                    return new HostileProjectile[] { new FollowProjectile(getPosX(), getPosY() + 40) };
                }
            }
            else if (phase == 2) {
                if (shootDelay % 50 == 0) {
                    return new HostileProjectile[] { new HostileProjectile(getPosX(), getPosY()),
                            new HostileProjectile(getPosX(), getPosY() + 40),
                            new HostileProjectile(getPosX(), getPosY() + 80) };
                }
                if(shootDelay % 20 == 0) {
                    return new HostileProjectile[] { new AimedProjectile(getPosX(), getPosY() + 40) };
                }
            } else if(phase == 1){
                if (shootDelay % 50 == 0) {
                    return new HostileProjectile[] { new HostileProjectile(getPosX(), getPosY()),
                            new HostileProjectile(getPosX(), getPosY() + 40),
                            new HostileProjectile(getPosX(), getPosY() + 80) };
                }
                if (shootDelay  == 249 || shootDelay == 499) {
                    return new HostileProjectile[] { new FollowProjectile(getPosX(), getPosY() + 40) };
                }
            }
            if (shootDelay == 500) {
                shootDelay = 0;
            }
        }
        return null;
    }

    @Override
    public void isHit() {
        lifebar.lifebarLoseHp(1);
        if (getHp() == 60) {
            phase = 2;
        }else if(getHp() == 30) {
            phase = 3;
        }
    }

    @Override
    public void onDeath() {
        lifebar.equals(null);
    }

    @Override
    public void move() {
        lifebar.sync(getPosX(), getPosY());
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