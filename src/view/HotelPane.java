package view;

import domain.model.Hotel;
import domain.model.HotelTillæg;
import domain.model.Tilmelding;
import javafx.beans.value.ChangeListener;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import storage.Storage;

public class HotelPane extends GridPane {
    private final ListView<Hotel> hotellerListView;
    private final ListView<String> tilmeldingerListView;

    public HotelPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        ColumnConstraints colConstraints = new ColumnConstraints();
        colConstraints.setHgrow(Priority.NEVER); // Kolonner skalerer ikke unødvendigt
        colConstraints.setMinWidth(Control.USE_COMPUTED_SIZE); // Brug kun nødvendig plads
        this.getColumnConstraints().addAll(
                new ColumnConstraints(186), // Opret hotel
                new ColumnConstraints(186), // Opdater hotel
                new ColumnConstraints(186) // Slet hotel
        );
        // Listviews and respective labels
        this.add(new Label("Vælg et hotel:"), 0, 0);
        hotellerListView = new ListView<>();
        this.add(hotellerListView, 0,1,3,1);

        hotellerListView.getItems().setAll(Storage.getHoteller());
        ChangeListener<Hotel> listener = (ov, oldHotel, newHotel) -> this.selectedHotelChanged(newHotel);
        hotellerListView.getSelectionModel().selectedItemProperty().addListener(listener);

        this.add(new Label("Deltagere reserveret på det valgte hotel:"), 3, 0);
        tilmeldingerListView = new ListView<>();
        tilmeldingerListView.setPrefWidth(598);
        this.add(tilmeldingerListView, 3, 1, 3, 1);

        Button opretHotelButton = new Button("Opret hotel");
        this.add(opretHotelButton, 0, 2);

        Button opdaterHotelButton = new Button("Opdater hotel");
        GridPane.setHalignment(opdaterHotelButton, HPos.CENTER);
        this.add(opdaterHotelButton, 1, 2);

        Button sletHotelButton = new Button("Slet hotel");
        GridPane.setHalignment(sletHotelButton, HPos.RIGHT);
        this.add(sletHotelButton, 2, 2);

    }

    // Funktionel kode
    private void selectedHotelChanged(Hotel newHotel) {
        if(newHotel != null) {
            for (Tilmelding tilmelding : newHotel.getTilmeldinger()) {
                String s = buildInfoOnTilmelding(tilmelding);
                tilmeldingerListView.getItems().add(s);
            }
        }
    }

    private String buildInfoOnTilmelding(Tilmelding tilmelding) {
        String s = tilmelding.getDeltager().getFuldeNavn();
        if(tilmelding.getLedsagerNavn() != null) {
            s += " har dobbeltværelse med " + tilmelding.getLedsagerNavn() + " og følgende hoteltillæg:\n";
        }

        else {
            s += " har enkeltværelse med følgende hoteltillæg:\n";
        }

        if(tilmelding.getHotelTillæg().isEmpty()) {
            s += "Ingen hoteltillæg.";
        }
        else {
            for (HotelTillæg hotelTillæg : tilmelding.getHotelTillæg()) {
                s += hotelTillæg.getNavn();
            }
        }
        return s;
    }
}
