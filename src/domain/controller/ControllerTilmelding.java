package domain.controller;

import domain.model.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class ControllerTilmelding {
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

    public static void fjernTilmelding(Tilmelding tilmeldingTilFjernelse, Konference konference){
        konference.fjernTilmelding(tilmeldingTilFjernelse);
        tilmeldingTilFjernelse.getHotel().removeTilmelding(tilmeldingTilFjernelse);
        tilmeldingTilFjernelse.getDeltager().fjernTilmelding(tilmeldingTilFjernelse);
        System.out.println(String.format("fjernTilmelding() på %s kaldt i controller", tilmeldingTilFjernelse.getDeltager().getFuldeNavn()));
    }
}