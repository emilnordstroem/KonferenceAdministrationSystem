package view.Hotel;

import domain.model.HotelTillæg;
import domain.model.Tilmelding;

import java.util.stream.Stream;

public class Utility {
    public static String buildInfoOnHotelTilmelding(Tilmelding tilmelding) {
        String s = tilmelding.getDeltager().getFuldeNavn();
        if(tilmelding.getLedsagerNavn() != null) {
            s += " har dobbeltværelse med " + tilmelding.getLedsagerNavn() + " og følgende hoteltillæg:\n";
        }

        else {
            s += " har enkeltværelse med følgende hoteltillæg:\n";
        }

        if(tilmelding.getHotelTillæg().isEmpty()) {
            s += "Ingen hoteltillæg.";
        }
        else {
            for (HotelTillæg hotelTillæg : tilmelding.getHotelTillæg()) {
                s += hotelTillæg.getNavn();
            }
        }
        return s;
    }

    public static boolean blankTextField(String navnInput, String enkeltværelsesPrisInput, String dobbeltværelsesPrisInput,
                                   String vejInput, String bygningNrInput,
                                   String byInput, String landInput){
        // Tjekker via Stream metoder om en forekommende string er blank
        return Stream.of(navnInput, enkeltværelsesPrisInput, dobbeltværelsesPrisInput, vejInput, bygningNrInput, byInput, landInput).anyMatch(String::isBlank);
    }

    public static boolean blankTextField(String navnInput, String prisInput){
        // Tjekker via Stream metoder om en forekommende string er blank
        return Stream.of(navnInput, prisInput).anyMatch(String::isBlank);
    }
}
