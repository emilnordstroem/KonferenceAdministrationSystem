package domain.controller;

import domain.model.Hotel;
import domain.model.Konference;
import domain.model.Tilmelding;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

public class ControllerKonference {
    // K3, UC2
    public static Konference opretKonference(String navn, LocalDate startDato, LocalDate slutDato, double afgiftPerDag,
                                             ArrayList<Hotel> hoteller) {
        Konference konference = new Konference(navn, startDato, slutDato, afgiftPerDag, hoteller);
        Storage.addKonference(konference);
        return konference;
    }

    public static void fjernKonference(Konference konference){
        for(Tilmelding tilmelding : konference.getTilmeldinger()){
            tilmelding.getDeltager().fjernTilmelding(tilmelding);
            System.out.println("Opdatere samlet udgifter for deltager");
        }
        Storage.removeKonference(konference);
    }
}
