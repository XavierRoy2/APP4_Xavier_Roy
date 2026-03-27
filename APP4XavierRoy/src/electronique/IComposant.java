package electronique;

public interface IComposant {

    static TypeCircuit getTYPE_CIRCUIT() {
        return null;
    }

    double calculerResistance();

    @Override
    String toString();
}
