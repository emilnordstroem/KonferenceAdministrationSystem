package domain.controller;

import domain.model.Firma;
import storage.Storage;

public class ControllerFirma {
    public static Firma opretFirma(String navn, String telefonnummer){
        Firma firma = new Firma(navn, telefonnummer);
        Storage.addFirma(firma);
        return firma;
    }
}
