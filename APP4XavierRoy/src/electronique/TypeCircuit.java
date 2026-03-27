package electronique;

public enum TypeCircuit {

    // Constantes
    SERIE("serie"),
    PARALLELE("parallele"),
    RESISTANCE("resistance");

    // Constructeur
    TypeCircuit(String NOM){
        this.NOM = NOM;
    }

    // Attributs
    private final String NOM;

    // Accesseur
    public String getNOM() {
        return NOM;
    }
}
