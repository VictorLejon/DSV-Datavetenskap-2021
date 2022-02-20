import java.util.ArrayList;

public class AssignmentEightPointThree {

    private InputHandler input = new InputHandler();

    @UnderTest(id="owners")
    private ArrayList<Owner> ownerList = new ArrayList<>();

    @UnderTest(id="dogs")
    private ArrayList<Dog> dogList = new ArrayList<>();

    @UnderTest(id="U8.3")
    public void addDogToOwner(){
        Dog dog = this.getDog();
        if (dog == null) return;

        if (dog.getOwner() != null){
            System.out.println("Error: dog already has an owner");
            return;
        }

        Owner owner = this.getOwner();
        if (owner == null) return;

        
        owner.addDog(dog);
    }



    private Dog getDog(){
        String dogName = input.readString("Enter the name of the dog");
        Dog dog = this.getDogByName(dogName);
        if (dog == null) return null;

        return dog;
    }

    private Owner getOwner(){
        String ownerName = input.readString("Enter the name of the owner");
        Owner owner = this.getOwnerByName(ownerName);
        return owner;
    }

    private Dog getDogByName(String name){
        for (Dog dog : dogList) {
            if (dog.getName().toLowerCase().equals(name.toLowerCase())) return dog;
        }
        System.out.println("Error: no such dog");
        return null;
    }

    private Owner getOwnerByName(String ownerName){
        for (Owner owner : ownerList) {
            if (owner.getName().toLowerCase().equals(ownerName.toLowerCase())) return owner;
        }
        System.out.println("Error: no such owner");
        return null;
    }
}
