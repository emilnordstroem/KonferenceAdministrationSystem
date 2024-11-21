package domain.model;

import java.util.ArrayList;

public class Hotel {
    private final String navn;
    private Addresse addresse;
    private double enkeltværelsePris;
    private double dobbeltværelsePris;
    private final ArrayList<Konference> konferenceList = new ArrayList<>();
    private final ArrayList<HotelTillæg> hotelTillægsList = new ArrayList<>();

    public Hotel(String navn, Addresse addresse, double enkeltværelsePris, double dobbeltværelsePris) {
        this.navn = navn;
        this.addresse = addresse;
        this.enkeltværelsePris = enkeltværelsePris;
        this.dobbeltværelsePris = dobbeltværelsePris;
    }

    public void setKonferenceList(Konference konference) {
        if(!konferenceList.contains(konference)){
            konferenceList.add(konference);
            System.out.println("Konference tilføjet til hotellets konferenceList");
        }
    }

    public void setHotelTillægsList(HotelTillæg hotelTillæg) {
        if(!hotelTillægsList.contains(hotelTillæg)){
            hotelTillægsList.add(hotelTillæg);
            System.out.println("HotelTillæg tilføjet til hotellets hotelTillægsList");
        }
    }

    public ArrayList<HotelTillæg> getHotelTillægsList() {
        return new ArrayList<>(hotelTillægsList);
    }

    public double getEnkeltværelsePris() {
        return enkeltværelsePris;
    }

    public double getDobbeltværelsePris() {
        return dobbeltværelsePris;
    }
}