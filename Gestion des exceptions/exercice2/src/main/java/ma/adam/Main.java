package ma.adam;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<CompteBancaire> comptes = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // Creating accounts
        CompteCourant compte1 = new CompteCourant("123456", "Alice", 1000, 500);
        CompteEpargne compte2 = new CompteEpargne("789012", "Bob", 2000, 2);

        // Adding accounts to list
        comptes.add(compte1);
        comptes.add(compte2);

        // Displaying accounts
        System.out.println("\n***Comptes disponibles:");
        for (CompteBancaire compte : comptes) {
            compte.afficherSolde();
        }

        try {
            // Depositing money
            System.out.println("\n--Dépôt de 500 sur le compte de Alice...");
            compte1.deposer(500);

            // Withdrawing money
            System.out.println("\n--Retrait de 3000 sur le compte de Bob...");
            compte2.retirer(3000); // This will throw an exception

        } catch (FondsInsuffisantsException e) {
            System.out.println(" Erreur: " + e.getMessage());
        }

        try {
            // Transferring money
            System.out.println("\n--Transfert de 700 de Alice vers Bob...");
            compte1.transferer(compte2, 700);
        } catch (FondsInsuffisantsException | CompteInexistantException e) {
            System.out.println(" Erreur: " + e.getMessage());
        }

        // Removing an account
        System.out.println("\n--Suppression du compte de Bob...");
        comptes.remove(compte2);

        // Checking if Bob's account exists before transferring
        try {
            compte1.transferer(compte2, 100); // Should throw CompteInexistantException
        } catch (FondsInsuffisantsException | CompteInexistantException e) {
            System.out.println("Erreur: " + e.getMessage());
        }

        scanner.close();
    }
}

