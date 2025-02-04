package ma.adam;

/**
 * Implémentation de l'interface IEmployeManagement
 */
public class GestionEmployes implements IEmployeManagement {
    private Employe[] employes;
    private int nombreEmployes;
    private final int MAX_EMPLOYES;

    public GestionEmployes(int capacite) {
        MAX_EMPLOYES = capacite;
        employes = new Employe[MAX_EMPLOYES];
        nombreEmployes = 0;
    }

    @Override
    public void ajouterEmploye(Employe employe) throws EmployeException {
        if (nombreEmployes >= MAX_EMPLOYES) {
            throw new EmployeException("Le tableau est plein !");
        }
        if (rechercherEmployeParId(employe.getId()) != -1) {
            throw new EmployeException("Cet ID existe déjà !");
        }
        employes[nombreEmployes++] = employe;
    }

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

    @Override
    public void calculerMasseSalariale() {
        double total = 0;
        for (int i = 0; i < nombreEmployes; i++) {
            total += employes[i].getSalaire();
        }
        System.out.printf("Masse salariale totale : %.2f€%n", total);
    }

    @Override
    public void trierEmployesParSalaire(boolean ordreCroissant) throws EmployeException {
        if (nombreEmployes == 0) {
            throw new EmployeException("Aucun employé à trier !");
        }
        for (int i = 0; i < nombreEmployes - 1; i++) {
            for (int j = 0; j < nombreEmployes - i - 1; j++) {
                if (ordreCroissant ?
                        employes[j].getSalaire() > employes[j + 1].getSalaire() :
                        employes[j].getSalaire() < employes[j + 1].getSalaire()) {
                    Employe temp = employes[j];
                    employes[j] = employes[j + 1];
                    employes[j + 1] = temp;
                }
            }
        }
    }

    private int rechercherEmployeParId(int id) {
        for (int i = 0; i < nombreEmployes; i++) {
            if (employes[i].getId() == id) {
                return i;
            }
        }
        return -1;
    }
}