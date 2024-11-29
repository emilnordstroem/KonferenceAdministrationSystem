package storage;

import domain.model.*;

import java.util.ArrayList;


public class Storage {
    private static final ArrayList<Konference> konferencer = new ArrayList<>();
    private static final ArrayList<Hotel> hoteller = new ArrayList<>();
    private static final ArrayList<Deltager> deltagere = new ArrayList<>();
    private static final ArrayList<Firma> firmaer = new ArrayList<>();
    private static final ArrayList<Udflugt> udflugter = new ArrayList<>();

    // Konference Methods -------------------------------------------------------------------
    public static void addKonference(Konference konference) {
        if (!konferencer.contains(konference)) {
            konferencer.add(konference);
            System.out.println("Konference er tilføjet i Storage");
        }
    }

    public static ArrayList<Konference> getKonferencer() {
        return new ArrayList<>(konferencer);
    }

    public static void removeKonference(Konference konference) {
        konferencer.remove(konference);
    }

    // Hotel Methods -------------------------------------------------------------------------------
    public static void addHotel(Hotel hotel) {
        if (!hoteller.contains(hotel)) {
            hoteller.add(hotel);
            System.out.println("Hotel er tilføjet i Storage");
        }
    }

    public static ArrayList<Hotel> getHoteller() {
        return new ArrayList<>(hoteller);
    }

    public static void removeHotel(Hotel hotel) {
        hoteller.remove(hotel);
    }

    // Deltager Methods ----------------------------------------------------------------------------
    public static void addDeltager(Deltager deltager) {
        if (!deltagere.contains(deltager)) {
            deltagere.add(deltager);
            System.out.println("Deltager er tilføjet i Storage");
        }
    }

    public static ArrayList<Deltager> getDeltagere() {
        return new ArrayList<>(deltagere);
    }

    public static void removeDeltager(Deltager deltager) {
        deltagere.remove(deltager);
    }

    public static void addFirma(Firma firma){
        if(!firmaer.contains(firma)){
            firmaer.add(firma);
            System.out.println("Firma er tilføjet i Storage");
        }
    }

    public static ArrayList<Firma> getFirmaer() {
        return new ArrayList<>(firmaer);
    }

    // Udflugter Methods ----------------------------------------------------------------------------
    public static ArrayList<Udflugt> getUdflugter() {
        return new ArrayList<>(udflugter);
    }

    public static void removeUdflugt(Udflugt udflugt){
        udflugter.remove(udflugt);
    }

    public static void addUdflugt(Udflugt udflugt){
        if (!udflugter.contains(udflugt)){
            udflugter.add(udflugt);
            System.out.println("udflugt er tilføjet");
        }
    }

    public static Hotel getHotelByNavn(String navn) {
        for (Hotel hotel : hoteller) {
            if(hotel.getNavn().equals(navn)) {
                return hotel;
            }
        }
        return null;
    }
}
