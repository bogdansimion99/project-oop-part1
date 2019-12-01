package heroes;

import helpers.*;
import maps.Map;

public class Knight extends Hero implements Modificator {
    private Hero knight;

    public Hero getKnight() {
        return knight;
    }

    public Knight(Hero knight, int[] position) {
        super(GeneralConstants.INITIAL_HP_KNIGHT, position, "Knight");
        this.knight = knight;
    }

    public static class Constants {
        public static final float MODIFICATOR_FIREBLAST = 1.2f;
        public static final float MODIFICATOR_IGNITE = 1.2f;
        public static final float MODIFICATOR_EXECUTE = 1.0f;
        public static final float MODIFICATOR_SLAM = 1.2f;
        public static final float MODIFICATOR_DRAIN = 1.2f;
        public static final float MODIFICATOR_DEFLECT = 1.4f;
        public static final float MODIFICATOR_BACKSTAB = 0.9f;
        public static final float MODIFICATOR_PARALYSIS = 0.8f;
    }

    @Override
    public float accept(final ModificatorVisitor modificatorVisitor, float[] modificators) {
        return modificatorVisitor.visit(this, modificators);
    }

    @Override
    public void action (final Hero aggressor, final Hero victim, final Map area) {
        execute(aggressor, victim, area);
        slam(aggressor, victim, area);
    }

    public void execute (final Hero aggressor, final Hero victim, final Map area) {
        // Trebuie sa fac acea limita
        if (victim.getHp() < victim.getMaximumHp() * Math.min(KnightConstants.INITIAL_HP_LIMIT +
                KnightConstants.ADDED_HP_LIMIT * aggressor.getLevel(), KnightConstants.
                MAXIMUM_HP_LIMIT)) {
            if (victim instanceof Wizard) {
                ((Wizard)victim).setDamage(((Wizard)victim).getDamage() + Math.round(victim.
                        getHp()));
            }
            victim.setHp(0);
            int xp = aggressor.getXp();
            xp = xp + Math.max(0, 200 - (aggressor.getLevel() - victim.getLevel()) * 40);
            aggressor.setXp(xp);
            levelUp(aggressor);
            aggressor.setHp(GeneralConstants.INITIAL_HP_KNIGHT + aggressor.getLevel() *
                    GeneralConstants.ADDED_HP_KNIGHT);
            return;
        }
        float hp = 0;
        hp = KnightConstants.BASE_DAMAGE_EXECUTE + KnightConstants.ADDED_DAMAGE_EXECUTE
                * aggressor.getLevel();
        if (area.getType().equals("Land")) {
            hp = hp * area.getModificator();
        }
        if (victim instanceof Wizard) {
            ((Wizard)victim).setDamage(((Wizard)victim).getDamage() + Math.round(hp));
        }
        float[] modificators = {Rogue.Constants.MODIFICATOR_EXECUTE, Knight.Constants.
                MODIFICATOR_EXECUTE, Pyromancer.Constants.MODIFICATOR_EXECUTE, Wizard.Constants.
                MODIFICATOR_EXECUTE};
        hp = hp * victim.accept(new Append(), modificators);
        victim.setHp(victim.getHp() - Math.round(hp));
    }

    public void slam (final Hero aggressor, final Hero victim, final Map area) {
        // Trebuie sa fac incapacitatea adversarului de a se misca
        float hp = 0;
        hp = KnightConstants.BASE_DAMAGE_SLAM + KnightConstants.ADDED_DAMAGE_SLAM
                * aggressor.getLevel();
        if (area.getType().equals("Volcanic")) {
            hp = hp * area.getModificator();
        }
        if (victim instanceof Wizard) {
            ((Wizard)victim).setDamage(((Wizard)victim).getDamage() + Math.round(hp));
        }
        float[] modificators = {Rogue.Constants.MODIFICATOR_SLAM, Knight.Constants.
                MODIFICATOR_SLAM, Pyromancer.Constants.MODIFICATOR_SLAM, Wizard.Constants.
                MODIFICATOR_SLAM};
        hp = hp * victim.accept(new Append(), modificators);
        victim.setOvertime(KnightConstants.NO_ROUND_PARALYSIS);
        victim.setDamageOvertime(0);
        victim.setHp(victim.getHp() - Math.round(hp));
        if(victim.getHp() <= 0) {
            victim.setHp(0);
            int xp = aggressor.getXp();
            xp = xp + Math.max(0, 200 - (aggressor.getLevel() - victim.getLevel()) * 40);
            aggressor.setXp(xp);
            levelUp(aggressor);
            aggressor.setHp(GeneralConstants.INITIAL_HP_KNIGHT + aggressor.getLevel() *
                    GeneralConstants.ADDED_HP_KNIGHT);
        }
    }
}
