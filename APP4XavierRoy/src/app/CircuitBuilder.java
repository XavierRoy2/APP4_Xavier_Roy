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
    private static final String pathIn = System.getProperty("user.dir") + fSep + "APP4XavierRoy" + fSep + "src" + fSep + "donnees" + fSep + "fichiers_json";
    private static final String[] fichiersJson = determinerFichierJson(new File(pathIn));

    public CircuitBuilder(){}

    public static Composant construireCircuit(String cheminFichier) throws IOException, IllegalArgumentException {
        boolean erreur;
        List<Composant> listeComposants = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        String typeString;
        try {
            JsonNode donneesComposants = mapper.readTree(new File(cheminFichier));
            typeString = donneesComposants.get("circuit").get("type").asText();
            if ("resistance".equals(typeString)) {
                return new Resistance(donneesComposants.get("valeur").asDouble());
            } else {
                for (int i = 0; i < donneesComposants.get("circuit").get("composants").size(); i++) {
                    JsonNode noyauComposantActuel = donneesComposants.get("circuit").get("composants").get(i);
                    Composant composantActuel = lireComposant(noyauComposantActuel);
                    listeComposants.add(composantActuel);
                }
                return switch (typeString) {
                    case "serie" -> new CircuitSerie(listeComposants);
                    case "parallele" -> new CircuitParallele(listeComposants);
                    default -> throw new IllegalArgumentException("Type de circuit impossible");
                };
            }
        } catch (IOException e) {
            throw e;
        }
    }

    private static Composant lireComposant(JsonNode composant) throws IllegalArgumentException {
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

    public static String getFichierJson(int numero) throws IllegalArgumentException {
        if (numero >= 1 || numero < fichiersJson.length) {
            return fichiersJson[numero-1];
        } else {
            throw new IllegalArgumentException("réseau introuvable");
        }
    }

    private static String[] determinerFichierJson(File dossier) throws RuntimeException {
        List<String> cheminFichiersJson = new ArrayList<>();
        if (dossier.isDirectory()) {
            File[] listeFichiersJson = dossier.listFiles();
            if (listeFichiersJson == null) {
                throw new RuntimeException("Dossier JSON inexistant");
            } else {
                for (File file : listeFichiersJson) {
                    if (!file.isDirectory()) {
                        cheminFichiersJson.add(file.getAbsolutePath());
                    }
                }
            }
            String[] retour = new String[cheminFichiersJson.size()];
            for (int i = 0; i < retour.length; i++) {
                retour[i] = cheminFichiersJson.get(i);
            }
            return retour;
        } else if (dossier.isAbsolute() && dossier.getAbsolutePath().endsWith(".json")){
            return new String[]{dossier.getAbsolutePath()};
        } else {
            throw new IllegalArgumentException("Dossier de JSON introuvable");
        }
    }

    public static String getPathIn(){
        return pathIn;
    }

    public static String[] getFichiersJson() {
        return fichiersJson;
    }
}
