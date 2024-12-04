package view.deltagerTabPane;

import domain.controller.ControllerDeltager;
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

import static view.errorHandling.Error.blankTextField;

public class OpretDeltagerWindow extends Stage {
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

    public OpretDeltagerWindow() {
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
        firmaComboBox.getItems().setAll(Storage.getFirmaer());
    }

    private void setButtons(GridPane pane){
        pane.add(registerFirmaBtn, 1, 12);
        pane.add(opretBtn,0,15);
        pane.add(cancelBtn,1,15);
        pane.add(errorLabel, 0, 13, 2, 1);
        errorLabel.setStyle("-fx-text-fill: red");
    }

    // Ikke korrekt fejlhåndtering ved oprettelse uden indput
    private void okAction(){
        String fornavn = fornavnTextField.getText().trim();
        String efternavn = efternavnTextField.getText().trim();
        String tlf = tlfTextField.getText().trim();

        String vejInput = vejNavnTextField.getText().trim();
        String bygningNrInput = bygningsNrTextField.getText().trim();
        String byInput = byTextField.getText().trim();
        String landInput = landTextField.getText().trim();

        if(blankTextField(fornavn, efternavn, tlf, vejInput, bygningNrInput, byInput, landInput)){
            errorLabel.setText("Udfyld alle krævet felter");
        } else {
            Adresse adresse = new Adresse(vejInput, bygningNrInput, byInput, landInput);
            Firma firma = firmaComboBox.getSelectionModel().getSelectedItem();

            if(firma == null){
                ControllerDeltager.opretDeltager(fornavn, efternavn, tlf, adresse, null);
                System.out.println("Deltager er korrekt oprettet uden firma");
            } else {
                ControllerDeltager.opretDeltager(fornavn, efternavn, tlf, adresse, firma);
                System.out.println("Deltager er korrekt oprettet med firma");
            }
            hide();
        }
    }

    private void registerFirma(){
        new OpretFirmaWindow().showAndWait();
        firmaComboBox.getItems().setAll(Storage.getFirmaer());
    }
}