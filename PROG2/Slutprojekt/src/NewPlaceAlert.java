// PROG2 VT2022, Inlämningsuppgift del 2
// Grupp 250
// Victor Lejon vile3398

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class NewPlaceAlert extends Alert {


    private final TextField nameField = new TextField();

    public NewPlaceAlert(){
        super(AlertType.CONFIRMATION);
        setTitle("Name");
        setHeaderText(null);
        GridPane grid = new GridPane();

        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10));
        grid.setHgap(5);
        grid.setVgap(10);

        grid.addRow(0, new Label("Name of place:"), nameField);
        getDialogPane().setContent(grid);
    }

    public String getName(){
        return nameField.getText();
    }
}
