package domain.model;

import java.util.InputMismatchException;

public class HotelTillæg {
    private String navn;
    private double pris;
    private Hotel hotel;

    public HotelTillæg(String navn, double pris, Hotel hotel) {
        this.navn = navn;
        this.pris = pris;
        hotel.addHotelTillæg(this);
        if(pris <= 0) {
            throw new InputMismatchException("Pris skal være et positivt tal");
        }
    }

    public String getNavn() {
        return navn;
    }

    public double getPris() {
        return pris;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public void setPris(double pris) {
        this.pris = pris;
    }

    @Override
    public String toString() {
        return navn + "(" + pris + "kr.)";
    }
}
