package ma.adam;

class CompteBancaire {
    protected String numeroCompte;
    protected double solde;
    protected String titulaire;

    public CompteBancaire(String numeroCompte, String titulaire, double soldeInitial) {
        this.numeroCompte = numeroCompte;
        this.titulaire = titulaire;
        this.solde = soldeInitial;
    }

    public void deposer(double montant) {
        solde += montant;
        System.out.println("Dépôt de " + montant + " effectué. Nouveau solde: " + solde);
    }

    public void retirer(double montant) throws FondsInsuffisantsException {
        if (montant > solde) {
            throw new FondsInsuffisantsException();
        }
        solde -= montant;
        System.out.println("Retrait de " + montant + " effectué. Nouveau solde: " + solde);
    }

    public void afficherSolde() {
        System.out.println("Compte: " + numeroCompte + " - Titulaire: " + titulaire + " - Solde: " + solde);
    }

    public void transferer(CompteBancaire destinataire, double montant) throws FondsInsuffisantsException, CompteInexistantException {
        if (destinataire == null) {
            throw new CompteInexistantException();
        }
        this.retirer(montant);
        destinataire.deposer(montant);
        System.out.println("Transfert de " + montant + " à " + destinataire.titulaire + " effectué.");
    }
}
