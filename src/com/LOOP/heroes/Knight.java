package com.LOOP.heroes;

import com.LOOP.helpers.*;
import com.LOOP.maps.Map;

public class Knight extends Hero implements Modificator {
    private Hero knight;

    public Hero getKnight() {
        return knight;
    }

    public Knight(Hero knight, int[] position) {
        super(GeneralConstants.INITIAL_HP_KNIGHT, position);
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
        float hp = 0;
        hp = KnightConstants.BASE_DAMAGE_EXECUTE + KnightConstants.ADDED_DAMAGE_EXECUTE
                * aggressor.getLevel();
        if (area.getType().equals("Land")) {
            hp = hp * area.getModificator();
        }
        float[] modificators = {Rogue.Constants.MODIFICATOR_EXECUTE, Knight.Constants.
                MODIFICATOR_EXECUTE, Pyromancer.Constants.MODIFICATOR_EXECUTE, Wizard.Constants.
                MODIFICATOR_EXECUTE};
        hp = hp * victim.accept(new Append(), modificators);
        hp = Math.round(hp);
        victim.setHp(victim.getHp() - (int)hp);
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

    public void slam (final Hero aggressor, final Hero victim, final Map area) {
        // Trebuie sa fac incapacitatea adversarului de a se misca
        float hp = 0;
        hp = KnightConstants.BASE_DAMAGE_SLAM + KnightConstants.ADDED_DAMAGE_SLAM
                * aggressor.getLevel();
        if (area.getType().equals("Volcanic")) {
            hp = hp * area.getModificator();
        }
        float[] modificators = {Rogue.Constants.MODIFICATOR_SLAM, Knight.Constants.
                MODIFICATOR_SLAM, Pyromancer.Constants.MODIFICATOR_SLAM, Wizard.Constants.
                MODIFICATOR_SLAM};
        hp = hp * victim.accept(new Append(), modificators);
        hp = Math.round(hp);
        victim.setHp(victim.getHp() - (int)hp);
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