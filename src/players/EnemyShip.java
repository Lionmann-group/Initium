package players;


import main.ImageRepository;
import object.HostileProjectile;

import java.awt.*;

public class EnemyShip extends BaseEnemy {

    int shootDelay;
    int turn;

    public EnemyShip(double posX, double posY) {
        super(posX, posY);
        setSizeX(55);
        setSizeY(40);
        setHp(1);
        setSpeedX(-1.5);
        if(Math.random() * 2 == 1) {
            setSpeedY(0.4);
        }else {
            setSpeedY(-0.4);
        }
        img = ImageRepository.getInstance().loadImage("/Enemies/Enemyship.png");
        img = img.getScaledInstance((int)getSizeX(), (int)getSizeY(), Image.SCALE_SMOOTH);
    }

    @Override
    public HostileProjectile[] shoot() {
        shootDelay++;
        if (shootDelay == 100) {
            shootDelay = 0;
            return new HostileProjectile[] { new HostileProjectile((int) getPosXMiddle(), (int) getPosYMiddle()) };
        }
        return null;
    }

    @Override
    public void move() {
        posX = posX + getSpeedX();
        posY = posY + getSpeedY();
        turn++;
        if (outOfArea(0, 21, 2000, 548)) {
            setSpeedY(getSpeedY() * (-1));
        }
        if(turn == 150) {
            setSpeedY(getSpeedY() * (-1));
            turn = 0;
        }
    }
}