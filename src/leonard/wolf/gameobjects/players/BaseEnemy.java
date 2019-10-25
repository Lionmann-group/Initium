package leonard.wolf.gameobjects.players;

import leonard.wolf.gamecore.ImageRepository;
import leonard.wolf.gameobjects.projectiles.FriendlyProjectile;
import leonard.wolf.gameobjects.projectiles.HostileProjectile;

import java.awt.*;

public class BaseEnemy extends BaseShip {

    public BaseEnemy(double posX, double posY) {
        super(posX, posY);
        setSizeX(80);
        setSizeY(60);
        setHp(1);
        setSpeedX(-2);
        img = ImageRepository.getInstance().loadImage("/Enemies/Asteroid.png");
        img = img.getScaledInstance((int)getSizeX(), (int)getSizeY(), Image.SCALE_SMOOTH);
    }

    @Override
    public boolean outOfArea(double posX,double posY,double width,double height) {
        return getPosX() <= posX || getPosY() +  getSizeY() >= height + posY|| getPosY() <= posY;
    }
    public boolean leftOutOfMap(double posX) {
        return getPosX() <= posX;
    }

    //TODO
    // I SHOULD HANDLE ALL STUFF THAT HAPPENS WHEN AN ENEMY IS HIT HERE AND REWORK MY PLAYERSHIP ENEMY COLLISION
    public void isHit() {

    }

    public void onDeath() {

    }

    public boolean hitByProjectile(FriendlyProjectile p) {
        return inBounds(p.getPosX(), p.getPosY(), p.getSizeX(), p.getSizeY());
    }

    public HostileProjectile[] shoot() {
        return null;
    }

}