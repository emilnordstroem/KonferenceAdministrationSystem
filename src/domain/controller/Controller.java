package domain.controller;

import domain.model.Addresse;
import domain.model.Deltager;
import domain.model.Hotel;
import domain.model.Konference;
import storage.Storage;

import java.time.LocalDate;

public class Controller {
    // K1, K2, UC1
    public static Deltager opretDeltager(String navn, String telefonNummer, Addresse addresse) {
        Deltager deltager = new Deltager(navn, telefonNummer, addresse);
        Storage.addDeltager(deltager);
        return deltager;
    }

    // K3, UC2
    public static Konference opretKonference(String navn, LocalDate startDato, LocalDate slutDato, double afgiftPerDag) {
        Konference konference = new Konference(navn, startDato, slutDato, afgiftPerDag);
        Storage.addKonference(konference);
        return konference;
    }

    // K8, UC3
    public static Hotel opretHotel(String navn, Addresse addresse, double enkeltværelsePris, double dobbeltværelsePris) {
        Hotel hotel = new Hotel(navn, addresse, enkeltværelsePris, dobbeltværelsePris);
        Storage.addHotel(hotel);
        return hotel;
    }



}
