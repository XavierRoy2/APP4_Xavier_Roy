package app;

import electronique.Composant;

import java.util.ArrayList;

public class CircuitBuilder {

    public CircuitBuilder(){}

    public Composant construireCircuit(String fichier) {
        return null;
    }

    private <JsonNode> Composant lireComposant(JsonNode composant) {

      return null;
    }

    public static String[] separerElementsJson(String composants) {
        if (composants != null) {
        int nbParanthesesOuvertes;
        int nbParanthesesFermees;
        int nbCrochetsOuverts;
        int nbCrochetsFermes;
        int debut;
        ArrayList<String> elements = new ArrayList<>();
        String[] lignes = composants.split("\n");
        String composant;

        composant = "";
        debut = 0;
        nbParanthesesOuvertes = 0;
        nbParanthesesFermees = 0;
        nbCrochetsOuverts = 0;
        nbCrochetsFermes = 0;
        do {
            if (lignes[debut].contains("[")) {
                    nbCrochetsOuverts++;
            }
            debut++;
        } while (lignes[debut].contains("["));
        int i = debut;
        do {
            lignes[i] = lignes[i].trim();
            if (lignes[debut].contains("[")) {
                nbCrochetsOuverts++;
            }
            if (lignes[debut].contains("]")) {
                nbCrochetsFermes++;
            }
            if (lignes[i].contains("{")) {
                nbParanthesesOuvertes++;
                if (lignes[i].length() != 1) {
                    composant += lignes[i] + "\n";
                    if (lignes[i].contains("}")) {
                            nbParanthesesFermees++;
                    }
                }
            } else if (lignes[i].contains("}")) {
                nbParanthesesFermees++;
            } else {
                composant += lignes[i] + "\n";
            }
            if (nbParanthesesOuvertes == nbParanthesesFermees) {
                elements.add(composant);
                composant = "";
                nbParanthesesOuvertes = 0;
                nbParanthesesFermees = 0;
            }
            i++;
        } while (nbCrochetsOuverts != nbCrochetsFermes);
        return (String[]) elements.toArray();
        } else {
            throw new IllegalArgumentException("code JSON invalide");
        }
    }
}
