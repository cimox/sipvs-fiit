package app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.text.NumberFormat;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.text.NumberFormatter;
import javax.swing.JFormattedTextField;
import javax.swing.UIManager;
import java.awt.Color;

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

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setTitle("Po\u017Ei\u010Dov\u0148a kn\u00EDh");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 472, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Pou\u017E\u00EDvate\u013E", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 0, 255, 179);
		contentPane.add(panel);
		panel.setToolTipText("");
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
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
		panel_1.setBounds(231, 190, 204, 81);
		contentPane.add(panel_1);
		panel_1.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
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
		panel_2.setBounds(164, 297, 282, 49);
		contentPane.add(panel_2);
		panel_2.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
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
		
		JLabel lblUpozornenie = new JLabel("Upozornenie:");
		lblUpozornenie.setBounds(10, 370, 70, 14);
		contentPane.add(lblUpozornenie);
		
		textField = new JTextField();
		textField.setBounds(79, 367, 76, 20);
		contentPane.add(textField);
		textField.setColumns(10);
	}
}
