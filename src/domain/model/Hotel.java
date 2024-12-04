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

    public Hotel(String navn, Adresse adresse, double enkeltVærelsesPris, double dobbeltVærelsesPris) throws InputMismatchException {
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

    public void setEnkeltVærelsesPris(double enkeltVærelsesPris) throws InputMismatchException {
        if(enkeltVærelsesPris <= 0) {
            throw new InputMismatchException("Prisen skal være et positivt tal.");
        }
        else {
            this.enkeltVærelsesPris = enkeltVærelsesPris;
        }
    }

    public double getDobbeltVærelsesPris() {
        return dobbeltVærelsesPris;
    }

    public void setDobbeltVærelsesPris(double dobbeltVærelsesPris) throws InputMismatchException {
        if(dobbeltVærelsesPris <= 0) {
            throw new InputMismatchException("Prisen skal være et positivt tal.");
        }
        else {
            this.dobbeltVærelsesPris = dobbeltVærelsesPris;
        }
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
            konference.removeHotel(this);
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
        hotelTillæger.remove(hotelTillæg);
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

    public void removeTilmelding(Tilmelding tilmelding) {
        if(tilmeldinger.contains(tilmelding)) {
            tilmeldinger.remove(tilmelding);
            tilmelding.setHotel(null);
        }
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder(String.format("Hotel \"%s\"%nEnkeltværelse: %.2f DKK%nDobbeltværelse: %.2f DKK%nMulighed for følgende hoteltillæg:%n",
                navn, enkeltVærelsesPris, dobbeltVærelsesPris));

        for (HotelTillæg hotelTillæg : hotelTillæger) {
            string.append(String.format("%s koster %.2f kr.\n", hotelTillæg.getNavn(), hotelTillæg.getPris()));
        }

        return string.toString();
    }
}