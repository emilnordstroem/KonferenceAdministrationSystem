package domain.controller;

import domain.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class BackEndTest {
    // This class is a console output test of the functionality of the system
    public static void main(String[] args) {
        Hotel denHvideSvane = ControllerHotel.opretHotel("Den Hvide Svane", null, 1050,1250, new ArrayList<>());
        HotelTillæg wifi = ControllerHotelTillæg.opretHotelTillæg("WIFI", 50, denHvideSvane);

        Hotel høtelPhønix = ControllerHotel.opretHotel("Høtel", null, 700, 800, new ArrayList<>());
        HotelTillæg bad = ControllerHotelTillæg.opretHotelTillæg("Bad", 200, høtelPhønix);
        HotelTillæg wifi1 = ControllerHotelTillæg.opretHotelTillæg("WIFI", 75, høtelPhønix);

        Hotel pensionTusindfryd = ControllerHotel.opretHotel("Pension TusindFryd", null, 500, 600, new ArrayList<>());
        HotelTillæg morgenmad = ControllerHotelTillæg.opretHotelTillæg("Morgenmad", 100, pensionTusindfryd);

        ArrayList<Udflugt> valgteUdflugter = new ArrayList<>();
        ArrayList<HotelTillæg> valgteHotelTillæg = new ArrayList<>();

        //=====================================================
        Konference havOgHimmel = ControllerKonference.opretKonference("Hav og himmel", LocalDate.of(2024, 12, 16),
                LocalDate.of(2024, 12, 18), 1500, new ArrayList<>());

        havOgHimmel.addHotel(denHvideSvane);
        havOgHimmel.addHotel(høtelPhønix);

        Udflugt egeskov = havOgHimmel.createUdflugt("Egeskov", null, LocalDate.of(2024, 12, 18), "Skov kiggeri", 75);
        Udflugt trapholtMuseum = havOgHimmel.createUdflugt("Trapholt", null, LocalDate.of(2024, 12, 20), "Museum", 200);
        Udflugt byRundtur = havOgHimmel.createUdflugt("Byrundtur", null, LocalDate.of(2024, 12,18), "Byrundbyr", 125);


        Konference itDays = ControllerKonference.opretKonference("IT-days", LocalDate.of(2025, 5,17),
                LocalDate.of(2025,5,22),500, new ArrayList<>());
        Udflugt centralBank = itDays.createUdflugt("Central Bank", null, LocalDate.of(2025,5,18), "Rundvisning ved cental banken",
                125);
        itDays.addHotel(høtelPhønix);

        //=====================================================
        Deltager finnMadsen = ControllerDeltager.opretDeltager("Finn", "Madsen", generatePhoneNumber(), null, null);
        ControllerTilmelding.opretTilmelding(havOgHimmel, finnMadsen, false,
                null, LocalDate.of(2024,12,16),
                LocalDate.of(2024,12,18),
                valgteUdflugter, null, valgteHotelTillæg);
        System.out.println("Finn Madsen: " + finnMadsen.getSamletUdgifter());
        System.out.println("==========================================");

        //=====================================================
        Deltager nielsPetersen = ControllerDeltager.opretDeltager("Niels", "Petersen", generatePhoneNumber(), null, null);
        ControllerTilmelding.opretTilmelding(havOgHimmel, nielsPetersen, false,
                null, LocalDate.of(2024,12,16),
                LocalDate.of(2024,12,18),
                valgteUdflugter, denHvideSvane, valgteHotelTillæg);
        System.out.println("Niels Petersen: " + nielsPetersen.getSamletUdgifter());
        System.out.println("==========================================");

        //=====================================================
        Deltager peterSommer = ControllerDeltager.opretDeltager("Peter", "Sommer", generatePhoneNumber(), null, null);
        valgteUdflugter.add(egeskov);
        valgteUdflugter.add(trapholtMuseum);
        valgteHotelTillæg.add(wifi);
        ControllerTilmelding.opretTilmelding(havOgHimmel, peterSommer, false,
                "Mie Sommer", LocalDate.of(2024,12,16),
                LocalDate.of(2024,12,18),
                valgteUdflugter, denHvideSvane, valgteHotelTillæg);

        System.out.println("Peter Sommer: " + peterSommer.getSamletUdgifter());
        System.out.println("==========================================");

        //=====================================================
        valgteUdflugter.clear();
        valgteHotelTillæg.clear();
        Deltager loneJensen = ControllerDeltager.opretDeltager("Lone", "Jensen", generatePhoneNumber(), null, null);
        valgteUdflugter.add(egeskov);
        valgteUdflugter.add(byRundtur);
        valgteHotelTillæg.add(wifi);
        ControllerTilmelding.opretTilmelding(havOgHimmel, loneJensen, true,
                "Jan Madsen", LocalDate.of(2024,12,16),
                LocalDate.of(2024,12,18),
                valgteUdflugter, denHvideSvane, valgteHotelTillæg);

        System.out.println("Lone Jensen: " + loneJensen.getSamletUdgifter());
        System.out.println("==========================================");

        valgteUdflugter.clear();
        valgteHotelTillæg.clear();
        Deltager hanneJensen = ControllerDeltager.opretDeltager("Hanne", "Jensen", generatePhoneNumber(), null, null);
        valgteUdflugter.add(byRundtur);
        valgteHotelTillæg.add(wifi);
        ControllerTilmelding.opretTilmelding(havOgHimmel, hanneJensen, false,
                "Mads Henning", LocalDate.of(2024,12,16),
                LocalDate.of(2024,12,18),
                valgteUdflugter, denHvideSvane, valgteHotelTillæg);

        System.out.println("Hanne Jensen: " + hanneJensen.getSamletUdgifter());
        System.out.println("==========================================");
    }

    private static String generatePhoneNumber(){
        return String.valueOf(new Random().nextInt(10_00_00_00,99_99_99_99) + 1);
    }
}
