// PROG2 VT2022, Inlämningsuppgift del 2
// Grupp 250
// Victor Lejon vile3398

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

public class PathAlert extends Alert{

    public PathAlert(String p1, String p2, String text){
        super(AlertType.INFORMATION);
        setTitle("Path");
        setHeaderText("The path from " + p1 + " to " + p2);
        BorderPane pane = new BorderPane();

        pane.setPadding(new Insets(10));

        pane.setCenter(new TextArea(text));
        getDialogPane().setContent(pane);
    }
}
