package domain.model;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class Hotel {
    private String navn;
    private Adresse adresse;
    private double enkeltVærelsesPris;
    private double dobbeltVærelsesPris;
    private ArrayList<Konference> konferencer= new ArrayList<>();
    private ArrayList<HotelTillæg> hotelTillæger = new ArrayList<>();
    private ArrayList<Tilmelding> tilmeldinger = new ArrayList<>();

    public Hotel(String navn, Adresse adresse, double enkeltVærelsesPris, double dobbeltVærelsesPris) {
        this.navn = navn;
        this.adresse = adresse;
        this.enkeltVærelsesPris = enkeltVærelsesPris;
        this.dobbeltVærelsesPris = dobbeltVærelsesPris;

        if(enkeltVærelsesPris <= 0 || dobbeltVærelsesPris <= 0) {
            throw new InputMismatchException("Prisen skal være et positivt tal.");
        }
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public Adresse getAddresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public double getEnkeltVærelsesPris() {
        return enkeltVærelsesPris;
    }

    public void setEnkeltVærelsesPris(double enkeltVærelsesPris) {
        this.enkeltVærelsesPris = enkeltVærelsesPris;
    }

    public double getDobbeltVærelsesPris() {
        return dobbeltVærelsesPris;
    }

    public void setDobbeltVærelsesPris(double dobbeltVærelsesPris) {
        this.dobbeltVærelsesPris = dobbeltVærelsesPris;
    }

    public ArrayList<Konference> getKonferencer() {
        return new ArrayList<>(konferencer);
    }

    public void addKonference(Konference konference) {
        if(!konferencer.contains(konference)){
            konferencer.add(konference);
            konference.addHotel(this);
        }
    }

    public void removeKonference(Konference konference) {
        if(konferencer.contains(konference)) {
            konferencer.remove(konference);
            //konference.removeHotel(this);
        }
    }

    public ArrayList<HotelTillæg> getHotelTillæger() {
        return new ArrayList<>(hotelTillæger);
    }


    public void addHotelTillæg(HotelTillæg hotelTillæg) {
        if(!hotelTillæger.contains(hotelTillæg)){
            hotelTillæger.add(hotelTillæg);
        }
    }

    public void removeHotelTillæg(HotelTillæg hotelTillæg) {
        if(hotelTillæger.contains(hotelTillæg)) {
            hotelTillæger.remove(hotelTillæg);
        }
    }

    public ArrayList<Tilmelding> getTilmeldinger() {
        return new ArrayList<>(tilmeldinger);
    }

    public void addTilmelding(Tilmelding tilmelding) {
        if(!tilmeldinger.contains(tilmelding)) {
            tilmeldinger.add(tilmelding);
            tilmelding.setHotel(this);
        }
    }

    @Override
    public String toString() {
        String s = String.format("Hotel \"%s\" har enkeltværelsespris/dobbeltværelsespris (%.2f kr./%.2f kr.) med følgende hoteltillæg:\n", navn, enkeltVærelsesPris, dobbeltVærelsesPris);
        for (HotelTillæg hotelTillæg : hotelTillæger) {
            s += String.format("%s koster %.2f kr.\n", hotelTillæg.getNavn(), hotelTillæg.getPris());
        }

        return s;
    }
}