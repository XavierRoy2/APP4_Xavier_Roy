package electronique;

import java.util.List;

public class CircuitSerie extends Circuit{

    // Constante
    private static final TypeCircuit TYPE_CIRCUIT = TypeCircuit.SERIE;

    // Constructeur
    public CircuitSerie(List<Composant> composants){
        super(composants, getTYPE_CIRCUIT());
    }

    public static TypeCircuit getTYPE_CIRCUIT() {
        return TYPE_CIRCUIT;
    }

    @Override
    public double calculerResistance() {
        double sommeResistance = 0;
        for (Composant composant : this.composants) {
            sommeResistance += composant.calculerResistance();
        }
        return sommeResistance;
    }
}
