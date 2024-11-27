package view;

import domain.controller.Controller;
import domain.model.*;
import javafx.application.Application;

import java.time.LocalDate;
import java.util.ArrayList;

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
        Hotel denHvideSvane = Controller.opretHotel("Den hvide svane", null, 1050, 1250);
        HotelTillæg wifi = new HotelTillæg("WIFI", 50, denHvideSvane);
        ArrayList<Udflugt> valgteUdflugter = new ArrayList<>();
        ArrayList<HotelTillæg> valgteHotelTillæg = new ArrayList<>();

        Deltager finnMadsen = Controller.opretDeltager("Finn", "Madsen", null, null);
        Controller.opretTilmelding(havOgHimmel, finnMadsen, false,
                null, LocalDate.of(2024,12,16),
                LocalDate.of(2024,12,18),
                valgteUdflugter, null, valgteHotelTillæg);

        Deltager nielsPetersen = Controller.opretDeltager("Niels", "Petersen", null, null);
        Controller.opretTilmelding(havOgHimmel, nielsPetersen, false,
                null, LocalDate.of(2024,12,16),
                LocalDate.of(2024,12,18),
                valgteUdflugter, denHvideSvane, valgteHotelTillæg);

        Deltager peterSommer = Controller.opretDeltager("Peter", "Sommer", null, null);
        valgteUdflugter.add(egeskov);
        valgteUdflugter.add(trapholtMuseum);
        valgteHotelTillæg.add(wifi);
        Controller.opretTilmelding(havOgHimmel, peterSommer, false,
                "Mie Sommer", LocalDate.of(2024,12,16),
                LocalDate.of(2024,12,18),
                valgteUdflugter, denHvideSvane, valgteHotelTillæg);

        Deltager loneJensen = Controller.opretDeltager("Lone", "Jensen", null, null);
        valgteUdflugter.clear();
        valgteHotelTillæg.clear();
        valgteUdflugter.add(egeskov);
        valgteUdflugter.add(byRundtur);
        valgteHotelTillæg.add(wifi);
        Controller.opretTilmelding(havOgHimmel, loneJensen, true,
                "Jan Madsen", LocalDate.of(2024,12,16),
                LocalDate.of(2024,12,18),
                valgteUdflugter, denHvideSvane, valgteHotelTillæg);
    }
}
