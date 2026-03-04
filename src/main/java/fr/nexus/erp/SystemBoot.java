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
}
