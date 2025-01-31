package domain.model;

import java.util.ArrayList;
import java.util.Random;

public class Deltager {
    private int id;
    private String forNavn;
    private String efterNavn;
    private String telefonnummer;
    private Adresse adresse;
    private final ArrayList<Tilmelding> tilmeldinger = new ArrayList<>();
    private Firma firma;
    private double udgifter;

    public Deltager(String forNavn, String efterNavn, String telefonnummer, Adresse adresse, Firma Firma){
        this.id = generateID();
        this.forNavn = forNavn;
        this.efterNavn = efterNavn;
        this.telefonnummer = telefonnummer;
        this.adresse = adresse;
        this.firma = Firma;
        this.udgifter = 0;
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

    public Firma getFirma() {
        return firma;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setUdgifter(double udgifter) {
        this.udgifter = udgifter;
    }

    public ArrayList<Tilmelding> getTilmeldinger() {
        return new ArrayList<>(tilmeldinger);
    }

    public void addTilmelding(Tilmelding tilmelding){
        if(!tilmeldinger.contains(tilmelding)){
            tilmeldinger.add(tilmelding);
        }
    }

    public void fjernTilmelding(Tilmelding tilmelding){
        if(tilmeldinger.contains(tilmelding)){
            tilmeldinger.remove(tilmelding);
            opdaterSamletUdgifter();
        }
    }

    public double getSamletUdgifter(){
        double samletUdgifter = 0;
        if(!tilmeldinger.isEmpty()){
            for(Tilmelding tilmelding : tilmeldinger){
                samletUdgifter += tilmelding.getSamletTilmeldingsUdgift();
            }
        }
        System.out.println("getSamletUdgifter(): " + samletUdgifter);
        setUdgifter(samletUdgifter);
        return samletUdgifter;
    }

    public void opdaterSamletUdgifter(){
        System.out.println("Opdater samlet udgifter for " + this.getFuldeNavn());
        setUdgifter(getSamletUdgifter());
    }

    private int generateID(){
        // If we store data on AttendanceID - we could make each unique to the attendance
        return new Random().nextInt(111_111, 999_999) + 1;
    }

    @Override
    public String toString(){
        return String.format("%s %s (ID: %d)%nTlf.nr: %s%nAdresse: %s", forNavn, efterNavn, id, telefonnummer, adresse);
    }
}