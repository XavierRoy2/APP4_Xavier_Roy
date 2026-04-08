package electronique;

import java.text.DecimalFormat;

public abstract class Composant implements IComposant {

    // Attributs
    protected TypeCircuit typeCircuit;

    // Constructeur
    public Composant(TypeCircuit typeCircuit){
        setTypeCircuit(typeCircuit);
    }

    public TypeCircuit getTypeCircuit() {
        return typeCircuit;
    }

    protected void setTypeCircuit(TypeCircuit typeCircuit) {
        this.typeCircuit = typeCircuit;
    }

    public static String afficherResistance(double resistance){
        DecimalFormat format = new DecimalFormat(".00");
        return format.format(resistance) + "Ω";
    }
}
