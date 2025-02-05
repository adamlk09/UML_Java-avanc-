package ma.adam;
import java.util.Scanner;
/**
 * Hello world!
 *
 */
public class App{
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            IEmployeManagement gestion = new GestionEmployes();
            Menu menu = new Menu(scanner, gestion);
            menu.executer();
            scanner.close();
        }
}