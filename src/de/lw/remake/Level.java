package de.lw.remake;

import de.lw.remake.objects.BaseShip;

public class Level {

    Formation[] formations;

    public Level(Formation[] formations) {

        this.formations = formations;

    }

    private class Formation {
        protected BaseShip[] formation;

        protected Formation(BaseShip[] formation){
            this.formation = formation;
        }

    }

}
