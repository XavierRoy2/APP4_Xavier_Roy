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

    // Autre méthode
    public static TypeCircuit lireJson(String nom) throws IllegalArgumentException {
        return switch (nom) {
            case "serie" -> TypeCircuit.SERIE;
            case "parallele" -> TypeCircuit.PARALLELE;
            case "resistance" -> TypeCircuit.RESISTANCE;
            case null, default -> throw new IllegalArgumentException("Type de circuit impossible");
        };
    }
}
