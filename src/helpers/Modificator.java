package helpers;

public interface Modificator {
    float accept(ModificatorVisitor modificatorVisitor, float[] modificators);
}
