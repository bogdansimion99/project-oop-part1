package com.LOOP.heroes;

import com.LOOP.helpers.GeneralConstants;
import com.LOOP.helpers.Modificator;
import com.LOOP.helpers.ModificatorVisitor;
import com.LOOP.maps.Map;

public class Wizard extends Hero implements Modificator {
    private Hero wizard;

    public Hero getWizard() {
        return wizard;
    }

    public Wizard(Hero wizard, int[] position) {
        super(GeneralConstants.INITIAL_HP_WIZARD, position);
        this.wizard = wizard;
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

    }

    public void deflect(final Hero aggressor, final Hero victim, final Map area) {

    }
}
