package domain.model;

import java.util.ArrayList;
import java.util.Random;

public class Deltager {
    private int id;
    private String forNavn;
    private String efterNavn;
    private String telefonnummer;
    private Addresse addresse;
    private final ArrayList<Tilmelding> tilmeldinger = new ArrayList<>();
    private Firma firma;

    public Deltager(String forNavn, String efterNavn, String telefonnummer, Addresse addresse){
        this.id = generateID();
        this.forNavn = forNavn;
        this.efterNavn = efterNavn;
        this.telefonnummer = telefonnummer;
        this.addresse = addresse;
    }

    public int getId() {
        return id;
    }

    public String getForNavn() {
        return forNavn;
    }

    public String getEfterNavn() {
        return efterNavn;
    }

    public String getFuldeNavn() {
        return forNavn + " " + efterNavn;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public Addresse getAddresse() {
        return addresse;
    }

    public Firma getFirma() {
        return firma;
    }

    public void setFirma(Firma firma) {
        if(this.firma != firma) {
            this.firma = firma;
        }
    }

    public ArrayList<Tilmelding> getTilmeldinger() {
        return new ArrayList<>(tilmeldinger);
    }

    public void addTilmelding(Tilmelding tilmelding){
        if(!tilmeldinger.contains(tilmelding)){
            tilmeldinger.add(tilmelding);
        }
    }

    public double getSamletUdgifter(){
        double samletUdgifter = 0;
        if(!tilmeldinger.isEmpty()){
            for(Tilmelding tilmelding : tilmeldinger){
                samletUdgifter += tilmelding.getSamletTilmeldingsUdgift();
            }
        }
        return samletUdgifter;
    }

    private int generateID(){
        // If we store data on AttendanceID - we could make each unique to the attendance
        return new Random().nextInt(111_111, 999_999) + 1;
    }
}