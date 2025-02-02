package ma.adam;

class CompteEpargne extends CompteBancaire {
    private double tauxInteret;

    public CompteEpargne(String numeroCompte, String titulaire, double soldeInitial, double tauxInteret) {
        super(numeroCompte, titulaire, soldeInitial);
        this.tauxInteret = tauxInteret;
    }

    public void appliquerInterets() {
        double interets = solde * tauxInteret / 100;
        solde += interets;
        System.out.println("Intérêts appliqués: " + interets + ". Nouveau solde: " + solde);
    }
}
