package domain.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Udflugt {
    private String navn;
    private Addresse addresse;
    private LocalDate dato;
    private LocalTime klokkeslætFra;
    private LocalTime klokkeslætTil;
    private String beskrivelse;
    private double pris;

    public Udflugt(String navn, Addresse addresse, LocalDate dato, LocalTime klokkeslætFra, LocalTime klokkeslætTil, String beskrivelse, double pris) {
        this.navn = navn;
        this.addresse = addresse;
        this.dato = dato;
        this.klokkeslætFra = klokkeslætFra;
        this.klokkeslætTil = klokkeslætTil;
        this.beskrivelse = beskrivelse;
        this.pris = pris;
    }

    public double getPris() {
        return pris;
    }
}
