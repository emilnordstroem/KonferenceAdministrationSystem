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

    private ArrayList<Udflugt> udflugtsList;

    private Hotel hotel;
    private ArrayList<HotelTillæg> hotelTillægsList;

    public Tilmelding(Konference konference, Deltager deltager, boolean erForedragsholder, LocalDate startDato, LocalDate slutDato, String ledsager, ArrayList<Udflugt> udflugtsList, Hotel hotel, ArrayList<HotelTillæg> hotelTillægsList) {
        this.konference = konference;
        this.deltager = deltager;
        this.erForedragsholder = erForedragsholder;
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.ledsager = ledsager;
        this.udflugtsList = udflugtsList;
        this.hotel = hotel;
        this.hotelTillægsList = new ArrayList<>(hotelTillægsList);
    }

    // Udregner samlet udgifter for tilmeldingen
    public double getSamletTilmeldingsUdgift(){
        double samletUdgifter = 0;
        int antalDageVedKonferencen = getAntalDage();

        samletUdgifter += udregnKonferenceAfgift(antalDageVedKonferencen);
        samletUdgifter += udregnHotelUdgift(antalDageVedKonferencen);
        samletUdgifter += udregnUdflugtUdgift();

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

    //============================================================
    // Helper method to getSamletTilmeldingsUdgift
    private int getAntalDage(){
        return (int) ChronoUnit.DAYS.between(startDato, slutDato);
    }

    private double udregnKonferenceAfgift(int antalDage){
        if(!isErForedragsholder()) {
            double konferenceAfgiftPerDag = konference.getPrisPrDag();
            return getAntalDage() * konferenceAfgiftPerDag;
        }
        return 0;
    }

    private double udregnHotelUdgift(int antalDage){
        if(hotel != null){
            double samletUdgifter = 0;
            if(ledsager != null){
                samletUdgifter += hotel.getDobbeltværelsePris() * antalDage;
            } else {
                samletUdgifter += hotel.getEnkeltværelsePris() * antalDage;
            }

            ArrayList<HotelTillæg> hotelTillægsList = new ArrayList<>(getHotelTillægsList());
            if(!hotelTillægsList.isEmpty()){
                for(HotelTillæg hotelTillæg : hotelTillægsList){
                    samletUdgifter += hotelTillæg.getPris() * antalDage;
                }
            }
            return samletUdgifter;
        }
        return 0;
    }

    private double udregnUdflugtUdgift(){
        if(!udflugtsList.isEmpty()){
            double samletUdgifter = 0;
            for(Udflugt udflugt : udflugtsList){
                samletUdgifter += udflugt.getPris();
            }
            return samletUdgifter;
        }
        return 0;
    }
}
