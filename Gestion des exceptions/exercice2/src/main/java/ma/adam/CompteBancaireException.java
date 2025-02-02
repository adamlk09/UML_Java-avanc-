package ma.adam;


// Exception when the balance is not sufficient
class FondsInsuffisantsException extends Exception {
    public FondsInsuffisantsException() {
        super("Fonds insuffisants pour effectuer cette opération !");
    }
}

// Exception when the account does not exist
class CompteInexistantException extends Exception {
    public CompteInexistantException() {
        super("Compte bancaire inexistant !");
    }
}

