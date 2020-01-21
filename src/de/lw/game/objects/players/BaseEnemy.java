package de.lw.game.objects.players;

import de.lw.game.core.ImageRepository;
import de.lw.game.objects.projectiles.FriendlyProjectile;
import de.lw.game.objects.projectiles.HostileProjectile;

import java.awt.Image;

public class BaseEnemy extends BaseShip {

    public BaseEnemy(final double posX, final double posY) {
        super(posX, posY);

        setSizeX(80);
        setSizeY(60);
        setHp(1);
        setSpeedX(-2);

        img = ImageRepository.getInstance().loadImage("/Enemies/Asteroid.png");
        img = img.getScaledInstance((int) getSizeX(), (int) getSizeY(), Image.SCALE_SMOOTH);
    }

    @Override
    public boolean outOfArea(final double posX, final double posY, final double width, final double height) {
        return getPosX() <= posX || getPosY() + getSizeY() >= height + posY || getPosY() <= posY;
    }

    public boolean leftOutOfMap(final double posX) {
        return getPosX() <= posX;
    }

    //TODO I SHOULD HANDLE ALL STUFF THAT HAPPENS WHEN AN ENEMY IS HIT HERE AND REWORK MY PLAYERSHIP ENEMY COLLISION
    public void isHit() {}

    public void onDeath() {}

    public boolean hitByProjectile(final FriendlyProjectile p) {
        return inBounds(p.getPosX(), p.getPosY(), p.getSizeX(), p.getSizeY());
    }

}
