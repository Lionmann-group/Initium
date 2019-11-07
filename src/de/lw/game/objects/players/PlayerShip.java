package de.lw.game.objects.players;

import de.lw.game.core.ImageRepository;
import de.lw.game.objects.projectiles.FriendlyProjectile;
import de.lw.game.objects.projectiles.HostileProjectile;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;

public class PlayerShip extends BaseShip {

    private int ammo, shield;
    private Color shieldColor = Color.GREEN;

    public PlayerShip(final double posX, final double posY) {
        super(posX, posY);

        setSizeX(80);
        setSizeY(30);
        setHp(3);
        setAmmo(10);
        setShield(10);

        img = ImageRepository.getInstance().loadImage("/Player/Spaceship.png");
        img = img.getScaledInstance((int) getSizeX(), (int) getSizeY(), Image.SCALE_SMOOTH);
    }

    @Override
    public void draw(final Graphics2D g2d) {
        if (getShield() > 1) {
            g2d.setColor(shieldColor);

            final Stroke before = g2d.getStroke();

            g2d.setStroke(new BasicStroke(3.0f));
            g2d.drawOval((int) getPosX() - 15, (int) getPosY() - 10, 110, 50);
            g2d.setStroke(before);
        }

        g2d.drawImage(img, (int) getPosX(), (int) getPosY(), null);
    }

    public void ifHit() {
        if (getShield() > 7) {
            shieldColor = Color.GREEN;
        } else if (getShield() > 4) {
            shieldColor = Color.ORANGE;
        } else if (getShield() > 1) {
            shieldColor = Color.RED;
        }
    }

    public boolean hitByEnemy(final BaseEnemy p) {
        ifHit();
        return inBounds(p.getPosX(), p.getPosY(), p.getSizeX(), p.getSizeY());
    }

    public boolean hitByProjectile(final HostileProjectile p) {
        ifHit();
        return inBounds(p.getPosX(), p.getPosY(), p.getSizeX(), p.getSizeY());
    }

    @Override
    public void shoot() {
        shotsFired.add(new FriendlyProjectile((int) getPosXMiddle(), (int) getPosYMiddle()));
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(final int ammo) {
        this.ammo = ammo;
    }

    public int getShield() {
        return shield;
    }

    public void setShield(final int shield) {
        this.shield = shield;
    }

}
