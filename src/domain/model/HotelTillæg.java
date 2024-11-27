package domain.model;

public class HotelTillæg {
    private String navn;
    private double pris;
    private Hotel hotel;


    public HotelTillæg(String navn, double pris, Hotel hotel) {
        this.navn = navn;
        this.pris = pris;
        this.hotel = hotel;
        hotel.addHotelTillæg(this);
    }

    public String getNavn() {
        return navn;
    }

    public double getPris() {
        return pris;
    }
}
