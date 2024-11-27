package domain.model;

import java.util.ArrayList;

public class Hotel {
    private String navn;
    private Addresse addresse;
    private double enkeltVærelsesPris;
    private double dobbeltVærelsesPris;
    private ArrayList<Konference> konferencer= new ArrayList<>();
    private ArrayList<HotelTillæg> hotelTillæger = new ArrayList<>();
    private ArrayList<Tilmelding> tilmeldinger = new ArrayList<>();

    public Hotel(String navn, Addresse addresse, double enkeltVærelsesPris, double dobbeltVærelsesPris) {
        this.navn = navn;
        this.addresse = addresse;
        this.enkeltVærelsesPris = enkeltVærelsesPris;
        this.dobbeltVærelsesPris = dobbeltVærelsesPris;
    }

    public String getNavn() {
        return navn;
    }

    public Addresse getAddresse() {
        return addresse;
    }

    public double getEnkeltVærelsesPris() {
        return enkeltVærelsesPris;
    }

    public double getDobbeltVærelsesPris() {
        return dobbeltVærelsesPris;
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

    public ArrayList<HotelTillæg> getHotelTillæger() {
        return new ArrayList<>(hotelTillæger);
    }


    public void addHotelTillæg(HotelTillæg hotelTillæg) {
        if(!hotelTillæger.contains(hotelTillæg)){
            hotelTillæger.add(hotelTillæg);
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