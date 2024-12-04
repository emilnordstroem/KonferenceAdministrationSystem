package domain.controller;

import domain.model.*;
import storage.Storage;

public class ControllerDeltager {
    // Opret metoder -------------------------------------------------------------------
    // K1, K2, UC1
    public static Deltager opretDeltager(String forNavn, String efterNavn, String telefonNummer, Adresse adresse, Firma firma) {
        Deltager deltager = new Deltager(forNavn, efterNavn, telefonNummer, adresse, firma);
        Storage.addDeltager(deltager);
        return deltager;
    }

    public static double getSamletUdgifter(Deltager deltager){
        return deltager.getSamletUdgifter();
    }

    public static void fjernDeltager(Deltager deltager){
        for(Tilmelding tilmelding : deltager.getTilmeldinger()){
            Konference konference = tilmelding.getKonference();
            Hotel hotel = tilmelding.getHotel();
            konference.fjernTilmelding(tilmelding);
            hotel.removeTilmelding(tilmelding);
        }
        Storage.removeDeltager(deltager);
    }
}
