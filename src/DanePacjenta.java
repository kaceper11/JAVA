import javax.swing.table.DefaultTableModel;
import java.util.*;

public class DanePacjenta {
    List<Pacjent> listaPacjentow = new ArrayList<>();

    //Metoda zwracajaca pacjenta o numerze "index"
    public Pacjent getPacjent(int index) {
        if(index < listaPacjentow.size() && index > -1)
            return listaPacjentow.get(index);
        else
            return null;
    }

    //Metoda dodajaca pacjenta do tabeli
    public void dodajPacjenta(Pacjent p) {
        listaPacjentow.add(p);
    }

    //Metoda usuwajaca pacjenta z tabeli
    public void usunPacjenta(int number) {
        if (number>0 && number<listaPacjentow.size())
            listaPacjentow.remove(number);
    }

    //Metoda zwracajaca liste pacjentow w postaci dwuwymiarowej tabeli
    public Object[][] toArray() {
        Object[][] tablica = new Object[listaPacjentow.size()][];
        for (int i=0; i<listaPacjentow.size(); i++) {
            tablica[i] = listaPacjentow.get(i).toArray();
        }
        return tablica;
    }

    //Metoda sprawdzajaca czy na liscie jest pacjent o peselu...
    public Pacjent wezPacjentaOPeselu(String pesel) {
        for (int i=0; i<listaPacjentow.size(); i++) {
            if (listaPacjentow.get(i).isEqual(pesel)) {
                return listaPacjentow.get(i);
            }
        }
        return null;
    }

}
