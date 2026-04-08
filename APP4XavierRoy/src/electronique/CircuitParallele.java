package electronique;

import java.util.List;

public class CircuitParallele extends Circuit{

    // Constante
    private static final TypeCircuit TYPE_CIRCUIT = TypeCircuit.PARALLELE;

    // Constructeur
    public CircuitParallele(List<Composant> composants){
        super(composants, getTYPE_CIRCUIT());
    }

    public static TypeCircuit getTYPE_CIRCUIT() {
        return TYPE_CIRCUIT;
    }

    // Autres méthodes
    @Override
    public double calculerResistance() {
        double denominateur = 0;
        for (Composant composant : this.composants) {
            denominateur += 1/composant.calculerResistance();
        }
        return 1/denominateur;
    }
}
