package helpers;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public interface ModificatorVisitor {
    float visit(Pyromancer pyromancer, float[] modificators);
    float visit(Knight knight, float[] modificators);
    float visit(Rogue rogue, float[] modificators);
    float visit(Wizard wizard, float[] modificators);
}
