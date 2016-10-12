package app;


import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

// main class
public class App {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainWindow frame = new MainWindow();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

//        System.out.println("Validating XML file...");
//        ValidatorXML validatorXML = new ValidatorXML();
//        try {
//            InputStream xml = new FileInputStream("../sipvs-fiit/data/sample.xml");
//            InputStream xsd = new FileInputStream("../sipvs-fiit/data/schema.xsd");
//
//            boolean validationResult = validatorXML.validateAgainstXSD(xml, xsd);
//            System.out.println("Validation result: " + validationResult);
//
//        } catch (FileNotFoundException e) {
//            System.err.println("Error: " + e.getMessage());
//            e.printStackTrace();
//        }
    }
}
