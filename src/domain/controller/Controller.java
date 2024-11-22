package domain.controller;

import domain.model.*;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

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

    // K1, K2, UC4
    public static Tilmelding opretTilmelding(Konference konference, Deltager deltager, boolean erForedragsholder,
                                             LocalDate fraDato, LocalDate tilDato, Hotel hotel, ArrayList<HotelTillæg> hotelTillæg,
                                             String ledsager, ArrayList<Udflugt> udflugter){
        Tilmelding tilmelding = new Tilmelding(konference, deltager, erForedragsholder, fraDato, tilDato);

        if(ledsager != null){
            tilmelding.addLedsager(ledsager);
            if(!udflugter.isEmpty()){
                tilmelding.setUdflugtsList(udflugter);
            }
        }
        if(hotel != null){
            tilmelding.addHotel(hotel);
            if(!hotelTillæg.isEmpty()){
                tilmelding.setHotelTillægsList(hotelTillæg);
            }
        }

        Storage.addTilmelding(tilmelding);
        return tilmelding;
    }
}
