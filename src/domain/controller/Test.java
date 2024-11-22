package domain.controller;

import domain.model.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        Konference havOgHimmel = Controller.opretKonference("Hav og himmel", LocalDate.of(2024, 12, 16), LocalDate.of(2024, 12, 18), 1500);

        havOgHimmel.opretUdflugt("Egeskov", null, LocalDate.of(2024, 12, 18), "Skov kiggeri", 75);
        havOgHimmel.opretUdflugt("Trapholt", null, LocalDate.of(2024, 12, 20), "Museum", 200);
        havOgHimmel.opretUdflugt("Byrundtur", null, LocalDate.of(2024, 12,18), "Byrundbyr", 125);

        Hotel denHvideSvane = Controller.opretHotel("Den Hvide Svane", null, 1050,1250);
        ArrayList<HotelTillæg> hotelTillæg = new ArrayList<>();
        hotelTillæg.add(new HotelTillæg("WIFI", 50));

        //=====================================================
        Deltager finn = Controller.opretDeltager("Finn Madsen", null, null);
        Controller.opretTilmelding(havOgHimmel, finn, false, LocalDate.of(2024, 12, 16),
                LocalDate.of(2024, 12, 18), null, new ArrayList<>(), null, new ArrayList<>());
        System.out.println("Finn Madsen: " + finn.getSamletUdgifter());
        System.out.println("==========================================");

        //=====================================================
        Deltager niels = Controller.opretDeltager("Finn Madsen", null, null);
        Controller.opretTilmelding(havOgHimmel, niels, false, LocalDate.of(2024, 12, 16),
                LocalDate.of(2024, 12, 18), denHvideSvane, new ArrayList<>(), null, new ArrayList<>());
        System.out.println("Niels Petersen: " + niels.getSamletUdgifter());
        System.out.println("==========================================");

        //=====================================================
        Deltager peter = Controller.opretDeltager("Finn Madsen", null, null);

        ArrayList<Udflugt> udflugter = new ArrayList<>();
        for(Udflugt udflugt : havOgHimmel.getUdflugter()){
            udflugter.add(udflugt);
        }

        Controller.opretTilmelding(havOgHimmel, peter, false, LocalDate.of(2024, 12, 16),
                LocalDate.of(2024, 12, 18), denHvideSvane, hotelTillæg, "Mie Sommer", udflugter);
        System.out.println("Peter Sommer: " + peter.getSamletUdgifter());
        System.out.println("==========================================");

        //=====================================================
        Deltager lone = Controller.opretDeltager("Lone Jensen", null, null);

        ArrayList<Udflugt> jansUdflugter = new ArrayList<>();
        for(Udflugt udflugt : havOgHimmel.getUdflugter()){
            if(udflugt.getNavn().equals("Byrundtur") || udflugt.getNavn().equals("Egeskov")){
                jansUdflugter.add(udflugt);
            }
        }

        Controller.opretTilmelding(havOgHimmel, lone, true, LocalDate.of(2024, 12, 16),
                LocalDate.of(2024, 12, 18), denHvideSvane, hotelTillæg, "Jan Madsen", jansUdflugter);
        System.out.println("Lone Jensen: " + lone.getSamletUdgifter());
        System.out.println("==========================================");
    }
}
