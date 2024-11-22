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

    public Tilmelding(Konference konference, Deltager deltager, boolean erForedragsholder, LocalDate startDato, LocalDate slutDato) {
        this.konference = konference;
        this.deltager = deltager;
        this.erForedragsholder = erForedragsholder;
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.ledsager = null; // Default null
    }

    //============================================================
    // Udregner samlet udgifter for tilmeldingen
    public double getSamletTilmeldingsUdgift(){
        double samletUdgifter = 0;
        int antalDageVedKonferencen = getAntalDage();

        samletUdgifter += udregnKonferenceAfgift(antalDageVedKonferencen);
        samletUdgifter += udregnHotelUdgift(antalDageVedKonferencen);
        samletUdgifter += udregnUdflugtUdgift();

        return samletUdgifter;
    }

    //============================================================
    // Helper method to getSamletTilmeldingsUdgift
    private int getAntalDage(){
        return (int) ChronoUnit.DAYS.between(startDato, slutDato);
    }

    private double udregnKonferenceAfgift(int antalDage){
        if(!isErForedragsholder()) {
            double konferenceAfgiftPerDag = konference.getAfgiftPerDag();
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

    //============================================================
    // Getter and Setter
    public void addLedsager(String ledsager) {
        if(!hasLedsager()){
            this.ledsager = ledsager;
        }
    }

    private boolean hasLedsager(){
        return ledsager != null;
    }

    public void addHotel(Hotel hotel){
        this.hotel = hotel;
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
}
