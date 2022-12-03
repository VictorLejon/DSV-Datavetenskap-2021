// PROG2 VT2022, Inlämningsuppgift del 2
// Grupp 250
// Victor Lejon vile3398

import javafx.scene.control.Alert;

public class ExitConfirmationAlert extends Alert {

    public ExitConfirmationAlert(){
        super(AlertType.CONFIRMATION);
        setTitle("Bekräftelse");
        setHeaderText("Du har osparade ändringar");
        setContentText("Vill du fortsätta ändå?");
    }
}
