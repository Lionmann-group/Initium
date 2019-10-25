package main;

import players.BaseEnemy;
import players.EnemyBoss;
import players.EnemyShip;

public class EnemySpawner {

    boolean lvl1Finished,lvl2Finished;
    double enemyTimer;
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
        //if (enemyTimer == 100) {
        //return new BaseEnemy[] { new EnemyBoss(1250, 225) };
        //}
        if (enemyTimer == 100) {
            return new BaseEnemy[] { new EnemyShip(1250, 25), new EnemyShip(1250, 525) };
        } else if (enemyTimer == 200) {
            return new BaseEnemy[] { new EnemyShip(1250, 125), new EnemyShip(1250, 425) };
        } else if (enemyTimer == 300) {
            return new BaseEnemy[] { new EnemyShip(1250, 225), new EnemyShip(1250, 325) };
        } else if (enemyTimer == 600) {
            return new BaseEnemy[] { new EnemyShip(1250, 25), new EnemyShip(1250, 525) };
        } else if (enemyTimer == 700) {
            return new BaseEnemy[] { new EnemyShip(1250, 125), new EnemyShip(1250, 425) };
        } else if (enemyTimer == 800) {
            return new BaseEnemy[] { new EnemyShip(1250, 225), new EnemyShip(1250, 325) };
        } else if (enemyTimer == 900) {
            return new BaseEnemy[] { new EnemyShip(1250, 125), new EnemyShip(1250, 225) };
        } else if (enemyTimer == 1300) {
            return new BaseEnemy[] { new EnemyShip(1250, 75), new EnemyShip(1250, 475) };
        } else if (enemyTimer == 1400) {
            return new BaseEnemy[] { new EnemyShip(1250, 125), new EnemyShip(1250, 425) };
        } else if (enemyTimer == 1500) {
            return new BaseEnemy[] { new EnemyShip(1250, 175), new EnemyShip(1250, 375) };
        } else if (enemyTimer == 1600) {
            return new BaseEnemy[] { new EnemyShip(1250, 225), new EnemyShip(1250, 325) };
        } else if (enemyTimer == 2000) {
            return new BaseEnemy[] { new EnemyShip(1250, 25), new EnemyShip(1250, 525) };
        } else if (enemyTimer == 2100) {
            return new BaseEnemy[] { new EnemyShip(1250, 125), new EnemyShip(1250, 425) };
        } else if (enemyTimer == 2200) {
            return new BaseEnemy[] { new EnemyShip(1250, 225), new EnemyShip(1250, 325) };
        } else if (enemyTimer == 2700) {
            return new BaseEnemy[] { new EnemyBoss(1250, 225) };
        }
        return null;
    }

    public BaseEnemy spawnAsteriodEnemy() {
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

    public double getEnemyTimer() {
        return enemyTimer;
    }

    public void setEnemyTimer(double enemyTimer) {
        this.enemyTimer = enemyTimer;
    }

}