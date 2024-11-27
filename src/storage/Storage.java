package storage;

import domain.model.Deltager;
import domain.model.Hotel;
import domain.model.Konference;
import domain.model.Tilmelding;
import java.util.ArrayList;


public class Storage {
    private static final ArrayList<Konference> konferencer = new ArrayList<>();
    private static final ArrayList<Hotel> hoteller = new ArrayList<>();
    private static final ArrayList<Deltager> deltagere = new ArrayList<>();

    // Konference Methods -------------------------------------------------------------------
    public static void addKonference(Konference konference) {
        if (!konferencer.contains(konference)) {
            konferencer.add(konference);
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
        }
    }

    public static ArrayList<Deltager> getDeltagere() {
        return new ArrayList<>(deltagere);
    }

    public static void removeDeltager(Deltager deltager) {
        deltagere.remove(deltager);
    }

}
