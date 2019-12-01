package com.LOOP.helpers;

import com.LOOP.heroes.Knight;
import com.LOOP.heroes.Pyromancer;
import com.LOOP.heroes.Rogue;
import com.LOOP.heroes.Wizard;

public class Append implements ModificatorVisitor {
    @Override
    public float visit(Pyromancer pyromancer, float[] modifcators) {
        return modifcators[2];
    }

    @Override
    public float visit(Knight knight, float[] modificators) {
        return modificators[1];
    }

    @Override
    public float visit(Rogue rogue, float[] modificators) {
        return modificators[0];
    }

    @Override
    public float visit(Wizard wizard, float[] modificators) {
        return modificators[3];
    }
}
