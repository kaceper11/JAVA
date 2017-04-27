import java.util.Calendar;

public class Badanie {
    Calendar date;
    double erytrocyty;
    double leukocyty;
    double plytki;

    final static double ERYTROCYTY_MAX = 5.9;
    final static double ERYTROCYTY_MIN= 4.5;
    final static double LEUKOCYTY_MAX = 10.8;
    final static double LEUKOCYTY_MIN = 4.0;
    final static double PLYTKI_MAX = 450;
    final static double PLYTKI_MIN = 130;

    //Konstruktor badania, o parametrach: data badania, liczba erytrocytow, liczba leukocytow i liczba plytek krwi
    Badanie(Calendar date, double erytrocyty, double leukocyty, double plytki) {
        this.date = date;
        this.erytrocyty = erytrocyty;
        this.leukocyty = leukocyty;
        this.plytki = plytki;
    }
}
