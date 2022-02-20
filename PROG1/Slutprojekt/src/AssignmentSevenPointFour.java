import java.util.ArrayList;

public class AssignmentSevenPointFour {
    
    private InputHandler input = new InputHandler();

    @UnderTest(id="dogs")
    private ArrayList<Dog> dogList = new ArrayList<>();

    @UnderTest(id="U7.4")
    public void increaseDogAge(){
        String dogName = input.readString("Enter the name of the dog");
        Dog dog = getDogByName(dogName);
        if (dog != null){
            dog.increaseAge();
        }
    }

    private Dog getDogByName(String name){
        for (Dog dog : dogList) {
            if (dog.getName().toLowerCase().equals(name.toLowerCase())) return dog;
        }
        System.out.println("Error: no such dog");
        return null;
    }
}
