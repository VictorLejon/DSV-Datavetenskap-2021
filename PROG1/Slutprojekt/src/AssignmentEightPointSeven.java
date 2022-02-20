import java.util.ArrayList;

public class AssignmentEightPointSeven{

    private InputHandler input = new InputHandler();

    @UnderTest(id="owners")
    private ArrayList<Owner> ownerList = new ArrayList<>();

    @UnderTest(id="dogs")
    private ArrayList<Dog> dogList = new ArrayList<>();

    @UnderTest(id="U8.7")
    public void removeOwner(){
        Owner ownerToRemove = getOwner();
        if (ownerToRemove!= null){
            removeAllDogsFromOwner(ownerToRemove);
            ownerList.remove(ownerToRemove);
        }
    }

    private void removeAllDogsFromOwner(Owner owner){
        Dog[] dogs = owner.getOwnedDogs();

        for (Dog dog : dogs) {
            dogList.remove(dog);
        }
    }

    private Owner getOwner(){
        String ownerName = input.readString("Enter the name of the owner");
        Owner owner = this.getOwnerByName(ownerName);
        return owner;
    }

    private Owner getOwnerByName(String ownerName){
        for (Owner owner : ownerList) {
            if (owner.getName().toLowerCase().equals(ownerName.toLowerCase())) return owner;
        }
        System.out.println("Error: no such owner");
        return null;
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

        return dog;
    }

    private Dog getDogByName(String name){
        for (Dog dog : dogList) {
            if (dog.getName().toLowerCase().equals(name.toLowerCase())) return dog;
        }
        System.out.println("Error: no such dog");
        return null;
    }


}