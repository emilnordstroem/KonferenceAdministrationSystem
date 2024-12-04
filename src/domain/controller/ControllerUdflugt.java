package domain.controller;

import domain.model.Adresse;
import domain.model.Udflugt;
import storage.Storage;

import java.time.LocalDate;

public class ControllerUdflugt {
    public static Udflugt opretUdflugt(String navn, Adresse adresse, LocalDate localDate, String beksrivelse, double pris){
        Udflugt udflugt = new Udflugt(navn, adresse, localDate, beksrivelse, pris);
        Storage.addUdflugt(udflugt);
        return udflugt;
    }
}
