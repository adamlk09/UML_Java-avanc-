package ma.adam;

/**
 * Implémentation de l'interface IEmployeManagement
 */
public class GestionEmployes implements IEmployeManagement {
    private Employe[] employes;
    private int nombreEmployes;
    private final int MAX_EMPLOYES;

    /**
     * Constructeur par défaut
     * Initialise un tableau avec une capacité maximale de 50 employés
     */
    public GestionEmployes() {
        MAX_EMPLOYES = 50;
        employes = new Employe[MAX_EMPLOYES];
        nombreEmployes = 0;
    }

    /**
     * Getter pour le nombre d'employés
     * 
     * @return le nombre d'employés actuellement enregistrés
     */
    public int getNombreEmployes() {
        return nombreEmployes;
    }

    @Override
    public void ajouterEmploye(Employe employe) throws EmployeException {
        if (nombreEmployes >= MAX_EMPLOYES) {
            throw new EmployeException("Le tableau est plein ! (Maximum " + MAX_EMPLOYES + " employés)");
        }
        if (rechercherEmployeParId(employe.getId()) != -1) {
            throw new EmployeException("Cet ID existe déjà !");
        }
        employes[nombreEmployes++] = employe;
    }

    /**
     * Modifie les informations d'un employé existant
     * 
     * @param id      l'identifiant de l'employé à modifier
     * @param nom     le nouveau nom de l'employé
     * @param poste   le nouveau poste de l'employé
     * @param salaire le nouveau salaire de l'employé
     */

    @Override
    public void modifierEmploye(int id, String nom, String poste, double salaire) throws EmployeException {
        int index = rechercherEmployeParId(id);
        if (index == -1) {
            throw new EmployeException("Employé non trouvé !");
        }
        employes[index].setNom(nom);
        employes[index].setPoste(poste);
        employes[index].setSalaire(salaire);
    }

    /**
     * Supprime un employé de la liste
     * 
     * @param id l'identifiant de l'employé à supprimer
     * @throws EmployeException si l'employé avec l'ID spécifié n'existe pas
     */

    @Override
    public void supprimerEmploye(int id) throws EmployeException {
        int index = rechercherEmployeParId(id);
        if (index == -1) {
            throw new EmployeException("Employé non trouvé !");
        }
        for (int i = index; i < nombreEmployes - 1; i++) {
            employes[i] = employes[i + 1];
        }
        employes[--nombreEmployes] = null;
    }

    /**
     * Affiche la liste des employés
     */
    @Override
    public void afficherEmployes() {
        if (nombreEmployes == 0) {
            System.out.println("Aucun employé enregistré !");
            return;
        }
        for (int i = 0; i < nombreEmployes; i++) {
            System.out.println(employes[i]);
        }
    }

    /**
     * Recherche un employé par son nom ou son poste
     * 
     * @param critere le critère de recherche (nom ou poste)
     */
    @Override
    public void rechercherEmploye(String critere) {
        boolean trouve = false;

        for (int i = 0; i < nombreEmployes; i++) {
            if (employes[i].getNom().toLowerCase().contains(critere.toLowerCase()) ||
                    employes[i].getPoste().toLowerCase().contains(critere.toLowerCase())) {
                System.out.println(employes[i]);
                trouve = true;
            }
        }
        if (!trouve) {
            System.out.println("Aucun employé trouvé !");
        }
    }

    /**
     * Calcule la masse salariale totale de tous les employés
     */
    @Override
    public void calculerMasseSalariale() {
        double total = 0;

        for (int i = 0; i < nombreEmployes; i++) {
            total += employes[i].getSalaire();
        }
        System.out.printf("Masse salariale totale : %.2f€%n", total);
    }

    /**
     * Trie les employés par salaire
     * 
     * @param ordreCroissant true pour trier par ordre croissant, false pour trier
     *                       par ordre décroissant
     * @throws EmployeException si aucun employé à trier
     */
    @Override
    public void trierEmployesParSalaire(boolean ordreCroissant) throws EmployeException {
        if (nombreEmployes == 0) {

            throw new EmployeException("Aucun employé à trier !");
        }
        for (int i = 0; i < nombreEmployes - 1; i++) {
            for (int j = 0; j < nombreEmployes - i - 1; j++) {
                if (ordreCroissant ? employes[j].getSalaire() > employes[j + 1].getSalaire()
                        : employes[j].getSalaire() < employes[j + 1].getSalaire()) {
                    Employe temp = employes[j];
                    employes[j] = employes[j + 1];
                    employes[j + 1] = temp;
                }
            }
        }
    }

    /**
     * Recherche un employé par son ID
     * 
     * @param id l'identifiant de l'employé à rechercher
     * @return l'index de l'employé dans le tableau ou -1 si non trouvé
     */
    public int rechercherEmployeParId(int id) {
        for (int i = 0; i < nombreEmployes; i++) {
            if (employes[i].getId() == id) {

                return i;
            }
        }
        return -1;
    }

    /**
     * Recherche un employé par son nom
     * 
     * @param nom le nom de l'employé à rechercher
     * @return true si l'employé est trouvé, false sinon
     */

    public boolean rechercherEmployeParNom(String nom) {
        for (int i = 0; i < nombreEmployes; i++) {
            if (employes[i].getNom().equalsIgnoreCase(nom)) {

                return true;
            }
        }
        return false;
    }

}