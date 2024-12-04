package view.hotelTabPane;

import domain.controller.ControllerHotel;
import domain.model.Hotel;
import domain.model.HotelTillæg;
import domain.model.Tilmelding;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import storage.Storage;
import view.errorHandling.Alert;

import java.util.Optional;

public class HotelPane extends GridPane {
    // Listviews
    private final ListView<Hotel> hotellerListView = new ListView<>();
    private final ListView<String> tilmeldingerListView = new ListView<>();

    // Buttons
    Button opretHotelButton = new Button("Opret hotel");
    Button opdaterHotelButton = new Button("Opdater hotel");
    Button sletHotelButton = new Button("Slet hotel");
    Button opretHotelTillægButton = new Button("Opret hoteltillæg");
    Button opdaterHotelTillægButton = new Button("Opdater hoteltillæg");
    Button sletHotelTillægButton = new Button("Slet hoteltillæg");

    public HotelPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        setHotellerListView();
        setTilmeldingerListView();
        setButtons();
    }

    private void setHotellerListView() {
        this.add(new Label("Vælg et hotel:"), 0, 0);
        this.add(hotellerListView, 0, 1, 6, 1);
        hotellerListView.getItems().setAll(Storage.getHoteller());
        ChangeListener<Hotel> listener = (ov, oldHotel, newHotel) -> this.selectedHotelChanged(newHotel);
        hotellerListView.getSelectionModel().selectedItemProperty().addListener(listener);
    }

    private void setTilmeldingerListView() {
        this.add(new Label("Deltagere reserveret på det valgte hotel:"), 6, 0);
        tilmeldingerListView.setPrefWidth(540);
        this.add(tilmeldingerListView, 6, 1, 3, 1);
    }

    private void setButtons() {
        this.add(opretHotelButton, 0, 2);
        opretHotelButton.setOnAction(event -> opretHotelAction());

        this.add(opdaterHotelButton, 1, 2);
        opdaterHotelButton.setOnAction(event -> opdaterHotelAction());

        this.add(sletHotelButton, 2, 2);
        sletHotelButton.setOnAction(event -> sletHotelAction());

        this.add(opretHotelTillægButton, 3, 2);
        opretHotelTillægButton.setOnAction(event -> opretHotelTillægAction());

        this.add(opdaterHotelTillægButton, 4, 2);
        opdaterHotelTillægButton.setOnAction(event -> opdaterHotelTillæg());

        this.add(sletHotelTillægButton, 5, 2);
        sletHotelTillægButton.setOnAction(event -> sletHotelTillægAction());
    }

    private void selectedHotelChanged(Hotel newHotel) {
        if (newHotel != null) {
            tilmeldingerListView.getItems().setAll();
            for (Tilmelding tilmelding : newHotel.getTilmeldinger()) {
                String string = buildInfoOnHotelTilmelding(tilmelding);
                tilmeldingerListView.getItems().add(string);
            }
        }
    }

    private void opretHotelAction() {
        new HotelWindow("Opret").showAndWait();
        hotellerListView.getItems().setAll(Storage.getHoteller());
    }

    private void opdaterHotelAction() {
        Hotel hotel = hotellerListView.getSelectionModel().getSelectedItem();
        if(hotel != null) {
            new HotelWindow("Opdater", hotel).showAndWait();
        }
        else {
            new Alert(javafx.scene.control.Alert.AlertType.INFORMATION, "Opdater hotel", "Du har ikke valgt et hotel i listen.");
        }
        hotellerListView.refresh();
    }

    private void sletHotelAction() {
        Hotel hotel = hotellerListView.getSelectionModel().getSelectedItem();
        if (hotel != null) {
            javafx.scene.control.Alert alert = new Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION, "Slet hotel", "Er du sikker på du vil slette " + hotel.getNavn() + "?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                ControllerHotel.fjernHotel(hotel);
                hotellerListView.getItems().setAll(Storage.getHoteller());
                tilmeldingerListView.getItems().setAll();
            }
        } else {
            new Alert(javafx.scene.control.Alert.AlertType.INFORMATION, "Slet hotel", "Du har ikke valgt et hotel i listen.");
        }
    }

    private void opretHotelTillægAction() {
        Hotel hotel = hotellerListView.getSelectionModel().getSelectedItem();
        if(hotel != null) {
            new HotelTillægWindow("Opret", hotel).showAndWait();
            hotellerListView.refresh();
        }
        else {
            new Alert(javafx.scene.control.Alert.AlertType.INFORMATION, "Opret hotel", "Du har ikke valgt et hotel i listen.");
        }
    }

    private void opdaterHotelTillæg() {
        Hotel hotel = hotellerListView.getSelectionModel().getSelectedItem();
        if(hotel != null) {
            if(!hotel.getHotelTillæger().isEmpty()) {
                new HotelTillægWindow("Opdater", hotel).showAndWait();
                hotellerListView.refresh();
            }
            else {
                new Alert(javafx.scene.control.Alert.AlertType.INFORMATION, "Opdater hotel", "Der er ikke nogle hoteltillæg at opdatere");
            }
        }
        else {
            new Alert(javafx.scene.control.Alert.AlertType.INFORMATION, "Opdater hotel", "Du har ikke valgt et hotel i listen.");
        }
    }

    private void sletHotelTillægAction() {
        Hotel hotel = hotellerListView.getSelectionModel().getSelectedItem();
        if (hotel != null) {
            if(!hotel.getHotelTillæger().isEmpty()) {
                new HotelTillægWindow("Slet", hotel).showAndWait();
                hotellerListView.refresh();
                hotellerListView.getSelectionModel().clearSelection();
                hotellerListView.getSelectionModel().select(hotel);
            }
            else {
                new Alert(javafx.scene.control.Alert.AlertType.INFORMATION, "Slet hoteltillæg", "Der er ikke nogle hoteltillæg at slette.");
            }
        } else {
            new Alert(javafx.scene.control.Alert.AlertType.INFORMATION, "Slet hoteltillæg", "Du har ikke valgt et hotel i listen.");
        }
    }

    private String buildInfoOnHotelTilmelding(Tilmelding tilmelding) {
        String string = tilmelding.getDeltager().getFuldeNavn();
        if(tilmelding.getLedsagerNavn() != null && !tilmelding.getLedsagerNavn().isEmpty()) {
            string += " har dobbeltværelse med " + tilmelding.getLedsagerNavn() + " og følgende hoteltillæg:\n";
        }

        else {
            string += " har enkeltværelse med følgende hoteltillæg:\n";
        }

        if(tilmelding.getHotelTillæg().isEmpty()) {
            string += "Ingen hoteltillæg.";
        }
        else {
            for (HotelTillæg hotelTillæg : tilmelding.getHotelTillæg()) {
                string += hotelTillæg.getNavn();
            }
        }
        return string;
    }
}