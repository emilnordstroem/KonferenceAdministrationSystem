package view;

import java.util.stream.Stream;

public class Error {
    public static boolean blankTextField(String navn, String tlf){
        // Tjekker via Stream metoder om en forekommende string er blank
        boolean hasBlankTextFields = Stream.of(navn, tlf).anyMatch(String::isBlank);
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
}
