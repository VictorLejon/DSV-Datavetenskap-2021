// PROG2 VT2022, Inlämningsuppgift del 2
// Grupp 250
// Victor Lejon vile3398
public class ConnectionAlert extends NewConnectionAlert{

    public ConnectionAlert(String p1, String p2, String edgeName, int weight, int restrictionLvl){
        super(p1, p2);
        setRestrictions(restrictionLvl);

        timeField.setText("" + weight);
        nameField.setText(edgeName);
    }

    private void setRestrictions(int rLvl){
        if (rLvl > 0)
            nameField.setEditable(false);

        if (rLvl > 1)
            timeField.setEditable(false);
    }

}
