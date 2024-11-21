package domain.model;

import java.util.ArrayList;

public class Hotel {
    private String navn;
    private Addresse addresse;
    private ArrayList<Konference> konferenceList = new ArrayList<>();
    private ArrayList<HotelTillæg> hotelTillægsList = new ArrayList<>();

    public Hotel(String navn, Addresse addresse) {
        this.navn = navn;
        this.addresse = addresse;
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
}