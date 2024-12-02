package view.hotelTabPane;

import javafx.scene.control.Alert;

public class HotelAlert extends Alert {

    public HotelAlert(AlertType alertType, String title, String beskrivelse){
        super(alertType);
        this.setTitle(title);
        this.setHeaderText(beskrivelse);
        this.show();
    }
}
