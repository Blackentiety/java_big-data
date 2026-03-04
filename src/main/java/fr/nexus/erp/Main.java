package fr.nexus.erp;

public class Main {
    public static void main(String[] args) {
        SystemBoot boot = new SystemBoot();
        DataEngine engine = new DataEngine();
        int niveau = 5;

        // 1. Validation de la sécurité (Niveau 6 pour passer)
        if (boot.verifierDroits(niveau)) {

            // 2. Affichage des étapes
            boot.afficherEtapes();

            // 3. Lancement du moteur de données
            engine.runPipeline();

        } else {
            System.out.println("Système verrouillé. Fin du programme.");
        }
    }
}
