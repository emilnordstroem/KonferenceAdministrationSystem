package domain.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Udflugt {
    private String navn;
    private Addresse addresse;
    private LocalDate dato;
    private String beskrivelse;
    private double pris;

    public Udflugt(String navn, Addresse addresse, LocalDate dato, String beskrivelse, double pris) {
        this.navn = navn;
        this.addresse = addresse;
        this.dato = dato;
        this.beskrivelse = beskrivelse;
        this.pris = pris;
    }

    public String getNavn() {
        return navn;
    }

    public double getPris() {
        return pris;
    }
}
