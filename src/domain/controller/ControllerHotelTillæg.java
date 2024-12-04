package domain.controller;

import domain.model.Hotel;
import domain.model.HotelTillæg;
import domain.model.Tilmelding;

import java.util.InputMismatchException;

public class ControllerHotelTillæg {
    public static HotelTillæg opretHotelTillæg(String navn, double pris, Hotel hotel) throws InputMismatchException {
        HotelTillæg hotelTillæg = new HotelTillæg(navn, pris, hotel);
        return hotelTillæg;
    }

    public static void opdaterHotelTillæg(HotelTillæg hotelTillæg, String newNavn, double newPris) throws InputMismatchException {
        hotelTillæg.setNavn(newNavn);
        hotelTillæg.setPris(newPris);
    }

    public static void fjernHotelTillæg(HotelTillæg hotelTillæg, Hotel hotel) {
        hotel.removeHotelTillæg(hotelTillæg);
        for (Tilmelding tilmelding : hotel.getTilmeldinger()) {
            tilmelding.removeHotelTillæg(hotelTillæg);
        }
    }
}
