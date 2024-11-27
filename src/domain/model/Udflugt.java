package domain.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Udflugt {
    private String navn;
    private Konference konference;
    private Addresse addresse;
    private LocalDate dato;
    private String beskrivelse;
    private double pris;
    private ArrayList<Tilmelding> tilmeldinger = new ArrayList<>();

    Udflugt(String navn, Konference konference, Addresse addresse, LocalDate dato, String beskrivelse, double pris) {
        this.navn = navn;
        this.konference = konference;
        this.addresse = addresse;
        this.dato = dato;
        this.beskrivelse = beskrivelse;
        this.pris = pris;
    }

    public String getNavn() {
        return navn;
    }


    public Konference getKonference() {
        return konference;
    }

    public Addresse getAddresse() {
        return addresse;
    }


    public LocalDate getDato() {
        return dato;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public double getPris() {
        return pris;
    }

    public ArrayList<Tilmelding> getTilmeldinger() {
        return new ArrayList<>(tilmeldinger);
    }

    public void addTilmelding(Tilmelding tilmelding) {
        if(!tilmeldinger.contains(tilmelding)) {
            tilmeldinger.add(tilmelding);
            tilmelding.addUdflugt(this);
        }
    }

    @Override
    public String toString() {
        String s = String.format("%s d. %s (pris for udflugt: %.2f). Den har de følgende deltagere:\n", navn, dato, pris);

        for (Tilmelding tilmelding : tilmeldinger) {
            s+= String.format("%s (%d %s)\n", tilmelding.getLedsagerNavn(), tilmelding.getDeltager().getId(), tilmelding.getDeltager().getFuldeNavn());
        }
        return s;
    }
}
