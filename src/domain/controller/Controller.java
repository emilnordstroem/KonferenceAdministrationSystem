package domain.controller;

import domain.model.*;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {
    // K1, K2, UC1
    public static Deltager opretDeltager(String forNavn, String efterNavn, String telefonNummer, Adresse adresse, Firma firma) {
        Deltager deltager = new Deltager(forNavn, efterNavn, telefonNummer, adresse, firma);
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
    public static Hotel opretHotel(String navn, Adresse adresse, double enkeltværelsePris, double dobbeltværelsePris) {
        Hotel hotel = new Hotel(navn, adresse, enkeltværelsePris, dobbeltværelsePris);
        Storage.addHotel(hotel);
        return hotel;
    }

    // K1, K2, UC4
    public static Tilmelding opretTilmelding(Konference konference, Deltager deltager, boolean foredragsholder,
                                             String ledsagerNavn, LocalDate startDato, LocalDate slutDato,
                                             ArrayList<Udflugt> valgteUdflugter, Hotel hotel, ArrayList<HotelTillæg> valgteHotelTillægs){
        Tilmelding tilmelding = new Tilmelding(konference, deltager, foredragsholder, startDato, slutDato);

        if(ledsagerNavn != null){
            tilmelding.setLedsagerNavn(ledsagerNavn);
            if(!valgteUdflugter.isEmpty()){
                for (Udflugt valgtUdflugt : valgteUdflugter) {
                    tilmelding.addUdflugt(valgtUdflugt);
                }
            }
        }
        if(hotel != null){
            tilmelding.setHotel(hotel);
            if(!valgteHotelTillægs.isEmpty()){
                for (HotelTillæg valgteHotelTillæg : valgteHotelTillægs) {
                    tilmelding.addHotelTillæg(valgteHotelTillæg);
                }
            }
        }
        return tilmelding;
    }

    public static Firma opretFirma(String navn, String telefonnummer){
        Firma firma = new Firma(navn, telefonnummer);
        Storage.addFirma(firma);
        return firma;
    }
}
