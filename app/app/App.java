package app;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

// main class
public class App {

    public static void main(String[] args) {
        System.out.println("I hate Java");

        System.out.println("Validating XML file...");
        ValidatorXML validatorXML = new ValidatorXML();
        try {
            InputStream xml = new FileInputStream("../sipvs-fiit/data/sample.xml");
            InputStream xsd = new FileInputStream("../sipvs-fiit/data/schema.xsd");

            boolean validationResult = validatorXML.validateAgainstXSD(xml, xsd);
            System.out.println("Validation result: " + validationResult);

        } catch (FileNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("Finished...");
    }
}
