package view.konference;

import domain.model.Udflugt;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import storage.Storage;

import java.util.ArrayList;

public class ConfirmDeleteUdflugtWindow extends Stage {
    private final Udflugt udflugtTilFjernelse;
    private final Button bekræftButton = new Button("OK");
    private final Button afbrydButton = new Button("Afbryd");

    public ConfirmDeleteUdflugtWindow(Udflugt udflugt) {
        this.setTitle("Bekræft fjernelse af udflugt");
        GridPane pane = new GridPane();
        this.udflugtTilFjernelse = udflugt;
        initContent(pane);
        this.setScene(new Scene(pane));
    }

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        HBox infoBox = new HBox(1);
        Label information = new Label("Bekræft fjernelse af: ");
        Label deltagerNavn = new Label(udflugtTilFjernelse.getNavn());
        infoBox.getChildren().addAll(information, deltagerNavn);
        pane.add(infoBox, 1,0);

        pane.add(bekræftButton, 1, 1);
        bekræftButton.setOnAction(event -> {
            bekræftFjernelseAction();
            hide();
        });

        pane.add(afbrydButton, 2, 1);
        afbrydButton.setOnAction(event -> hide());
    }

    private void bekræftFjernelseAction(){
        ArrayList<Udflugt> udflugterList = new ArrayList<>(Storage.getUdflugter());
        for(Udflugt udflugt : udflugterList){
            if(udflugt.equals(udflugtTilFjernelse)){
                Storage.removeUdflugt(udflugt);
                System.out.printf("%s er nu fjernet fra Storage%n", udflugt.getNavn());
            }
        }
    }


}
