package leonard.wolf.gameobjects.players;

import leonard.wolf.gamecore.ImageRepository;
import leonard.wolf.gameobjects.projectiles.FriendlyProjectile;
import leonard.wolf.gameobjects.projectiles.HostileProjectile;

import java.awt.*;

public class PlayerShip extends BaseShip {

    double ammo;
    double shield;
    Color shieldColor = Color.GREEN;

    public PlayerShip(double posX, double posY) {
        super(posX, posY);
        setSizeX(80);
        setSizeY(30);
        setHp(3);
        setAmmo(10);
        setShield(10);
        img = ImageRepository.getInstance().loadImage("/Player/Spaceship.png");
        img = img.getScaledInstance((int)getSizeX(), (int)getSizeY(), Image.SCALE_SMOOTH);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(shieldColor);
        g2d.fillOval((int) getPosX() -10, (int) getPosY() -5, 100, 40);
        g2d.drawImage(img, (int) getPosX(), (int) getPosY(), null);
    }

    public void ifHit() {
        if(getShield() > 7) {
            shieldColor = Color.GREEN;
        }else if(getShield() > 4) {
            shieldColor = Color.ORANGE;
        }else if(getShield() > 1) {
            shieldColor = Color.RED;
        }else if(getShield() < 1) {
            shieldColor = Color.BLACK;
        }
    }

    public boolean hitByEnemy(BaseEnemy p) {
        ifHit();
        return inBounds(p.getPosX(), p.getPosY(), p.getSizeX(), p.getSizeY());
    }

    public boolean hitByProjectile(HostileProjectile p) {
        ifHit();
        return inBounds(p.getPosX(), p.getPosY(), p.getSizeX(), p.getSizeY());
    }

    public FriendlyProjectile shoot() {
        return new FriendlyProjectile((int)getPosXMiddle(), (int)getPosYMiddle());
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public double getAmmo() {
        return ammo;
    }

    public void setAmmo(double ammo) {
        this.ammo = ammo;
    }

    public double getShield() {
        return shield;
    }

    public void setShield(double shield) {
        this.shield = shield;
    }
}