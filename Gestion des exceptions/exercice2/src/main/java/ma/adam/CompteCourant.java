package ma.adam;

class CompteCourant extends CompteBancaire {
    private double decouvertAutorise;

    public CompteCourant(String numeroCompte, String titulaire, double soldeInitial, double decouvertAutorise) {
        super(numeroCompte, titulaire, soldeInitial);
        this.decouvertAutorise = decouvertAutorise;
    }

    @Override
    public void retirer(double montant) throws FondsInsuffisantsException {
        if (montant > solde + decouvertAutorise) {
            throw new FondsInsuffisantsException();
        }
        solde -= montant;
        System.out.println("Retrait de " + montant + " effectué avec découvert. Nouveau solde: " + solde);
    }
}

