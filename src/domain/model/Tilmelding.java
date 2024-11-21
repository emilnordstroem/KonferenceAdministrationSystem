package domain.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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

    // Udregner samlet udgifter for tilmeldingen
    public double getSamletTilmeldingsUdgift(){
        double samletUdgifter = 0;
        int antalDageVedKonference = getAntalDage();

        // Konference Afgift
        if(!isErForedragsholder()){
            double konferenceAfgiftPerDag = konference.getPrisPrDag();
            samletUdgifter += antalDageVedKonference * konferenceAfgiftPerDag;
        }

        // Hotel + HotelTillæg
        if(hotel != null){
            if(ledsager != null){
                samletUdgifter += hotel.getDobbeltværelsePris() * antalDageVedKonference;
            } else {
                samletUdgifter += hotel.getEnkeltværelsePris() * antalDageVedKonference;
            }

            ArrayList<HotelTillæg> hotelTillægsList = new ArrayList<>(getHotelTillægsList());
            if(!hotelTillægsList.isEmpty()){
                for(HotelTillæg hotelTillæg : hotelTillægsList){
                    samletUdgifter += hotelTillæg.getPris() * antalDageVedKonference;
                }
            }
        }

        // Udflugter
        ArrayList<Udflugt> udflugtsList = new ArrayList<>(getUdflugtsList());
        if(!udflugtsList.isEmpty()){
            for(Udflugt udflugt : udflugtsList){
                samletUdgifter += udflugt.getPris();
            }
        }
        return samletUdgifter;
    }

    public ArrayList<HotelTillæg> getHotelTillægsList() {
        return new ArrayList<>(hotelTillægsList);
    }

    public ArrayList<Udflugt> getUdflugtsList() {
        return udflugtsList;
    }

    public boolean isErForedragsholder() {
        return erForedragsholder;
    }

    // Helper method
    private int getAntalDage(){
        return (int) ChronoUnit.DAYS.between(startDato, slutDato);
    }
}
