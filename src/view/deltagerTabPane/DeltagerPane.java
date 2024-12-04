package view.deltagerTabPane;

import domain.controller.ControllerDeltager;
import domain.model.Deltager;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import storage.Storage;
import java.util.ArrayList;

import static view.deltagerTabPane.SearchAlgorithm.sortedStringArray;

public class DeltagerPane extends GridPane {
    private final ListView<Deltager> deltagereListView = new ListView<>();
    private final TextField prisTextField = new TextField();
    private final TextField personSøgning = new TextField();
    private final TextField deltagerInto = new TextField();

    private final Button opretDeltagerButton = new Button("Opret deltager");
    private final Button sletDeltagerButton = new Button("Slet deltager");
    private final Button søgKnap = new Button("Søg");
    private final Button beregnPrisButton = new Button("Beregn pris");

    public DeltagerPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        setSøgningHBox();
        setDeltagerListView();
        setButtonHBox();
        setBeregnPrisHBox();
        setNonEdibleTextFields();

        søgKnap.setOnMouseClicked(e -> {
            System.out.println("Søgning...");
            String søgning = personSøgning.getText();

            ArrayList<Deltager> sortedDeltagerList = sortedStringArray(Storage.getDeltagere());

            if(!søgning.isBlank() && !sortedDeltagerList.isEmpty()){
                Deltager deltager = SearchAlgorithm.binaryPersonSearch(sortedDeltagerList, søgning);
                if(deltager != null){
                    deltagerInto.setText(infoDeltager(deltager));
                    deltagereListView.getSelectionModel().select(deltager); // Beregn pris ud fra søgning
                    personSøgning.clear();
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
                double samletUdgifter = ControllerDeltager.getSamletUdgifter(deltager);
                prisTextField.setText(String.format("%.2f DKK", samletUdgifter));
            } catch (NullPointerException exception){
                System.out.println("NullPointerException ved samlet udgifter i DeltagerPane.java");
            }
        });

        opretDeltagerButton.setOnAction(event -> opretDeltagerAction());

        sletDeltagerButton.setOnAction(event -> {
            Deltager deltager = deltagereListView.getSelectionModel().getSelectedItem();
            if(deltager != null){
                fjernDeltager(deltager);
            }
        });
    }

    private void setSøgningHBox(){
        HBox søgningHBox = new HBox(10);
        Label deltagereLabel = new Label("Deltagere:");
        personSøgning.setPrefWidth(50);
        deltagerInto.setPrefWidth(450);
        søgningHBox.getChildren().addAll(deltagereLabel, personSøgning, deltagerInto, søgKnap);
        this.add(søgningHBox, 0,0);
    }

    private void setBeregnPrisHBox(){
        HBox beregnPrisHBox = new HBox(10);
        beregnPrisHBox.getChildren().addAll(beregnPrisButton, prisTextField);
        this.add(beregnPrisHBox, 2, 0);
    }

    private void setButtonHBox(){
        HBox buttonHBox = new HBox(10);
        buttonHBox.getChildren().addAll(opretDeltagerButton, sletDeltagerButton);
        this.add(buttonHBox, 0, 2);
    }

    private void opretDeltagerAction() {
        new OpretDeltagerWindow().showAndWait();
        deltagereListView.getItems().setAll(sortedStringArray(Storage.getDeltagere()));
    }

    private void fjernDeltager(Deltager deltager){
        new ConfirmDeleteWindow(deltager).showAndWait();
        deltagereListView.getItems().setAll(sortedStringArray(Storage.getDeltagere()));
    }

    private void setNonEdibleTextFields(){
        deltagerInto.setEditable(false);
        prisTextField.setEditable(false);
    }

    private void setDeltagerListView(){
        deltagereListView.setPrefWidth(1000);
        deltagereListView.getItems().setAll(sortedStringArray(Storage.getDeltagere()));
        this.add(deltagereListView, 0, 1, 6,1);
    }

    private String infoDeltager(Deltager deltager) {
        return String.format("%s (ID: %d) | Tlf.nr: %s | Adresse: %s", deltager.getFuldeNavn(), deltager.getId(), deltager.getTelefonnummer(), deltager.getAdresse().toString());
    }
}