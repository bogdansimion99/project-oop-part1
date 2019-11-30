package com.LOOP.helpers;

public interface Modificator {
    public float accept(ModificatorVisitor modificatorVisitor, float[] modificators);
}
