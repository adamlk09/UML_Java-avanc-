package ma.adam;

public class EmployeException extends Exception {
    /**
     * Constructeur par défaut de l'exception
     * Cette classe gère les exceptions spécifiques aux employés
     * Elle est utilisée pour signaler des erreurs de validation des données
     * employés
     * comme des salaires invalides ou des champs obligatoires manquants
     */
    public EmployeException(String message) {
        super(message);
    }
}
