package domain.controller;

import domain.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Test {
    // This class is a console output test of the functionality of the system
    public static void main(String[] args) {
        Konference havOgHimmel = Controller.opretKonference("Hav og himmel", LocalDate.of(2024, 12, 16), LocalDate.of(2024, 12, 18), 1500);

        Udflugt egeskov = havOgHimmel.createUdflugt("Egeskov", null, LocalDate.of(2024, 12, 18), "Skov kiggeri", 75);
        Udflugt trapholtMuseum = havOgHimmel.createUdflugt("Trapholt", null, LocalDate.of(2024, 12, 20), "Museum", 200);
        Udflugt byRundtur = havOgHimmel.createUdflugt("Byrundtur", null, LocalDate.of(2024, 12,18), "Byrundbyr", 125);

        Hotel denHvideSvane = Controller.opretHotel("Den Hvide Svane", null, 1050,1250, new ArrayList<>());
        HotelTillæg wifi = new HotelTillæg("WIFI", 50);
        denHvideSvane.addHotelTillæg(wifi);

        ArrayList<Udflugt> valgteUdflugter = new ArrayList<>();
        ArrayList<HotelTillæg> valgteHotelTillæg = new ArrayList<>();

        //=====================================================
        Deltager finnMadsen = Controller.opretDeltager("Finn", "Madsen", generatePhoneNumber(), null, null);
        Controller.opretTilmelding(havOgHimmel, finnMadsen, false,
                null, LocalDate.of(2024,12,16),
                LocalDate.of(2024,12,18),
                valgteUdflugter, null, valgteHotelTillæg);
        System.out.println("Finn Madsen: " + finnMadsen.getSamletUdgifter());
        System.out.println("==========================================");

        //=====================================================
        Deltager nielsPetersen = Controller.opretDeltager("Niels", "Petersen", generatePhoneNumber(), null, null);
        Controller.opretTilmelding(havOgHimmel, nielsPetersen, false,
                null, LocalDate.of(2024,12,16),
                LocalDate.of(2024,12,18),
                valgteUdflugter, denHvideSvane, valgteHotelTillæg);
        System.out.println("Niels Petersen: " + nielsPetersen.getSamletUdgifter());
        System.out.println("==========================================");

        //=====================================================
        Deltager peterSommer = Controller.opretDeltager("Peter", "Sommer", generatePhoneNumber(), null, null);
        valgteUdflugter.add(egeskov);
        valgteUdflugter.add(trapholtMuseum);
        valgteHotelTillæg.add(wifi);
        Controller.opretTilmelding(havOgHimmel, peterSommer, false,
                "Mie Sommer", LocalDate.of(2024,12,16),
                LocalDate.of(2024,12,18),
                valgteUdflugter, denHvideSvane, valgteHotelTillæg);

        System.out.println("Peter Sommer: " + peterSommer.getSamletUdgifter());
        System.out.println("==========================================");

        //=====================================================
        valgteUdflugter.clear();
        valgteHotelTillæg.clear();
        Deltager loneJensen = Controller.opretDeltager("Lone", "Jensen", generatePhoneNumber(), null, null);
        valgteUdflugter.add(egeskov);
        valgteUdflugter.add(byRundtur);
        valgteHotelTillæg.add(wifi);
        Controller.opretTilmelding(havOgHimmel, loneJensen, true,
                "Jan Madsen", LocalDate.of(2024,12,16),
                LocalDate.of(2024,12,18),
                valgteUdflugter, denHvideSvane, valgteHotelTillæg);

        System.out.println("Lone Jensen: " + loneJensen.getSamletUdgifter());
        System.out.println("==========================================");
    }

    private static String generatePhoneNumber(){
        return String.valueOf(new Random().nextInt(10_00_00_00,99_99_99_99) + 1);
    }
}
