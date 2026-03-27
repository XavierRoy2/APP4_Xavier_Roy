package electronique;

import java.util.List;

public abstract class Circuit extends Composant {

    // Attributs
    protected List<Composant> composants;

    // Constructeur
    public Circuit(List<Composant> composants, TypeCircuit typeCircuit){
        super(typeCircuit);
        setComposants(composants);
    }

    // Accesseur
    public List<Composant> getComposants() {
        return this.composants;
    }

    // Mutateur
    protected void setComposants(List<Composant> composants) {
        this.composants = composants;
    }

    // toString
    @Override
    public String toString(){
        String str = "Type de circuit : " + this.typeCircuit.getNOM();
        for (int i = 0; i < this.composants.size(); i++) {
            str += "\n      composant " + i  + " : " + this.composants.get(i).toString();
        }
        return str;
    }
}
