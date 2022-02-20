import java.util.ArrayList;

public class AssignmentSevenPointTwo {

    private InputHandler input = new InputHandler();
    
    @UnderTest(id="dogs")
    private ArrayList<Dog> dogList = new ArrayList<>();
    
    @UnderTest(id="U7.2")
    public void listDogs(){
        if(dogList.size() > 0){
            double smallestTailLength = input.readDouble("Smallest tail length to display");
            printDogs(smallestTailLength);
        }
        else{
            System.out.println("Error: No dogs added");
        }
    }

    private void printDogs(double smallestTailLength){
        for (Dog dog : dogList) {
            if (dog.getTailLength() >= smallestTailLength){
                System.out.println(dog);
            }
        }
    }


}
