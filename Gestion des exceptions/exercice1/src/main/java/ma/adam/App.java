package ma.adam;
import ma.adam.EntierNaturel;
import ma.adam.NombreNegatifException;

public class App 
{
    public static void main(String[] args){
    // Test valid initialization
        try {
    EntierNaturel en = new EntierNaturel(5);
    System.out.println("Initial value: " + en.getVal());
} catch (NombreNegatifException e) {
    System.out.println("Error: " + e.getMessage() + ", Erroneous Value: " + e.getValeurErronee());
}

    // Test invalid initialization
        try {
    EntierNaturel enNeg = new EntierNaturel(-3);
} catch (NombreNegatifException e) {
    System.out.println("Error: " + e.getMessage() + ", Erroneous Value: " + e.getValeurErronee());
}

    // Test setVal with negative value
        try {
    EntierNaturel en = new EntierNaturel(10);
    en.setVal(-5);
} catch (NombreNegatifException e) {
    System.out.println("Error: " + e.getMessage() + ", Erroneous Value: " + e.getValeurErronee());
}

    // Test decrementer leading to negative value
        try {
    EntierNaturel en = new EntierNaturel(0);
    en.decrementer();
} catch (NombreNegatifException e) {
    System.out.println("Error: " + e.getMessage() + ", Erroneous Value: " + e.getValeurErronee());
}

    // Test valid decrement
        try {
    EntierNaturel en = new EntierNaturel(2);
    en.decrementer();
    System.out.println("Value after decrement: " + en.getVal());
} catch (NombreNegatifException e) {
    System.out.println("Error: " + e.getMessage());
}
}
}
