package com.LOOP.heroes;

import com.LOOP.helpers.GeneralConstants;
import com.LOOP.helpers.Modificator;
import com.LOOP.helpers.ModificatorVisitor;
import com.LOOP.maps.Map;

public class Rogue extends Hero implements Modificator {
    private Hero rogue;

    public Hero getRogue() {
        return rogue;
    }

    public Rogue(Hero rogue, int[] position) {
        super(GeneralConstants.INITIAL_HP_ROGUE, position);
        this.rogue = rogue;
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
    }

    public void paralysis(final Hero aggressor, final Hero victim, final Map area) {
        //trebuie sa vad incapacitatea adversarului + nr runde
    }
}
