package view;

import domain.controller.Controller;
import domain.model.*;
import javafx.application.Application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class App {
    public static void main(String[] args) {
        initStorage();
        Application.launch(StartWindow.class);
    }

    private static void initStorage() {
        Konference havOgHimmel = Controller.opretKonference("Hav og himmel", LocalDate.of(2024,12,16), LocalDate.of(2024,12,18), 1500);
        Udflugt byRundtur = havOgHimmel.createUdflugt("Byrundtur, Odense", null, LocalDate.of(2024, 12, 18), "Kr. 125 inkl. Frokost", 125);
        Udflugt egeskov = havOgHimmel.createUdflugt("Egeskov", null, LocalDate.of(2024, 12, 19), "Kr. 75", 75);
        Udflugt trapholtMuseum = havOgHimmel.createUdflugt("Trapholt Museum, Kolding", null, LocalDate.of(2024, 12, 20), "Kr. 200 inkl. Frokost", 200);

        Hotel denHvideSvane = Controller.opretHotel("Den hvide svane", new Adresse("Sønderhøj", "31", "Aarhus", "Denmark"), 1050, 1250, new ArrayList<>());
        HotelTillæg wifi = new HotelTillæg("WIFI", 50, denHvideSvane);
        ArrayList<Udflugt> valgteUdflugter = new ArrayList<>();
        ArrayList<HotelTillæg> valgteHotelTillæg = new ArrayList<>();

        Adresse finnMadsenAdresse = new Adresse("Tekandevej", "4","Horsens","Danmark");
        Deltager finnMadsen = Controller.opretDeltager("Finn", "Madsen", generatePhoneNumber(), finnMadsenAdresse, null);
        Controller.opretTilmelding(havOgHimmel, finnMadsen, false,
                null, LocalDate.of(2024,12,16),
                LocalDate.of(2024,12,18),
                valgteUdflugter, null, valgteHotelTillæg);

        Adresse nielsPetersenAdresse = new Adresse("Kælke Boulevarden", "94","Aarhus C","Danmark");
        Deltager nielsPetersen = Controller.opretDeltager("Niels", "Petersen", generatePhoneNumber(), nielsPetersenAdresse, null);
        Controller.opretTilmelding(havOgHimmel, nielsPetersen, false,
                null, LocalDate.of(2024,12,16),
                LocalDate.of(2024,12,18),
                valgteUdflugter, denHvideSvane, valgteHotelTillæg);

        Adresse peterSommerAdresse = new Adresse("Thor-kristiansen vej", "10","Aalborg C","Danmark");
        Deltager peterSommer = Controller.opretDeltager("Peter", "Sommer", generatePhoneNumber(), peterSommerAdresse, null);
        valgteUdflugter.add(egeskov);
        valgteUdflugter.add(trapholtMuseum);
        valgteHotelTillæg.add(wifi);
        Controller.opretTilmelding(havOgHimmel, peterSommer, false,
                "Mie Sommer", LocalDate.of(2024,12,16),
                LocalDate.of(2024,12,18),
                valgteUdflugter, denHvideSvane, valgteHotelTillæg);

        Adresse loneJensenAdresse = new Adresse("Kane allé", "27","København K","Danmark");
        Deltager loneJensen = Controller.opretDeltager("Lone", "Jensen", generatePhoneNumber(), loneJensenAdresse, null);
        valgteUdflugter.clear();
        valgteHotelTillæg.clear();
        valgteUdflugter.add(egeskov);
        valgteUdflugter.add(byRundtur);
        valgteHotelTillæg.add(wifi);
        Controller.opretTilmelding(havOgHimmel, loneJensen, true,
                "Jan Madsen", LocalDate.of(2024,12,16),
                LocalDate.of(2024,12,18),
                valgteUdflugter, denHvideSvane, valgteHotelTillæg);

        Adresse emilStoeveAdresse = new Adresse("Bernhardt Jensens Boulevard", "95","Aarhus C","Danmark");
        Deltager emilStoeve = Controller.opretDeltager("Emil", "Støve", "25472030", emilStoeveAdresse, null);
        valgteUdflugter.clear();
        valgteHotelTillæg.clear();
        valgteUdflugter.add(egeskov);
        valgteUdflugter.add(byRundtur);
        valgteHotelTillæg.add(wifi);
        Controller.opretTilmelding(havOgHimmel, emilStoeve, true,
                "Maria Thor-Kristiansen", LocalDate.of(2024,12,16),
                LocalDate.of(2024,12,18),
                valgteUdflugter, denHvideSvane, valgteHotelTillæg);
    }

    private static String generatePhoneNumber(){
        return String.valueOf(new Random().nextInt(10_00_00_00, 99_99_99_99) + 1);
    }
}
