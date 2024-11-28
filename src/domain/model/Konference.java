package domain.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Konference {
    private String navn;
    private LocalDate startDato;
    private LocalDate slutDato;
    private final ArrayList<Udflugt> udflugter = new ArrayList<>();
    private final ArrayList<Hotel> hoteller = new ArrayList<>();
    private final ArrayList<Tilmelding> tilmeldinger = new ArrayList<>();
    private double prisPrDag;

    public Konference(String navn, LocalDate startDato, LocalDate slutDato, double prisPrDag) {
        this.navn = navn;
        if(startDato.isBefore(slutDato)){
            this.startDato = startDato;
            this.slutDato = slutDato;
        }
        this.prisPrDag = prisPrDag;
    }

    public ArrayList<Udflugt> getUdflugter() {
        return new ArrayList<>(udflugter);
    }

    public Udflugt createUdflugt(String navn, Adresse adresse, LocalDate dato, String beskrivelse, double pris) {
        Udflugt udflugt = new Udflugt(navn, this, adresse, dato, beskrivelse, pris);
        udflugter.add(udflugt);
        return udflugt;
    }

    public ArrayList<Hotel> getHoteller() {
        return new ArrayList<>(hoteller);
    }

    public void addHotel(Hotel hotel) {
        if(!hoteller.contains(hotel)) {
            hoteller.add(hotel);
            hotel.addKonference(this);
        }
    }

    public ArrayList<Tilmelding> getTilmeldinger() {
        return new ArrayList<>(tilmeldinger);
    }

    public void addTilmelding(Tilmelding tilmelding) {
        if(!tilmeldinger.contains(tilmelding)) {
            tilmeldinger.add(tilmelding);
        }
    }

    public double getPrisPrDag() {
        return prisPrDag;
    }

    @Override
    public String toString() {
        return String.format("%s fra %s til %s (pris pr. dag: %.2f kr.).", navn, startDato, slutDato, prisPrDag);
    }

}
