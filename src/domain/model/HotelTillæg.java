package domain.model;

import java.util.InputMismatchException;

public class HotelTillæg {
    private String navn;
    private double pris;

    public HotelTillæg(String navn, double pris, Hotel hotel) throws InputMismatchException {
        this.navn = navn;
        if(pris <= 0) {
            throw new InputMismatchException("Prisen skal være et positivt tal.");
        }
        this.pris = pris;
        hotel.addHotelTillæg(this);
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

    public void setPris(double pris) throws InputMismatchException {
        if(pris <= 0) {
            throw new InputMismatchException("Prisen skal være et positivt tal.");
        } else {
            this.pris = pris;
        }
    }

    @Override
    public String toString() {
        return String.format("%s(%.2fkr.)", navn, pris);
    }
}
