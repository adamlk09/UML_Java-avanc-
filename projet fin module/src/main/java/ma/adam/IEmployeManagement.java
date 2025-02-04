package ma.adam;


    /**
     * Interface définissant les opérations de gestion des employés
     */
    public interface IEmployeManagement {
        void ajouterEmploye(Employe employe) throws EmployeException;
        void modifierEmploye(int id, String nom, String poste, double salaire) throws EmployeException;
        void supprimerEmploye(int id) throws EmployeException;
        void afficherEmployes();
        void rechercherEmploye(String critere);
        void calculerMasseSalariale();
        void trierEmployesParSalaire(boolean ordreCroissant) throws EmployeException;
    }

