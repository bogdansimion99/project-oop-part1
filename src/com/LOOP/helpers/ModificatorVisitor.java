package com.LOOP.helpers;

import com.LOOP.heroes.Knight;
import com.LOOP.heroes.Pyromancer;
import com.LOOP.heroes.Rogue;
import com.LOOP.heroes.Wizard;

public interface ModificatorVisitor {
    public float visit(Pyromancer pyromancer, float[] modificators);
    public float visit(Knight knight, float[] modificators);
    public float visit(Rogue rogue, float[] modificators);
    public float visit(Wizard wizard, float[] modificators);
}
