// PROG2 VT2022, Inlämningsuppgift del 2
// Grupp 250
// Victor Lejon vile3398

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Place extends Circle{

    private Label nameLabel;
    private boolean selected;
    private double x, y;
    public Place(String name, double x, double y){
        super(x, y, 10);
        this.x = x;
        this.y = y;
        this.nameLabel = new Label(name);
        nameLabel.relocate(x, y + 5);
        nameLabel.setStyle("font-weight:bold");
        setFill(Color.BLUE);
    }

    public Label getNameLabel(){
        return nameLabel;
    }
    public String getName(){ return nameLabel.getText(); }
    public double getX(){ return x; }
    public double getY(){ return y; }
    public boolean getSelected() { return selected; }
    public void setSelected(boolean selected) { this.selected = selected; }
    public String toString(){
        return this.getName();
    }

}
