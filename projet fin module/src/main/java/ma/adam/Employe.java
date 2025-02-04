package ma.adam;
/**
 * Classe représentant un employé
 */
public class Employe {
    private int id;
    private String nom;
    private String poste;
    private double salaire;

    private static final double SALAIRE_MIN = 0;
    private static final double SALAIRE_MAX = 1000000;

    public Employe() {
        this.id = 0;
        this.nom = "";
        this.poste = "";
        this.salaire = 0.0;
    }

    public Employe(int id, String nom, String poste, double salaire) throws EmployeException {
        setId(id);
        setNom(nom);
        setPoste(poste);
        setSalaire(salaire);
    }

    public int getId() { return id; }

    public void setId(int id) throws EmployeException {
        if (id < 0) {
            throw new EmployeException("L'ID doit être un nombre positif");
        }
        this.id = id;
    }

    public String getNom() { return nom; }

    public void setNom(String nom) throws EmployeException {
        if (nom == null || nom.trim().isEmpty()) {
            throw new EmployeException("Le nom ne peut pas être vide");
        }
        if (!nom.matches("^[a-zA-Z\\s-]{2,50}$")) {
            throw new EmployeException("Le nom doit contenir uniquement des lettres, espaces et tirets (2-50 caractères)");
        }
        this.nom = nom.trim();
    }

    public String getPoste() { return poste; }

    public void setPoste(String poste) throws EmployeException {
        if (poste == null || poste.trim().isEmpty()) {
            throw new EmployeException("Le poste ne peut pas être vide");
        }
        if (!poste.matches("^[a-zA-Z\\s-]{2,50}$")) {
            throw new EmployeException("Le poste doit contenir uniquement des lettres, espaces et tirets (2-50 caractères)");
        }
        this.poste = poste.trim();
    }

    public double getSalaire() { return salaire; }

    public void setSalaire(double salaire) throws EmployeException {
        if (salaire < SALAIRE_MIN || salaire > SALAIRE_MAX) {
            throw new EmployeException("Le salaire doit être entre " + SALAIRE_MIN + " et " + SALAIRE_MAX);
        }
        this.salaire = salaire;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Nom: %s | Poste: %s | Salaire: %.2f€",
                id, nom, poste, salaire);
    }
}