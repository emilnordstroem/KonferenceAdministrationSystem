package domain.controller;

import domain.model.*;
import storage.Storage;

import java.util.ArrayList;

public class ControllerDeltager {
    // Opret metoder -------------------------------------------------------------------
    // K1, K2, UC1
    public static Deltager opretDeltager(String forNavn, String efterNavn, String telefonNummer, Adresse adresse, Firma firma) {
        Deltager deltager = new Deltager(forNavn, efterNavn, telefonNummer, adresse, firma);
        Storage.addDeltager(deltager);
        return deltager;
    }

    public static double getSamletUdgifter(Deltager deltager){
        return deltager.getSamletUdgifter();
    }

    public static void fjernDeltager(Deltager deltager){
        for(Tilmelding tilmelding : deltager.getTilmeldinger()){
            Konference konference = tilmelding.getKonference();
            Hotel hotel = tilmelding.getHotel();
            konference.fjernTilmelding(tilmelding);
            hotel.removeTilmelding(tilmelding);
        }
        Storage.removeDeltager(deltager);
    }

    // Sorteret arrayList : bubble sort
    public static ArrayList<Deltager> sortedStringArray(ArrayList<Deltager> deltagerListe) {
        if(!deltagerListe.isEmpty()) {
            for (int outerIndex = 0; outerIndex < deltagerListe.size() - 1; outerIndex++) {
                for (int innerIndex = 0; innerIndex < deltagerListe.size() - outerIndex - 1; innerIndex++) {
                    String currentFornavn = deltagerListe.get(innerIndex).getForNavn();
                    String nextFornavn = deltagerListe.get(innerIndex + 1).getForNavn();
                    if(currentFornavn.compareTo(nextFornavn) > 0) {
                        Deltager temp = deltagerListe.get(innerIndex);
                        deltagerListe.set(innerIndex, deltagerListe.get(innerIndex + 1));
                        deltagerListe.set(innerIndex + 1, temp);
                    }
                }
            }
            return deltagerListe;
        }
        return null;
    }

    // Binary search algorithm
    public static Deltager binaryPersonSearch(ArrayList<Deltager> sorteretDeltagerListe, String target){
        Deltager deltager = null;
        int left = 0;
        int right = sorteretDeltagerListe.size();
        while(left <= right){
            int middle = (left + right) / 2;
            Deltager kandidat = sorteretDeltagerListe.get(middle);
            if(kandidat.getForNavn().compareTo(target) == 0){
                System.out.println("Fundet!");
                return kandidat;
            } else if (kandidat.getForNavn().compareTo(target) > 0){
                System.out.println("Tilstede i venstre halvdel");
                right = middle - 1;
            } else {
                System.out.println("Tilstede i højre halvdel");
                left = middle + 1;
            }
        }
        return deltager;
    }
}
