package ma.adam;

/**
 * Classe représentant un employé avec ses informations de base
 */
public class Employe {
    // Identifiant unique de l'employé
    private int id;

    // Nom de l'employé
    private String nom;

    // Poste occupé par l'employé
    private String poste;

    // Salaire de l'employé
    private double salaire;

    // Valeurs minimales et maximales autorisées pour le salaire
    private static final double SALAIRE_MIN = 0;
    private static final double SALAIRE_MAX = 1000000;

    /**
     * Constructeur par défaut initialisant les attributs à des valeurs neutres
     */
    public Employe() {
        this.id = 0;
        this.nom = "";
        this.poste = "";
        this.salaire = 0.0;
    }

    /**
     * Constructeur permettant d'initialiser un employé avec des valeurs spécifiques
     * 
     * @param id      l'identifiant de l'employé
     * @param nom     le nom de l'employé
     * @param poste   le poste occupé par l'employé
     * @param salaire le salaire de l'employé
     * @throws EmployeException en cas de valeurs invalides
     */
    public Employe(int id, String nom, String poste, double salaire) throws EmployeException {
        setId(id);
        setNom(nom);
        setPoste(poste);
        setSalaire(salaire);
    }

    /**
     * Getter pour l'ID de l'employé
     */
    public int getId() {
        return id;
    }

    /**
     * Setter pour l'ID de l'employé avec validation
     * 
     * @param id L'ID à attribuer
     * @throws EmployeException si l'ID est négatif ou nul
     */
    public void setId(int id) throws EmployeException {
        if (id <= 0) {
            throw new EmployeException("L'ID doit être un nombre positif");
        }
        this.id = id;
    }

    /**
     * Getter pour le nom de l'employé
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter pour le nom de l'employé avec validation
     * 
     * @param nom Le nom à attribuer
     * @throws EmployeException si le nom est vide ou invalide
     */
    public void setNom(String nom) throws EmployeException {
        if (nom == null || nom.trim().isEmpty()) {
            throw new EmployeException("Le nom ne peut pas être vide");
        }
        if (!nom.matches("^[a-zA-ZÀ-ÖØ-öø-ÿ\\s-]{2,50}$")) {
            throw new EmployeException(
                    "Le nom doit contenir uniquement des lettres, espaces et tirets (2-50 caractères)");
        }
        this.nom = nom.trim();
    }

    /**
     * Getter pour le poste de l'employé
     */
    public String getPoste() {
        return poste;
    }

    /**
     * Setter pour le poste de l'employé avec validation
     * 
     * @param poste Le poste à attribuer
     * @throws EmployeException si le poste est vide ou invalide
     */
    public void setPoste(String poste) throws EmployeException {
        if (poste == null || poste.trim().isEmpty()) {
            throw new EmployeException("Le poste ne peut pas être vide");
        }
        if (!poste.matches("^[a-zA-ZÀ-ÖØ-öø-ÿ\\s-]{2,50}$")) {
            throw new EmployeException(
                    "Le poste doit contenir uniquement des lettres, espaces et tirets (2-50 caractères)");
        }
        this.poste = poste.trim();
    }

    /**
     * Getter pour le salaire de l'employé
     */
    public double getSalaire() {
        return salaire;
    }

    /**
     * Setter pour le salaire de l'employé avec validation
     * 
     * @param salaire Le salaire à attribuer
     * @throws EmployeException si le salaire est hors des limites autorisées
     */
    public void setSalaire(double salaire) throws EmployeException {
        if (salaire < SALAIRE_MIN || salaire > SALAIRE_MAX) {
            throw new EmployeException("Le salaire doit être entre " + SALAIRE_MIN + " et " + SALAIRE_MAX);
        }
        this.salaire = salaire;
    }

    /**
     * Méthode pour représenter les informations d'un employé sous forme de chaîne
     * de caractères
     * 
     * @return Une chaîne contenant les informations de l'employé
     */
    @Override
    public String toString() {
        return String.format("ID: %d | Nom: %s | Poste: %s | Salaire: %.2f€",
                id, nom, poste, salaire);
    }
}
