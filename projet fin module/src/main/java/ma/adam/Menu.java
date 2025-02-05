package ma.adam;

import java.util.Scanner;

public class Menu {
    /**
     * Classe Menu pour l'interface IEmployeManagement
     * 
     * @param scanner le scanner pour lire les entrées de l'utilisateur
     * @param gestion la gestion des employés
     */

    private Scanner scanner;
    private IEmployeManagement gestion;
    private static final int MAX_TENTATIVES = 3;
    private static final int TAILLE_MAX = 50;

    /**
     * Constructeur de la classe Menu
     * 
     * @param scanner le scanner pour lire les entrées de l'utilisateur
     * @param gestion la gestion des employés
     */
    public Menu(Scanner scanner, IEmployeManagement gestion) {
        this.scanner = scanner;
        this.gestion = gestion;
    }

    /**
     * Méthode pour exécuter le menu
     */
    public void executer() {
        int tentatives = 0;

        boolean continuer = true;

        // Boucle principale du menu
        // Permet à l'utilisateur de faire des choix jusqu'à ce qu'il décide de quitter
        // ou qu'il dépasse le nombre maximum de tentatives autorisées (MAX_TENTATIVES)

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

    /**
     * Méthode pour afficher le menu
     */
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

    /**
     * Méthode pour traiter le choix de l'utilisateur
     * 
     * @param choix le choix de l'utilisateur
     * @return true si le choix est valide, false sinon
     */

    private boolean traiterChoix(int choix) throws EmployeException {
        switch (choix) {
            case 1:
                ajouterEmploye();
                break;
            case 2:
                modifierEmploye();
                break;
            case 3:
                supprimerEmploye();
                break;
            case 4:
                gestion.afficherEmployes();
                break;
            case 5:
                rechercherEmploye();
                break;
            case 6:
                gestion.calculerMasseSalariale();
                break;
            case 7:
                trierEmployes();
                break;
            case 0:
                return false;
        }
        return true;
    }

    /**
     * Méthode pour lire un entier
     * 
     * @param message             le message à afficher
     * @param min                 la valeur minimale
     * @param max                 la valeur maximale
     * @param tentativesRestantes le nombre de tentatives restantes
     */
    private int lireEntier(String message, int min, int max, int tentativesRestantes) throws EmployeException {
        while (tentativesRestantes > 0) {
            try {

                System.out.print(message + " : ");
                int valeur = Integer.parseInt(scanner.nextLine().trim());
                if (valeur >= min && valeur <= max)
                    return valeur;
                throw new EmployeException("La valeur doit être entre " + min + " et " + max);
            } catch (NumberFormatException e) {
                System.out.println("Erreur : Veuillez entrer un nombre valide");
                tentativesRestantes--;
            }
        }
        throw new EmployeException("Nombre maximum de tentatives atteint");
    }

    /**
     * Méthode pour lire une chaîne de caractères avec validation
     * 
     * @param message             le message à afficher
     * @param tentativesRestantes le nombre de tentatives restantes
     * @return la chaîne de caractères validée
     * @throws EmployeException si le nombre maximum de tentatives est atteint
     */

    private double lireDouble(String message, double min, double max, int tentativesRestantes) throws EmployeException {
        while (tentativesRestantes > 0) {
            try {
                System.out.print(message + " : ");
                double valeur = Double.parseDouble(scanner.nextLine().trim());
                if (valeur >= min && valeur <= max)
                    return valeur;
                throw new EmployeException("La valeur doit être entre " + min + " et " + max);
            } catch (NumberFormatException e) {
                System.out.println("Erreur : Veuillez entrer un nombre valide");
                tentativesRestantes--;
            }
        }
        throw new EmployeException("Nombre maximum de tentatives atteint");
    }

    /**
     * Méthode pour lire une chaîne de caractères avec validation
     * 
     * @param message             le message à afficher
     * @param tentativesRestantes le nombre de tentatives restantes
     * @return la chaîne de caractères validée
     * @throws EmployeException si le nombre maximum de tentatives est atteint
     */
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
                System.out
                        .println("La valeur doit contenir uniquement des lettres, espaces et tirets (2-50 caractères)");
                tentativesRestantes--;
                continue;
            }
            return valeur;
        }
        throw new EmployeException("Nombre maximum de tentatives atteint");
    }

    /**
     * Méthode pour ajouter un employé
     * 
     * @throws EmployeException si l'ajout échoue (tableau plein ou ID existant)
     */
    private void ajouterEmploye() throws EmployeException {
        System.out.println("\n=== AJOUT D'UN EMPLOYÉ ===");

        if (((GestionEmployes) gestion).getNombreEmployes() >= TAILLE_MAX) {
            throw new EmployeException("Le tableau est plein (maximum " + TAILLE_MAX + " employés)");
        }

        while (true) {
            int id = lireEntier("ID", 1, Integer.MAX_VALUE, MAX_TENTATIVES);
            if (((GestionEmployes) gestion).rechercherEmployeParId(id) != -1) {
                System.out.println(
                        "Cet ID existe déjà. Veuillez entrer un autre ID ou appuyez sur 'espace' pour quitter.");
                if (scanner.nextLine().trim().equals(""))
                    return;
                continue;
            }

            String nom = lireChaine("Nom", MAX_TENTATIVES);
            if (((GestionEmployes) gestion).rechercherEmployeParNom(nom)) {
                System.out.println(
                        "Ce nom existe déjà. Veuillez entrer un autre nom ou appuyez sur 'espace' pour quitter.");
                if (scanner.nextLine().trim().equals(""))
                    return;
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

    /**
     * Méthode pour modifier un employé
     * 
     * @throws EmployeException si la modification échoue
     */

    private void modifierEmploye() throws EmployeException {
        System.out.println("\n=== MODIFICATION D'UN EMPLOYÉ ===");

        int id = lireEntier("ID", 1, Integer.MAX_VALUE, MAX_TENTATIVES);
        String nom = lireChaine("Nouveau nom", MAX_TENTATIVES);
        String poste = lireChaine("Nouveau poste", MAX_TENTATIVES);
        double salaire = lireDouble("Nouveau salaire", 0, 1000000, MAX_TENTATIVES);

        gestion.modifierEmploye(id, nom, poste, salaire);
        System.out.println("Employé modifié avec succès !");
    }

    /**
     * Méthode pour supprimer un employé
     * 
     * @throws EmployeException si la suppression échoue
     */
    private void supprimerEmploye() throws EmployeException {
        System.out.println("\n=== SUPPRESSION D'UN EMPLOYÉ ===");

        int id = lireEntier("ID de l'employé à supprimer", 1, Integer.MAX_VALUE, MAX_TENTATIVES);
        gestion.supprimerEmploye(id);
        System.out.println("Employé supprimé avec succès !");
    }

    /**
     * Méthode pour rechercher un employé
     * 
     * @throws EmployeException si la recherche échoue
     */

    private void rechercherEmploye() throws EmployeException {
        System.out.println("\n=== RECHERCHE D'EMPLOYÉ ===");

        String critere = lireChaine("Entrez le nom ou le poste à rechercher", MAX_TENTATIVES);
        gestion.rechercherEmploye(critere);
    }

    /**
     * Méthode pour trier les employés par salaire
     * 
     * @throws EmployeException si le tri échoue
     */
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
