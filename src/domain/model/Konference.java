package domain.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Konference {
    private String navn;
    private LocalDate startDato;
    private LocalDate slutDato;
    private final ArrayList<Udflugt> udflugter = new ArrayList<>();
    private ArrayList<Hotel> hoteller = new ArrayList<>();
    private final ArrayList<Tilmelding> tilmeldinger = new ArrayList<>();
    private double prisPrDag;

    public Konference(String navn, LocalDate startDato, LocalDate slutDato, double prisPrDag, ArrayList<Hotel> hoteller) {
        this.navn = navn;
        if(startDato.isBefore(slutDato)){
            this.startDato = startDato;
            this.slutDato = slutDato;
        }
        this.prisPrDag = prisPrDag;

        if(hoteller != null){
            this.hoteller = new ArrayList<>(hoteller);
        }
    }

    public String getNavn(){
        return this.navn;
    }

    public LocalDate getStartDato() {
        return startDato;
    }

    public LocalDate getSlutDato() {
        return slutDato;
    }

    public ArrayList<Udflugt> getUdflugter() {
        return new ArrayList<>(udflugter);
    }

    // Back trace denne - skal rettes
    public Udflugt createUdflugt(String navn, Adresse adresse, LocalDate dato, String beskrivelse, double pris) {
        Udflugt udflugt = new Udflugt(navn, adresse, dato, beskrivelse, pris);
        udflugter.add(udflugt);
        return udflugt;
    }

    public void addFlugter (ArrayList<Udflugt> udflugter){
        this.udflugter.addAll(udflugter);
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

    public void removeHotel(Hotel hotel) {
        hoteller.remove(hotel);
    }

    public ArrayList<Tilmelding> getTilmeldinger() {
        return new ArrayList<>(tilmeldinger);
    }

    public void addTilmelding(Tilmelding tilmelding) {
        if(!tilmeldinger.contains(tilmelding)) {
            tilmeldinger.add(tilmelding);
        }
    }

    public void fjernTilmelding(Tilmelding tilmeldingTilFjernelse){
        tilmeldinger.remove(tilmeldingTilFjernelse);
        System.out.println("Tilmelding er fjernet fra konferencen");
    }

    public double getPrisPrDag() {
        return prisPrDag;
    }

    @Override
    public String toString() {
        return String.format("%s%n%s til %s (pris pr. dag: %.2f DKK)", navn, startDato, slutDato, prisPrDag);
    }

}
