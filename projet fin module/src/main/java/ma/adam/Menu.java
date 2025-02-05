package ma.adam;

import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private IEmployeManagement gestion;
    private static final int MAX_TENTATIVES = 3;
    private static final int TAILLE_MAX = 50;

    public Menu(Scanner scanner, IEmployeManagement gestion) {
        this.scanner = scanner;
        this.gestion = gestion;
    }

    public void executer() {
        int tentatives = 0;
        boolean continuer = true;

        while (continuer && tentatives < MAX_TENTATIVES) {
            afficherMenu();
            try {
                int choix = lireEntier("Votre choix", 0, 7, MAX_TENTATIVES);
                continuer = traiterChoix(choix);
                tentatives = 0; // Reset on successful operation
            } catch (EmployeException e) {
                System.out.println("Erreur : " + e.getMessage());
                tentatives++;
                if (tentatives < MAX_TENTATIVES) {
                    System.out.println("Il vous reste " + (MAX_TENTATIVES - tentatives) + " tentative(s)");
                } else {
                    System.out.println("Nombre maximum de tentatives atteint. Programme terminé.");
                }
            }
        }
    }

    private void afficherMenu() {
        System.out.println("\n=== GESTION DES EMPLOYÉS ===");
        System.out.println("1. Ajouter un employé");
        System.out.println("2. Modifier un employé");
        System.out.println("3. Supprimer un employé");
        System.out.println("4. Afficher tous les employés");
        System.out.println("5. Rechercher un employé");
        System.out.println("6. Calculer la masse salariale");
        System.out.println("7. Trier les employés par salaire");
        System.out.println("0. Quitter");
    }

    private boolean traiterChoix(int choix) throws EmployeException {
        switch (choix) {
            case 1: ajouterEmploye(); break;
            case 2: modifierEmploye(); break;
            case 3: supprimerEmploye(); break;
            case 4: gestion.afficherEmployes(); break;
            case 5: rechercherEmploye(); break;
            case 6: gestion.calculerMasseSalariale(); break;
            case 7: trierEmployes(); break;
            case 0: return false;
        }
        return true;
    }

    private int lireEntier(String message, int min, int max, int tentativesRestantes) throws EmployeException {
        while (tentativesRestantes > 0) {
            try {
                System.out.print(message + " : ");
                int valeur = Integer.parseInt(scanner.nextLine().trim());
                if (valeur >= min && valeur <= max) return valeur;
                throw new EmployeException("La valeur doit être entre " + min + " et " + max);
            } catch (NumberFormatException e) {
                System.out.println("Erreur : Veuillez entrer un nombre valide");
                tentativesRestantes--;
            }
        }
        throw new EmployeException("Nombre maximum de tentatives atteint");
    }

    private double lireDouble(String message, double min, double max, int tentativesRestantes) throws EmployeException {
        while (tentativesRestantes > 0) {
            try {
                System.out.print(message + " : ");
                double valeur = Double.parseDouble(scanner.nextLine().trim());
                if (valeur >= min && valeur <= max) return valeur;
                throw new EmployeException("La valeur doit être entre " + min + " et " + max);
            } catch (NumberFormatException e) {
                System.out.println("Erreur : Veuillez entrer un nombre valide");
                tentativesRestantes--;
            }
        }
        throw new EmployeException("Nombre maximum de tentatives atteint");
    }

    private String lireChaine(String message, int tentativesRestantes) throws EmployeException {
        while (tentativesRestantes > 0) {
            System.out.print(message + " : ");
            String valeur = scanner.nextLine().trim();
            if (valeur.isEmpty()) {
                System.out.println("La valeur ne peut pas être vide");
                tentativesRestantes--;
                continue;
            }
            if (!valeur.matches("^[a-zA-ZÀ-ÖØ-öø-ÿ\\s\\-/]{2,50}$")) {
                System.out.println("La valeur doit contenir uniquement des lettres, espaces et tirets (2-50 caractères)");
                tentativesRestantes--;
                continue;
            }
            return valeur;
        }
        throw new EmployeException("Nombre maximum de tentatives atteint");
    }

    private void ajouterEmploye() throws EmployeException {
        System.out.println("\n=== AJOUT D'UN EMPLOYÉ ===");

        if (((GestionEmployes) gestion).getNombreEmployes() >= TAILLE_MAX) {
            throw new EmployeException("Le tableau est plein (maximum " + TAILLE_MAX + " employés)");
        }

        while (true) {
            int id = lireEntier("ID", 1, Integer.MAX_VALUE, MAX_TENTATIVES);
            if (((GestionEmployes) gestion).rechercherEmployeParId(id) != -1) {
                System.out.println("Cet ID existe déjà. Veuillez entrer un autre ID ou appuyez sur 'espace' pour quitter.");
                if (scanner.nextLine().trim().equals("")) return;
                continue;
            }

            String nom = lireChaine("Nom", MAX_TENTATIVES);
            if (((GestionEmployes) gestion).rechercherEmployeParNom(nom)) {
                System.out.println("Ce nom existe déjà. Veuillez entrer un autre nom ou appuyez sur 'espace' pour quitter.");
                if (scanner.nextLine().trim().equals("")) return;
                continue;
            }

            String poste = lireChaine("Poste", MAX_TENTATIVES);
            double salaire = lireDouble("Salaire", 0, 1000000, MAX_TENTATIVES);

            Employe nouvelEmploye = new Employe(id, nom, poste, salaire);
            gestion.ajouterEmploye(nouvelEmploye);
            System.out.println("Employé ajouté avec succès !");
            break;
        }
    }

    private void modifierEmploye() throws EmployeException {
        System.out.println("\n=== MODIFICATION D'UN EMPLOYÉ ===");

        int id = lireEntier("ID", 1, Integer.MAX_VALUE, MAX_TENTATIVES);
        String nom = lireChaine("Nouveau nom", MAX_TENTATIVES);
        String poste = lireChaine("Nouveau poste", MAX_TENTATIVES);
        double salaire = lireDouble("Nouveau salaire", 0, 1000000, MAX_TENTATIVES);

        gestion.modifierEmploye(id, nom, poste, salaire);
        System.out.println("Employé modifié avec succès !");
    }

    private void supprimerEmploye() throws EmployeException {
        System.out.println("\n=== SUPPRESSION D'UN EMPLOYÉ ===");

        int id = lireEntier("ID de l'employé à supprimer", 1, Integer.MAX_VALUE, MAX_TENTATIVES);
        gestion.supprimerEmploye(id);
        System.out.println("Employé supprimé avec succès !");
    }

    private void rechercherEmploye() throws EmployeException {
        System.out.println("\n=== RECHERCHE D'EMPLOYÉ ===");

        String critere = lireChaine("Entrez le nom ou le poste à rechercher", MAX_TENTATIVES);
        gestion.rechercherEmploye(critere);
    }

    private void trierEmployes() throws EmployeException {
        System.out.println("\n=== TRI DES EMPLOYÉS PAR SALAIRE ===");
        System.out.println("1. Ordre croissant");
        System.out.println("2. Ordre décroissant");

        int choix = lireEntier("Votre choix", 1, 2, MAX_TENTATIVES);
        gestion.trierEmployesParSalaire(choix == 1);
        System.out.println("\nListe triée des employés :");
        gestion.afficherEmployes();
    }
}
