package view.Deltager;

import domain.controller.Controller;
import domain.model.Adresse;
import domain.model.Firma;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import storage.Storage;

import java.util.stream.Stream;

public class DeltagerWindow extends Stage {
    private TextField fornavnTextField = new TextField();
    private TextField efternavnTextField = new TextField();
    private TextField tlfTextField = new TextField();

    private TextField vejNavnTextField = new TextField();
    private TextField bygningsNrTextField = new TextField();
    private TextField byTextField = new TextField();
    private TextField landTextField = new TextField();

    private final ComboBox<Firma> firmaComboBox = new ComboBox<>();
    private final Button registerFirmaBtn = new Button("Register Firma");

    private Label errorLabel = new Label();

    private Button opretBtn = new Button("Opret");
    private Button cancelBtn = new Button("Cancel");


    public DeltagerWindow() {
        this.setTitle("Opret deltager");
//        initStyle(StageStyle.UTILITY);
//        initModality(Modality.APPLICATION_MODAL);
//        setResizable(false);
        GridPane pane = new GridPane();
        initContent(pane);
        this.setScene(new Scene(pane));
    }

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        setNavnOgTelefonNummer(pane);
        setAdresseOgFirma(pane);
        setButtons(pane);

        registerFirmaBtn.setOnAction(event -> {
            registerFirma();
        });
        opretBtn.setOnAction(event -> {
            okAction();
            hide();
        });
        cancelBtn.setOnAction(event -> hide());
    }

    private void setNavnOgTelefonNummer(GridPane pane){
        Label lblName = new Label("Fornavn");
        pane.add(lblName, 0, 0, 2, 1);
        pane.add(fornavnTextField, 0, 1, 2, 1);

        Label lblEfternavn = new Label("Efternavn");
        pane.add(lblEfternavn,0,2, 2, 1);
        pane.add(efternavnTextField,0,3, 2, 1);

        Label lblTlf = new Label("Tlf.nr.");
        pane.add(lblTlf,0,4, 2, 1);
        pane.add(tlfTextField,0,5, 2, 1);
    }

    private void setAdresseOgFirma(GridPane pane){
        Label adresseLbl = new Label("Adresse");
        pane.add(adresseLbl, 0, 6); // Span across 2 columns

        Label vejNavnLabel = new Label("Vej:");
        pane.add(vejNavnLabel, 0, 7);
        pane.add(vejNavnTextField, 1, 7);

        Label bygningsNrLabel = new Label("Bygningsnr.:");
        pane.add(bygningsNrLabel, 0, 8);
        pane.add(bygningsNrTextField, 1, 8);

        Label byLabel = new Label("By:");
        pane.add(byLabel, 0, 9);
        pane.add(byTextField, 1, 9);

        Label landLabel = new Label("Land:");
        pane.add(landLabel, 0, 10);
        pane.add(landTextField, 1, 10);

        Label firmaLbl = new Label("Firma (Valgfrit)");
        pane.add(firmaLbl,0,11);
        pane.add(firmaComboBox,0,12);
    }

    private void setButtons(GridPane pane){
        pane.add(registerFirmaBtn, 1, 11);
        pane.add(opretBtn,0,13);
        pane.add(cancelBtn,1,13);
        pane.add(errorLabel, 0, 12);
        errorLabel.setStyle("-fx-text-fill: red");
    }

    private void okAction(){
        String fornavn = fornavnTextField.getText().trim();
        String efternavn = efternavnTextField.getText().trim();
        String tlf = tlfTextField.getText().trim();

        String vejInput = vejNavnTextField.getText().trim();
        String bygningNrInput = bygningsNrTextField.getText().trim();
        String byInput = byTextField.getText().trim();
        String landInput = landTextField.getText().trim();

        if(!blankTextField(fornavn, efternavn, tlf, vejInput, bygningNrInput, byInput, landInput)){
            return;
        }

        Adresse adresse = new Adresse(vejInput, bygningNrInput, byInput, landInput);
        Firma firma = firmaComboBox.getSelectionModel().getSelectedItem();

        if(firma == null){
            Controller.opretDeltager(fornavn, efternavn, tlf, adresse, null);
            System.out.println("Deltager er korrekt oprettet uden firma");
        } else {
            Controller.opretDeltager(fornavn, efternavn, tlf, adresse, firma);
            System.out.println("Deltager er korrekt oprettet med firma");
        }
    }

    private boolean blankTextField(String fornavn, String efternavn, String tlf, String vejInput, String bygningNrInput,
                                   String byInput, String landInput){
        // Tjekker via Stream metoder om en forekommende string er blank
        boolean hasBlankTextFields = Stream.of(fornavn, efternavn, tlf, vejInput, bygningNrInput, byInput, landInput).anyMatch(String::isBlank);
        if(hasBlankTextFields){
            System.out.println("Forekommer blanke felter i oprettelse af deltager");
            errorLabel.setText("Ikke alle felter er udfyldt");
            return false;
        }
        return true;
    }

    private void registerFirma(){
        new FirmaWindow().showAndWait();
        firmaComboBox.getItems().setAll(Storage.getFirmaer());
    }
}