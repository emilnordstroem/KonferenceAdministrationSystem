package domain.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Tilmelding {
    private Konference konference;
    private Deltager deltager;
    private boolean erForedragsholder;
    private String ledsagerNavn;
    private LocalDate startDato;
    private LocalDate slutDato;
    private ArrayList<Udflugt> valgteUdflugter = new ArrayList<>();
    private Hotel hotel;
    private ArrayList<HotelTillæg> valgteHotelTillæg = new ArrayList<>();

    public Tilmelding(Konference konference, Deltager deltager, boolean foredragsholder, LocalDate startDato, LocalDate slutDato) {
        this.konference = konference;
        konference.addTilmelding(this);
        this.deltager = deltager;
        deltager.addTilmelding(this);
        this.erForedragsholder = foredragsholder;
        this.startDato = startDato;
        this.slutDato = slutDato;
    }

    //============================================================
    // Udregner samlet udgifter for tilmeldingen
    public double getSamletTilmeldingsUdgift(){
        double samletUdgifter = 0;
        int antalDageVedKonferencen = getAntalDageKonference();
        int antalDageOphold = getAntalDageOphold();

        samletUdgifter += udregnKonferenceAfgift(antalDageVedKonferencen);
        System.out.println("Udregn konference:" + samletUdgifter);
        samletUdgifter += udregnHotelUdgift(antalDageOphold);
        System.out.println("Udregn hotel:" + samletUdgifter);
        samletUdgifter += udregnUdflugtUdgift();
        System.out.println("Udregn udflugt:" + samletUdgifter);

        return samletUdgifter;
    }

    //============================================================
    // Helper method to getSamletTilmeldingsUdgift
    private int getAntalDageKonference(){
        // we plus 1 to include both from and to date.
        return (int) ChronoUnit.DAYS.between(startDato, slutDato) + 1;
    }

    private int getAntalDageOphold(){
        return (int) ChronoUnit.DAYS.between(startDato, slutDato);
    }

    private double udregnKonferenceAfgift(int antalDage){
        if(!isErForedragsholder()) {
            double konferenceAfgiftPerDag = konference.getPrisPrDag();
            return antalDage * konferenceAfgiftPerDag;
        }
        return 0;
    }

    private double udregnHotelUdgift(int antalDage){
        if(hotel != null){
            double samletUdgifter = 0;
            if(ledsagerNavn != null){
                samletUdgifter += hotel.getDobbeltVærelsesPris() * antalDage;
            } else {
                samletUdgifter += hotel.getEnkeltVærelsesPris() * antalDage;
            }

            if(!valgteHotelTillæg.isEmpty()){
                for(HotelTillæg valgtHotelTillæg : valgteHotelTillæg){
                    samletUdgifter += valgtHotelTillæg.getPris() * antalDage;
                }
            }
            return samletUdgifter;
        }
        return 0;
    }

    private double udregnUdflugtUdgift(){
        if(!valgteUdflugter.isEmpty()){
            double samletUdgifter = 0;
            for(Udflugt valgtUdflugt : valgteUdflugter){
                samletUdgifter += valgtUdflugt.getPris();
            }
            return samletUdgifter;
        }
        return 0;
    }

    //============================================================
    // Getter and Setter
    public Konference getKonference() {
        return konference;
    }

    public Deltager getDeltager() {
        return deltager;
    }

    public LocalDate getStartDato() {
        return startDato;
    }

    public LocalDate getSlutDato() {
        return slutDato;
    }

    public boolean isErForedragsholder() {
        return erForedragsholder;
    }

    public String getLedsagerNavn() {
        return ledsagerNavn;
    }

    public void setLedsagerNavn(String ledsagerNavn) {
        this.ledsagerNavn = ledsagerNavn;
    }

    public ArrayList<Udflugt> getUdflugter() {
        return new ArrayList<>(valgteUdflugter);
    }

    public void addUdflugt(Udflugt udflugt) {
        if(!valgteUdflugter.contains(udflugt)) {
            valgteUdflugter.add(udflugt);
            udflugt.addTilmelding(this);
        }
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        if(this.hotel != hotel) {
            this.hotel = hotel;
            if(hotel != null) {
                hotel.addTilmelding(this);
            }
        }
    }

    public ArrayList<HotelTillæg> getHotelTillæg() {
        return new ArrayList<>(valgteHotelTillæg);
    }

    public void addHotelTillæg(HotelTillæg hotelTillæg) {
        if(!valgteHotelTillæg.contains(hotelTillæg)) {
            valgteHotelTillæg.add(hotelTillæg);
        }
    }

    @Override
    public String toString() {
        String s = String.format("%s (%d) er tilmeldt konferencen fra %s til %s", deltager.getFuldeNavn(), deltager.getId(), startDato, slutDato);
        if(ledsagerNavn != null) {
            s+= " med " + ledsagerNavn;
        }
        else {
            s+= " uden ledsager";
        }

        if(erForedragsholder) {
            s+=" og er foredragsholder.";
        }
        else {
            s+=" og er ikke foredragsholder.";
        }
        return s;
    }
}
