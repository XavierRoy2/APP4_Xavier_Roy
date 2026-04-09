package app;

import electronique.Composant;

import java.io.IOException;
import java.util.Scanner;

public class CircuitApp {

    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args){
        String[] fichiersLecture = CircuitBuilder.getFichiersJson();
        boolean arretInterface = false;
        String reponseUtilisateur;
        String numeroCircuit;
        boolean premiereDemande = true;

        System.out.println("---------------- Interface en console du programme ----------------");
        System.out.println("\nCircuit au format JSON trouvés : ");
        if (fichiersLecture == null || fichiersLecture.length == 0) {
            System.out.println("Oups : aucun fichier JSON trouvé");
        } else {
            for (int i = 0; i < fichiersLecture.length; i++) {
                System.out.println("[" + (i+1) +"]" + nomCircuitExact(fichiersLecture[i]));
            }
        }
        do {
            Composant circuitRecherche = null;
            String cheminCircuitRecherche;
            if (premiereDemande) {
                System.out.println("\nVoulez-vous tester un fichier ?");
                reponseUtilisateur = SC.next();
                arretInterface = !reponseUtilisateur.equalsIgnoreCase("oui");
            }
            if (!arretInterface) {
                System.out.println("\nEntrer le numéro du circuit que vous voulez tester :");
                numeroCircuit = SC.next();
                Exception probleme = estUnNumeroLegal(numeroCircuit, fichiersLecture);
                if (probleme == null) {
                    int numeroInt = Integer.parseInt(numeroCircuit);
                    cheminCircuitRecherche = fichiersLecture[numeroInt-1];
                    try {
                        circuitRecherche = CircuitBuilder.construireCircuit(fichiersLecture[numeroInt-1]);
                    } catch (IOException e) {
                        System.out.println("\nErreur : " + e.getMessage());
                    }
                    double resistance = circuitRecherche.calculerResistance();
                    System.out.println("\nCircuit recherché : " + nomCircuitExact(cheminCircuitRecherche) +
                            "\nComposants du circuit : \n" + circuitRecherche.toString() +
                            "\nRésistance équivalente : " + Composant.afficherResistance(resistance));
                } else {
                    System.out.println("\nErreur : " + probleme.getMessage());
                }
                System.out.println("\nVoulez vous tester un autre fichier ?\n" +
                        "[R] Tester un autre fichier\n" +
                        "[Q] Quitter");
                reponseUtilisateur = SC.next();
                arretInterface = !reponseUtilisateur.equalsIgnoreCase("R");
                premiereDemande = false;
          }
        } while (!arretInterface);
    }
    
    private static Exception estUnNumeroLegal(String numero, String[] tableau){
        if (numero == null || tableau == null) {
            return new IllegalArgumentException("Objet inexistant");
        } else {
            int numeroInt = 0;
            boolean erreurFormat = false;
            Exception erreur = null;
            try {
                numeroInt = Integer.parseInt(numero);
            } catch (NumberFormatException e) {
                erreurFormat = true;
                erreur = e;
            }
            if (erreurFormat) {
                return erreur;
            } else if (numeroInt < 1 || numeroInt > tableau.length) {
                return new IllegalArgumentException("Fichier JSON inexsitant");
            } else {
                return null;
            }
        }
    }

    private static String nomCircuitExact(String cheminFichierCircuit){
        if (cheminFichierCircuit != null && cheminFichierCircuit.endsWith(".json") && cheminFichierCircuit.startsWith(CircuitBuilder.getPathIn())) {
            return cheminFichierCircuit.substring(CircuitBuilder.getPathIn().length() + 1,cheminFichierCircuit.length()-".json".length());
        } else {
            return null;
        }
    }
}
