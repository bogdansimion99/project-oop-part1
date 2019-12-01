package heroes;

import helpers.*;
import maps.Map;

public class Pyromancer extends Hero implements Modificator {
    private Hero pyromancer;

    /**
     * @return
     */
    public Hero getPyromancer() {
        return pyromancer;
    }

    public Pyromancer(final Hero pyromancer, final int[] position) {
        super(GeneralConstants.INITIAL_HP_PYROMANCER, position, "Pyromancer");
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

    /**
     * @param modificatorVisitor
     * @param modificators
     * @return
     */
    @Override
    public float accept(final ModificatorVisitor modificatorVisitor, final float[] modificators) {
        return modificatorVisitor.visit(this, modificators);
    }

    /**
     * @param aggressor
     * @param victim
     * @param area
     */
    @Override
    public void action(final Hero aggressor, final Hero victim, final Map area) {
        fireblast(aggressor, victim, area);
        ignite(aggressor, victim, area);
        // O sa fac o functie pentru calcularea xp-ului
    }

    /**
     * @param aggressor
     * @param victim
     * @param area
     */
    public void fireblast(final Hero aggressor, final Hero victim, final Map area) {
        float hp = 0;
        hp = PyromancerConstants.BASE_DAMAGE_FIREBLAST + PyromancerConstants.ADDED_DAMAGE_FIREBLAST
                * aggressor.getLevel();
        if (area.getType().equals("Volcanic")) {
            hp = hp * area.getModificator();
        }
        if (victim instanceof Wizard) {
            ((Wizard) victim).setDamage(((Wizard) victim).getDamage() + Math.round(hp));
        }
        float[] modificators = {Rogue.Constants.MODIFICATOR_FIREBLAST, Knight.Constants.
                MODIFICATOR_FIREBLAST, Constants.MODIFICATOR_FIREBLAST, Wizard.Constants.
                MODIFICATOR_FIREBLAST};
        hp = hp * victim.accept(new Append(), modificators);
        /*
        * Ma duc in alta clasa (de exemplu, modificator)
        * Stiu ca este fireblast, deci nu mai trebuie sa verific asta
        * Clasa in care ma duc este, de fapt, o interfata care implementeaza toate functiile
        * ce imi trebuiesc pentru a lua modificatorul.
        * Imi trebuie o clasa unde sunt implementate toate visit-urile
        * Clasele in care fac accept -> visit sunt chiar aceste clase din hero.
        * Se face override la accept si la visit
        * */
        victim.setHp(victim.getHp() - Math.round(hp));
    }

    /**
     * @param aggressor
     * @param victim
     * @param area
     */
    public void ignite(final Hero aggressor, final Hero victim, final Map area) {
        // Sa nu uit sa fac damage suplimentar
        float hp = 0;
        hp = PyromancerConstants.BASE_DAMAGE_IGNITE + PyromancerConstants.ADDED_DAMAGE_IGNITE
                * aggressor.getLevel();
        if (area.getType().equals("Volcanic")) {
            hp = hp * area.getModificator();
        }
        if (victim instanceof Wizard) {
            ((Wizard) victim).setDamage(((Wizard) victim).getDamage() + Math.round(hp));
        }
        float[] modificators = {Rogue.Constants.MODIFICATOR_IGNITE, Knight.Constants.
                MODIFICATOR_IGNITE, Constants.MODIFICATOR_IGNITE, Wizard.Constants.
                MODIFICATOR_IGNITE};
        hp = hp * victim.accept(new Append(), modificators);
        victim.setDamageOvertime(PyromancerConstants.BASE_DAMAGE_PER_ROUND_IGNITE
                + PyromancerConstants.ADDED_DAMAGE_PER_ROUND_IGNITE * aggressor.getLevel());
        victim.setHp(victim.getHp() - Math.round(hp));
        if (victim.getHp() <= 0) {
            victim.setHp(0);
            int xp = aggressor.getXp();
            xp = xp + Math.max(0, GeneralConstants.STANDARD_ADDED_XP - (aggressor.getLevel()
                    - victim.getLevel()) * GeneralConstants.MODIFIED_ADDED_XP);
            aggressor.setXp(xp);
            int level = aggressor.getLevel();
            levelUp(aggressor);
            if (aggressor.getLevel() != level) {
                aggressor.setHp(GeneralConstants.INITIAL_HP_PYROMANCER + aggressor.getLevel()
                        * GeneralConstants.ADDED_HP_PYROMANCER);
                aggressor.setMaximumHp(GeneralConstants.INITIAL_HP_PYROMANCER + aggressor.getLevel()
                        * GeneralConstants.ADDED_HP_PYROMANCER);
            }
        }
    }
}
