package heroes;

import helpers.*;
import maps.Map;

public class Rogue extends Hero implements Modificator {
    private Hero rogue;
    private int critical;

    public Hero getRogue() {
        return rogue;
    }

    public int getCritical() {
        return critical;
    }

    public void setCritical(int critical) {
        this.critical = critical;
    }

    public Rogue(Hero rogue, int[] position) {
        super(GeneralConstants.INITIAL_HP_ROGUE, position, "Rogue");
        this.rogue = rogue;
        this.critical = 0;
    }

    public static class Constants {
        public static final float MODIFICATOR_FIREBLAST = 0.8f;
        public static final float MODIFICATOR_IGNITE = 0.8f;
        public static final float MODIFICATOR_EXECUTE = 1.15f;
        public static final float MODIFICATOR_SLAM = 0.8f;
        public static final float MODIFICATOR_DRAIN = 0.8f;
        public static final float MODIFICATOR_DEFLECT = 1.2f;
        public static final float MODIFICATOR_BACKSTAB = 1.2f;
        public static final float MODIFICATOR_PARALYSIS = 0.9f;
    }

    @Override
    public float accept(final ModificatorVisitor modificatorVisitor, float[] modificators) {
        return modificatorVisitor.visit(this, modificators);
    }

    @Override
    public void action(final Hero aggressor, final Hero victim, final Map area) {
        backstab(aggressor, victim, area);
        paralysis(aggressor, victim, area);
    }

    public void backstab(final Hero aggressor, final Hero victim, final Map area) {
        //trebuie sa vad daca si cand sa fac critical
        float hp = RogueConstants.INITIAL_DAMAGE_BACKSTAB + RogueConstants.ADDED_DAMAGE_BACKSTAB *
                aggressor.getLevel();
        if ((((Rogue)aggressor).critical - 1) % 3 == 0 && area.getType().equals("Woods")) {
            hp = hp * RogueConstants.CRITICAL;
        }
        ((Rogue)aggressor).critical++;
        if (area.getType().equals("Woods")) {
            hp = hp * GeneralConstants.WOODS_MODIFICATOR;
        }
        if (victim instanceof Wizard) {
            ((Wizard)victim).setDamage(((Wizard)victim).getDamage() + Math.round(hp));
        }
        float[] modificators = {Constants.MODIFICATOR_BACKSTAB, Knight.Constants.
                MODIFICATOR_BACKSTAB, Pyromancer.Constants.MODIFICATOR_BACKSTAB, Wizard.Constants.
                MODIFICATOR_BACKSTAB};
        hp = hp * victim.accept(new Append(), modificators);
        victim.setHp(victim.getHp() - Math.round(hp));
    }

    public void paralysis(final Hero aggressor, final Hero victim, final Map area) {
        //trebuie sa vad incapacitatea adversarului + nr runde
        float hp = RogueConstants.INITIAL_DAMAGE_PARALYSIS + RogueConstants.ADDED_DAMAGE_PARALYSIS *
                aggressor.getLevel();
        if (area.getType().equals("Woods")) {
            hp = hp * GeneralConstants.WOODS_MODIFICATOR;
            victim.setOvertime(RogueConstants.OVERTIME_PARALYSIS_WOODS);
        }
        victim.setOvertime(RogueConstants.OVERTIME_PARALYSIS);
        if (victim instanceof Wizard) {
            ((Wizard)victim).setDamage(((Wizard)victim).getDamage() + Math.round(hp));
        }
        float[] modificators = {Constants.MODIFICATOR_PARALYSIS, Knight.Constants.
                MODIFICATOR_PARALYSIS, Pyromancer.Constants.MODIFICATOR_PARALYSIS, Wizard.Constants.
                MODIFICATOR_PARALYSIS};
        hp = hp * victim.accept(new Append(), modificators);
        victim.setHp(victim.getHp() - Math.round(hp));
        if (victim.getHp() <= 0) {
            victim.setHp(0);
            int xp = aggressor.getXp();
            xp = xp + Math.max(0, 200 - (aggressor.getLevel() - victim.getLevel()) * 40);
            aggressor.setXp(xp);
            int level = aggressor.getLevel();
            levelUp(aggressor);
            if (aggressor.getLevel() != level) {
                aggressor.setHp(GeneralConstants.INITIAL_HP_ROGUE + aggressor.getLevel() * 50);
                aggressor.setMaximumHp(GeneralConstants.INITIAL_HP_ROGUE + aggressor.getLevel() *
                        50);
            }
        }
    }
}
