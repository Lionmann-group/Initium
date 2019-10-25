package object;


import main.ImageRepository;

import java.awt.*;

public class FollowProjectile extends HostileProjectile {

    double followerX;
    double followerY;

    public FollowProjectile(double posX, double posY) {
        super(posX, posY);
        setSizeX(30);
        setSizeY(30);
        setSpeedX(-4);
        img = ImageRepository.getInstance().loadImage("/Objects/Energyball.png");
        img = img.getScaledInstance((int)getSizeX(), (int)getSizeY(), Image.SCALE_SMOOTH);
    }

    @Override
    public void move() {
        posX = posX + getSpeedX();
        if (posY < followerY)
            posY = posY + 2;
        else if (posY > followerY) {
            posY = posY - 2;
        }
    }

    @Override
    public void shipPosition(double x ,double y) {
        followerX = x;
        followerY = y;
    }

}