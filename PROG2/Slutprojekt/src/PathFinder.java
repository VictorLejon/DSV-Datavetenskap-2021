// PROG2 VT2022, Inlämningsuppgift del 2
// Grupp 250
// Victor Lejon vile3398

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;


public class PathFinder extends Application {
    private Graph<Place> graph = new ListGraph<>();
    private boolean changed;
    private String mapName = "file:europa.gif";
    private Place place1;
    private Place place2;
    private HBox buttonBox = new HBox(12);
    private Pane center;
    private MenuBar fileMenu = new MenuBar();
    private Stage mainStage;
    private BorderPane root;
    private BorderPane top;
    private Button newPlaceButton;

    @Override
    public void start(Stage mainStage){
        this.mainStage = mainStage;
        root = new BorderPane();
        top = new BorderPane();
        top.setTop(fileMenu);
        top.setBottom(buttonBox);
        buttonBox.setPadding(new Insets(5));

        center = new Pane();
        center.setId("outputArea");

        root.setTop(top);
        root.setCenter(center);
        root.setStyle("-fx-font-size:12");

        setUpButtons();
        createMenu();

        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.setTitle("PathFinder");
        mainStage.setOnCloseRequest(new ExitHandler());
        mainStage.show();
    }

    private void createMenu(){
        fileMenu.setId("menu");
        Menu menu = new Menu("File");
        menu.setId("menuFile");
        MenuItem newMapItem = new MenuItem("New Map");
        newMapItem.setId("menuNewMap");
        newMapItem.setOnAction(new NewMapHandler());
        MenuItem openItem = new MenuItem("Open");
        openItem.setId("menuOpenFile");
        openItem.setOnAction(new OpenHandler());
        MenuItem saveItem = new MenuItem("Save");
        saveItem.setOnAction(new SaveItemHandler());
        saveItem.setId("menuSaveFile");
        MenuItem saveImageItem = new MenuItem("Save Image");
        saveImageItem.setOnAction(new SaveImageHandler());
        saveImageItem.setId("menuSaveImage");
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setId("menuExit");
        exitItem.setOnAction(new ExitItemHandler());
        menu.getItems().addAll(newMapItem, openItem, saveItem, saveImageItem, exitItem);
        fileMenu.getMenus().add(menu);
    }

    private void setUpButtons(){
        Button findPathButton = new Button("Find Path");
        findPathButton.setId("btnFindPath");
        findPathButton.setOnAction(new FindPathHandler());
        Button showConnectionButton = new Button("Show Connection");
        showConnectionButton.setOnAction(new ShowConnectionHandler());
        showConnectionButton.setId("btnShowConnection");
        newPlaceButton = new Button("New Place");
        newPlaceButton.setOnAction(new NewPlaceHandler());
        newPlaceButton.setId("btnNewPlace");
        Button newConnectionButton = new Button("New Connection");
        newConnectionButton.setOnAction(new NewConnectionHandler());
        newConnectionButton.setId("btnNewConnection");
        Button changeConnectionButton = new Button("Change Connection");
        changeConnectionButton.setId("btnChangeConnection");
        changeConnectionButton.setOnAction(new EditWeightHandler());
        buttonBox.setAlignment(Pos.BASELINE_CENTER);
        buttonBox.getChildren().addAll(findPathButton, showConnectionButton, newPlaceButton, newConnectionButton, changeConnectionButton);
    }

    private void setPlace(double x, double y){
        NewPlaceAlert form = new NewPlaceAlert();
        Optional<ButtonType> answer = form.showAndWait();
        if (answer.isPresent() && answer.get() != ButtonType.OK){
            return;
        }

        String name = form.getName();

        if (name.equals("")) {
            displayErrorAlert("You must enter a name");
            return;
        }
        for (Place p : graph.getNodes()){
            if (p.getName().equals(name)){
                displayErrorAlert("Place already exists");
                return;
            }
        }
        createPlace(name, x, y);
    }

    private void createPlace(String name, double x, double y){
        Place p = new Place(name, x, y);
        p.setId(p.getName());
        graph.add(p);
        center.getChildren().addAll(p, p.getNameLabel());
        p.setOnMouseClicked(new SelectPlaceHandler());
    }

    private void setImage(){
        Image image = new Image(mapName);
        ImageView imageView = new ImageView(image);
        center.getChildren().add(imageView);
        double h = image.getHeight();
        double w = image.getWidth();
        mainStage.setMinHeight(top.getHeight() + h);
        mainStage.setMinWidth(w);
    }

    private boolean isValidImage(String fileName){
        if (new File(fileName.split(":")[1]).exists())
            return true;
        displayErrorAlert("File doesn't exist");
        return false;
    }

    private void drawConnection(Place p1, Place p2){
        Line line = new Line(p1.getCenterX(), p1.getCenterY(), p2.getCenterX(), p2.getCenterY());
        line.setDisable(true);
        center.getChildren().add(line);
    }

    private void displayErrorAlert(String text){
        Alert alert = new Alert(Alert.AlertType.ERROR, text);
        alert.setTitle("Error!");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    private void writeToFile(PrintWriter printWriter){
        Set<Place> places = graph.getNodes();

        if (places.size() > 0){
            StringBuilder sb = new StringBuilder();
            for (Place p : places)
                sb.append(p.getName()).append(";").append(p.getX()).append(";").append(p.getY()).append(";");

            sb.deleteCharAt(sb.length()-1);
            printWriter.println(sb);

            for (Place p : places){
                Collection<Edge<Place>> destinations = graph.getEdgesFrom(p);
                for (Edge<Place> d : destinations) {
                    sb = new StringBuilder();
                    sb.append(p.getName()).append(";").append(d.getDestination().getName()).append(";").append(d.getName()).append(";").append(d.getWeight());
                    printWriter.println(sb);
                }
            }
        }
        else{
            printWriter.print("");
        }
    }

    private void clearMap(){
        place1 = null;
        place2 = null;
        graph = new ListGraph<>();
        center.getChildren().clear();
    }

    private boolean confirmChangeAlert(){
        if (changed){
            ExitConfirmationAlert alert = new ExitConfirmationAlert();
            Optional<ButtonType> svar = alert.showAndWait();
            return svar.isEmpty() || svar.get() == ButtonType.OK;
        }
        return true;
    }

    class ExitItemHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            mainStage.fireEvent(new WindowEvent(mainStage, WindowEvent.WINDOW_CLOSE_REQUEST));
        }
    }

    class ExitHandler implements EventHandler<WindowEvent>{
        @Override
        public void handle(WindowEvent event){
            if (!confirmChangeAlert())
                event.consume();
        }
    }

    class SaveImageHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            try{
                WritableImage image = root.snapshot(null, null);
                BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
                ImageIO.write(bufferedImage, "png", new File("capture.png"));
            }catch (IOException e){
                displayErrorAlert("IO error" + e.getMessage());
            }
        }
    }

    class FindPathHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            if (place1 == null || place2 == null){
                displayErrorAlert("You must select two places");
                return;
            }
            StringBuilder sb = new StringBuilder();
            List<Edge<Place>> edgeList = graph.getPath(place1, place2);

            int total = 0;
            for (Edge<Place> edge : edgeList){
                sb.append(edge).append("\n");
                total += edge.getWeight();
            }
            sb.append("Total ").append(total);
            PathAlert alert = new PathAlert(place1.getName(), place2.getName(), sb.toString());
            alert.showAndWait();
        }
    }

    class EditWeightHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            if (place1 == null || place2 == null){
                displayErrorAlert("You must select two places");
                return;
            }
            Edge<Place> edge = graph.getEdgeBetween(place1, place2);
            if (edge == null){
                displayErrorAlert("No connection exists between places");
                return;
            }

            ConnectionAlert form = new ConnectionAlert(place1.getName(), place2.getName(), edge.getName(), edge.getWeight(), 1);
            Optional<ButtonType> answer = form.showAndWait();
            if (answer.isPresent() && answer.get() != ButtonType.OK) {
                return;
            }
            changed = true;
            graph.setConnectionWeight(place1, place2, form.getTime());
        }
    }

    class ShowConnectionHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){

            if (place1 == null || place2 == null){
                displayErrorAlert("You must select two places");
                return;
            }
            Edge<Place> edge = graph.getEdgeBetween(place1, place2);
            if (edge == null){
                displayErrorAlert("No connection exists between places");
                return;
            }

            ConnectionAlert alert = new ConnectionAlert(place1.getName(), place2.getName(), edge.getName(), edge.getWeight(), 2);
            alert.showAndWait();
        }
    }

    class OpenHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event) {
            if (confirmChangeAlert()) {
                try {
                    FileReader fileReader = new FileReader("europa.graph");
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String newMapName = bufferedReader.readLine();
                    if (isValidImage(newMapName)) {
                        mapName = newMapName;
                        clearMap();
                        setImage();

                        String firstLine = bufferedReader.readLine();
                        if (firstLine == null) {
                            displayErrorAlert("Save file is empty");
                            return;
                        }

                        String[] stringPlaces = firstLine.split(";");
                        for (int i = 0; i < stringPlaces.length - 2; i += 3)
                            createPlace(stringPlaces[i], Double.parseDouble(stringPlaces[i + 1]), Double.parseDouble(stringPlaces[i + 2]));

                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            String[] edgeLine = line.split(";");
                            Place p1 = null;
                            Place p2 = null;

                            for (Place p : graph.getNodes()) {
                                if (p.getName().equals(edgeLine[0]))
                                    p1 = p;
                                if (p.getName().equals(edgeLine[1]))
                                    p2 = p;
                                if (p1 != null && p2 != null)
                                    break;
                            }

                            if (graph.getEdgeBetween(p1, p2) == null && p1 != null && p2 != null) {
                                graph.connect(p1, p2, edgeLine[2], Integer.parseInt(edgeLine[3]));
                                drawConnection(p1, p2);
                            }
                        }
                    }
                } catch(FileNotFoundException e){
                    displayErrorAlert("No file saved");
                } catch(IOException e){
                    displayErrorAlert("IO error");
                }
            }
        }
    }

    class SaveItemHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            try{
                FileWriter fileWriter = new FileWriter("europa.graph");
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.println(mapName);
                writeToFile(printWriter);
                printWriter.close();
                fileWriter.close();
                changed = false;
            }catch(IOException e){
                displayErrorAlert("IO-error");
            }

        }
    }

    class NewConnectionHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            if (place1 == null || place2 == null){
                displayErrorAlert("Two places must be selected!");
                return;
            }
            if (graph.getEdgeBetween(place1, place2) != null){
                displayErrorAlert("Connection already exists");
                return;
            }

            try {
                NewConnectionAlert form = new NewConnectionAlert(place1.getName(), place2.getName());

                Optional<ButtonType> answer = form.showAndWait();
                if (answer.isPresent() && answer.get() != ButtonType.OK) {
                    return;
                }

                graph.connect(place1, place2, form.getName(), form.getTime());
                drawConnection(place1, place2);
                changed = true;
            }catch(NumberFormatException | IndexOutOfBoundsException e){
                displayErrorAlert("Invalid input");
            }

        }
    }

    class SelectPlaceHandler implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            Place p = (Place) event.getSource();
            if (p.getSelected()){
                if (p == place1)
                    place1 = null;

                else if (p == place2)
                    place2 = null;

                p.setFill(Color.BLUE);
                p.setSelected(false);
                return;
            }
            if (place1 == null) {
                place1 = p;
                p.setFill(Color.RED);
                p.setSelected(true);

            }
            else if (place2 == null && p != place1){
                place2 = p;
                p.setFill(Color.RED);
                p.setSelected(true);
            }

        }
    }

    class NewMapHandler implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event){
            if (confirmChangeAlert()) {
                clearMap();
                setImage();
            }
        }
    }

    class NewPlaceHandler implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event){
            center.setOnMouseClicked(new ClickHandler());
            center.setCursor(Cursor.CROSSHAIR);
            newPlaceButton.setDisable(true);
        }
    }

    class ClickHandler implements EventHandler<MouseEvent>{

        @Override
        public void handle(MouseEvent event){
            double x = event.getX();
            double y = event.getY();
            setPlace(x, y);
            changed = true;
            center.setOnMouseClicked(null);
            newPlaceButton.setDisable(false);
            center.setCursor(Cursor.DEFAULT);
        }
    }



    public static void main(String[] args){
        Application.launch(args);
    }
}
