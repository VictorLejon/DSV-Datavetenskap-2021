// PROG2 VT2022, Inlämningsuppgift del 2
// Grupp 250
// Victor Lejon vile3398
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class NewConnectionAlert extends Alert {


    protected final TextField nameField = new TextField();
    protected final TextField timeField = new TextField();

    public NewConnectionAlert(String p1, String p2){
        super(AlertType.CONFIRMATION);
        setTitle("Connection");
        setHeaderText("Connection from " + p1 + " to " + p2);
        GridPane grid = new GridPane();

        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10));
        grid.setHgap(5);
        grid.setVgap(10);

        grid.addRow(0, new Label("Name:"), nameField);
        grid.addRow(1, new Label("Time:"), timeField);
        getDialogPane().setContent(grid);
    }

    public String getName(){
        return nameField.getText();
    }

    public int getTime(){
        return Integer.parseInt(timeField.getText());
    }
}
