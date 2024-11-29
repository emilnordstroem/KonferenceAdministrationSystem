package view.Deltager;

import domain.model.Deltager;

import java.util.ArrayList;

public class SearchAlgorithm {

    // Sorteret arrayList : bubble sort
    protected static ArrayList<Deltager> sortedArray(ArrayList<Deltager> deltagerListe) {
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
    protected static Deltager binaryPersonSearch(ArrayList<Deltager> sorteretDeltagerListe, String target){
        Deltager deltager = null;
        int left = 0;
        int right = sorteretDeltagerListe.size();
        while(deltager == null && left <= right){
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
