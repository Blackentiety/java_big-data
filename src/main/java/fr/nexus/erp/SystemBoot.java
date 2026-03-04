package fr.nexus.erp;

public class SystemBoot {
    public boolean verifierDroits(int niveauAccreditation) {
        if (niveauAccreditation >= 5) {
            System.out.println("Accès accordé. Bienvenue, Administrateur Nexus.");
            return true;
        } else {
            System.out.println("ACCÈS REFUSÉ : Niveau d'accréditation insuffisant (" + niveauAccreditation + "/5).");
            return false;
        }
    }

    public void afficherEtapes() {
        String[] etapes = {"Initialisation", "Connexion DB", "Nettoyage", "Export"};
        System.out.println("Lancement du protocole Nexus...");
        for (String etape : etapes) {
            System.out.println("[ETAPE] : " + etape + " en cours...");
        }
    }
}
