import java.util.Objects;

public class Pacjent {

    public enum Sex {
        Kobieta,
        Mężczyzna;
    }

    public enum Ubezpieczenie {
        NFZ,
        Prywatne,
        Brak;
    }

    String name;
    String surname;
    String pesel;
    Sex mySex;
    Ubezpieczenie mojeUbezpieczenie;
    Badanie badanie=null;

    //Konstruktor pacjenta o danym imieniu, nazwisku, plci, peselu i ubezpieczeniu
    Pacjent(String name, String surname, Sex mySex, String pesel, int ubezpieczenie) {
        this.pesel = pesel;
        updatePacjent(name, surname, mySex, ubezpieczenie);
    }

    //Metoda sprawdzajca czy pacjent ma dany pesel
    public boolean isEqual(String pesel) {
        if (this.pesel.equals(pesel)) {
            return true;
        }
        else {
            return false;
        }
    }

    //Metoda aktualizuja dane pacjenta w tabeli
    public void updatePacjent(String name, String surname, Sex mySex, int liczba) {
        this.name = name;
        this.surname = surname;
        this.mySex = mySex;
        switch(liczba) {
            case 0:
                this.mojeUbezpieczenie = Ubezpieczenie.NFZ;
                break;
            case 1:
                this.mojeUbezpieczenie = Ubezpieczenie.Prywatne;
                break;
            case 2:
                this.mojeUbezpieczenie = Ubezpieczenie.Brak;
                break;
        }
    }

    //Metoda przyporzadkowujaca kolejne numery rodzajom ubezpieczenia
    public int getInsuranceNumber() {
        switch(mojeUbezpieczenie) {
            case NFZ:
                return 0;
            case Prywatne:
                return 1;
            case Brak:
                return 2;
            default:
                return 10;
        }
    }

    //Metoda dodajaca badanie do pacjenta
    public void addBadanie(Badanie badanie) {
        this.badanie = badanie;
    }

    //Metoda zwracajaca pacjenta w postaci jednowymiarowej tablicy
    public Object[] toArray() {
        Object[] tablica = {name + " " + surname, pesel, mySex==Sex.Kobieta ? "K": "M", mojeUbezpieczenie.toString(),
                badanie==null ? false : true };
        return tablica;
    }
}
