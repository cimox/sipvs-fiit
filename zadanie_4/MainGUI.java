import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MainGUI extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textFieldFileName;

    public MainGUI() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        String path = "Priklady/" + textFieldFileName.getText() + ".xml";
        File xml = new File(path);
        verifyXML(xml);
        //dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    // Premenné pre overovanie XML súborov

    // Dokument - Profil XAdES - kapitola 4.5
    private List<String> kryptAlg = new LinkedList<String>(Arrays.asList("http://www.w3.org/2000/09/xmldsig#dsa-sha1",
            "http://www.w3.org/2000/09/xmldsig#rsa-sha1",
            "http://www.w3.org/2001/04/xmldsig-more#rsa-sha256",
            "http://www.w3.org/2001/04/xmldsig-more#rsa-sha384",
            "http://www.w3.org/2001/04/xmldsig-more#rsa-sha512",
            "http://www.w3.org/2000/09/xmldsig#sha1",
            "http://www.w3.org/2001/04/xmldsig#sha224",
            "http://www.w3.org/2001/04/xmlenc#sha256",
            "http://www.w3.org/2001/04/xmldsig-more#sha384",
            "http://www.w3.org/2001/04/xmlenc#sha512"));

    private void verifyXML(File xml) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            Document doc = documentBuilder.parse(xml);
            doc.getDocumentElement().normalize();

            /*******************Overenie dátovej obálky*******************/
            // Dokument - Profil XAdES - kapitola 4.2.1
            Element element = (Element) doc.getElementsByTagName("xzep:DataEnvelope").item(0);

            if (element == null)
            {
                JOptionPane.showMessageDialog(null,"Dátová obálka neexistuje!","Overenie dátovej obálky",JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!element.getAttribute("xmlns:xzep").equals("http://www.ditec.sk/ep/signature_formats/xades_zep/v1.0"))
            {
                JOptionPane.showMessageDialog(null,"Dátová obálka neobsahuje xmlns:xzep podľa profilu XADES_ZEP!","Overenie dátovej obálky",JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!element.getAttribute("xmlns:ds").equals("http://www.w3.org/2000/09/xmldsig#"))
            {
                JOptionPane.showMessageDialog(null,"Dátová obálka neobsahuje xmlns:ds podľa profilu XADES_ZEP!","Overenie dátovej obálky",JOptionPane.ERROR_MESSAGE);
                return;
            }

            /*******************Overenie ds:SignatureMethod a ds:CanonicalizationMethod *******************/
            // ds:SignatureMethod
            // Dokument - Profil XAdES - kapitola 4.3.1.2
            element = (Element) doc.getElementsByTagName("ds:SignatureMethod").item(0);
            if (element == null)
            {
                JOptionPane.showMessageDialog(null,"Neexistuje element ds:SignatureMethod!","Overenie ds:SignatureMethod a ds:CanonicalizationMethod",JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(!kryptAlg.contains(element.getAttribute("Algorithm")))
            {
                JOptionPane.showMessageDialog(null,"ds:SignatureMethod neobsahuje URI podporovaných algoritmov!","Overenie ds:SignatureMethod a ds:CanonicalizationMethod",JOptionPane.ERROR_MESSAGE);
                return;
            }

            // ds:CanonicalizationMethod
            // Dokument - Profil XAdES - kapitola 4.3.1.1
            element = (Element) doc.getElementsByTagName("ds:CanonicalizationMethod").item(0);
            if (element == null)
            {
                JOptionPane.showMessageDialog(null,"Neexistuje element ds:CanonicalizationMethod!","Overenie ds:SignatureMethod a ds:CanonicalizationMethod",JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(!element.getAttribute("Algorithm").equals("http://www.w3.org/TR/2001/REC-xml-c14n-20010315"))
            {
                JOptionPane.showMessageDialog(null,"ds:CanonicalizationMethod neobsahuje URI podporovaných algoritmov!","Overenie ds:SignatureMethod a ds:CanonicalizationMethod",JOptionPane.ERROR_MESSAGE);
                return;
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JOptionPane.showMessageDialog(null,"Všetko prebehlo OK!","Overenie „XADES-T“ podpisu",JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        MainGUI dialog = new MainGUI();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
