package app;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import electronique.CircuitParallele;
import electronique.CircuitSerie;
import electronique.Composant;
import electronique.Resistance;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CircuitBuilder {

    private static final char fSep = File.separatorChar;
    private static final String[] pathIn = {
            System.getProperty("user.dir") + fSep + "APP4XavierRoy" + fSep + "src" + fSep + "donnees" + fSep + "fichiers_json" + fSep + "complexe_industriel_zone_nord.json",
            System.getProperty("user.dir") + fSep + "APP4XavierRoy" + fSep + "src" + fSep + "donnees" + fSep + "fichiers_json" + fSep + "eclairage_public_quartier.json",
            System.getProperty("user.dir") + fSep + "APP4XavierRoy" + fSep + "src" + fSep + "donnees" + fSep + "fichiers_json" + fSep + "reseau_secours_hopital.json"
    };

    public CircuitBuilder(){}

    public Composant construireCircuit(String cheminFichier) {
        List<Composant> listeComposants = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode donneesComposants = mapper.readTree(new File(cheminFichier));
            if (donneesComposants.get("type").asText().equals("resistance")) {
                return new Resistance(donneesComposants.get("valeur").asDouble());
            } else {
                for (int i = 0; i < donneesComposants.size(); i++) {
                    listeComposants.add(lireComposant(donneesComposants.get(i)));
                }
                switch (donneesComposants.get("type").asText()) {
                }
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
        return null;
    }

    private Composant lireComposant(JsonNode composant) throws IllegalArgumentException {
        if (composant == null) {
            throw new IllegalArgumentException("Code JSON invalide");
        } else {
            String type = composant.get("type").asText();
            switch (type) {
                case "resistance":
                    return new Resistance(composant.get("valeur").asDouble());
                case "serie":
                    List<Composant> composantsSeries = new ArrayList<>();
                    for (int i = 0; i < composant.size(); i++) {
                        composantsSeries.add(lireComposant(composant.get(i)));
                    }
                    return new CircuitSerie(composantsSeries);
                case "parallele":
                    List<Composant> composantsParralleles = new ArrayList<>();
                    for (int i = 0; i < composant.size(); i++) {
                        composantsParralleles.add(lireComposant(composant.get(i)));
                    }
                    return new CircuitParallele(composantsParralleles);
                default:
                    throw new IllegalArgumentException("Type de circuit invalide");
            }
        }
    }

    public static String getPathIn(String nom) {
        if (nom == null) {
            throw new IllegalArgumentException("réseau impossible");
        } else {
            return switch (nom.toLowerCase()) {
                case "complexe industriel de la zone nord" -> pathIn[0];
                case "éclairage public du quartier" -> pathIn[1];
                case "réseau de secours de l'hôpital" -> pathIn[2];
                default -> throw new IllegalArgumentException("réseau introuvable");
            };
        }
    }
}
