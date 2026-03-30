package electronique;

public interface IComposant {

    TypeCircuit TYPE_CIRCUIT = null;

    static TypeCircuit getTYPE_CIRCUIT() {
        return null;
    }

    double calculerResistance();

    @Override
    String toString();
}
