package view.Deltager;

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

        setSøgningHBox();
        setDeltagerListView();
        setBeregnPrisHBox();
        setNonEdibleTextFields();

        søgKnap.setOnMouseClicked(e -> {
            System.out.println("Søgning...");
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

        Button opretDeltagerButton = new Button("Opret deltager");
        this.add(opretDeltagerButton, 0, 2);
        opretDeltagerButton.setOnAction(event -> opretDeltagerAction());

        Button sletDeltagerButton = new Button("Slet deltager");
        this.add(sletDeltagerButton, 1,2);
        sletDeltagerButton.setOnAction(event -> {
            Deltager deltager = deltagereListView.getSelectionModel().getSelectedItem();
            fjernDeltager(deltager);
        });
    }

    private void setSøgningHBox(){
        HBox søgningHBox = new HBox(10);
        Label deltagereLabel = new Label("Deltagere:");
        personSøgning.setPrefWidth(50);
        deltagerInto.setPrefWidth(450);
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
    private Deltager binaryPersonSearch(ArrayList<Deltager> sorteretDeltagerListe, String target){
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

    private void opretDeltagerAction() {
        new OpretDeltagerWindow().showAndWait();
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

    private void setDeltagerListView(){
        deltagereListView.setPrefWidth(1215);
        deltagereListView.getItems().setAll(Storage.getDeltagere());
        this.add(deltagereListView, 0, 1, 6,1);
    }
}