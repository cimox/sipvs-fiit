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
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.NumberFormat;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.NumberFormatter;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;

//import org.apache.fop.apps.FOPException;
//import org.apache.fop.apps.FOUserAgent;
//import org.apache.fop.apps.Fop;
//import org.apache.fop.apps.FopFactory;
//import org.apache.fop.apps.MimeConstants;

import javax.swing.JFormattedTextField;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import javax.swing.JLayeredPane;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.SpinnerNumberModel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

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
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	private JTextField textField_18;
	private JTextField textField_19;
	private JTextField textField_20;
	private JTextField textField_21;
	private JTextField textField_22;
	private JTextField textField_23;
	private JTextField textField_24;
	private JTextField textField_25;
	private JTextField textField_26;
	private JTextField textField_27;
	private JTextField textField_28;
	private JTextField textField_29;
	private JTextField textField_30;
	private JTextField textField_31;
	private JTextField textField_32;
	private JTextField textField_33;
	private JTextField textField_34;
	private JTextField textField_35;
	private JTextField textField_36;
	private JTextField textField_37;
	private JTextField textField_38;
	private JTextField textField_39;
	private JTextField textField_40;

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setTitle("Po\u017Ei\u010Dov\u0148a kn\u00EDh");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUpozornenie = new JLabel("Upozornenie (v dnoch):");
		lblUpozornenie.setBounds(524, 195, 150, 20);
		contentPane.add(lblUpozornenie);

		textField = new JTextField();
		textField.setBounds(674, 195, 100, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		// Save button
		JButton saveButton = new JButton("Save XML");
		saveButton.setBounds(524, 225, 100, 25);
		contentPane.add(saveButton);
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String firstName = textFieldMeno.getText();
				String lastName = textFieldPriezvisko.getText();
				String street = textFieldUlica.getText();
				Integer streetNumber = Integer.parseInt(textFieldCisloUlice.getText());
				String city = textFieldMesto.getText();
				String postalCode = textFieldPSC.getText();// Integer.parseInt(textFieldPSC.getText());
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

				BookDocument doc = new BookDocument(firstName, lastName, street, streetNumber, city, postalCode, state,
						email, notification, bookTitle, dateFrom, dateTo);
				Document document = doc.generateXML();
				File fileToSave;

				JFileChooser fileChooser = new JFileChooser();
				int userSelection = fileChooser.showSaveDialog(null);

				if (userSelection == JFileChooser.APPROVE_OPTION) {
					fileToSave = fileChooser.getSelectedFile();
					String fileName = fileToSave.getAbsolutePath() + ".xml";
					fileToSave = new File("../sipvs-fiit/data/file.xml");
					// System.out.println("Save as file: " +
					// fileToSave.getAbsolutePath());

					// write the content into xml file
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = null;
					try {
						transformer = transformerFactory.newTransformer();
						DOMSource source = new DOMSource(document);
						StreamResult result = new StreamResult(fileToSave);
						transformer.transform(source, result);
					} catch (TransformerConfigurationException e1) {
						e1.printStackTrace();
					} catch (TransformerException e1) {
						e1.printStackTrace();
					}
					// Validation
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

					if (validationResult == true) {
						JOptionPane.showMessageDialog(null, "File saved!", "Save XML file",
								JOptionPane.INFORMATION_MESSAGE);
						fileToSave = new File(fileName);

						try {
							transformer = transformerFactory.newTransformer();
							DOMSource source = new DOMSource(document);
							StreamResult result = new StreamResult(fileToSave);
							transformer.transform(source, result);
						} catch (TransformerConfigurationException e1) {
							e1.printStackTrace();
						} catch (TransformerException e1) {
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Invalid file!", "Save XML file",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}

			}
		});

		// Save as PDF
		JButton savePDFButton = new JButton("Save as PDF");
		savePDFButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				/*
				 * try { convertToPDF(); } catch (FOPException e) { // TODO
				 * Auto-generated catch block e.printStackTrace(); } catch
				 * (IOException e) { // TODO Auto-generated catch block
				 * e.printStackTrace(); } catch (TransformerException e) { //
				 * TODO Auto-generated catch block e.printStackTrace(); }
				 */
			}
		});
		savePDFButton.setBounds(674, 226, 100, 25);
		contentPane.add(savePDFButton);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 392, 250);
		contentPane.add(panel);
		panel.setBorder(new TitledBorder(null, "Pou\u017E\u00EDvate\u013E", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		panel.setToolTipText("");
		panel.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

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
		panel_1.setBounds(469, 66, 173, 75);
		contentPane.add(panel_1);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Kniha(y)", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		FormLayout fl_panel_1 = new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, });
		panel_1.setLayout(fl_panel_1);

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
		panel_2.setBounds(652, 66, 234, 49);
		contentPane.add(panel_2);
		panel_2.setBorder(new TitledBorder(null, "D\u00E1tum", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

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

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(10, 261, 898, 490);
		contentPane.add(layeredPane);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 0, 438, 84);
		layeredPane.add(panel_3);
		panel_3.setLayout(null);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Kniha(y)", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(0, 11, 179, 70);
		panel_3.add(panel_4);
		panel_4.setLayout(null);

		JLabel label_1 = new JLabel("Titulok knihy:");
		label_1.setBounds(6, 15, 80, 14);
		panel_4.add(label_1);

		textField_1 = new JTextField();
		textField_1.setBounds(89, 12, 80, 20);
		panel_4.add(textField_1);
		textField_1.setColumns(10);

		JLabel label_2 = new JLabel("ISBN:");
		label_2.setBounds(6, 43, 63, 14);
		panel_4.add(label_2);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(89, 40, 80, 20);
		panel_4.add(textField_2);

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "D\u00E1tum", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(189, 11, 239, 70);
		panel_3.add(panel_5);
		panel_5.setLayout(null);

		JLabel label_3 = new JLabel("Od");
		label_3.setBounds(10, 42, 24, 14);
		panel_5.add(label_3);

		textField_3 = new JTextField();
		textField_3.setBounds(34, 39, 82, 20);
		panel_5.add(textField_3);
		textField_3.setColumns(10);

		JLabel label_4 = new JLabel("do");
		label_4.setBounds(122, 42, 24, 14);
		panel_5.add(label_4);

		textField_4 = new JTextField();
		textField_4.setBounds(144, 39, 82, 20);
		panel_5.add(textField_4);
		textField_4.setColumns(10);

		JPanel panel_6 = new JPanel();
		panel_6.setVisible(false);
		panel_6.setBounds(0, 97, 438, 84);
		layeredPane.add(panel_6);
		panel_6.setLayout(null);

		JPanel panel_7 = new JPanel();
		panel_7.setLayout(null);
		panel_7.setBorder(new TitledBorder(null, "Kniha(y)", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_7.setBounds(0, 11, 179, 70);
		panel_6.add(panel_7);

		JLabel label_5 = new JLabel("Titulok knihy:");
		label_5.setBounds(6, 15, 80, 14);
		panel_7.add(label_5);

		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(89, 12, 80, 20);
		panel_7.add(textField_5);

		JLabel label_6 = new JLabel("ISBN:");
		label_6.setBounds(6, 43, 63, 14);
		panel_7.add(label_6);

		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(89, 40, 80, 20);
		panel_7.add(textField_6);

		JPanel panel_8 = new JPanel();
		panel_8.setLayout(null);
		panel_8.setBorder(new TitledBorder(null, "D\u00E1tum", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_8.setBounds(189, 11, 239, 70);
		panel_6.add(panel_8);

		JLabel label_7 = new JLabel("Od");
		label_7.setBounds(10, 42, 24, 14);
		panel_8.add(label_7);

		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(34, 39, 82, 20);
		panel_8.add(textField_7);

		JLabel label_8 = new JLabel("do");
		label_8.setBounds(122, 42, 24, 14);
		panel_8.add(label_8);

		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(144, 39, 82, 20);
		panel_8.add(textField_8);

		JPanel panel_9 = new JPanel();
		panel_9.setVisible(false);
		panel_9.setBounds(0, 189, 438, 84);
		layeredPane.add(panel_9);
		panel_9.setLayout(null);

		JPanel panel_10 = new JPanel();
		panel_10.setLayout(null);
		panel_10.setBorder(new TitledBorder(null, "Kniha(y)", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_10.setBounds(0, 11, 179, 70);
		panel_9.add(panel_10);

		JLabel label_9 = new JLabel("Titulok knihy:");
		label_9.setBounds(6, 15, 80, 14);
		panel_10.add(label_9);

		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(89, 12, 80, 20);
		panel_10.add(textField_9);

		JLabel label_10 = new JLabel("ISBN:");
		label_10.setBounds(6, 43, 63, 14);
		panel_10.add(label_10);

		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(89, 40, 80, 20);
		panel_10.add(textField_10);

		JPanel panel_11 = new JPanel();
		panel_11.setLayout(null);
		panel_11.setBorder(new TitledBorder(null, "D\u00E1tum", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_11.setBounds(189, 11, 239, 70);
		panel_9.add(panel_11);

		JLabel label_11 = new JLabel("Od");
		label_11.setBounds(10, 42, 24, 14);
		panel_11.add(label_11);

		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(34, 39, 82, 20);
		panel_11.add(textField_11);

		JLabel label_12 = new JLabel("do");
		label_12.setBounds(122, 42, 24, 14);
		panel_11.add(label_12);

		textField_12 = new JTextField();
		textField_12.setColumns(10);
		textField_12.setBounds(144, 39, 82, 20);
		panel_11.add(textField_12);

		JPanel panel_12 = new JPanel();
		panel_12.setVisible(false);
		panel_12.setBounds(0, 284, 438, 84);
		layeredPane.add(panel_12);
		panel_12.setLayout(null);

		JPanel panel_13 = new JPanel();
		panel_13.setLayout(null);
		panel_13.setBorder(new TitledBorder(null, "Kniha(y)", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_13.setBounds(0, 11, 179, 70);
		panel_12.add(panel_13);

		JLabel label_13 = new JLabel("Titulok knihy:");
		label_13.setBounds(6, 15, 80, 14);
		panel_13.add(label_13);

		textField_13 = new JTextField();
		textField_13.setColumns(10);
		textField_13.setBounds(89, 12, 80, 20);
		panel_13.add(textField_13);

		JLabel label_14 = new JLabel("ISBN:");
		label_14.setBounds(6, 43, 63, 14);
		panel_13.add(label_14);

		textField_14 = new JTextField();
		textField_14.setColumns(10);
		textField_14.setBounds(89, 40, 80, 20);
		panel_13.add(textField_14);

		JPanel panel_14 = new JPanel();
		panel_14.setLayout(null);
		panel_14.setBorder(new TitledBorder(null, "D\u00E1tum", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_14.setBounds(189, 11, 239, 70);
		panel_12.add(panel_14);

		JLabel label_15 = new JLabel("Od");
		label_15.setBounds(10, 42, 24, 14);
		panel_14.add(label_15);

		textField_15 = new JTextField();
		textField_15.setColumns(10);
		textField_15.setBounds(34, 39, 82, 20);
		panel_14.add(textField_15);

		JLabel label_16 = new JLabel("do");
		label_16.setBounds(122, 42, 24, 14);
		panel_14.add(label_16);

		textField_16 = new JTextField();
		textField_16.setColumns(10);
		textField_16.setBounds(144, 39, 82, 20);
		panel_14.add(textField_16);

		JPanel panel_15 = new JPanel();
		panel_15.setVisible(false);
		panel_15.setBounds(0, 379, 438, 84);
		layeredPane.add(panel_15);
		panel_15.setLayout(null);

		JPanel panel_16 = new JPanel();
		panel_16.setLayout(null);
		panel_16.setBorder(new TitledBorder(null, "Kniha(y)", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_16.setBounds(0, 11, 179, 70);
		panel_15.add(panel_16);

		JLabel label_17 = new JLabel("Titulok knihy:");
		label_17.setBounds(6, 15, 80, 14);
		panel_16.add(label_17);

		textField_17 = new JTextField();
		textField_17.setColumns(10);
		textField_17.setBounds(89, 12, 80, 20);
		panel_16.add(textField_17);

		JLabel label_18 = new JLabel("ISBN:");
		label_18.setBounds(6, 43, 63, 14);
		panel_16.add(label_18);

		textField_18 = new JTextField();
		textField_18.setColumns(10);
		textField_18.setBounds(89, 40, 80, 20);
		panel_16.add(textField_18);

		JPanel panel_17 = new JPanel();
		panel_17.setLayout(null);
		panel_17.setBorder(new TitledBorder(null, "D\u00E1tum", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_17.setBounds(189, 11, 239, 70);
		panel_15.add(panel_17);

		JLabel label_19 = new JLabel("Od");
		label_19.setBounds(10, 42, 24, 14);
		panel_17.add(label_19);

		textField_19 = new JTextField();
		textField_19.setColumns(10);
		textField_19.setBounds(34, 39, 82, 20);
		panel_17.add(textField_19);

		JLabel label_20 = new JLabel("do");
		label_20.setBounds(122, 42, 24, 14);
		panel_17.add(label_20);

		textField_20 = new JTextField();
		textField_20.setColumns(10);
		textField_20.setBounds(144, 39, 82, 20);
		panel_17.add(textField_20);

		JPanel panel_18 = new JPanel();
		panel_18.setVisible(false);
		panel_18.setBounds(448, 0, 438, 84);
		layeredPane.add(panel_18);
		panel_18.setLayout(null);

		JPanel panel_19 = new JPanel();
		panel_19.setLayout(null);
		panel_19.setBorder(new TitledBorder(null, "Kniha(y)", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_19.setBounds(0, 11, 179, 70);
		panel_18.add(panel_19);

		JLabel label_21 = new JLabel("Titulok knihy:");
		label_21.setBounds(6, 15, 80, 14);
		panel_19.add(label_21);

		textField_21 = new JTextField();
		textField_21.setColumns(10);
		textField_21.setBounds(89, 12, 80, 20);
		panel_19.add(textField_21);

		JLabel label_22 = new JLabel("ISBN:");
		label_22.setBounds(6, 43, 63, 14);
		panel_19.add(label_22);

		textField_22 = new JTextField();
		textField_22.setColumns(10);
		textField_22.setBounds(89, 40, 80, 20);
		panel_19.add(textField_22);

		JPanel panel_20 = new JPanel();
		panel_20.setLayout(null);
		panel_20.setBorder(new TitledBorder(null, "D\u00E1tum", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_20.setBounds(189, 11, 239, 70);
		panel_18.add(panel_20);

		JLabel label_23 = new JLabel("Od");
		label_23.setBounds(10, 42, 24, 14);
		panel_20.add(label_23);

		textField_23 = new JTextField();
		textField_23.setColumns(10);
		textField_23.setBounds(34, 39, 82, 20);
		panel_20.add(textField_23);

		JLabel label_24 = new JLabel("do");
		label_24.setBounds(122, 42, 24, 14);
		panel_20.add(label_24);

		textField_24 = new JTextField();
		textField_24.setColumns(10);
		textField_24.setBounds(144, 39, 82, 20);
		panel_20.add(textField_24);

		JPanel panel_21 = new JPanel();
		panel_21.setVisible(false);
		panel_21.setBounds(448, 97, 438, 84);
		layeredPane.add(panel_21);
		panel_21.setLayout(null);

		JPanel panel_22 = new JPanel();
		panel_22.setLayout(null);
		panel_22.setBorder(new TitledBorder(null, "Kniha(y)", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_22.setBounds(0, 11, 179, 70);
		panel_21.add(panel_22);

		JLabel label_25 = new JLabel("Titulok knihy:");
		label_25.setBounds(6, 15, 80, 14);
		panel_22.add(label_25);

		textField_25 = new JTextField();
		textField_25.setColumns(10);
		textField_25.setBounds(89, 12, 80, 20);
		panel_22.add(textField_25);

		JLabel label_26 = new JLabel("ISBN:");
		label_26.setBounds(6, 43, 63, 14);
		panel_22.add(label_26);

		textField_26 = new JTextField();
		textField_26.setColumns(10);
		textField_26.setBounds(89, 40, 80, 20);
		panel_22.add(textField_26);

		JPanel panel_23 = new JPanel();
		panel_23.setLayout(null);
		panel_23.setBorder(new TitledBorder(null, "D\u00E1tum", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_23.setBounds(189, 11, 239, 70);
		panel_21.add(panel_23);

		JLabel label_27 = new JLabel("Od");
		label_27.setBounds(10, 42, 24, 14);
		panel_23.add(label_27);

		textField_27 = new JTextField();
		textField_27.setColumns(10);
		textField_27.setBounds(34, 39, 82, 20);
		panel_23.add(textField_27);

		JLabel label_28 = new JLabel("do");
		label_28.setBounds(122, 42, 24, 14);
		panel_23.add(label_28);

		textField_28 = new JTextField();
		textField_28.setColumns(10);
		textField_28.setBounds(144, 39, 82, 20);
		panel_23.add(textField_28);

		JPanel panel_27 = new JPanel();
		panel_27.setVisible(false);

		JPanel panel_24 = new JPanel();
		panel_24.setVisible(false);
		panel_24.setBounds(448, 189, 438, 84);
		layeredPane.add(panel_24);
		panel_24.setLayout(null);

		JPanel panel_25 = new JPanel();
		panel_25.setLayout(null);
		panel_25.setBorder(new TitledBorder(null, "Kniha(y)", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_25.setBounds(0, 11, 179, 70);
		panel_24.add(panel_25);

		JLabel label_29 = new JLabel("Titulok knihy:");
		label_29.setBounds(6, 15, 80, 14);
		panel_25.add(label_29);

		textField_29 = new JTextField();
		textField_29.setColumns(10);
		textField_29.setBounds(89, 12, 80, 20);
		panel_25.add(textField_29);

		JLabel label_30 = new JLabel("ISBN:");
		label_30.setBounds(6, 43, 63, 14);
		panel_25.add(label_30);

		textField_30 = new JTextField();
		textField_30.setColumns(10);
		textField_30.setBounds(89, 40, 80, 20);
		panel_25.add(textField_30);

		JPanel panel_26 = new JPanel();
		panel_26.setLayout(null);
		panel_26.setBorder(new TitledBorder(null, "D\u00E1tum", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_26.setBounds(189, 11, 239, 70);
		panel_24.add(panel_26);

		JLabel label_31 = new JLabel("Od");
		label_31.setBounds(10, 42, 24, 14);
		panel_26.add(label_31);

		textField_31 = new JTextField();
		textField_31.setColumns(10);
		textField_31.setBounds(34, 39, 82, 20);
		panel_26.add(textField_31);

		JLabel label_32 = new JLabel("do");
		label_32.setBounds(122, 42, 24, 14);
		panel_26.add(label_32);

		textField_32 = new JTextField();
		textField_32.setColumns(10);
		textField_32.setBounds(144, 39, 82, 20);
		panel_26.add(textField_32);
		panel_27.setBounds(448, 284, 438, 84);
		layeredPane.add(panel_27);
		panel_27.setLayout(null);

		JPanel panel_28 = new JPanel();
		panel_28.setLayout(null);
		panel_28.setBorder(new TitledBorder(null, "Kniha(y)", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_28.setBounds(0, 11, 179, 70);
		panel_27.add(panel_28);

		JLabel label_33 = new JLabel("Titulok knihy:");
		label_33.setBounds(6, 15, 80, 14);
		panel_28.add(label_33);

		textField_33 = new JTextField();
		textField_33.setColumns(10);
		textField_33.setBounds(89, 12, 80, 20);
		panel_28.add(textField_33);

		JLabel label_34 = new JLabel("ISBN:");
		label_34.setBounds(6, 43, 63, 14);
		panel_28.add(label_34);

		textField_34 = new JTextField();
		textField_34.setColumns(10);
		textField_34.setBounds(89, 40, 80, 20);
		panel_28.add(textField_34);

		JPanel panel_29 = new JPanel();
		panel_29.setLayout(null);
		panel_29.setBorder(new TitledBorder(null, "D\u00E1tum", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_29.setBounds(189, 11, 239, 70);
		panel_27.add(panel_29);

		JLabel label_35 = new JLabel("Od");
		label_35.setBounds(10, 42, 24, 14);
		panel_29.add(label_35);

		textField_35 = new JTextField();
		textField_35.setColumns(10);
		textField_35.setBounds(34, 39, 82, 20);
		panel_29.add(textField_35);

		JLabel label_36 = new JLabel("do");
		label_36.setBounds(122, 42, 24, 14);
		panel_29.add(label_36);

		textField_36 = new JTextField();
		textField_36.setColumns(10);
		textField_36.setBounds(144, 39, 82, 20);
		panel_29.add(textField_36);

		JPanel panel_30 = new JPanel();
		panel_30.setVisible(false);
		panel_30.setBounds(448, 379, 438, 84);
		layeredPane.add(panel_30);
		panel_30.setLayout(null);

		JPanel panel_31 = new JPanel();
		panel_31.setLayout(null);
		panel_31.setBorder(new TitledBorder(null, "Kniha(y)", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_31.setBounds(0, 11, 179, 70);
		panel_30.add(panel_31);

		JLabel label_37 = new JLabel("Titulok knihy:");
		label_37.setBounds(6, 15, 80, 14);
		panel_31.add(label_37);

		textField_37 = new JTextField();
		textField_37.setColumns(10);
		textField_37.setBounds(89, 12, 80, 20);
		panel_31.add(textField_37);

		JLabel label_38 = new JLabel("ISBN:");
		label_38.setBounds(6, 43, 63, 14);
		panel_31.add(label_38);

		textField_38 = new JTextField();
		textField_38.setColumns(10);
		textField_38.setBounds(89, 40, 80, 20);
		panel_31.add(textField_38);

		JPanel panel_32 = new JPanel();
		panel_32.setLayout(null);
		panel_32.setBorder(new TitledBorder(null, "D\u00E1tum", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_32.setBounds(189, 11, 239, 70);
		panel_30.add(panel_32);

		JLabel label_39 = new JLabel("Od");
		label_39.setBounds(10, 42, 24, 14);
		panel_32.add(label_39);

		textField_39 = new JTextField();
		textField_39.setColumns(10);
		textField_39.setBounds(34, 39, 82, 20);
		panel_32.add(textField_39);

		JLabel label_40 = new JLabel("do");
		label_40.setBounds(122, 42, 24, 14);
		panel_32.add(label_40);

		textField_40 = new JTextField();
		textField_40.setColumns(10);
		textField_40.setBounds(144, 39, 82, 20);
		panel_32.add(textField_40);

		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 10, 1));
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Component[] com = layeredPane.getComponents();
				for (int i = 1; i <= com.length; i++) {
					if (i <= (Integer) spinner.getValue())
						((JPanel) com[i - 1]).setVisible(true);
					else
						((JPanel) com[i - 1]).setVisible(false);
				}
				/*
				 * for (int j = 0; j < com.length; j++) { Component[] components
				 * = ((JPanel) com[j]).getComponents(); Component[] titulky =
				 * ((JPanel) components[0]).getComponents(); Component[] datumy
				 * = ((JPanel) components[1]).getComponents(); String titulok =
				 * ((JTextField) titulky[1]).getText(); String isbn =
				 * ((JTextField) titulky[3]).getText(); String jeden_druhy =
				 * ((JTextField) datumy[1]).getText(); String druhy_datum =
				 * ((JTextField) datumy[3]).getText(); j++; }
				 */
			}
		});
		spinner.setBounds(412, 227, 36, 20);
		contentPane.add(spinner);

		JLabel lblPoetKnh = new JLabel("Po\u010Det kn\u00EDh");
		lblPoetKnh.setBounds(412, 198, 74, 14);
		contentPane.add(lblPoetKnh);
	}
}