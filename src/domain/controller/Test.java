package domain.controller;

import domain.model.*;
import storage.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
        LocalDate startDato = LocalDate.of(2025, 5, 1);
        LocalDate slutDato = LocalDate.of(2025, 5, 7);
        Konference miljøKonference = new Konference("Miljø 2.0", startDato, slutDato, 75);

        LocalDate udflugtsDato = LocalDate.of(2025, 5, 5);
        LocalTime startTidspunkt = LocalTime.of(12,30);
        LocalTime slutTidspunkt = LocalTime.of(16, 0);
        Addresse udflugtAddresse = new Addresse("Vejens Udflugt", "3", "København", "Denmark");
        miljøKonference.opretUdflugt("Skov Kiggeri", udflugtAddresse, udflugtsDato, startTidspunkt, slutTidspunkt, "Ude i skoven og se på træer", 129);
        miljøKonference.opretUdflugt("Svampe smagning", udflugtAddresse, udflugtsDato, startTidspunkt, slutTidspunkt, "Ude i skoven og spise svampe", 850);

        Addresse hotelAddresse = new Addresse("Hilton Street", "39", "Aarhus", "Denmark");
        Hotel hilton = new Hotel("Hilton", hotelAddresse, 1050,  1800);

        String[] tillægsNavn = {"Bad", "Wift", "Morgenmad"};
        for(int index = 0; index < 3; index++){
            HotelTillæg hotelTillæg = new HotelTillæg(tillægsNavn[index], new Random().nextDouble(100, 500) + 1);
            hilton.setHotelTillægsList(hotelTillæg);
        }

        Addresse deltagerAddresse = new Addresse("Vejens Vej", "4", "Danmark");
        Deltager deltager = new Deltager("Emil", "25472030", deltagerAddresse);

        ArrayList<Udflugt> udflugtsList = new ArrayList<>();
        for(Udflugt udflugt : miljøKonference.getUdflugter()){
            udflugtsList.add(udflugt);
        }

        ArrayList<HotelTillæg> hotelTillægsList = new ArrayList<>();
        for(HotelTillæg hotelTillæg : hilton.getHotelTillægsList()){
            hotelTillægsList.add(hotelTillæg);
        }

        LocalDate deltagerStartDato = LocalDate.of(2025, 5, 2);
        LocalDate DeltagerSlutDato = LocalDate.of(2025, 5, 4);
        Tilmelding tilmelding = new Tilmelding(miljøKonference, deltager, false, deltagerStartDato, DeltagerSlutDato, "Louis", udflugtsList, hilton,hotelTillægsList);

        deltager.setTilmeldingsList(tilmelding);
        System.out.println(deltager.getSamletUdgifter());
    }
}
