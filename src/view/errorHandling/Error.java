package view.errorHandling;

import java.time.LocalDate;
import java.util.stream.Stream;

public class Error {
    public static boolean blankTextField(String string1, String string2){
        // Tjekker via Stream metoder om en forekommende string er blank
        boolean hasBlankTextFields = Stream.of(string1, string2).anyMatch(String::isBlank);
        if(hasBlankTextFields){
            System.out.println("Forekommer blanke felter i oprettelse af firma");
            return false;
        }
        return true;
    }

    public static boolean blankTextField(String navn, String enkeltværelsesPrisInput, String dobbeltværelsesPrisInput,
                                         String vejInput, String bygningNrInput,
                                         String byInput, String landInput){
        // Tjekker via Stream metoder om en forekommende string er blank
        return Stream.of(navn, enkeltværelsesPrisInput, dobbeltværelsesPrisInput, vejInput, bygningNrInput, byInput, landInput).anyMatch(String::isBlank);
    }

    public static boolean conflictingDates(LocalDate date1, LocalDate date2){
        if(date1 != null && date2 != null){
            if(date2.isBefore(date1)){
                System.out.println("Første dato kommer efter sidste dato");
                return true;
            }
        }
        return false;
    }
}
