package com.LOOP.heroes;

import com.LOOP.helpers.*;
import com.LOOP.maps.Map;

public class Wizard extends Hero implements Modificator {
    private Hero wizard;
    private int damage;

    public Hero getWizard() {
        return wizard;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Wizard(Hero wizard, int[] position) {
        super(GeneralConstants.INITIAL_HP_WIZARD, position);
        this.wizard = wizard;
        this.damage = 0;
    }

    public static class Constants {
        public static final float MODIFICATOR_FIREBLAST = 1.05f;
        public static final float MODIFICATOR_IGNITE = 1.05f;
        public static final float MODIFICATOR_EXECUTE = 0.8f;
        public static final float MODIFICATOR_SLAM = 1.05f;
        public static final float MODIFICATOR_DRAIN = 1.05f;
        public static final float MODIFICATOR_DEFLECT = 0.0f;
        public static final float MODIFICATOR_BACKSTAB = 1.25f;
        public static final float MODIFICATOR_PARALYSIS = 1.25f;
    }

    @Override
    public float accept(final ModificatorVisitor modificatorVisitor, float[] modificators) {
        return modificatorVisitor.visit(this, modificators);
    }

    @Override
    public void action(final Hero aggressor, final Hero victim, final Map area) {
        drain(aggressor, victim, area);
        deflect(aggressor, victim, area);
    }

    public void drain(final Hero aggressor, final Hero victim, final Map area) {
        float hp = Math.min(0.3f * victim.getMaximumHp(), victim.getHp());
        float procent = WizardConstants.INITIAL_DAMAGE_DRAIN + WizardConstants.ADDED_DAMAGE_DRAIN *
                aggressor.getLevel();
        float[] modificators = {Rogue.Constants.MODIFICATOR_DRAIN, Knight.Constants.
                MODIFICATOR_DRAIN, Pyromancer.Constants.MODIFICATOR_DRAIN, Constants.
                MODIFICATOR_DRAIN};
        procent = procent * victim.accept(new Append(), modificators);
        hp = hp * procent;
        if (area.getType().equals("Desert")) {
            hp = hp * GeneralConstants.DESERT_MODIFICATOR;
        }
        victim.setHp(victim.getHp() - Math.round(hp));
    }

    public void deflect(final Hero aggressor, final Hero victim, final Map area) {
        float hp = ((Wizard)aggressor).damage;
        float procent = Math.min(WizardConstants.INITIAL_DAMAGE_DEFLECT + WizardConstants.ADDED_DAMAGE_DEFLECT
                * aggressor.getLevel(), WizardConstants.MAXIMUM_DAMAGE_DEFLECT);
        hp = hp * procent;
        if (area.getType().equals("Desert")) {
            hp = hp * GeneralConstants.DESERT_MODIFICATOR;
        }
        float[] modificators = {Rogue.Constants.MODIFICATOR_DEFLECT, Knight.Constants.
                MODIFICATOR_DEFLECT, Pyromancer.Constants.MODIFICATOR_DEFLECT, Constants.
                MODIFICATOR_DEFLECT};
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
                aggressor.setHp(GeneralConstants.INITIAL_HP_WIZARD + aggressor.getLevel() * 50);
                aggressor.setMaximumHp(GeneralConstants.INITIAL_HP_WIZARD + aggressor.getLevel() *
                        50);
            }
        }
    }
}