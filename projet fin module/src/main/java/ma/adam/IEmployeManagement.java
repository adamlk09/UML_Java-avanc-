package ma.adam;

/**
 * Interface définissant les opérations de gestion des employés
 */
public interface IEmployeManagement {
    /**
     * Ajoute un nouvel employé à la liste
     * 
     * @param employe l'objet Employe à ajouter
     * @throws "EmployeException" si l'ajout échoue (tableau plein ou ID existant)
     */
    void ajouterEmploye(Employe employe) throws EmployeException;

    /**
     * Modifie les informations d'un employé existant
     * 
     * @param id l'identifiant de l'employé à modifier
     *           * @throws EmployeException si l'employé avec l'ID spécifié n'existe
     *           pas
     */
    void modifierEmploye(int id, String nom, String poste, double salaire) throws EmployeException;

    /**
     * Supprime un employé de la liste
     * 
     * @param id l'identifiant de l'employé à supprimer
     * @throws "EmployeException" si l'employé avec l'ID spécifié n'existe pas
     */
    void supprimerEmploye(int id) throws EmployeException;

    /**
     * Affiche la liste des employés
     */
    void afficherEmployes();

    /**
     * Recherche un employé par son ID
     * 
     * @param "id" l'identifiant de l'employé à rechercher
     * @return l'index de l'employé dans le tableau ou -1 si non trouvé
     */
    void rechercherEmploye(String critere);

    /**
     * Recherche un employé par son ID
     * 
     * @param "id" l'identifiant de l'employé à rechercher
     * @return l'index de l'employé dans le tableau ou -1 si non trouvé
     */
    void calculerMasseSalariale();

    /**
     * Recherche un employé par son ID
     * 
     * @param "id" l'identifiant de l'employé à rechercher
     * @return l'index de l'employé dans le tableau ou -1 si non trouvé
     */
    void trierEmployesParSalaire(boolean ordreCroissant) throws EmployeException;
}
