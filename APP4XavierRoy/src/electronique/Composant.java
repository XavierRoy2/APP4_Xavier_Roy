package electronique;

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
}
