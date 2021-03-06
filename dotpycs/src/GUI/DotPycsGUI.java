/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.dotPycsLib.Modelclasses.*;
import BL.ScrollPaneWithWatermark;
import BL.SkypeThread;
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
import javax.swing.SpinnerDateModel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.text.html.HTMLEditorKit;
import com.dotPycsLib.*;
import com.skype.SkypeException;
import java.awt.Toolkit;
import java.sql.SQLException;
import javax.swing.ImageIcon;

/**
 *
 * @author Corinna und PETER und BERNHARD
 */

//trallala
public class DotPycsGUI extends JFrame {

    private TableModelUebersichtBesetzt ModelBesetzt = new TableModelUebersichtBesetzt();
    private TableModelUebersichtFrei ModelFrei = new TableModelUebersichtFrei();
    private int currentRow = -1;
    private SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
    private Date date = new Date();
    SpinnerDateModel smNorth = new SpinnerDateModel(date, null, null, Calendar.MINUTE);
    SpinnerDateModel smSouth = new SpinnerDateModel(date, null, null, Calendar.MINUTE);
    private DataBaseQueries dbq;
    private final static int size = 20;
    

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
        lbVerwaltenSchuelerNorth = new JLabel("Schüler:");
        coVerwaltenSchuelerNorth = new JComboBox();
        lbVerwaltenAbteilungNorth = new JLabel("Abteilung:");
        coVerwaltenAbteilungNorth = new JComboBox();
        lbStartFuehrungNorth = new JLabel("Startzeit der Führung:");
        jsStartFuehrungNorth = new JSpinner();
        btJetztNorth = new JButton("jetzt");
        btBuchenNorth = new JButton("Buchen");
        paVerwaltenPanel1North = new JPanel();
        paVerwaltenPanel2North = new JPanel();
        paVerwaltenPanel3North = new JPanel();
        paVerwaltenPanel4North = new JPanel();
        jsStartFuehrungNorth = new JSpinner(smNorth);
        lbVerwaltenSchuelerSouth = new JLabel("Schüler:");
        coVerwaltenSchuelerSouth = new JComboBox();
        lbVerwaltenAbteilungSouth = new JLabel("Abteilung:");
        coVerwaltenAbteilungSouth = new JComboBox();
        lbEndFuehrungSouth = new JLabel("Endzeit der Führung:");
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
        paVerwaltenSouthEast = new JPanel(); 
  
        lbVerwaltenSouthEast = new JLabel();
        
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
        paVerwaltenNorthNorth.setLayout(new GridLayout(8, 1));
        paVerwaltenPanel1North.setLayout(new GridLayout(1, 2));
        paVerwaltenPanel2North.setLayout(new GridLayout(1, 2));
        paVerwaltenPanel3North.setLayout(new GridLayout(1, 2));
        paVerwaltenPanel4North.setLayout(new GridLayout(1, 2));
        paVerwaltenNorthSouth.setLayout(new GridLayout(8, 1));
        paVerwaltenPanel1South.setLayout(new GridLayout(1, 2));
        paVerwaltenPanel2South.setLayout(new GridLayout(1, 2));
        paVerwaltenPanel3South.setLayout(new GridLayout(1, 2));
        paVerwaltenPanel4South.setLayout(new GridLayout(1, 2));

        
        //Bild Setzen
        ImageIcon img = new ImageIcon(System.getProperty("user.dir")+File.separator+"src"+ File.separator+"res"+File.separator+"logo.png");
        lbVerwaltenSouthEast = new JLabel(img);
        this.setIconImage(img.getImage());
        
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
        tabPane.addTab("Impressum", paImpressum);

        //Bearbeiten Komponenten paVerwaltenSouth
        paVerwaltenSouth.setLayout(new GridLayout(1,2));
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
        
        lbGuideID.setFont(new Font("Calibri Light", Font.BOLD, size)); //asdasdasddddddddddddddddddddddddddddddddddddddd
        lbLastname.setFont(new Font("Calibri Light", Font.BOLD, size));
        lbFirstname.setFont(new Font("Calibri Light", Font.BOLD, size));
        lbGuideClass.setFont(new Font("Calibri Light", Font.BOLD, size));
        lbSkypeID.setFont(new Font("Calibri Light", Font.BOLD, size));
        lbTelNo.setFont(new Font("Calibri Light", Font.BOLD, size));
        lbDept.setFont(new Font("Calibri Light", Font.BOLD, size));
        btAdden.setFont(new Font("Calibri Light", Font.BOLD, size));
        coDept.setFont(new Font("Calibri Light", Font.PLAIN, size));
        coGuideClass.setFont(new Font("Calibri Light", Font.PLAIN, size));
        txtLastname.setFont(new Font("Calibri Light", Font.PLAIN, size));
        txtFirstname.setFont(new Font("Calibri Light", Font.PLAIN, size));
        txtSkypeID.setFont(new Font("Calibri Light", Font.PLAIN, size));
        txtTelNo.setFont(new Font("Calibri Light", Font.PLAIN, size));
        
        
        impressum = new JEditorPane();
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

        paVerwaltenNorthNorth.add(paVerwaltenPanel1North); //saaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
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
        
        //Font setzen paVerwaltenNorthNorth
        lbVerwaltenSchuelerNorth.setFont(new Font("Calibri Light", Font.BOLD, size));
        lbVerwaltenAbteilungNorth.setFont(new Font("Calibri Light", Font.BOLD, size));
        lbStartFuehrungNorth.setFont(new Font("Calibri Light", Font.BOLD, size));        
        btJetztNorth.setFont(new Font("Calibri Light", Font.PLAIN, size));        
        btBuchenNorth.setFont(new Font("Calibri Light", Font.BOLD, size));               
        coVerwaltenAbteilungNorth.setFont(new Font("Calibri Light", Font.PLAIN, size));        
        coVerwaltenSchuelerNorth.setFont(new Font("Calibri Light", Font.PLAIN, size));     
        jsStartFuehrungNorth.setFont(new Font("Calibri Light", Font.PLAIN, size));     
                
        for (int i = 0; i < 4; i++) {
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
        
        //Font setzten paVerwaltenSouth
        btBuchenSouth.setFont(new Font("Calibri Light", Font.BOLD, size));
        lbVerwaltenSchuelerSouth.setFont(new Font("Calibri Light", Font.BOLD, size));
        coVerwaltenSchuelerSouth.setFont(new Font("Calibri Light", Font.PLAIN, size));    
        lbVerwaltenAbteilungSouth.setFont(new Font("Calibri Light", Font.BOLD, size));
        coVerwaltenAbteilungSouth.setFont(new Font("Calibri Light", Font.PLAIN, size));
        lbEndFuehrungSouth.setFont(new Font("Calibri Light", Font.BOLD, size));
        btJetztSouth.setFont(new Font("Calibri Light", Font.PLAIN, size));
        jsEndFuehrungSouth.setFont(new Font("Calibri Light", Font.PLAIN, size));
        
        
        
        
        for (int i = 0; i < 4; i++) {
            paVerwaltenNorthSouth.add(new JPanel());
        }

        //Adden von Komponenten auf paVerwaltenSouthEast
        paVerwaltenSouthEast.add(lbVerwaltenSouthEast); 
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

        paVerwaltenSouth.add(paVerwaltenSouthSouth);
        paVerwaltenSouth.add(paVerwaltenSouthEast);

        //Tabs designen
        tabPane.setBackgroundAt(0, Color.white);
        tabPane.setBackgroundAt(1, Color.white);
        tabPane.setBackgroundAt(2, Color.white);
        tabPane.setFont(new Font("Calibri Light", Font.PLAIN, 25));

        //paUebersicht designen
        tableFrei.getTableHeader().setFont(new Font("Calibri Light", Font.PLAIN, 22));
        tableBesetzt.getTableHeader().setFont(new Font("Calibri Light", Font.PLAIN, 22));

        //paVerwalten designen
        TitledBorder border1 = new TitledBorder("Buchen");
        border1.setTitleFont(new Font("Calibri Light", Font.BOLD, size));
        border1.setTitleColor(new Color(150,168,90));
        
        TitledBorder border2 = new TitledBorder("Guide hinzufügen");
        border2.setTitleFont(new Font("Calibri Light", Font.BOLD, 15));
        border2.setTitleColor(new Color(150,168,90));
        TitledBorder border3 = new TitledBorder("Einchecken");
        border3.setTitleFont(new Font("Calibri Light", Font.BOLD, 15));
        border3.setTitleColor(new Color(150,168,90));
        TitledBorder border4 = new TitledBorder("Auschecken");
        border4.setTitleFont(new Font("Calibri Light", Font.BOLD, 15));
        border4.setTitleColor(new Color(150,168,90));
        
        paVerwaltenNorth.setBorder(border1);
        paVerwaltenSouth.setBorder(border2);
        paVerwaltenNorthNorth.setBorder(border3);
        paVerwaltenNorthSouth.setBorder(border4);

        //Spalten verschieben deaktivieren
        tableFrei.getTableHeader().setReorderingAllowed(false);
        tableBesetzt.getTableHeader().setReorderingAllowed(false);

        //Models bzw. Renderer setzen
        tableBesetzt.setModel(ModelBesetzt);
        tableFrei.setModel(ModelFrei);
        tableBesetzt.setDefaultRenderer(Object.class, new TableRendererUebersichtBesetzt());
        tableFrei.setDefaultRenderer(Object.class, new TableRendererUebersichtFrei());

         //Events setzen
        tableFrei.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton() == MouseEvent.BUTTON3) {
                    if (tableFrei.getSelectedRowCount() == 1) {
                        Point point = e.getPoint();
                        currentRow = tableFrei.rowAtPoint(point);
                        if (ModelFrei.getGuideNameinSpecificRow(tableFrei.getSelectedRow()).equals(ModelFrei.getGuideNameinSpecificRow(currentRow))) {
                            popupCall = new JPopupMenu();
                            menuItem1.setText(ModelFrei.getGuideNameinSpecificRow(currentRow) + " eine Führungsanfrage schicken");
                            menuItem2.setText(ModelFrei.getGuideNameinSpecificRow(currentRow) + " anrufen");
                            menuItem3.setText(ModelFrei.getGuideNameinSpecificRow(currentRow) + "'s Telefonnummer anzeigen");
                            popupCall.add(menuItem1);
                            popupCall.add(menuItem2);
                            popupCall.add(menuItem3);
                            popupCall.show(tableFrei, e.getX(), e.getY());
                        }
                    } else if (tableFrei.getSelectedRowCount() >= 1) {
                        Point point = e.getPoint();
                        currentRow = tableFrei.rowAtPoint(point);

                        int[] selected = tableFrei.getSelectedRows();
                        for (int i = 0; i < selected.length; i++) {
                            if (selected[i] == currentRow) {
                                popupCall = new JPopupMenu();
                                menuItem1.setText("Allen markierten Guides eine Führungsanfrage schicken");
                                popupCall.add(menuItem1);
                                popupCall.show(tableFrei, e.getX(), e.getY());
                                break;
                            }
                        }
                    }
                }
            }
        });

        menuItem1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                try {
                    int[] selected = tableFrei.getSelectedRows();
                    if (menuItem1.getText().contains("Allen")) 
                    {
                        for(int i = 0; i<selected.length; i++)
                        {
                            Guide g = ModelFrei.getGuideFromIndex(selected[1]);
                            Skypeadapter.sendMessage(g.getSkype_id(), "Hallo, komme bitte zum Eingang, es werden Guides gebraucht! Danke");
                        }
                    } 
                    else 
                    {
                        if (selected.length == 1) 
                        {
                            Guide g = ModelFrei.getGuideFromIndex(selected[0]);
                            Skypeadapter.sendMessage(g.getSkype_id(), "Hallo, komme bitte zum Eingang, es werden Guides gebraucht! Danke");
                        }
                    }

                } catch (SkypeException ex) {
                    Logger.getLogger(DotPycsGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        menuItem2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                int[] selected = tableFrei.getSelectedRows();
                if (selected.length == 1) {
                    Guide g = ModelFrei.getGuideFromIndex(selected[0]);
                    //Skypeadapter.call(g.getSkype_id());
                    SkypeThread st = new SkypeThread(g.getSkype_id());
                    Thread t = new Thread(st);
                    t.start();
                }
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
            RFIDController con = new RFIDController();
            String rfid = con.GetEduCardId();

            String nachname = txtLastname.getText();
            String vorname = txtFirstname.getText();
            String skypeName = txtSkypeID.getText();
            String telNr = txtTelNo.getText();
            String klasse = coGuideClass.getSelectedItem().toString();
            String abteilung = coDept.getSelectedItem().toString();
            
            Guide g = new Guide(rfid, nachname, vorname, klasse, skypeName, telNr, "EDV");
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

                

                ModelFrei.firstInit(pathname);
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
    private JPanel paVerwaltenSouthEast; 
    private JLabel lbVerwaltenSouthEast;
}
