package electronique;

import java.text.DecimalFormat;

public class Resistance extends Composant {

    // Constante
    private static final TypeCircuit TYPE_CIRCUIT = TypeCircuit.RESISTANCE;

    // Attribut
    private double resistance;

    // Constructeur
    public Resistance(double resistance){
        super(getTYPE_CIRCUIT());
        setResistance(resistance);
    }

    // Accesseur
    public static TypeCircuit getTYPE_CIRCUIT() {
        return TYPE_CIRCUIT;
    }

    public double getResistance() {
        return this.resistance;
    }

    // Mutateur
    private void setResistance(double resistance) throws IllegalArgumentException {
        if (validerResitance(resistance)) {
            this.resistance = resistance;
        } else {
            throw new IllegalArgumentException("Valeur de résistance interdite");
        }
    }

    // Validateur
    public static boolean validerResitance(double resistance){
        return resistance > 0;
    }

    // Autres méthodes
    @Override
    public double calculerResistance() {
        return this.resistance;
    }

    @Override
    public String toString(){
        return afficherResistance(this.resistance);
    }
}
