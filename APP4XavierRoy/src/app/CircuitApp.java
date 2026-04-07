package app;

import electronique.Composant;

import java.util.Scanner;

public class CircuitApp {

    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args){
        String[] fichiersLecture = CircuitBuilder.getFichiersJson();
        boolean arretInterface;
        String reponseUtilisateur;
        String numeroCircuit;

        System.out.println("---------------- Interface en console du programme ----------------");
        System.out.println("Circuit au format JSON trouvés : ");
        if (fichiersLecture == null || fichiersLecture.length == 0) {
            System.out.println("Oups : aucun fichier JSON trouvé");
        } else {
            for (int i = 0; i < fichiersLecture.length; i++) {
                System.out.println("[" + (i+1) +"]" + fichiersLecture[i].substring(CircuitBuilder.getPathIn().length()));
            }
        }
        do {
            Composant circuitRecherche;
            System.out.println("\nVoulez-vous voir un circuit ?");
            reponseUtilisateur = SC.next();
            arretInterface = reponseUtilisateur.equalsIgnoreCase("oui");
            if (!arretInterface) {
                System.out.println("Entrer le numéro du circuit que vous voulez voir :");
                numeroCircuit = SC.next();
                Exception probleme = estUnNumeroLegal(numeroCircuit, fichiersLecture);
                if (probleme == null) {
                    int numeroInt = Integer.parseInt(numeroCircuit);
                    circuitRecherche = CircuitBuilder.construireCircuit(fichiersLecture[numeroInt-1]);
                } else {
                    System.out.println(probleme.getMessage());
                }
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
}
