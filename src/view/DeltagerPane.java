package view;

import domain.model.Deltager;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import storage.Storage;

public class DeltagerPane extends GridPane {
    private final ListView<Deltager> deltagereListView;
    private final TextField prisTextField;

    public DeltagerPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        ColumnConstraints colConstraints = new ColumnConstraints();
        colConstraints.setHgrow(Priority.NEVER); // Kolonner skalerer ikke unødvendigt
        colConstraints.setMinWidth(Control.USE_COMPUTED_SIZE); // Brug kun nødvendig plads
        this.getColumnConstraints().addAll(
                new ColumnConstraints(239), // Opret deltager
                new ColumnConstraints(238), // Opdater deltager
                new ColumnConstraints(238), // Slet deltager
                new ColumnConstraints(238), // Søg deltager
                new ColumnConstraints(77), // Beregn pris
                new ColumnConstraints(85) // Prisen i kr
        );

        Label deltagereLabel = new Label("Deltagere:");
        this.add(deltagereLabel, 0, 0);
        deltagereListView = new ListView<>();
        deltagereListView.setPrefWidth(1215);
        deltagereListView.getItems().setAll(Storage.getDeltagere());
        this.add(deltagereListView, 0, 1, 6,1);

        Button opretDeltagerButton = new Button("Opret deltager");
        this.add(opretDeltagerButton, 0, 2);

        Button opdaterDeltagerButton = new Button("Opdater deltager");
        this.add(opdaterDeltagerButton, 1, 2);

        Button sletDeltagerButton = new Button("Slet deltager");
        this.add(sletDeltagerButton, 2,2);

        Button søgDeltagerButton = new Button("Søg deltager");
        this.add(søgDeltagerButton, 3, 2);

        Button beregnPrisButton = new Button("Beregn pris");
        this.add(beregnPrisButton, 4, 2);

         prisTextField = new TextField();
         this.add(prisTextField, 5, 2);
    }
}
