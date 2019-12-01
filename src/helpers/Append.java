package helpers;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public class Append implements ModificatorVisitor {
    /**
     * @param pyromancer
     * @param modifcators
     * @return
     */
    @Override
    public float visit(final Pyromancer pyromancer, final float[] modifcators) {
        return modifcators[2];
    }

    /**
     * @param knight
     * @param modificators
     * @return
     */
    @Override
    public float visit(final Knight knight, final float[] modificators) {
        return modificators[1];
    }

    /**
     * @param rogue
     * @param modificators
     * @return
     */
    @Override
    public float visit(final Rogue rogue, final float[] modificators) {
        return modificators[0];
    }

    /**
     * @param wizard
     * @param modificators
     * @return
     */
    @Override
    public float visit(final Wizard wizard, final float[] modificators) {
        return modificators[3];
    }
}
