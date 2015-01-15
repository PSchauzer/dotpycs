/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.dotPycsLib.Modelclasses.*;
import BL.ScrollPaneWithWatermark;
import BL.TableModelUebersichtBesetzt;
import BL.TableModelUebersichtFrei;
import BL.TableRendererUebersichtBesetzt;
import BL.TableRendererUebersichtFrei;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.ListSelectionModel.SINGLE_SELECTION;
import javax.swing.SpinnerDateModel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.text.html.HTMLEditorKit;
import com.dotPycsLib.*;
import java.sql.SQLException;
/**
 *
 * @author Corinna und PETER und BERNHARD
 */
public class DotPycsGUI extends JFrame {

    private TableModelUebersichtBesetzt ModelBesetzt = new TableModelUebersichtBesetzt();
    private TableModelUebersichtFrei ModelFrei = new TableModelUebersichtFrei();
    private int currentRow = -1;
    private SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
    private Date date = new Date();
    SpinnerDateModel smNorth = new SpinnerDateModel(date, null, null, Calendar.MINUTE);
    SpinnerDateModel smSouth = new SpinnerDateModel(date, null, null, Calendar.MINUTE);
    private DataBaseQueries dbq;
    public DotPycsGUI() throws HeadlessException {

        initComponents();

        //Fenstergröße setzen
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        //Verhalten bei Schließung von Programm
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Location setzen
        this.setLocationRelativeTo(null);

        //Titel setzen
        this.setTitle("DotPYCS - Guideverwaltung © Yvonne Hartner, Corinna Kindlhofer, Sarah Resch, Philipp Schauzer");

        try {
            dbq = new DataBaseQueries();
            dbq.toString();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        firstInit();
    }

    public void initComponents() {
        Container con = this.getContentPane();

        //UIManager-Styles setzen
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Komponenten erstellen
        tabPane = new JTabbedPane();
        paUebersicht = new JPanel();
        paVerwalten = new JPanel();
        paImpressum = new JPanel();
        paStatistik = new JPanel();
        tableBesetzt = new JTable();
        tableFrei = new JTable();
        popupCall = new JPopupMenu();
        menuItem1 = new JMenuItem();
        menuItem2 = new JMenuItem();
        menuItem3 = new JMenuItem();
        paUebersichtNorth = new JPanel();
        paUebersichtSouth = new JPanel();
        lbFilterFrei = new JLabel(" ");
        lbFilterBesetzt = new JLabel(" ");
        cbEdvFrei = new JCheckBox();
        cbAutMechaFrei = new JCheckBox();
        cbEdvBesetzt = new JCheckBox();
        cbAutMechaBesetzt = new JCheckBox();
        paVerwalten = new JPanel();
        paVerwaltenSouth = new JPanel();
        paVerwaltenNorth = new JPanel();
        btReadCSV = new JButton("CSV - Datei einlesen");
        paVerwaltenNorthNorth = new JPanel();
        paVerwaltenNorthSouth = new JPanel();
        lbVerwaltenSchuelerNorth = new JLabel("Schüler");
        coVerwaltenSchuelerNorth = new JComboBox();
        lbVerwaltenAbteilungNorth = new JLabel("Abteilung");
        coVerwaltenAbteilungNorth = new JComboBox();
        lbStartFuehrungNorth = new JLabel("Startzeit der Führung");
        jsStartFuehrungNorth = new JSpinner();
        btJetztNorth = new JButton("jetzt");
        btBuchenNorth = new JButton("Buchen");
        paVerwaltenPanel1North = new JPanel();
        paVerwaltenPanel2North = new JPanel();
        paVerwaltenPanel3North = new JPanel();
        paVerwaltenPanel4North = new JPanel();
        jsStartFuehrungNorth = new JSpinner(smNorth);
        lbVerwaltenSchuelerSouth = new JLabel("Schüler");
        coVerwaltenSchuelerSouth = new JComboBox();
        lbVerwaltenAbteilungSouth = new JLabel("Abteilung");
        coVerwaltenAbteilungSouth = new JComboBox();
        lbEndFuehrungSouth = new JLabel("Endzeit der Führung");
        jsEndFuehrungSouth = new JSpinner(smSouth);
        btBuchenSouth = new JButton("Buchen");
        paVerwaltenPanel1South = new JPanel();
        paVerwaltenPanel2South = new JPanel();
        paVerwaltenPanel3South = new JPanel();
        paVerwaltenPanel4South = new JPanel();
        btJetztSouth = new JButton("jetzt");
        paVerwaltenSouthSouth = new JPanel();
        txtGuideID = new JTextField();
        txtLastname = new JTextField();
        txtFirstname = new JTextField();
        coGuideClass = new JComboBox();
        txtSkypeID = new JTextField();
        txtTelNo = new JTextField();
        coDept = new JComboBox();
        lbGuideID = new JLabel();
        lbLastname = new JLabel();
        lbFirstname = new JLabel();
        lbGuideClass = new JLabel();
        lbSkypeID = new JLabel();
        lbTelNo = new JLabel();
        lbDept = new JLabel();
        btAdden = new JButton();
        lbPlatzhalter = new JLabel();

        txtGuideID.setEnabled(false);
        
        //Tabs auf der linken Seite anzeigen
        tabPane.setTabPlacement(JTabbedPane.LEFT);

        JEditorPane impressum = new JEditorPane();
        impressum.setEditorKit(new HTMLEditorKit());

        impressum.setText("<h1>hihi</h1>");

        //Komponenten auf Opaque(true) setzen
        tabPane.setOpaque(true);
        paUebersicht.setOpaque(true);
        paVerwalten.setOpaque(true);
        paImpressum.setOpaque(true);

        //Hintergrundfarben setzen
        tabPane.setBackground(Color.white);
        paUebersicht.setBackground(Color.white);
        paVerwalten.setBackground(Color.white);
        paImpressum.setBackground(Color.white);
        paStatistik.setOpaque(true);
        paStatistik.setBackground(Color.white);
        paUebersichtNorth.setOpaque(true);
        paUebersichtNorth.setBackground(Color.white);
        paUebersichtSouth.setOpaque(true);
        paUebersichtSouth.setBackground(Color.white);
        cbEdvFrei.setOpaque(true);
        cbEdvFrei.setBackground(Color.white);
        cbAutMechaFrei.setOpaque(true);
        cbAutMechaFrei.setBackground(Color.white);
        cbEdvBesetzt.setOpaque(true);
        cbEdvBesetzt.setBackground(Color.white);
        cbAutMechaBesetzt.setOpaque(true);
        cbAutMechaBesetzt.setBackground(Color.white);

        //set Checkboxes selected
        cbEdvBesetzt.setSelected(true);
        cbAutMechaBesetzt.setSelected(true);
        cbAutMechaFrei.setSelected(true);
        cbEdvFrei.setSelected(true);

        //Set Layouts
        paUebersicht.setLayout(new GridLayout(2, 1));
        paUebersichtNorth.setLayout(new BorderLayout());
        paUebersichtSouth.setLayout(new BorderLayout());
        lbFilterFrei.setLayout(new GridLayout(1, 15));
        lbFilterBesetzt.setLayout(new GridLayout(1, 15));
        paVerwalten.setLayout(new GridLayout(2, 1));
        paVerwaltenNorth.setLayout(new GridLayout(1, 2));
        paVerwaltenNorthNorth.setLayout(new GridLayout(14, 1));
        paVerwaltenPanel1North.setLayout(new GridLayout(1, 2));
        paVerwaltenPanel2North.setLayout(new GridLayout(1, 2));
        paVerwaltenPanel3North.setLayout(new GridLayout(1, 2));
        paVerwaltenPanel4North.setLayout(new GridLayout(1, 2));
        paVerwaltenNorthSouth.setLayout(new GridLayout(14, 1));
        paVerwaltenPanel1South.setLayout(new GridLayout(1, 2));
        paVerwaltenPanel2South.setLayout(new GridLayout(1, 2));
        paVerwaltenPanel3South.setLayout(new GridLayout(1, 2));
        paVerwaltenPanel4South.setLayout(new GridLayout(1, 2));

        //set checkboxen Text
        cbEdvFrei.setText("EDV");
        cbAutMechaFrei.setText("MECHA/AUT");
        cbEdvBesetzt.setText("EDV");
        cbAutMechaBesetzt.setText("MECHA/AUT");

        //config JSpinner
        JSpinner.DateEditor deNorth = new JSpinner.DateEditor(jsStartFuehrungNorth, "HH:mm");
        deNorth.getTextField().setEditable(false);
        jsStartFuehrungNorth.setEditor(deNorth);

        JSpinner.DateEditor deSouth = new JSpinner.DateEditor(jsEndFuehrungSouth, "HH:mm");
        deSouth.getTextField().setEditable(false);
        jsEndFuehrungSouth.setEditor(deSouth);

        //Adden von sämtlichen Komponenten
        tabPane.addTab("Übersicht", paUebersicht);
        tabPane.addTab("Verwalten", paVerwalten);
        tabPane.addTab("Statistik", paStatistik);
        tabPane.addTab("Impressum", paImpressum);

        //Bearbeiten Komponenten paVerwaltenSouth
        paVerwaltenSouth.setLayout(new BorderLayout());
        paVerwaltenSouthSouth.setLayout(new GridLayout(8, 2));

        //Beschriften von Komponenten auf paVerwaltenSouthSouth
        lbGuideID.setText("GuideId:");
        lbLastname.setText("Nachname:");
        lbFirstname.setText("Vorname:");
        lbGuideClass.setText("Klasse:");
        lbSkypeID.setText("SkypeID:");
        lbTelNo.setText("Tel.Nr.:");
        lbDept.setText("Abteilung:");
        btAdden.setText("Guide hinzufügen");

        impressum = new JEditorPane();
        //impressum.setBorder(new TitledBorder("Impressum"));
        impressum.setEditorKit(new HTMLEditorKit());

        impressum.setText("<br/><h1>Haftungshinweis:</h1><p>Trotz sorgfältiger inhaltlicher "
                + "Kontrolle übernehmen wir keine Haftung für die Inhalte. <br/>"
                + "</p><h1><br/>Copyright:</h1><p>© 2014 by Projektgruppe dotPYCS.<br/>Alle Rechte "
                + "vorbehalten. Die Inhalte dieses Programms dienen ausschließlich zur "
                + "Information <br/>und Verwaltung.Übernahme und Nutzung der Daten zu anderen "
                + "Zwecken bedarf der <br/>schriftlichen Zustimmung der Projektgruppe dotPYCS</p>"
                + "<br/><h1>Ansprechpartner:</h1><p>Sarah Resch<br/>Corinna Kindlhofer<br/>"
                + "Yvonne Hartner<br/>Phillipp Schauzer");

        //Comboboxen befüllen paVerwaltenSouthSouth
        for (int i = 1; i < 6; i++) {
            coGuideClass.addItem(i + "AHIF");
        }
        for (int i = 1; i < 6; i++) {
            coGuideClass.addItem(i + "BHIF");
        }
        for (int i = 1; i < 6; i++) {
            coGuideClass.addItem(i + "CHIF");
        }
        coGuideClass.addItem("1DHIF");
        for (int i = 1; i < 6; i++) {
            coGuideClass.addItem(i + "AHMBA");
        }
        for (int i = 1; i < 6; i++) {
            coGuideClass.addItem(i + "BHMBA");
        }
        for (int i = 1; i < 6; i++) {
            coGuideClass.addItem(i + "AHMEA");
        }

        coDept.addItem("EDV");
        coDept.addItem("MECHA/AUT");

        popupCall.add(menuItem1);
        popupCall.add(menuItem2);
        popupCall.add(menuItem3);
        paUebersicht.add(paUebersichtNorth);
        paUebersicht.add(paUebersichtSouth);
        con.add(tabPane);
        paImpressum.add(impressum);
        lbFilterFrei.add(cbEdvFrei);
        lbFilterFrei.add(cbAutMechaFrei);
        lbFilterBesetzt.add(cbEdvBesetzt);
        lbFilterBesetzt.add(cbAutMechaBesetzt);
        for (int i = 0; i < 15; i++) {
            lbFilterFrei.add(new JLabel());
        }
        for (int i = 0; i < 15; i++) {
            lbFilterBesetzt.add(new JLabel());
        }

        try {
            paUebersichtNorth.add(new ScrollPaneWithWatermark(tableFrei, new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "res" + File.separator + "frei.jpg").toURL()), BorderLayout.CENTER);
            paUebersichtSouth.add(new ScrollPaneWithWatermark(tableBesetzt, new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "res" + File.separator + "besetzt.jpg").toURL()), BorderLayout.CENTER);
        } catch (MalformedURLException ex) {
            Logger.getLogger(DotPycsGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        paUebersichtNorth.add(lbFilterFrei, BorderLayout.SOUTH);
        paUebersichtSouth.add(lbFilterBesetzt, BorderLayout.SOUTH);
        paVerwalten.add(paVerwaltenNorth);
        paVerwalten.add(paVerwaltenSouth);
        paVerwaltenNorth.add(paVerwaltenNorthNorth);
        paVerwaltenNorth.add(paVerwaltenNorthSouth);

        paVerwaltenNorthNorth.add(paVerwaltenPanel1North);
        paVerwaltenNorthNorth.add(paVerwaltenPanel2North);
        paVerwaltenNorthNorth.add(paVerwaltenPanel3North);
        paVerwaltenNorthNorth.add(btBuchenNorth);
        paVerwaltenPanel1North.add(lbVerwaltenSchuelerNorth);
        paVerwaltenPanel1North.add(coVerwaltenSchuelerNorth);
        paVerwaltenPanel2North.add(lbVerwaltenAbteilungNorth);
        paVerwaltenPanel2North.add(coVerwaltenAbteilungNorth);
        paVerwaltenPanel3North.add(lbStartFuehrungNorth);
        paVerwaltenPanel3North.add(paVerwaltenPanel4North);
        paVerwaltenPanel4North.add(btJetztNorth);
        paVerwaltenPanel4North.add(jsStartFuehrungNorth);
        for (int i = 0; i < 10; i++) {
            paVerwaltenNorthNorth.add(new JPanel());
        }

        paVerwaltenNorthSouth.add(paVerwaltenPanel1South);
        paVerwaltenNorthSouth.add(paVerwaltenPanel2South);
        paVerwaltenNorthSouth.add(paVerwaltenPanel3South);
        paVerwaltenNorthSouth.add(btBuchenSouth);
        paVerwaltenPanel1South.add(lbVerwaltenSchuelerSouth);
        paVerwaltenPanel1South.add(coVerwaltenSchuelerSouth);
        paVerwaltenPanel2South.add(lbVerwaltenAbteilungSouth);
        paVerwaltenPanel2South.add(coVerwaltenAbteilungSouth);
        paVerwaltenPanel3South.add(lbEndFuehrungSouth);
        paVerwaltenPanel3South.add(paVerwaltenPanel4South);
        paVerwaltenPanel4South.add(btJetztSouth);
        paVerwaltenPanel4South.add(jsEndFuehrungSouth);
        for (int i = 0; i < 10; i++) {
            paVerwaltenNorthSouth.add(new JPanel());
        }

        //Adden von Komponenten auf paVerwaltenSouthSouth
        paVerwaltenSouthSouth.add(lbGuideID);
        paVerwaltenSouthSouth.add(txtGuideID);
        paVerwaltenSouthSouth.add(lbLastname);
        paVerwaltenSouthSouth.add(txtLastname);
        paVerwaltenSouthSouth.add(lbFirstname);
        paVerwaltenSouthSouth.add(txtFirstname);
        paVerwaltenSouthSouth.add(lbGuideClass);
        paVerwaltenSouthSouth.add(coGuideClass);
        paVerwaltenSouthSouth.add(lbSkypeID);
        paVerwaltenSouthSouth.add(txtSkypeID);
        paVerwaltenSouthSouth.add(lbTelNo);
        paVerwaltenSouthSouth.add(txtTelNo);
        paVerwaltenSouthSouth.add(lbDept);
        paVerwaltenSouthSouth.add(coDept);
        paVerwaltenSouthSouth.add(lbPlatzhalter);
        paVerwaltenSouthSouth.add(btAdden);

        paVerwaltenSouth.add(paVerwaltenSouthSouth, BorderLayout.CENTER);

        //Tabs designen
        tabPane.setBackgroundAt(0, Color.white);
        tabPane.setBackgroundAt(1, Color.white);
        tabPane.setBackgroundAt(2, Color.white);
        tabPane.setBackgroundAt(3, Color.white);
        tabPane.setFont(new Font("Calibri Light", Font.PLAIN, 25));

        //paUebersicht designen
        tableFrei.getTableHeader().setFont(new Font("Calibri Light", Font.PLAIN, 22));
        tableBesetzt.getTableHeader().setFont(new Font("Calibri Light", Font.PLAIN, 22));

        //paVerwalten designen
        paVerwaltenNorth.setBorder(new TitledBorder("Buchen"));
        paVerwaltenSouth.setBorder(new TitledBorder("Guide hinzufügen"));
        paVerwaltenNorthNorth.setBorder(new TitledBorder("Einchecken"));
        paVerwaltenNorthSouth.setBorder(new TitledBorder("Auschecken"));

        //Spalten verschieben deaktivieren
        tableFrei.getTableHeader().setReorderingAllowed(false);
        tableBesetzt.getTableHeader().setReorderingAllowed(false);

        //Models bzw. Renderer setzen
        tableBesetzt.setModel(ModelBesetzt);
        tableFrei.setModel(ModelFrei);
        tableBesetzt.setDefaultRenderer(Object.class, new TableRendererUebersichtBesetzt());
        tableFrei.setDefaultRenderer(Object.class, new TableRendererUebersichtFrei());

        //Selektionsmodi setzen
        tableBesetzt.setSelectionMode(SINGLE_SELECTION);
        tableFrei.setSelectionMode(SINGLE_SELECTION);

        //Events setzen
        tableFrei.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton() == MouseEvent.BUTTON3) {
                    Point point = e.getPoint();
                    currentRow = tableFrei.rowAtPoint(point);
                    menuItem1.setText(ModelFrei.getGuideNameinSpecificRow(currentRow) + " eine Führungsanfrage schicken");
                    menuItem2.setText(ModelFrei.getGuideNameinSpecificRow(currentRow) + " anrufen");
                    menuItem3.setText(ModelFrei.getGuideNameinSpecificRow(currentRow) + "'s Telefonnummer anzeigen");
                    popupCall.show(tableFrei, e.getX(), e.getY());
                }
            }
        });

        menuItem1.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                System.out.println("not implemented yet");
            }

        });

        menuItem2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                System.out.println("not implemented yet");
            }

        });

        menuItem3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                String telNo = ModelFrei.getGuideTelNoInSpecificRow(currentRow);
                String name = ModelFrei.getGuideNameinSpecificRow(currentRow);
                JOptionPane.showMessageDialog(null, "Die Telefonnummer von " + name + " lautet " + telNo);
            }

        });

        btJetztNorth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                smNorth.setValue(new Date());
            }
        });

        btJetztSouth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                smSouth.setValue(new Date());
            }
        });

        btBuchenNorth.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(con, "Buchen - Einchecken not implemented yet");
            }
        });

        btBuchenSouth.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(con, "Buchen - Auschecken not implemented yet");
            }
        });
//--------------------------TABELLE TESTEN-----------------------------------------

        //ENIS AUF STRING ÄNDERN
        Guide g = new Guide("999", "Lushtaku", "Enis", "4AHIF", "geileralbaner", "066466666", "EDV");
        Guide g2 = new Guide("999", "Micevic", "Tin", "4BHIF", "geilerkroate", "066466666", "EDV");
        Guide g3 = new Guide("999", "Schmidt", "Marcel", "4CHIF", "geileroesterreicher", "066466666", "EDV");
        Guide g4 = new Guide("999", "Herbst", "Bernhard", "5AHMIA", "generellnetgeil", "066466666", "MECHA/AUT");
        ModelFrei.addGuide(g);
        ModelBesetzt.addGuide(g);
        ModelFrei.addGuide(g2);
        ModelBesetzt.addGuide(g2);
        ModelFrei.addGuide(g3);
        ModelBesetzt.addGuide(g3);
        ModelFrei.addGuide(g4);
        ModelBesetzt.addGuide(g4);

        //------------------------TESTEN ENDE---------------------------------------------
        cbAutMechaFrei.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterFrei(e);
            }
        });

        cbEdvFrei.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterFrei(e);
            }
        });

        cbEdvBesetzt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterBesetzt(e);
            }
        });

        cbAutMechaBesetzt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterBesetzt(e);
            }
        });

        //Guides hinzufügen
        btAdden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guideHinzufuegen(e);
            }
        });

        //Comboboxen geändert
        coGuideClass.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {

                if (coGuideClass.getSelectedItem().toString().contains("HIF")) {
                    coDept.setSelectedItem("EDV");
                } else {
                    coDept.setSelectedItem("MECHA/AUT");
                }
            }
        });

    }

    public void filterFrei(ActionEvent e) {
        String filter = "";

        if (cbEdvFrei.isSelected()) {
            filter = "EDV";
        }
        if (cbAutMechaFrei.isSelected()) {
            filter = "MECHA/AUT";
        }
        if (cbAutMechaFrei.isSelected() && cbEdvFrei.isSelected()) {
            filter = "all";
        }

        ModelFrei.filterListe(filter);
        tableFrei.updateUI();
    }

    public void filterBesetzt(ActionEvent e) {
        String filter = "";

        if (cbEdvBesetzt.isSelected()) {
            filter = "EDV";
        }
        if (cbAutMechaBesetzt.isSelected()) {
            filter = "MECHA/AUT";
        }
        if (cbAutMechaBesetzt.isSelected() && cbEdvBesetzt.isSelected()) {
            filter = "all";
        }

        ModelBesetzt.filterListe(filter);
        tableBesetzt.updateUI();
    }

    public void guideHinzufuegen(ActionEvent e) {

        try {

            String id = RFIDController.GetEduCardId();
            txtGuideID.setText(id);
            String nachname = txtLastname.getText();
            String vorname = txtFirstname.getText();
            String skypeName = txtSkypeID.getText();
            String telNr = txtTelNo.getText();
            String klasse = coGuideClass.getSelectedItem().toString();
            String abteilung = coDept.getSelectedItem().toString();

            
            Guide g = new Guide(id, nachname, vorname, klasse, skypeName, telNr, "EDV");
            ModelFrei.addGuide(g);
            ModelBesetzt.addGuide(g);
            
            dbq.addGuide(g);

        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }

    }

    public void firstInit() {
        try {
            JFileChooser fc = new JFileChooser();
            fc.showOpenDialog(null);
            File f = fc.getSelectedFile();
            if (f != null) {
                String pathname = f.getAbsolutePath();

                TableModelUebersichtFrei tmuf = new TableModelUebersichtFrei();

                tmuf.firstInit(pathname);
                tableFrei.updateUI();
            }
        } catch (IOException ex) {
            Logger.getLogger(DotPycsGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        new DotPycsGUI().setVisible(true);
    }

    private JTabbedPane tabPane;
    private JPanel paUebersicht;
    private JPanel paUebersichtNorth;
    private JPanel paUebersichtSouth;
    private JPanel paVerwalten;
    private JPanel paImpressum;
    private JPanel paStatistik;
    private JTable tableBesetzt;
    private JTable tableFrei;
    private JPopupMenu popupCall;
    private JMenuItem menuItem1;
    private JMenuItem menuItem2;
    private JMenuItem menuItem3;
    private JLabel lbFilterFrei;
    private JLabel lbFilterBesetzt;
    private JCheckBox cbEdvFrei;
    private JCheckBox cbAutMechaFrei;
    private JCheckBox cbEdvBesetzt;
    private JCheckBox cbAutMechaBesetzt;
    private JButton btReadCSV;
    private JPanel paVerwaltenNorth;
    private JPanel paVerwaltenSouth;
    private JPanel paVerwaltenNorthNorth;
    private JPanel paVerwaltenNorthSouth;
    private JLabel lbVerwaltenSchuelerNorth;
    private JComboBox coVerwaltenSchuelerNorth;
    private JLabel lbVerwaltenAbteilungNorth;
    private JComboBox coVerwaltenAbteilungNorth;
    private JLabel lbStartFuehrungNorth;
    private JSpinner jsStartFuehrungNorth;
    private JButton btJetztNorth;
    private JButton btBuchenNorth;
    private JPanel paVerwaltenPanel1North;
    private JPanel paVerwaltenPanel2North;
    private JPanel paVerwaltenPanel3North;
    private JPanel paVerwaltenPanel4North;
    private JLabel lbVerwaltenSchuelerSouth;
    private JComboBox coVerwaltenSchuelerSouth;
    private JLabel lbVerwaltenAbteilungSouth;
    private JComboBox coVerwaltenAbteilungSouth;
    private JLabel lbEndFuehrungSouth;
    private JSpinner jsEndFuehrungSouth;
    private JButton btBuchenSouth;
    private JPanel paVerwaltenPanel1South;
    private JPanel paVerwaltenPanel2South;
    private JPanel paVerwaltenPanel3South;
    private JPanel paVerwaltenPanel4South;
    private JButton btJetztSouth;
    private JPanel paVerwaltenSouthSouth;
    private JTextField txtGuideID;
    private JTextField txtLastname;
    private JTextField txtFirstname;
    private JComboBox coGuideClass;
    private JTextField txtSkypeID;
    private JTextField txtTelNo;
    private JComboBox coDept;
    private JLabel lbGuideID;
    private JLabel lbLastname;
    private JLabel lbFirstname;
    private JLabel lbGuideClass;
    private JLabel lbSkypeID;
    private JLabel lbTelNo;
    private JLabel lbDept;
    private JButton btAdden;
    private JLabel lbPlatzhalter;
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.dotPycsLib.Modelclasses.*;
import BL.ScrollPaneWithWatermark;
import BL.TableModelUebersichtBesetzt;
import BL.TableModelUebersichtFrei;
import BL.TableRendererUebersichtBesetzt;
import BL.TableRendererUebersichtFrei;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.ListSelectionModel.SINGLE_SELECTION;
import javax.swing.SpinnerDateModel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.text.html.HTMLEditorKit;

/**
 *
 * @author Corinna und PETER und BERNHARD
 */
public class DotPycsGUI extends JFrame {

    private TableModelUebersichtBesetzt ModelBesetzt = new TableModelUebersichtBesetzt();
    private TableModelUebersichtFrei ModelFrei = new TableModelUebersichtFrei();
    private int currentRow = -1;
    private SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
    private Date date = new Date();
    SpinnerDateModel smNorth = new SpinnerDateModel(date, null, null, Calendar.MINUTE);
    SpinnerDateModel smSouth = new SpinnerDateModel(date, null, null, Calendar.MINUTE);

    public DotPycsGUI() throws HeadlessException {

        initComponents();
        
        //Fenstergröße setzen
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        //Verhalten bei Schließung von Programm
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Location setzen
        this.setLocationRelativeTo(null);

        //Titel setzen
        this.setTitle("DotPYCS - Guideverwaltung © Yvonne Hartner, Corinna Kindlhofer, Sarah Resch, Philipp Schauzer");
        
        firstInit();
    }

    public void initComponents() {
        Container con = this.getContentPane();

        //UIManager-Styles setzen
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Komponenten erstellen
        tabPane = new JTabbedPane();
        paUebersicht = new JPanel();
        paVerwalten = new JPanel();
        paImpressum = new JPanel();
        paStatistik = new JPanel();
        tableBesetzt = new JTable();
        tableFrei = new JTable();
        popupCall = new JPopupMenu();
        menuItem1 = new JMenuItem();
        menuItem2 = new JMenuItem();
        menuItem3 = new JMenuItem();
        paUebersichtNorth = new JPanel();
        paUebersichtSouth = new JPanel();
        lbFilterFrei = new JLabel(" ");
        lbFilterBesetzt = new JLabel(" ");
        cbEdvFrei = new JCheckBox();
        cbAutMechaFrei = new JCheckBox();
        cbEdvBesetzt = new JCheckBox();
        cbAutMechaBesetzt = new JCheckBox();
        paVerwalten = new JPanel();
        paVerwaltenSouth = new JPanel();
        paVerwaltenNorth = new JPanel();
        btReadCSV = new JButton("CSV - Datei einlesen");
        paVerwaltenNorthNorth = new JPanel();
        paVerwaltenNorthSouth = new JPanel();
        lbVerwaltenSchuelerNorth = new JLabel("Schüler");
        coVerwaltenSchuelerNorth = new JComboBox();
        lbVerwaltenAbteilungNorth = new JLabel("Abteilung");
        coVerwaltenAbteilungNorth = new JComboBox();
        lbStartFuehrungNorth = new JLabel("Startzeit der Führung");
        jsStartFuehrungNorth = new JSpinner();
        btJetztNorth = new JButton("jetzt");
        btBuchenNorth = new JButton("Buchen");
        paVerwaltenPanel1North = new JPanel();
        paVerwaltenPanel2North = new JPanel();
        paVerwaltenPanel3North = new JPanel();
        paVerwaltenPanel4North = new JPanel();
        jsStartFuehrungNorth = new JSpinner(smNorth);
        lbVerwaltenSchuelerSouth = new JLabel("Schüler");
        coVerwaltenSchuelerSouth = new JComboBox();
        lbVerwaltenAbteilungSouth = new JLabel("Abteilung");
        coVerwaltenAbteilungSouth = new JComboBox();
        lbEndFuehrungSouth = new JLabel("Endzeit der Führung");
        jsEndFuehrungSouth = new JSpinner(smSouth);
        btBuchenSouth = new JButton("Buchen");
        paVerwaltenPanel1South = new JPanel();
        paVerwaltenPanel2South = new JPanel();
        paVerwaltenPanel3South = new JPanel();
        paVerwaltenPanel4South = new JPanel();
        btJetztSouth = new JButton("jetzt");
        paVerwaltenSouthSouth = new JPanel(); 
        txtGuideID = new JTextField(); 
        txtLastname = new JTextField(); 
        txtFirstname = new JTextField(); 
        coGuideClass = new JComboBox(); 
        txtSkypeID = new JTextField(); 
        txtTelNo = new JTextField(); 
        coDept = new JComboBox(); 
        lbGuideID = new JLabel(); 
        lbLastname = new JLabel(); 
        lbFirstname = new JLabel(); 
        lbGuideClass = new JLabel(); 
        lbSkypeID = new JLabel(); 
        lbTelNo = new JLabel(); 
        lbDept = new JLabel(); 
        btAdden = new JButton(); 
        lbPlatzhalter = new JLabel();
        

        //Tabs auf der linken Seite anzeigen
        tabPane.setTabPlacement(JTabbedPane.LEFT);

        JEditorPane impressum = new JEditorPane();
        impressum.setEditorKit(new HTMLEditorKit());

        impressum.setText("<h1>hihi</h1>");

        //Komponenten auf Opaque(true) setzen
        tabPane.setOpaque(true);
        paUebersicht.setOpaque(true);
        paVerwalten.setOpaque(true);
        paImpressum.setOpaque(true);

        //Hintergrundfarben setzen
        tabPane.setBackground(Color.white);
        paUebersicht.setBackground(Color.white);
        paVerwalten.setBackground(Color.white);
        paImpressum.setBackground(Color.white);
        paStatistik.setOpaque(true);
        paStatistik.setBackground(Color.white);
        paUebersichtNorth.setOpaque(true);
        paUebersichtNorth.setBackground(Color.white);
        paUebersichtSouth.setOpaque(true);
        paUebersichtSouth.setBackground(Color.white);
        cbEdvFrei.setOpaque(true);
        cbEdvFrei.setBackground(Color.white);
        cbAutMechaFrei.setOpaque(true);
        cbAutMechaFrei.setBackground(Color.white);
        cbEdvBesetzt.setOpaque(true);
        cbEdvBesetzt.setBackground(Color.white);
        cbAutMechaBesetzt.setOpaque(true);
        cbAutMechaBesetzt.setBackground(Color.white);
        
        //set Checkboxes selected
        cbEdvBesetzt.setSelected(true);
        cbAutMechaBesetzt.setSelected(true);
        cbAutMechaFrei.setSelected(true);
        cbEdvFrei.setSelected(true);

        //Set Layouts
        paUebersicht.setLayout(new GridLayout(2, 1));
        paUebersichtNorth.setLayout(new BorderLayout());
        paUebersichtSouth.setLayout(new BorderLayout());
        lbFilterFrei.setLayout(new GridLayout(1, 15));
        lbFilterBesetzt.setLayout(new GridLayout(1, 15));
        paVerwalten.setLayout(new GridLayout(2, 1));
        paVerwaltenNorth.setLayout(new GridLayout(1, 2));
        paVerwaltenNorthNorth.setLayout(new GridLayout(14, 1));
        paVerwaltenPanel1North.setLayout(new GridLayout(1, 2));
        paVerwaltenPanel2North.setLayout(new GridLayout(1, 2));
        paVerwaltenPanel3North.setLayout(new GridLayout(1, 2));
        paVerwaltenPanel4North.setLayout(new GridLayout(1, 2));
        paVerwaltenNorthSouth.setLayout(new GridLayout(14, 1));
        paVerwaltenPanel1South.setLayout(new GridLayout(1, 2));
        paVerwaltenPanel2South.setLayout(new GridLayout(1, 2));
        paVerwaltenPanel3South.setLayout(new GridLayout(1, 2));
        paVerwaltenPanel4South.setLayout(new GridLayout(1, 2));

        //set checkboxen Text
        cbEdvFrei.setText("EDV");
        cbAutMechaFrei.setText("MECHA/AUT");
        cbEdvBesetzt.setText("EDV");
        cbAutMechaBesetzt.setText("MECHA/AUT");

        //config JSpinner
        JSpinner.DateEditor deNorth = new JSpinner.DateEditor(jsStartFuehrungNorth, "HH:mm");
        deNorth.getTextField().setEditable(false);
        jsStartFuehrungNorth.setEditor(deNorth);

        JSpinner.DateEditor deSouth = new JSpinner.DateEditor(jsEndFuehrungSouth, "HH:mm");
        deSouth.getTextField().setEditable(false);
        jsEndFuehrungSouth.setEditor(deSouth);

        //Adden von sämtlichen Komponenten
        tabPane.addTab("Übersicht", paUebersicht);
        tabPane.addTab("Verwalten", paVerwalten);
        tabPane.addTab("Statistik", paStatistik);
        tabPane.addTab("Impressum", paImpressum);
        
        //Bearbeiten Komponenten paVerwaltenSouth
        paVerwaltenSouth.setLayout(new BorderLayout());
        paVerwaltenSouthSouth.setLayout(new GridLayout(8,2));
        
        //Beschriften von Komponenten auf paVerwaltenSouthSouth
        lbGuideID.setText("GuideId:");
        lbLastname.setText("Nachname:");
        lbFirstname.setText("Vorname:");
        lbGuideClass.setText("Klasse:");      
        lbSkypeID.setText("SkypeID:");        
        lbTelNo.setText("Tel.Nr.:");
        lbDept.setText("Abteilung:");     
        btAdden.setText("Guide hinzufügen"); 
        
        impressum = new JEditorPane();
        //impressum.setBorder(new TitledBorder("Impressum"));
        impressum.setEditorKit(new HTMLEditorKit());
        
        
        impressum.setText("<br/><h1>Haftungshinweis:</h1><p>Trotz sorgfältiger inhaltlicher "
                + "Kontrolle übernehmen wir keine Haftung für die Inhalte. <br/>"
                + "</p><h1><br/>Copyright:</h1><p>© 2014 by Projektgruppe dotPYCS.<br/>Alle Rechte "
                + "vorbehalten. Die Inhalte dieses Programms dienen ausschließlich zur "
                + "Information <br/>und Verwaltung.Übernahme und Nutzung der Daten zu anderen "
                + "Zwecken bedarf der <br/>schriftlichen Zustimmung der Projektgruppe dotPYCS</p>"
                + "<br/><h1>Ansprechpartner:</h1><p>Sarah Resch<br/>Corinna Kindlhofer<br/>" 
                +"Yvonne Hartner<br/>Phillipp Schauzer");
        
        //Comboboxen befüllen paVerwaltenSouthSouth
    
        for (int i = 1; i < 6; i++) 
        {
            coGuideClass.addItem(i+"AHIF");
        }
        for (int i = 1; i < 6; i++) 
        {
            coGuideClass.addItem(i+"BHIF");
        }
        for (int i = 1; i < 6; i++) 
        {
            coGuideClass.addItem(i+"CHIF");
        }
        coGuideClass.addItem("1DHIF");
        for (int i = 1; i < 6; i++) 
        {
            coGuideClass.addItem(i+"AHMBA");
        }
        for (int i = 1; i < 6; i++) 
        {
            coGuideClass.addItem(i+"BHMBA");
        }
        for (int i = 1; i < 6; i++) 
        {
            coGuideClass.addItem(i+"AHMEA");
        }
        
        coDept.addItem("EDV");
        coDept.addItem("MECHA/AUT");

        popupCall.add(menuItem1);
        popupCall.add(menuItem2);
        popupCall.add(menuItem3);
        paUebersicht.add(paUebersichtNorth);
        paUebersicht.add(paUebersichtSouth);
        con.add(tabPane);
        paImpressum.add(impressum);
        lbFilterFrei.add(cbEdvFrei);
        lbFilterFrei.add(cbAutMechaFrei);
        lbFilterBesetzt.add(cbEdvBesetzt);
        lbFilterBesetzt.add(cbAutMechaBesetzt);
        for (int i = 0; i < 15; i++) {
            lbFilterFrei.add(new JLabel());
        }
        for (int i = 0; i < 15; i++) {
            lbFilterBesetzt.add(new JLabel());
        }

        try {
            paUebersichtNorth.add(new ScrollPaneWithWatermark(tableFrei, new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "res" + File.separator + "frei.jpg").toURL()), BorderLayout.CENTER);
            paUebersichtSouth.add(new ScrollPaneWithWatermark(tableBesetzt, new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "res" + File.separator + "besetzt.jpg").toURL()), BorderLayout.CENTER);
        } catch (MalformedURLException ex) {
            Logger.getLogger(DotPycsGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        paUebersichtNorth.add(lbFilterFrei, BorderLayout.SOUTH);
        paUebersichtSouth.add(lbFilterBesetzt, BorderLayout.SOUTH);
        paVerwalten.add(paVerwaltenNorth);
        paVerwalten.add(paVerwaltenSouth);
        paVerwaltenNorth.add(paVerwaltenNorthNorth);
        paVerwaltenNorth.add(paVerwaltenNorthSouth);

        paVerwaltenNorthNorth.add(paVerwaltenPanel1North);
        paVerwaltenNorthNorth.add(paVerwaltenPanel2North);
        paVerwaltenNorthNorth.add(paVerwaltenPanel3North);
        paVerwaltenNorthNorth.add(btBuchenNorth);
        paVerwaltenPanel1North.add(lbVerwaltenSchuelerNorth);
        paVerwaltenPanel1North.add(coVerwaltenSchuelerNorth);
        paVerwaltenPanel2North.add(lbVerwaltenAbteilungNorth);
        paVerwaltenPanel2North.add(coVerwaltenAbteilungNorth);
        paVerwaltenPanel3North.add(lbStartFuehrungNorth);
        paVerwaltenPanel3North.add(paVerwaltenPanel4North);
        paVerwaltenPanel4North.add(btJetztNorth);
        paVerwaltenPanel4North.add(jsStartFuehrungNorth);
        for (int i = 0; i < 10; i++) {
            paVerwaltenNorthNorth.add(new JPanel());
        }

        paVerwaltenNorthSouth.add(paVerwaltenPanel1South);
        paVerwaltenNorthSouth.add(paVerwaltenPanel2South);
        paVerwaltenNorthSouth.add(paVerwaltenPanel3South);
        paVerwaltenNorthSouth.add(btBuchenSouth);
        paVerwaltenPanel1South.add(lbVerwaltenSchuelerSouth);
        paVerwaltenPanel1South.add(coVerwaltenSchuelerSouth);
        paVerwaltenPanel2South.add(lbVerwaltenAbteilungSouth);
        paVerwaltenPanel2South.add(coVerwaltenAbteilungSouth);
        paVerwaltenPanel3South.add(lbEndFuehrungSouth);
        paVerwaltenPanel3South.add(paVerwaltenPanel4South);
        paVerwaltenPanel4South.add(btJetztSouth);
        paVerwaltenPanel4South.add(jsEndFuehrungSouth);
        for (int i = 0; i < 10; i++) {
            paVerwaltenNorthSouth.add(new JPanel());
        }
        
         //Adden von Komponenten auf paVerwaltenSouthSouth
        paVerwaltenSouthSouth.add(lbGuideID);
        paVerwaltenSouthSouth.add(txtGuideID);
        paVerwaltenSouthSouth.add(lbLastname);
        paVerwaltenSouthSouth.add(txtLastname);
        paVerwaltenSouthSouth.add(lbFirstname);
        paVerwaltenSouthSouth.add(txtFirstname);
        paVerwaltenSouthSouth.add(lbGuideClass);
        paVerwaltenSouthSouth.add(coGuideClass);
        paVerwaltenSouthSouth.add(lbSkypeID);
        paVerwaltenSouthSouth.add(txtSkypeID);
        paVerwaltenSouthSouth.add(lbTelNo);
        paVerwaltenSouthSouth.add(txtTelNo);
        paVerwaltenSouthSouth.add(lbDept);
        paVerwaltenSouthSouth.add(coDept);
        paVerwaltenSouthSouth.add(lbPlatzhalter);
        paVerwaltenSouthSouth.add(btAdden);
        
        paVerwaltenSouth.add(paVerwaltenSouthSouth, BorderLayout.CENTER);

        //Tabs designen
        tabPane.setBackgroundAt(0, Color.white);
        tabPane.setBackgroundAt(1, Color.white);
        tabPane.setBackgroundAt(2, Color.white);
        tabPane.setBackgroundAt(3, Color.white);
        tabPane.setFont(new Font("Calibri Light", Font.PLAIN, 25));

        //paUebersicht designen
        tableFrei.getTableHeader().setFont(new Font("Calibri Light", Font.PLAIN, 22));
        tableBesetzt.getTableHeader().setFont(new Font("Calibri Light", Font.PLAIN, 22));

        //paVerwalten designen
        paVerwaltenNorth.setBorder(new TitledBorder("Buchen"));
        paVerwaltenSouth.setBorder(new TitledBorder("Guide hinzufügen"));
        paVerwaltenNorthNorth.setBorder(new TitledBorder("Einchecken"));
        paVerwaltenNorthSouth.setBorder(new TitledBorder("Auschecken"));

        //Spalten verschieben deaktivieren
        tableFrei.getTableHeader().setReorderingAllowed(false);
        tableBesetzt.getTableHeader().setReorderingAllowed(false);

        //Models bzw. Renderer setzen
        tableBesetzt.setModel(ModelBesetzt);
        tableFrei.setModel(ModelFrei);
        tableBesetzt.setDefaultRenderer(Object.class, new TableRendererUebersichtBesetzt());
        tableFrei.setDefaultRenderer(Object.class, new TableRendererUebersichtFrei());

        //Selektionsmodi setzen
        tableBesetzt.setSelectionMode(SINGLE_SELECTION);
        tableFrei.setSelectionMode(SINGLE_SELECTION);

        //Events setzen
        tableFrei.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton() == MouseEvent.BUTTON3) {
                    Point point = e.getPoint();
                    currentRow = tableFrei.rowAtPoint(point);
                    menuItem1.setText(ModelFrei.getGuideNameinSpecificRow(currentRow) + " eine Führungsanfrage schicken");
                    menuItem2.setText(ModelFrei.getGuideNameinSpecificRow(currentRow) + " anrufen");
                    menuItem3.setText(ModelFrei.getGuideNameinSpecificRow(currentRow) + "'s Telefonnummer anzeigen");
                    popupCall.show(tableFrei, e.getX(), e.getY());
                }
            }
        });

        menuItem1.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                System.out.println("not implemented yet");
            }

        });

        menuItem2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                System.out.println("not implemented yet");
            }
        });

        menuItem3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                String telNo = ModelFrei.getGuideTelNoInSpecificRow(currentRow);
                String name = ModelFrei.getGuideNameinSpecificRow(currentRow);
                JOptionPane.showMessageDialog(null, "Die Telefonnummer von " + name + " lautet " + telNo);
            }
        });

        btJetztNorth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                smNorth.setValue(new Date());
            }
        });

        btJetztSouth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                smSouth.setValue(new Date());
            }
        });

        btBuchenNorth.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(con, "Buchen - Einchecken not implemented yet");
            }
        });

        btBuchenSouth.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(con, "Buchen - Auschecken not implemented yet");
            }
        });
//--------------------------TABELLE TESTEN-----------------------------------------
        
        //ENIS AUF STRING ÄNDERN
        Guide g = new Guide("999", "Lushtaku", "Enis", "4AHIF", "geileralbaner", "066466666", "EDV");
        Guide g2 = new Guide("999", "Micevic", "Tin", "4BHIF", "geilerkroate", "066466666", "EDV");
        Guide g3 = new Guide("999", "Schmidt", "Marcel", "4CHIF", "geileroesterreicher", "066466666", "EDV");
        Guide g4 = new Guide("999", "Herbst", "Bernhard", "5AHMIA", "generellnetgeil", "066466666", "MECHA/AUT");
        ModelFrei.addGuide(g);
        ModelBesetzt.addGuide(g);
        ModelFrei.addGuide(g2);
        ModelBesetzt.addGuide(g2);
        ModelFrei.addGuide(g3);
        ModelBesetzt.addGuide(g3);
        ModelFrei.addGuide(g4);
        ModelBesetzt.addGuide(g4);
        
 //------------------------TESTEN ENDE---------------------------------------------
        
        cbAutMechaFrei.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterFrei(e);
            }
        });

        cbEdvFrei.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterFrei(e);
            }
        });

        

        cbEdvBesetzt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterBesetzt(e);
            }
        });

        cbAutMechaBesetzt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterBesetzt(e);
            }
        });
        
        //Guides hinzufügen
        btAdden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 guideHinzufuegen(e); 
            }
        });
        
        //Comboboxen geändert
        coGuideClass.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
            
            if(coGuideClass.getSelectedItem().toString().contains("HIF"))
            {
                coDept.setSelectedItem("EDV");
            }
            else
            {
                coDept.setSelectedItem("MECHA/AUT");
            }
            }
        });

    }

    public void filterFrei(ActionEvent e) 
    {
        String filter = "";
        
        if (cbEdvFrei.isSelected()) 
        {
            filter = "EDV";
        }
        if (cbAutMechaFrei.isSelected()) 
        {
            filter = "MECHA/AUT";
        }
        if (cbAutMechaFrei.isSelected() && cbEdvFrei.isSelected())
        {
            filter = "all";
        }
        
        
        ModelFrei.filterListe(filter);
        tableFrei.updateUI();
    }
    
    public void filterBesetzt(ActionEvent e) 
    {
        String filter = "";
        
        if (cbEdvBesetzt.isSelected()) 
        {
            filter = "EDV";
        }
        if (cbAutMechaBesetzt.isSelected()) 
        {
            filter = "MECHA/AUT";
        }
        if (cbAutMechaBesetzt.isSelected() && cbEdvBesetzt.isSelected())
        {
            filter = "";
        }
        
        
        
        ModelBesetzt.filterListe(filter);
        tableBesetzt.updateUI();
    }
    
     public void guideHinzufuegen(ActionEvent e)
    {
        
        try 
        {
            
        
            String id = txtGuideID.getText(); 
            String nachname = txtLastname.getText();
            String vorname = txtFirstname.getText(); 
            String skypeName = txtSkypeID.getText().toString(); 
            String telNr = txtTelNo.getText(); 
            String klasse = coGuideClass.getSelectedItem().toString(); 
            String abteilung = coDept.getSelectedItem().toString(); 
            
            if(abteilung.contains("EDV"))
            {
                Guide g = new Guide(id, nachname, vorname, klasse, skypeName, telNr, "EDV");
                ModelFrei.addGuide(g);
                ModelBesetzt.addGuide(g);
            }
            else
            {
                Guide g = new Guide(id, nachname, vorname, klasse, skypeName, telNr, "MECHA");
                ModelFrei.addGuide(g);
                ModelBesetzt.addGuide(g);
            }
            
            
        } 
        catch (Exception ex) 
        {
            System.out.println(ex.toString());
        }
        
        
    }
     
     
     public void firstInit()
     {
        try {
            JFileChooser fc = new JFileChooser();
            fc.showOpenDialog(null);
            String pathname = fc.getSelectedFile().getAbsolutePath();
            TableModelUebersichtFrei tmuf = new TableModelUebersichtFrei();
            
            tmuf.firstInit(pathname);
            tableFrei.updateUI();
        } catch (IOException ex) {
            Logger.getLogger(DotPycsGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
     }

    public static void main(String[] args) {
        new DotPycsGUI().setVisible(true);
    }

    private JTabbedPane tabPane;
    private JPanel paUebersicht;
    private JPanel paUebersichtNorth;
    private JPanel paUebersichtSouth;
    private JPanel paVerwalten;
    private JPanel paImpressum;
    private JPanel paStatistik;
    private JTable tableBesetzt;
    private JTable tableFrei;
    private JPopupMenu popupCall;
    private JMenuItem menuItem1;
    private JMenuItem menuItem2;
    private JMenuItem menuItem3;
    private JLabel lbFilterFrei;
    private JLabel lbFilterBesetzt;
    private JCheckBox cbEdvFrei;
    private JCheckBox cbAutMechaFrei;
    private JCheckBox cbEdvBesetzt;
    private JCheckBox cbAutMechaBesetzt;
    private JButton btReadCSV;
    private JPanel paVerwaltenNorth;
    private JPanel paVerwaltenSouth;
    private JPanel paVerwaltenNorthNorth;
    private JPanel paVerwaltenNorthSouth;
    private JLabel lbVerwaltenSchuelerNorth;
    private JComboBox coVerwaltenSchuelerNorth;
    private JLabel lbVerwaltenAbteilungNorth;
    private JComboBox coVerwaltenAbteilungNorth;
    private JLabel lbStartFuehrungNorth;
    private JSpinner jsStartFuehrungNorth;
    private JButton btJetztNorth;
    private JButton btBuchenNorth;
    private JPanel paVerwaltenPanel1North;
    private JPanel paVerwaltenPanel2North;
    private JPanel paVerwaltenPanel3North;
    private JPanel paVerwaltenPanel4North;
    private JLabel lbVerwaltenSchuelerSouth;
    private JComboBox coVerwaltenSchuelerSouth;
    private JLabel lbVerwaltenAbteilungSouth;
    private JComboBox coVerwaltenAbteilungSouth;
    private JLabel lbEndFuehrungSouth;
    private JSpinner jsEndFuehrungSouth;
    private JButton btBuchenSouth;
    private JPanel paVerwaltenPanel1South;
    private JPanel paVerwaltenPanel2South;
    private JPanel paVerwaltenPanel3South;
    private JPanel paVerwaltenPanel4South;
    private JButton btJetztSouth;
    private JPanel paVerwaltenSouthSouth; 
    private JTextField txtGuideID; 
    private JTextField txtLastname; 
    private JTextField txtFirstname; 
    private JComboBox coGuideClass; 
    private JTextField txtSkypeID; 
    private JTextField txtTelNo; 
    private JComboBox coDept; 
    private JLabel lbGuideID; 
    private JLabel lbLastname; 
    private JLabel lbFirstname; 
    private JLabel lbGuideClass; 
    private JLabel lbSkypeID; 
    private JLabel lbTelNo; 
    private JLabel lbDept; 
    private JButton btAdden; 
    private JLabel lbPlatzhalter; 
}
