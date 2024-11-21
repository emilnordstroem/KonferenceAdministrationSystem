package domain.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Konference {
    private String navn;
    private LocalDate startDato;
    private LocalDate slutDato;
    private ArrayList<Udflugt> udflugter;
    private ArrayList<Hotel> hoteller;
    private ArrayList<Tilmelding> tilmeldinger;
    private double prisPrDag;

    public Konference(String navn, LocalDate startDato, LocalDate slutDato, double prisPrDag) {
        this.navn = navn;
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.prisPrDag = prisPrDag;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public LocalDate getStartDato() {
        return startDato;
    }

    public void setStartDato(LocalDate startDato) {
        this.startDato = startDato;
    }

    public LocalDate getSlutDato() {
        return slutDato;
    }

    public void setSlutDato(LocalDate slutDato) {
        this.slutDato = slutDato;
    }

    public ArrayList<Udflugt> getUdflugter() {
        return new ArrayList<>(udflugter);
    }

    public void setUdflugter(ArrayList<Udflugt> udflugter) {
        this.udflugter = udflugter;
    }

    public ArrayList<Hotel> getHoteller() {
        return hoteller;
    }

    public void setHoteller(ArrayList<Hotel> hoteller) {
        this.hoteller = hoteller;
    }

    public ArrayList<Tilmelding> getTilmeldinger() {
        return tilmeldinger;
    }

    public void setTilmeldinger(ArrayList<Tilmelding> tilmeldinger) {
        this.tilmeldinger = tilmeldinger;
    }

    public double getPrisPrDag() {
        return prisPrDag;
    }

    public void setPrisPrDag(double prisPrDag) {
        this.prisPrDag = prisPrDag;
    }
}
