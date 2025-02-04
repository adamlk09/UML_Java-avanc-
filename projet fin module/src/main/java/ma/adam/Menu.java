package ma.adam;

import java.util.Scanner;

/**
 * Classe gérant l'interface utilisateur
 */
public class Menu {
    private Scanner scanner;
    private IEmployeManagement gestion;

    public Menu(Scanner scanner, IEmployeManagement gestion) {
        this.scanner = scanner;
        this.gestion = gestion;
    }

    public void executer() {
        boolean continuer = true;
        while (continuer) {
            afficherMenu();
            try {
                int choix = lireEntier("Votre choix", 0, 7);
                continuer = traiterChoix(choix);
            } catch (Exception e) {
                System.out.println("Erreur : " + e.getMessage());
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

    private String lireChaine(String message) {
        System.out.print(message + " : ");
        return scanner.nextLine().trim();
    }

    private int lireEntier(String message, int min, int max) {
        while (true) {
            try {
                System.out.print(message + " : ");
                int valeur = Integer.parseInt(scanner.nextLine());
                if (valeur >= min && valeur <= max) return valeur;
                System.out.println("Veuillez entrer un nombre entre " + min + " et " + max);
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un nombre valide");
            }
        }
    }

    private double lireDouble(String message, double min, double max) {
        while (true) {
            try {
                System.out.print(message + " : ");
                double valeur = Double.parseDouble(scanner.nextLine());
                if (valeur >= min && valeur <= max) return valeur;
                System.out.println("Veuillez entrer un nombre entre " + min + " et " + max);
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un nombre valide");
            }
        }
    }

    // === MÉTHODES DE VALIDATION ===
    private void validerNomOuPoste(String valeur, String champ) throws EmployeException {
        if (!valeur.matches("[A-Za-zÀ-ÖØ-öø-ÿ\\s]+")) {
            throw new EmployeException("Erreur : " + champ + " ne peut contenir que des lettres et des espaces.");
        }
    }

    private void validerSalaire(double salaire) throws EmployeException {
        if (salaire < 0 || salaire > 1000000) {
            throw new EmployeException("Erreur : Le salaire doit être entre 0 et 1 000 000.");
        }
    }

    private void validerID(int id) throws EmployeException {
        if (id <= 0) {
            throw new EmployeException("Erreur : L'ID doit être un entier positif.");
        }
    }

    // === MÉTHODES DE GESTION ===
    private void ajouterEmploye() throws EmployeException {
        System.out.println("\n=== AJOUT D'UN EMPLOYÉ ===");

        int id = lireEntier("ID", 1, Integer.MAX_VALUE);
        validerID(id);

        String nom = lireChaine("Nom");
        validerNomOuPoste(nom, "Nom");

        String poste = lireChaine("Poste");
        validerNomOuPoste(poste, "Poste");

        double salaire = lireDouble("Salaire", 0, 1000000);
        validerSalaire(salaire);

        gestion.ajouterEmploye(new Employe(id, nom, poste, salaire));
        System.out.println("Employé ajouté avec succès !");
    }

    private void modifierEmploye() throws EmployeException {
        System.out.println("\n=== MODIFICATION D'UN EMPLOYÉ ===");

        int id = lireEntier("ID", 1, Integer.MAX_VALUE);
        validerID(id);

        String nom = lireChaine("Nouveau nom");
        validerNomOuPoste(nom, "Nouveau nom");

        String poste = lireChaine("Nouveau poste");
        validerNomOuPoste(poste, "Nouveau poste");

        double salaire = lireDouble("Nouveau salaire", 0, 1000000);
        validerSalaire(salaire);

        gestion.modifierEmploye(id, nom, poste, salaire);
        System.out.println("Employé modifié avec succès !");
    }

    private void supprimerEmploye() throws EmployeException {
        System.out.println("\n=== SUPPRESSION D'UN EMPLOYÉ ===");

        int id = lireEntier("ID", 1, Integer.MAX_VALUE);
        validerID(id);

        gestion.supprimerEmploye(id);
        System.out.println("Employé supprimé avec succès !");
    }

    private void rechercherEmploye() {
        System.out.println("\n=== RECHERCHE D'EMPLOYÉ ===");

        String critere = lireChaine("Entrez le nom ou le poste à rechercher");
        try {
            validerNomOuPoste(critere, "Critère de recherche");
            gestion.rechercherEmploye(critere);
        } catch (EmployeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void trierEmployes() throws EmployeException {
        System.out.println("\n=== TRI DES EMPLOYÉS ===");
        System.out.println("1. Ordre croissant");
        System.out.println("2. Ordre décroissant");

        int choix = lireEntier("Choix", 1, 2);
        gestion.trierEmployesParSalaire(choix == 1);
        gestion.afficherEmployes();
    }
}
