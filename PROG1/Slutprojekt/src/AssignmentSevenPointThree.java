import java.util.ArrayList;

public class AssignmentSevenPointThree {
    
    @UnderTest(id="dogs")
    private ArrayList<Dog> dogList = new ArrayList<>();

    @UnderTest(id="U7.3")
    public Dog getDogByName(String name){
        for (Dog dog : dogList) {
            if (dog.getName().toLowerCase().equals(name.toLowerCase())) return dog;
        }
        return null;
    }
}
