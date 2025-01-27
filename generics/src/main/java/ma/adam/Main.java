package ma.adam;

import ma.adam.tp.MetierProduitImpl;
import ma.adam.tp.Produit;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        MetierProduitImpl metier = new MetierProduitImpl();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Afficher la liste des produits");
            System.out.println("2. Rechercher un produit par ID");
            System.out.println("3. Ajouter un nouveau produit");
            System.out.println("4. Supprimer un produit par ID");
            System.out.println("5. Quitter");
            System.out.print("Choisissez une option: ");
            int choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    System.out.println("Liste des produits:");
                    for (Produit p : metier.getAll()) {
                        System.out.println(p);
                    }
                    break;

                case 2:
                    System.out.print("Entrez l'ID du produit: ");
                    long id = scanner.nextLong();
                    Produit produit = metier.findById(id);
                    if (produit != null) {
                        System.out.println(produit);
                    } else {
                        System.out.println("Produit non trouvé !");
                    }
                    break;

                case 3:
                    System.out.print("Entrez l'ID: ");
                    long newId = scanner.nextLong();
                    scanner.nextLine(); // Clear buffer
                    System.out.print("Entrez le nom: ");
                    String nom = scanner.nextLine();
                    System.out.print("Entrez la marque: ");
                    String marque = scanner.nextLine();
                    System.out.print("Entrez le prix: ");
                    double prix = scanner.nextDouble();
                    scanner.nextLine(); // Clear buffer
                    System.out.print("Entrez la description: ");
                    String description = scanner.nextLine();
                    System.out.print("Entrez le nombre en stock: ");
                    int stock = scanner.nextInt();
                    metier.add(new Produit(newId, nom, marque, prix, description, stock));
                    break;

                case 4:
                    System.out.print("Entrez l'ID du produit à supprimer: ");
                    long deleteId = scanner.nextLong();
                    metier.delete(deleteId);
                    System.out.println("Produit supprimé !");
                    break;

                case 5:
                    System.out.println("Au revoir !");
                    scanner.close();
                    return;

                default:
                    System.out.println("Option invalide !");
            }
        }
    }
    }
