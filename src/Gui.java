import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;


public class Gui{

    static JFrame frame;
    static Container container;
    static JPanel panelDaneBadanie, panelDane, panelBadanie, panelLista;
    static Pacjent p;
    static Badanie b;

    static JTextField nameText,surnameText, peselText, erytrocytyText, leukocytyText, plytkiText;
    static JDateChooser dateChooser;
    static JRadioButton femaleRadioButton, maleRadioButton;
    static DanePacjenta dane = new DanePacjenta();
    static JTable listaPacjentowTable;
    static TableColumnModel columnModel;
    static String[] categories = {"Imię i Nazwisko",
            "PESEL",
            "Płeć",
            "Ubezpieczenie",
            "Badanie"};
    static DefaultTableModel tableModel;
    static JComboBox insuranceDropdown;


    private static void createAndShowGUI() {

        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("Rejestracja wyników badań");
        int x = 900;
        int y = 530;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(x, y);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(((int)dimension.getWidth()/2)-x/2, ((int)dimension.getHeight()/2)-y/2);
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu aplikacjaMenu = new JMenu("Aplikacja");
        menuBar.add(aplikacjaMenu);
        JMenuItem exitMenu = new JMenuItem("Zamknij ALT + F4");
        aplikacjaMenu.add(exitMenu);

        exitMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        container = frame.getContentPane();
        container.setLayout(new BorderLayout());
        addPanelDaneBadanie();
        addPanelDane();
        addPanelBadanie();
        addPanelListaPacjentow();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public static void main(String[] args) {
        createAndShowGUI();
    }

    //Metoda dodajaca panel w ktorym znajduja sie panele z danymi pacjenta oraz z badaniem
    public static void addPanelDaneBadanie() {
        panelDaneBadanie = new JPanel();
        panelDaneBadanie.setBackground(new Color(200, 200, 255));
        panelDaneBadanie.setLayout(new BorderLayout(10,10));
        panelDaneBadanie.setPreferredSize(new Dimension(390, 500));
        container.add(panelDaneBadanie, BorderLayout.LINE_START);
    }

    //Metoda dodajaca panel z danymi pacjenta
    public static void addPanelDane() {
        panelDane = new JPanel();
        panelDane.setSize(600,400);
        panelDane.setBorder(BorderFactory.createTitledBorder("Dane pacjenta"));
        panelDane.setBackground(new Color(200, 200, 255));
        panelDane.setLayout(new GridLayout(6,2, 5, 10));
        panelDane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        panelDane.add(new JLabel("Imię:"));
        nameText = new JTextField();
        panelDane.add(nameText);

        panelDane.add(new JLabel("Nazwisko:"));
        surnameText = new JTextField();
        panelDane.add(surnameText);

        panelDane.add(new JLabel("PESEL:"));
        peselText = new JTextField();
        panelDane.add(peselText);

        panelDane.add(new JLabel("Płeć"));
        Container sexPanel = Box.createHorizontalBox();
        femaleRadioButton = new JRadioButton("Kobieta");
        sexPanel.add(femaleRadioButton);
        femaleRadioButton.setSelected(true);
        maleRadioButton = new JRadioButton("Mężczyzna");
        sexPanel.add(maleRadioButton);

        femaleRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(femaleRadioButton.isSelected()) {
                    maleRadioButton.setSelected(false);
                }
                else {
                    maleRadioButton.setSelected(true);
                }
            }
        });

        maleRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(maleRadioButton.isSelected()) {
                    femaleRadioButton.setSelected(false);
                }
                else {
                    femaleRadioButton.setSelected(true);
                }
            }
        });
        panelDane.add(sexPanel);

        String[] dropdownStrings = {"NFZ", "Prywatne", "Brak"};
        panelDane.add(new JLabel("Ubezpieczenie:"));
        insuranceDropdown = new JComboBox(dropdownStrings);
        panelDane.add(insuranceDropdown);

        Container buttonPanelPacjent = Box.createHorizontalBox();
        JButton zapiszButtonPacjent = new JButton("Zapisz");
        buttonPanelPacjent.add(zapiszButtonPacjent);
        JButton anulujButtonPacjent = new JButton("Anuluj");
        buttonPanelPacjent.add(anulujButtonPacjent);
        panelDane.add(buttonPanelPacjent);

        zapiszButtonPacjent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodajPacjenta();
            }

        });

        anulujButtonPacjent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anulujPacjenta();
            }
        });
        panelDaneBadanie.add(panelDane, BorderLayout.NORTH);
    }

    //Metoda dodajaca panel z badaniem
    public static void addPanelBadanie() {

        panelBadanie = new JPanel();
        panelBadanie.setBorder(BorderFactory.createTitledBorder("Badanie"));
        panelBadanie.setSize(600,400);
        panelBadanie.setBackground(new Color(200, 200, 255));
        panelBadanie.setLayout(new GridLayout(6,2, 5, 10));
        panelBadanie.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        panelBadanie.add(new JLabel("Data:"));
        dateChooser = new com.toedter.calendar.JDateChooser();
        panelBadanie.add(dateChooser);

        panelBadanie.add(new JLabel("Liczba erytrocytów (x1mln):"));
        erytrocytyText = new JTextField();

        erytrocytyText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                erytrocytyIsOk();
            }
        });
        panelBadanie.add(erytrocytyText);

        panelBadanie.add(new JLabel("Liczba leukocytów (x1000):"));
        leukocytyText = new JTextField();

        leukocytyText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leukocytyIsOk();
            }
        });
        panelBadanie.add(leukocytyText);

        panelBadanie.add(new JLabel("Liczba płytek krwi (x1000):"));
        plytkiText = new JTextField();
        plytkiText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                plytkiIsOk();
            }
        });
        panelBadanie.add(plytkiText);

        Container buttonPanelBadanie = Box.createHorizontalBox();
        JButton zapiszButtonBadanie = new JButton("Zapisz");
        buttonPanelBadanie.add(zapiszButtonBadanie);
        JButton anulujButtonBadanie = new JButton("Anuluj");
        buttonPanelBadanie.add(anulujButtonBadanie);
        panelBadanie.add(buttonPanelBadanie);

        zapiszButtonBadanie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodajBadanie();
            }

        });
        panelDaneBadanie.add(panelBadanie, BorderLayout.SOUTH);

        anulujButtonBadanie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anulujBadanie();
            }
        });
    }

    //Metoda ustalajaca szerokosc kolumn w tabeli
    public static void setColumnsWidth() {
        if(columnModel == null) {
            columnModel = listaPacjentowTable.getColumnModel();
        }

        columnModel.getColumn(0).setPreferredWidth(185);
        columnModel.getColumn(1).setPreferredWidth(165);
        columnModel.getColumn(2).setPreferredWidth(50);
        columnModel.getColumn(3).setPreferredWidth(120);
        columnModel.getColumn(4).setPreferredWidth(80);
    }

    //Metoda dodajaca panel z lista pacjentow
    public static void addPanelListaPacjentow() {
        panelLista = new JPanel();
        panelLista.setSize(600, 800);
        panelLista.setBackground(new Color(200, 200, 255));
        panelLista.setLayout(new BorderLayout(10,10));
        panelLista.setPreferredSize(new Dimension(500, 500));
        panelLista.setBorder(BorderFactory.createTitledBorder("Lista pacjentów"));
        container.add(panelLista, BorderLayout.LINE_END);

        tableModel = new DefaultTableModel(0, categories.length) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableModel.setColumnIdentifiers(categories);
        listaPacjentowTable = new JTable(tableModel) {

            private static final long serialVersionUID = 1L;

            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return String.class;
                    case 4:
                        return Boolean.class;
                    default:
                        return Boolean.class;
                }
            }

        };

        listaPacjentowTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(row % 2 == 0 ? (new Color(200, 200, 255)): Color.WHITE);
                return this;
            }
        });
        setColumnsWidth();

        listaPacjentowTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                p = dane.getPacjent(listaPacjentowTable.getSelectedRow());
                if(p != null) {

                    nameText.setText(p.name);
                    surnameText.setText(p.surname);
                    peselText.setText(p.pesel);

                    if (p.mySex == Pacjent.Sex.Kobieta) {
                        femaleRadioButton.setSelected(true);
                        maleRadioButton.setSelected(false);
                    } else {
                        maleRadioButton.setSelected(true);
                        femaleRadioButton.setSelected(false);
                    }
                    insuranceDropdown.setSelectedIndex(p.getInsuranceNumber());

                    b = p.badanie;
                    if(b!=null) {
                        dateChooser.setCalendar(b.date);
                        erytrocytyText.setText(b.erytrocyty + "");
                        leukocytyText.setText(b.leukocyty + "");
                        plytkiText.setText(b.plytki + "");
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(listaPacjentowTable);
        listaPacjentowTable.setFillsViewportHeight(true);
        panelLista.add(scrollPane, BorderLayout.NORTH);
        Container buttonPanelLista = Box.createHorizontalBox();
        JButton dodajButtonLista = new JButton("Dodaj");
        buttonPanelLista.add(dodajButtonLista);
        JButton usunButtonLista = new JButton("Usuń");
        buttonPanelLista.add(usunButtonLista);
        panelLista.add(buttonPanelLista, BorderLayout.SOUTH);

        //Listener odpowiadajacy za dodanie pacjenta do tabeli
        dodajButtonLista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodajPacjenta();
                dodajBadanie();
            }
        });

        //Listener odpowiadajacy za usuniecie pacjenta z tabeli
        usunButtonLista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usunPacjenta(listaPacjentowTable.getSelectedRow());
            }
        });
    }

    //Metoda odpowiadająca za czyszczenie formularza "Dane pacjenta"
    public static void anulujPacjenta() {
        nameText.setText(null);
        surnameText.setText(null);
        peselText.setText(null);
        maleRadioButton.setSelected(false);
        femaleRadioButton.setSelected(true);
        insuranceDropdown.setSelectedIndex(0);
    }

    //Metoda odpowiadająca za czyszczenie formularza "Badanie"
    public static void anulujBadanie() {
        dateChooser.setCalendar(null);
        erytrocytyText.setText(null);
        leukocytyText.setText(null);
        plytkiText.setText(null);
        erytrocytyText.setBackground(Color.white);
        leukocytyText.setBackground(Color.white);
        plytkiText.setBackground(Color.white);
    }

    //Metoda sprawdzająca czy dane pacjenta zostały podane
    public static boolean pacjentIsOk() {
        if (nameText.getText().length() == 0 || surnameText.getText().length() == 0 || !peselIsOk()) {

            JOptionPane.showMessageDialog(null, "Nie podałeś danych pacjenta!", "Uwaga", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else {
            return true;
        }
    }

    //Metoda sprawdzająca czy w badaniu została podana data
    public static boolean badanieIsOk() {
        Calendar date= dateChooser.getCalendar();
        if (date != null) {
            return true;
        }
        else {
            JOptionPane.showMessageDialog(null, "Nie podałeś daty!", "Uwaga", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    //Metoda sprawdzająca czy pola w panelu badanie wykraczają poza normę czy nie
    public static boolean isFieldOK(JTextField field, double min, double max) {
        if(field.getText() != null &&  field.getText().length() > 0) {
            if (Double.parseDouble(field.getText()) > max || Double.parseDouble(field.getText()) < min) {
                field.setBackground(Color.RED);
                field.validate();
                return true;
            }
            else {
                field.setBackground(Color.green);
                field.validate();
                return false;
            }
        }
        else {
            return true;
        }
    }

    //Metoda sprawdzajaca czy liczba erytrocytow jest w normie
    public static boolean erytrocytyIsOk() {
        return isFieldOK(erytrocytyText, Badanie.ERYTROCYTY_MIN, Badanie.ERYTROCYTY_MAX);
    }

    //Metoda sprawdzajaca czy liczba leukocytow jest w normie
    public static boolean leukocytyIsOk() {
        return isFieldOK(leukocytyText, Badanie.LEUKOCYTY_MIN, Badanie.LEUKOCYTY_MAX);
    }

    //Metoda sprawdzajaca czy liczba plytek krwi jest w normie
    public static boolean plytkiIsOk() {
        return isFieldOK(plytkiText, Badanie.PLYTKI_MIN, Badanie.PLYTKI_MAX);
    }

    //Metoda sprawdzająca czy pole PESEL zostało dobrze wypełnione
    public static boolean peselIsOk() {
        String peselek = peselText.getText();

        if(peselek.length() !=11) {

            JOptionPane.showMessageDialog(null,"PESEL ma nieprawidłową długość lub została podana litera!","Uwaga", JOptionPane.ERROR_MESSAGE);
            System.out.println("pesel ma nieprawidlowa dlugosc");
            peselText.setText(null);
            return false;
        }
        else {
            byte PESEL[] = new byte[11];
            try {
                for (int i = 0; i < 11; i++){
                    PESEL[i] = Byte.parseByte(peselek.substring(i, i+1));
                }
            }
            catch(NumberFormatException e) {
                JOptionPane.showMessageDialog(null,"Pole PESEL musi być liczbą!","Uwaga", JOptionPane.ERROR_MESSAGE);
                peselText.setText(null);
                return false;
            }
        }
        return true;
    }

    //Metoda dodająca pacjenta do tabeli
    public static void dodajPacjenta() {
        //pobieranie danych z formularza + stworzenie pacjenta
        if(pacjentIsOk() == true) {
            p = dane.wezPacjentaOPeselu(peselText.getText());
            if (p ==null) {
                p = new Pacjent(nameText.getText(),
                        surnameText.getText(),
                        femaleRadioButton.isSelected() ? Pacjent.Sex.Kobieta : Pacjent.Sex.Mężczyzna,
                        peselText.getText(), insuranceDropdown.getSelectedIndex());

                //dodanie pacjenta do listy

                dane.dodajPacjenta(p);
            }
            else {
                int reply = JOptionPane.showConfirmDialog(null,"Pacjent o podanym peselu istnieje w tabeli, czy chcesz zaktualizować?","Uwaga", JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION) {
                        p.updatePacjent(nameText.getText(), surnameText.getText(),
                                femaleRadioButton.isSelected() ? Pacjent.Sex.Kobieta : Pacjent.Sex.Mężczyzna,
                                insuranceDropdown.getSelectedIndex());
                    }
            }
            updateTable();

        }
    }

    //Metoda usuwająca pacjenta z tabeli
    public static void usunPacjenta(int index) {
        dane.usunPacjenta(index);
        updateTable();
        anulujBadanie();
        anulujPacjenta();
    }

    //Metoda dodająca badanie do pacjenta z tabeli
    public static boolean dodajBadanie() {

        if(p != null && badanieIsOk()) {
            //pobieranie danych z formularza + stworzenie badania

            try {
                b = new Badanie(dateChooser.getCalendar(), Double.parseDouble(erytrocytyText.getText()),
                        Double.parseDouble(leukocytyText.getText()), Double.parseDouble(plytkiText.getText()));
                p.addBadanie(b);
                updateTable();
            }
            catch(NumberFormatException e) {
                JOptionPane.showMessageDialog(null,"Pola w panelu Badanie zostały źle podane","Uwaga", JOptionPane.ERROR_MESSAGE);
            }
            return true;
        }
        else
            JOptionPane.showMessageDialog(null,"Badanie nie zostało dodane!","Uwaga", JOptionPane.ERROR_MESSAGE);
            return false;
    }

    //Metoda uaktualniajaca tabele z lista pacjentow
    public static void updateTable() {
        tableModel.setDataVector(dane.toArray(), categories);
        setColumnsWidth();
        tableModel.fireTableDataChanged();
    }
}










