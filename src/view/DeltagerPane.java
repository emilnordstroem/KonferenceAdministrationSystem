package view;

import domain.model.Deltager;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import storage.Storage;

import java.util.ArrayList;

public class DeltagerPane extends GridPane {
    private final ListView<Deltager> deltagereListView = new ListView<>();
    private final TextField prisTextField = new TextField();
    private final TextField personSøgning = new TextField();
    private final TextField deltagerInto = new TextField();
    private final Button søgKnap = new Button("Søg");
    private final Button beregnPrisButton = new Button("Beregn pris");

    public DeltagerPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

//        ColumnConstraints colConstraints = new ColumnConstraints();
//        colConstraints.setHgrow(Priority.NEVER); // Kolonner skalerer ikke unødvendigt
//        colConstraints.setMinWidth(Control.USE_COMPUTED_SIZE); // Brug kun nødvendig plads
//        this.getColumnConstraints().addAll(
//                new ColumnConstraints(239), // Opret deltager
//                new ColumnConstraints(238), // Opdater deltager
//                new ColumnConstraints(238), // Slet deltager
//                new ColumnConstraints(238), // Søg deltager
//                new ColumnConstraints(77), // Beregn pris
//                new ColumnConstraints(85) // Prisen i kr
//        );

        setSøgningHBox();
        setBeregnPrisHBox();
        setNonEdibleTextFields();

        søgKnap.setOnMouseClicked(e -> {
            System.out.println("Enter blev trykket");
            String søgning = personSøgning.getText();
            ArrayList<Deltager> sortedDeltagerList = sortedArray(Storage.getDeltagere());

            if(!søgning.isBlank() && !sortedDeltagerList.isEmpty()){
                Deltager deltager = binaryPersonSearch(sortedDeltagerList, søgning);
                if(deltager != null){
                    deltagerInto.setText(deltager.toString());
                } else {
                    deltagerInto.setText("[Deltager findes ikke på listen]");
                }
            } else {
                deltagerInto.setText("Indsæt navn");
                System.out.println("Blank søgning");
            }
        });

        beregnPrisButton.setOnAction(event -> {
            Deltager deltager = deltagereListView.getSelectionModel().getSelectedItem();
            if(deltager == null){
                System.out.println("Ingen deltager er valgt");
            }
            try{
                assert deltager != null;
                double samletUdgifter = deltager.getSamletUdgifter();
                prisTextField.setText(String.format("%.2f DKK", samletUdgifter));
            } catch (NullPointerException exception){
                System.out.println("NullPointerException ved samlet udgifter i DeltagerPane.java");
            }
        });

        deltagereListView.setPrefWidth(1215);
        deltagereListView.getItems().setAll(sortedArray(Storage.getDeltagere()));
        this.add(deltagereListView, 0, 1, 6,1);

        Button opretDeltagerButton = new Button("Opret deltager");
        this.add(opretDeltagerButton, 0, 2);
        opretDeltagerButton.setOnAction(event -> opretDeltagerAction());

        Button opdaterDeltagerButton = new Button("Opdater deltager");
        this.add(opdaterDeltagerButton, 1, 2);

        Button sletDeltagerButton = new Button("Slet deltager");
        this.add(sletDeltagerButton, 2,2);
        sletDeltagerButton.setOnAction(event -> {
            Deltager deltager = deltagereListView.getSelectionModel().getSelectedItem();
            fjernDeltager(deltager);
        });

    }

    private void setSøgningHBox(){
        HBox søgningHBox = new HBox(10);
        Label deltagereLabel = new Label("Deltagere:");
        søgningHBox.getChildren().addAll(deltagereLabel, personSøgning, deltagerInto, søgKnap);
        this.add(søgningHBox, 1,0);
    }

    private void setBeregnPrisHBox(){
        HBox beregnPrisHBox = new HBox(10);
        beregnPrisHBox.getChildren().addAll(beregnPrisButton, prisTextField);
        this.add(beregnPrisHBox, 2, 0);
    }

    // Sorteret arrayList : bubble sort
    private ArrayList<Deltager> sortedArray(ArrayList<Deltager> deltagerListe) {
        if(deltagerListe != null) {
            for(int outerIndex = 0; outerIndex < deltagerListe.size() - 1; outerIndex++){
                for(int innerIndex = outerIndex + 1; innerIndex < deltagerListe.size(); innerIndex++){
                    String currentLastName = deltagerListe.get(outerIndex).getForNavn();
                    String nextLastName = deltagerListe.get(innerIndex).getForNavn();
                    if(currentLastName.compareTo(nextLastName) > 0){
                        Deltager tempDeltager = deltagerListe.get(outerIndex);
                        deltagerListe.set(outerIndex, deltagerListe.get(innerIndex));
                        deltagerListe.set(innerIndex, tempDeltager);
                    } else if(currentLastName.compareTo(nextLastName) == 0){

                    }
                }
            }
            return deltagerListe;
        }
        return null;
    }

    // Binary search algorithm
    private Deltager binaryPersonSearch(ArrayList<Deltager> sorteretDeltagerListe, String target){
        Deltager deltager = null;
        int left = 0;
        int right = 0;
        while(deltager == null && left <= right){
            int middle = (left + right) / 2;
            Deltager kandidat = sorteretDeltagerListe.get(middle);
            if(kandidat.getForNavn().compareTo(target) == 0){
                deltager = kandidat;
            } else if (kandidat.getForNavn().compareTo(target) > 0){
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return deltager;
    }

    private void opretDeltagerAction() {
        new DeltagerWindow().showAndWait();
        deltagereListView.getItems().setAll(Storage.getDeltagere());
    }

    private void fjernDeltager(Deltager deltager){
        new ConfirmDeleteWindow(deltager).showAndWait();
        deltagereListView.getItems().setAll(Storage.getDeltagere());
    }

    private void setNonEdibleTextFields(){
        deltagerInto.setEditable(false);
        prisTextField.setEditable(false);
    }
}