package domain.controller;

import domain.model.Adresse;
import domain.model.Hotel;
import domain.model.Konference;
import storage.Storage;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class ControllerHotel {
    // K8, UC3
    public static Hotel opretHotel(String navn, Adresse adresse, double enkeltværelsePris, double dobbeltværelsePris, ArrayList<Konference> konferencer) throws InputMismatchException {
        Hotel hotel = new Hotel(navn, adresse, enkeltværelsePris, dobbeltværelsePris);
        if(!konferencer.isEmpty()) {
            for (Konference konference : konferencer) {
                hotel.addKonference(konference);
            }
        }
        Storage.addHotel(hotel);
        return hotel;
    }

    // Update metoder -------------------------------------------------------------------
    public static void opdaterHotel(String navn, Adresse adresse, double enkeltværelsesPris, double dobbeltværelsesPris, ArrayList<Konference> valgteKonferencer) {
        Hotel hotel = Storage.getHotelByNavn(navn);
        if(hotel != null) {
            hotel.setNavn(navn);
            hotel.setAdresse(adresse);
            hotel.setEnkeltVærelsesPris(enkeltværelsesPris);
            hotel.setDobbeltVærelsesPris(dobbeltværelsesPris);
            for (Konference tidligereValgtKonference : hotel.getKonferencer()) {
                if(!valgteKonferencer.contains(tidligereValgtKonference)) {
                    hotel.removeKonference(tidligereValgtKonference);
                }
            }

            for (Konference valgtKonference : valgteKonferencer) {
                if(!hotel.getKonferencer().contains(valgtKonference)) {
                    hotel.addKonference(valgtKonference);
                }
            }
        }
    }

    // Remove metoder -------------------------------------------------------------------
    public static void fjernHotel(Hotel hotel) {
        Storage.removeHotel(hotel);
    }
}
