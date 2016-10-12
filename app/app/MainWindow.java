package app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.NumberFormat;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
//import org.jdatepicker.impl.JDatePanelImpl;
//import org.jdatepicker.impl.JDatePickerImpl;
//import org.jdatepicker.impl.UtilDateModel;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.NumberFormatter;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import javax.swing.JFormattedTextField;
import javax.swing.UIManager;
import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

public class MainWindow extends JFrame {

    private JPanel contentPane;
    private JTextField textFieldMeno;
    private JTextField textFieldPriezvisko;
    private JTextField textFieldUlica;
    private JTextField textFieldMesto;
    private JTextField textFieldTitulKnihy;
    private JTextField textFieldISBN;
    private JTextField textFieldStat;
    private JTextField textFieldDatumOd;
    private JTextField textFieldDatumDo;
    private JTextField textFieldPSC;
    private JTextField textFieldCisloUlice;
    private JTextField textFieldMail;
    private JTextField textField;

    private JButton saveButton;

    /**
     * Create the frame.
     */
    public MainWindow() {
        setTitle("Po\u017Ei\u010Dov\u0148a kn\u00EDh");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "Pou\u017E\u00EDvate\u013E", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setBounds(10, 0, 300, 250);
        contentPane.add(panel);
        panel.setToolTipText("");
        panel.setLayout(new FormLayout(new ColumnSpec[]{
                FormSpecs.RELATED_GAP_COLSPEC,
                FormSpecs.DEFAULT_COLSPEC,
                FormSpecs.RELATED_GAP_COLSPEC,
                ColumnSpec.decode("default:grow"),
                FormSpecs.RELATED_GAP_COLSPEC,
                FormSpecs.DEFAULT_COLSPEC,
                FormSpecs.RELATED_GAP_COLSPEC,
                ColumnSpec.decode("default:grow"),},
                new RowSpec[]{
                        FormSpecs.RELATED_GAP_ROWSPEC,
                        FormSpecs.DEFAULT_ROWSPEC,
                        FormSpecs.RELATED_GAP_ROWSPEC,
                        FormSpecs.DEFAULT_ROWSPEC,
                        FormSpecs.RELATED_GAP_ROWSPEC,
                        FormSpecs.DEFAULT_ROWSPEC,
                        FormSpecs.RELATED_GAP_ROWSPEC,
                        FormSpecs.DEFAULT_ROWSPEC,
                        FormSpecs.RELATED_GAP_ROWSPEC,
                        FormSpecs.DEFAULT_ROWSPEC,
                        FormSpecs.RELATED_GAP_ROWSPEC,
                        FormSpecs.DEFAULT_ROWSPEC,}));

        JLabel lblMeno = new JLabel("Meno:");
        panel.add(lblMeno, "2, 2, right, default");

        textFieldMeno = new JTextField();
        panel.add(textFieldMeno, "4, 2, fill, default");
        textFieldMeno.setColumns(10);

        JLabel lblPriezvisko = new JLabel("Priezvisko:");
        panel.add(lblPriezvisko, "2, 4, right, default");

        textFieldPriezvisko = new JTextField();
        panel.add(textFieldPriezvisko, "4, 4, fill, default");
        textFieldPriezvisko.setColumns(10);

        JLabel lbltt = new JLabel("\u0160t\u00E1t:");
        panel.add(lbltt, "2, 6, right, default");

        textFieldStat = new JTextField();
        panel.add(textFieldStat, "4, 6, fill, default");
        textFieldStat.setColumns(10);

        JLabel lblMesto = new JLabel("Mesto:");
        panel.add(lblMesto, "2, 8, right, default");

        textFieldMesto = new JTextField();
        panel.add(textFieldMesto, "4, 8, fill, default");
        textFieldMesto.setColumns(10);

        JLabel lblPs = new JLabel("PS\u010C:");
        panel.add(lblPs, "6, 8, right, default");

        textFieldPSC = new JTextField();
        panel.add(textFieldPSC, "8, 8, fill, default");
        textFieldPSC.setColumns(10);

        JLabel lblUlica = new JLabel("Ulica:");
        panel.add(lblUlica, "2, 10, right, default");

        textFieldUlica = new JTextField();
        panel.add(textFieldUlica, "4, 10, fill, default");
        textFieldUlica.setColumns(10);

        JLabel label = new JLabel("\u010D.");
        panel.add(label, "6, 10, right, default");

        textFieldCisloUlice = new JTextField();
        panel.add(textFieldCisloUlice, "8, 10, fill, default");
        textFieldCisloUlice.setColumns(10);

        JLabel lblEmail = new JLabel("E-mail");
        panel.add(lblEmail, "2, 12, right, default");

        textFieldMail = new JTextField();
        panel.add(textFieldMail, "4, 12, fill, default");
        textFieldMail.setColumns(10);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Kniha(y)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel_1.setBounds(10, 260, 200, 100);
        contentPane.add(panel_1);
        panel_1.setLayout(new FormLayout(new ColumnSpec[]{
                FormSpecs.RELATED_GAP_COLSPEC,
                FormSpecs.DEFAULT_COLSPEC,
                FormSpecs.RELATED_GAP_COLSPEC,
                ColumnSpec.decode("default:grow"),},
                new RowSpec[]{
                        FormSpecs.RELATED_GAP_ROWSPEC,
                        FormSpecs.DEFAULT_ROWSPEC,
                        FormSpecs.RELATED_GAP_ROWSPEC,
                        FormSpecs.DEFAULT_ROWSPEC,}));

        JLabel lblTitulokKnihy = new JLabel("Titulok knihy:");
        panel_1.add(lblTitulokKnihy, "2, 2, right, default");

        textFieldTitulKnihy = new JTextField();
        panel_1.add(textFieldTitulKnihy, "4, 2, fill, default");
        textFieldTitulKnihy.setColumns(10);

        JLabel lblIsbn = new JLabel("ISBN:");
        panel_1.add(lblIsbn, "2, 4, right, default");

        textFieldISBN = new JTextField();
        panel_1.add(textFieldISBN, "4, 4, fill, default");
        textFieldISBN.setColumns(10);

        JPanel panel_2 = new JPanel();
        panel_2.setBorder(new TitledBorder(null, "D\u00E1tum", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_2.setBounds(210, 260, 280, 100);
        contentPane.add(panel_2);
        panel_2.setLayout(new FormLayout(new ColumnSpec[]{
                FormSpecs.RELATED_GAP_COLSPEC,
                FormSpecs.DEFAULT_COLSPEC,
                FormSpecs.RELATED_GAP_COLSPEC,
                FormSpecs.DEFAULT_COLSPEC,
                FormSpecs.RELATED_GAP_COLSPEC,
                FormSpecs.DEFAULT_COLSPEC,
                FormSpecs.RELATED_GAP_COLSPEC,
                ColumnSpec.decode("default:grow"),},
                new RowSpec[]{
                        FormSpecs.RELATED_GAP_ROWSPEC,
                        FormSpecs.DEFAULT_ROWSPEC,}));

        JLabel lblOd = new JLabel("Od");
        panel_2.add(lblOd, "2, 2");

        textFieldDatumOd = new JTextField();
        panel_2.add(textFieldDatumOd, "4, 2");
        textFieldDatumOd.setColumns(10);

        JLabel lblDo = new JLabel("do");
        panel_2.add(lblDo, "6, 2, right, default");

        textFieldDatumDo = new JTextField();
        panel_2.add(textFieldDatumDo, "8, 2, fill, default");
        textFieldDatumDo.setColumns(10);

        JLabel lblUpozornenie = new JLabel("Upozornenie (v dnoch):");
        lblUpozornenie.setBounds(10, 370, 150, 20);
        contentPane.add(lblUpozornenie);

        textField = new JTextField();
        textField.setBounds(160, 370, 100, 20);
        contentPane.add(textField);
        textField.setColumns(10);

//		Save button
        JButton saveButton = new JButton("Save XML");
        saveButton.setBounds(10, 400, 100, 25);
        contentPane.add(saveButton);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Save button pressed");

//				private JTextField textFieldMeno;
//				private JTextField textFieldPriezvisko;
//				private JTextField textFieldUlica;
//				private JTextField textFieldMesto;
//				private JTextField textFieldTitulKnihy;
//				private JTextField textFieldISBN;
//				private JTextField textFieldStat;
//				private JTextField textFieldDatumOd;
//				private JTextField textFieldDatumDo;
//				private JTextField textFieldPSC;
//				private JTextField textFieldCisloUlice;
//				private JTextField textFieldMail;
//				private JTextField textField;

                
                // Person info
                String firstName = textFieldMeno.getText();
                String lastName = textFieldPriezvisko.getText();
                String street = textFieldUlica.getText();
                Integer streetNumber = Integer.parseInt(textFieldCisloUlice.getText());
                String city = textFieldMesto.getText();
                String postalCode = textFieldPSC.getText();//Integer.parseInt(textFieldPSC.getText());
                String state = textFieldStat.getText();
                String email = textFieldMail.getText();
                Integer notification = Integer.parseInt(textField.getText());

                // Book info
                DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
                String bookTitle = textFieldTitulKnihy.getText();
                Date dateFrom = null, dateTo = null;
                try {
                    dateFrom = format.parse(textFieldDatumOd.getText());
                    dateTo = format.parse(textFieldDatumDo.getText());
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }


                BookDocument doc = new BookDocument(firstName, lastName, street, streetNumber, city, postalCode, state, email, notification, bookTitle, dateFrom, dateTo);
                Document document = doc.generateXML();
                File fileToSave;
                
                JFileChooser fileChooser = new JFileChooser();
                int userSelection = fileChooser.showSaveDialog(null);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    fileToSave = fileChooser.getSelectedFile();
                    String fileName = fileToSave.getAbsolutePath() + ".xml";
                    fileToSave = new File("../sipvs-fiit/data/file.xml");
                    //System.out.println("Save as file: " + fileToSave.getAbsolutePath());
                    
                    // write the content into xml file
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = null;
					try {
						transformer = transformerFactory.newTransformer();					
						DOMSource source = new DOMSource(document);                    
						StreamResult result = new StreamResult(fileToSave);
						transformer.transform(source, result);
					} 
                    catch (TransformerConfigurationException e1) {
						e1.printStackTrace();
					}
                    catch (TransformerException e1) {
						e1.printStackTrace();
					}
					//Validation
					ValidatorXML validatorXML = new ValidatorXML();
					boolean validationResult = false;
	                try {
	                    InputStream xml = new FileInputStream("../sipvs-fiit/data/file.xml");
	                    InputStream xsd = new FileInputStream("../sipvs-fiit/data/schema.xsd");
	                    validationResult = validatorXML.validateAgainstXSD(xml, xsd);
	                } catch (FileNotFoundException err) {
	                    System.err.println("Error: " + err.getMessage());
	                    err.printStackTrace();
	                }
	                
					if(validationResult == true) {
						JOptionPane.showMessageDialog(null, "File saved!", "Save XML file", JOptionPane.INFORMATION_MESSAGE);
						fileToSave = new File(fileName);
						
						try {
							transformer = transformerFactory.newTransformer();					
							DOMSource source = new DOMSource(document);                    
							StreamResult result = new StreamResult(fileToSave);
							transformer.transform(source, result);
						} 
	                    catch (TransformerConfigurationException e1) {
							e1.printStackTrace();
						}
	                    catch (TransformerException e1) {
							e1.printStackTrace();
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Invalid file!", "Save XML file", JOptionPane.INFORMATION_MESSAGE);
					}
                }
                

            }
        });

//		Save as PDF
        JButton savePDFButton = new JButton("Save as PDF");
        savePDFButton.setBounds(100, 400, 100, 25);
        contentPane.add(savePDFButton);

        // don' delete this
//		UtilDateModel model = new UtilDateModel();
//		//model.setDate(20,04,2014);
//		// Need this...
//		Properties p = new Properties();
//		p.put("text.today", "Today");
//		p.put("text.month", "Month");
//		p.put("text.year", "Year");
//		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
//		// Don't know about the formatter, but there it is...
//		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
//		datePicker.setBounds(10, 450, 100, 100);
//		contentPane.add(datePicker);
    }
}
