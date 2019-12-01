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
     * @return face legatura cu metoda visit implementata in Append si mentionata
     * in ModificatorVisitor.
     */
    @Override
    public float accept(final ModificatorVisitor modificatorVisitor, final float[] modificators) {
        return modificatorVisitor.visit(this, modificators);
    }

    /**
     * @param aggressor cel care da damage
     * @param victim cel care isi ia damage
     * @param area terenul pe care se desfasoara jocul
     * Aceasta metoda apeleaza metodele specifice jucatorului de tip Pyromancer.
     * La final se calculeaza xp-ul aggresorului (cel care da damage).
     */
    @Override
    public void action(final Hero aggressor, final Hero victim, final Map area) {
        fireblast(aggressor, victim, area);
        ignite(aggressor, victim, area);
        calculateXp(aggressor, victim);
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
        victim.setHp(victim.getHp() - Math.round(hp));
    }

    /**
     * @param aggressor
     * @param victim
     * @param area
     */
    public void ignite(final Hero aggressor, final Hero victim, final Map area) {
        float hp = 0;
        hp = PyromancerConstants.BASE_DAMAGE_IGNITE + PyromancerConstants.ADDED_DAMAGE_IGNITE
                * aggressor.getLevel();
        if (area.getType().equals("Volcanic")) {
            hp = hp * area.getModificator();
        }
        if (victim.getType().equals("Wizard")) {
            ((Wizard) victim).setDamage(((Wizard) victim).getDamage() + Math.round(hp));
        }
        float[] modificators = {Rogue.Constants.MODIFICATOR_IGNITE, Knight.Constants.
                MODIFICATOR_IGNITE, Constants.MODIFICATOR_IGNITE, Wizard.Constants.
                MODIFICATOR_IGNITE};
        hp = hp * victim.accept(new Append(), modificators);
        victim.setDamageOvertime(PyromancerConstants.BASE_DAMAGE_PER_ROUND_IGNITE
                + PyromancerConstants.ADDED_DAMAGE_PER_ROUND_IGNITE * aggressor.getLevel());
        victim.setHp(victim.getHp() - Math.round(hp));
    }

    /**
     * @param aggressor
     * @param victim
     * Modifica nivelul jucatorului doar daca victima a murit.
     */
    public void calculateXp(final Hero aggressor, final Hero victim) {
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
