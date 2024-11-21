package domain.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Tilmelding {
    private Konference konference;
    private Deltager deltager;
    private boolean erForedragsholder;
    private String ledsager;

    private LocalDate startDato;
    private LocalDate slutDato;

    private ArrayList<Udflugt> udflugtsList = new ArrayList<>();

    private Hotel hotel;
    private ArrayList<HotelTillæg> hotelTillægsList = new ArrayList<>();

    public Tilmelding(Konference konference, Deltager deltager, boolean erForedragsholder, Hotel hotel, LocalDate startDato, LocalDate slutDato, ArrayList<Udflugt> udflugtsList, ArrayList<HotelTillæg> hotelTillægsList) {
        this.konference = konference;
        this.deltager = deltager;
        this.erForedragsholder = erForedragsholder;
        this.hotel = hotel;
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.udflugtsList = udflugtsList;
        this.hotelTillægsList = hotelTillægsList;
    }

    public ArrayList<HotelTillæg> getHotelTillægsList() {
        return new ArrayList<>(hotelTillægsList);
    }

    public ArrayList<Udflugt> getUdflugtsList() {
        return udflugtsList;
    }
}
