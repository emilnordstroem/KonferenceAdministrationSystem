package view.konferenceTabPane.konference.udflugt;

import domain.controller.ControllerUdflugt;
import domain.model.Adresse;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.time.LocalDate;

public class OpretUdflugtWindow extends Stage {
    private TextField navnTextField = new TextField();
    private DatePicker datoDatepicker = new DatePicker();
    private TextField beskrivelseTextField = new TextField();
    private TextField prisTextField = new TextField();
    private TextField vejnavnTextField = new TextField();
    private TextField bygningsNrTextField = new TextField();
    private TextField byTextField = new TextField();
    private TextField landTextField = new TextField();

    private Button opretUdflugtBtn = new Button("Opret Udflugt");
    private Button cancelButton = new Button("Cancel");

    public OpretUdflugtWindow() {
        this.setTitle("Opret udlflugt");
        GridPane pane = new GridPane();
        initContent(pane);
        this.setScene(new Scene(pane));
    }

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        addbuttons(pane);

        Label navnLbl = new Label("Navn");
        Label datoLbl = new Label("Dato");
        Label beskrivelseLbl = new Label("Beskrivelse");
        Label prisLbl = new Label("Pris");
        Label vejnavnLbl = new Label("Vejnavn");
        Label bygningsnrLbl = new Label("Bygnings nummer");
        Label byLbl = new Label("By");
        Label landLbl = new Label("Land");

        pane.add(navnLbl,0,0);
        pane.add(datoLbl,0,2);
        pane.add(beskrivelseLbl,0,4);
        pane.add(prisLbl,0,6);
        pane.add(vejnavnLbl,0,8);
        pane.add(bygningsnrLbl,0,10);
        pane.add(byLbl,0,12);
        pane.add(landLbl,0,14);

        pane.add(navnTextField,0,1);
        pane.add(datoDatepicker,0,3);
        pane.add(beskrivelseTextField,0,5);
        pane.add(prisTextField,0,7);
        pane.add(vejnavnTextField,0,9);
        pane.add(bygningsNrTextField,0,11);
        pane.add(byTextField,0,13);
        pane.add(landTextField,0,15);

        cancelButton.setOnAction(event -> close());

        opretUdflugtBtn.setOnAction(event -> opretUdflugt());
    }

    private void addbuttons(GridPane pane){
        HBox buttonHBox= new HBox(1);
        buttonHBox.getChildren().addAll(opretUdflugtBtn, cancelButton);
        pane.add(buttonHBox,0,16);
    }

    private void opretUdflugt(){
        String vejNavnInput = vejnavnTextField.getText().trim();
        String bygningsNrInput = bygningsNrTextField.getText().trim();
        String byInput = byTextField.getText().trim();
        String landInput = landTextField.getText().trim();

        Adresse adresse = new Adresse(vejNavnInput,bygningsNrInput,byInput,landInput);

        String navnInput = navnTextField.getText().trim();
        LocalDate datoInput = datoDatepicker.getValue();
        String besrkvilseInput = beskrivelseTextField.getText().trim();
        double prisInput = Double.parseDouble(prisTextField.getText());

        ControllerUdflugt.opretUdflugt(navnInput, adresse, datoInput, besrkvilseInput, prisInput);
        close();
    }
}
