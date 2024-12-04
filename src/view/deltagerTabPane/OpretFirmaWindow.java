package view.deltagerTabPane;

import domain.controller.ControllerFirma;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import static view.errorHandling.Error.blankTextField;

public class OpretFirmaWindow extends Stage {
    private final TextField firmaNavnTextField = new TextField();
    private final TextField firmaTelefonNummerTextField = new TextField();

    private Button opretFirmaButton = new Button("Opret");
    private Button cancelButton = new Button("Cancel");

    private Label errorLabel = new Label();

    public OpretFirmaWindow() {
        this.setTitle("Opret Firma");
        setResizable(false);
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
        setButtons(pane);


        opretFirmaButton.setOnAction(event -> {
            String navn = firmaNavnTextField.getText();
            String telefonNummer = firmaTelefonNummerTextField.getText();

            if(!blankTextField(navn, telefonNummer)) {
                System.out.println("Ikke alle virksomhedsfelter er udfyldt");
                errorLabel.setText("Ikke alle felter er udfyldt");
            } else {
                okAction(navn, telefonNummer);
                hide();
            }
        });
        cancelButton.setOnAction(event -> hide());
    }

    private void setNavnOgTelefonNummer(GridPane pane){
        Label lblName = new Label("Virksomhedens navn");
        pane.add(lblName, 0, 0, 2, 1);
        pane.add(firmaNavnTextField, 0, 1, 2, 1);

        Label lblEfternavn = new Label("Tlf.nr.");
        pane.add(lblEfternavn,0,2, 2, 1);
        pane.add(firmaTelefonNummerTextField,0,3, 2, 1);
    }

    private void setButtons(GridPane pane){
        pane.add(opretFirmaButton, 0, 5);
        pane.add(cancelButton,1,5);
        pane.add(errorLabel, 0, 4,2,1);
        errorLabel.setStyle("-fx-text-fill: red");
    }

    private void okAction(String navn, String telefonNummer){
        ControllerFirma.opretFirma(navn, telefonNummer);
        System.out.println("Firma blev oprettet i OpretFirmaWindow.java");
    }
}
