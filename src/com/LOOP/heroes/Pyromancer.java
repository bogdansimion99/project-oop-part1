package com.LOOP.heroes;

import com.LOOP.helpers.*;
import com.LOOP.maps.Map;

public class Pyromancer extends Hero implements Modificator {
    private Hero pyromancer;

    public Hero getPyromancer() {
        return pyromancer;
    }

    public Pyromancer(Hero pyromancer, int[] position) {
        super(GeneralConstants.INITIAL_HP_PYROMANCER, position/*, "Pyromancer"*/);
        this.pyromancer = pyromancer;
    }

    public static class Constants {
        public static final float MODIFICATOR_FIREBLAST = 0.9f;
        public static final float MODIFICATOR_IGNITE = 0.9f;
        public static final float MODIFICATOR_EXECUTE = 1.1f;
        public static final float MODIFICATOR_SLAM = 0.9f;
        public static final float MODIFICATOR_DRAIN = 0.9f;
        public static final float MODIFICATOR_DEFLECT = 1.3f;
        public static final float MODIFICATOR_BACKSTAB = 1.25f;
        public static final float MODIFICATOR_PARALYSIS = 1.2f;
    }

    @Override
    public float accept(final ModificatorVisitor modificatorVisitor, float[] modificators) {
        return modificatorVisitor.visit(this, modificators);
    }

    @Override
    public void action(final Hero aggressor, final Hero victim, final Map area) {
        fireblast(aggressor, victim, area);
        if (victim.getHp() == 0) {
            return;
        }
        ignite(aggressor, victim, area);
    }

    public void fireblast(final Hero aggressor, final Hero victim, final Map area) {
        float hp = 0;
        hp = PyromancerConstants.BASE_DAMAGE_FIREBLAST + PyromancerConstants.ADDED_DAMAGE_FIREBLAST
                * aggressor.getLevel();
        if (area.getType().equals("Volcanic")) {
            hp = hp * area.getModificator();
        }
        float[] modificators = {Rogue.Constants.MODIFICATOR_FIREBLAST, Knight.Constants.
                MODIFICATOR_FIREBLAST, Constants.MODIFICATOR_FIREBLAST, Wizard.Constants.
                MODIFICATOR_FIREBLAST};
        hp = hp * victim.accept(new Append(), modificators);
        /*
        * victim.accept(new ModificatorVisitor);
        * Ma duc in alta clasa (de exemplu, modificator)
        * Stiu ca este fireblast, deci nu mai trebuie sa verific asta
        * Clasa in care ma duc este, de fapt, o interfata care implementeaza toate functiile
        * ce imi trebuiesc pentru a lua modificatorul.
        * Imi trebuie o clasa unde sunt implementate toate visit-urile
        * Clasele in care fac accept -> visit sunt chiar aceste clase din hero.
        * Se face override la accept si la visit
        *
        * */
        hp = Math.round(hp);
        victim.setHp(victim.getHp() - (int)hp);
        if(victim.getHp() <= 0) {
            victim.setHp(0);
            int xp = aggressor.getXp();
            xp = xp + Math.max(0, 200 - (aggressor.getLevel() - victim.getLevel()) * 40);
            aggressor.setXp(xp);
            levelUp(aggressor);
            aggressor.setHp(GeneralConstants.INITIAL_HP_PYROMANCER + aggressor.getLevel() * 50);
        }
    }

    public void ignite(final Hero aggressor, final Hero victim, final Map area) {
        // Sa nu uit sa fac damage suplimentar
        float hp = 0;
        hp = PyromancerConstants.BASE_DAMAGE_IGNITE + PyromancerConstants.ADDED_DAMAGE_IGNITE
                * aggressor.getLevel();
        if (area.getType().equals("Volcanic")) {
            hp = hp * area.getModificator();
        }
        float[] modificators = {Rogue.Constants.MODIFICATOR_IGNITE, Knight.Constants.
                MODIFICATOR_IGNITE, Constants.MODIFICATOR_IGNITE, Wizard.Constants.
                MODIFICATOR_IGNITE};
        hp = hp * victim.accept(new Append(), modificators);
        hp = Math.round(hp);
        victim.setHp(victim.getHp() - (int)hp);
        if(victim.getHp() <= 0) {
            victim.setHp(0);
            int xp = aggressor.getXp();
            xp = xp + Math.max(0, 200 - (aggressor.getLevel() - victim.getLevel()) * 40);
            aggressor.setXp(xp);
            levelUp(aggressor);
            aggressor.setHp(GeneralConstants.INITIAL_HP_PYROMANCER + aggressor.getLevel() *
                    GeneralConstants.ADDED_HP_PYROMANCER);
        }
    }
}
