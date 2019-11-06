package de.lw.game.core;

import de.lw.game.objects.players.BaseEnemy;
import de.lw.game.objects.players.EnemyBoss;
import de.lw.game.objects.players.EnemyShip;

public class EnemySpawner {

    boolean lvl1Finished,lvl2Finished;
    int enemyTimer;
    double posX;
    double posY;
    double sizeX;
    double sizeY;

    public EnemySpawner(double posX,double posY,double sizeX,double sizeY) {
        this.posX = posX;
        this.posY = posY;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public BaseEnemy[] spawnLvL1() {
        switch (enemyTimer) {
            case 100:
            case 600:
            case 2000: return new BaseEnemy[] { new EnemyShip(1250, 25), new EnemyShip(1250, 525) };

            case 200:
            case 700:
            case 1400:
            case 2100: return new BaseEnemy[] { new EnemyShip(1250, 125), new EnemyShip(1250, 425) };

            case 300:
            case 800:
            case 1600:
            case 2200: return new BaseEnemy[] { new EnemyShip(1250, 225), new EnemyShip(1250, 325) };

            case 900: return new BaseEnemy[] { new EnemyShip(1250, 125), new EnemyShip(1250, 225) };

            case 1300: return new BaseEnemy[] { new EnemyShip(1250, 75), new EnemyShip(1250, 475) };

            case 1500: return new BaseEnemy[] { new EnemyShip(1250, 175), new EnemyShip(1250, 375) };

            case 2700: return new BaseEnemy[] { new EnemyBoss(1250, 225) };

            default: return null;
        }
    }

    public BaseEnemy spawnAsteroidEnemy() {
        if(enemyTimer % 400 == 0) {
            return new BaseEnemy(1250, (Math.random() * 530) + 20);
        }

        return null;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public double getSizeX() {
        return sizeX;
    }

    public double getSizeY() {
        return sizeY;
    }

    public int getEnemyTimer() {
        return enemyTimer;
    }

    public void setEnemyTimer(final int enemyTimer) {
        this.enemyTimer = enemyTimer;
    }

}
