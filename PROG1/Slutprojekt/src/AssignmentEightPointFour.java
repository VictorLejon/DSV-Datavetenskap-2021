import java.util.ArrayList;

public class AssignmentEightPointFour {
    private InputWrapper input = new InputWrapper();

    @UnderTest(id="owners")
    public ArrayList<Owner> ownerList = new ArrayList<>();

    @UnderTest(id="dogs")
    public ArrayList<Dog> dogList = new ArrayList<>();


    @UnderTest(id="U8.4")
    public void listOwnersWithDogs(){
        if (ownerList.size() == 0){
            System.out.println("Error: no owners registered");
        }
        else{
            for (Owner owner : ownerList) {
                System.out.println(owner + " " + owner.getDogs());
            }
        }
    }

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
                Owner owner = dog.getOwner();
                String output = (owner == null) ? dog.toString() : dog.toString() + " owned by " + owner.toString();
                System.out.println(output);
            }
        }
    }

    @UnderTest(id="U8.3")
    public void addDogToOwner(){
        Dog dog = this.getDog();
        if (dog == null) return;

        Owner owner = this.getOwner();
        if (owner == null) return;
        
        owner.addDog(dog);
    }

    private Dog getDog(){
        String dogName = input.readString("Enter the name of the dog");
        Dog dog = this.getDogByName(dogName);
        if (dog == null) return null;

        if (dog.getOwner() != null){
            System.out.println("Error: dog already has an owner");
            return null;
        }
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
