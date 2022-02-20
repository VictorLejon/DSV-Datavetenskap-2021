
import java.util.ArrayList;

public class AssignmentSevenPointOne {

    @UnderTest(id="dogs")
    private ArrayList<Dog> dogList = new ArrayList<>();

    private InputHandler input = new InputHandler();

    @UnderTest(id="U6.4")
    public Dog addDog(){    
        String name = this.input.readString("Vad heter hunden");
        String breed = this.input.readString("Av vilken ras är hunden");
        int age = this.input.readInt("Hur gammal är hunden (år)");
        int weight = this.input.readInt("Hur mycket väger hunden (kg)");
        return new Dog(name, breed, age, weight);
    }

    @UnderTest(id="U7.1")
    public void addDogToList(){
        Dog dogToAdd = addDog();
        dogList.add(dogToAdd);
    }

}
