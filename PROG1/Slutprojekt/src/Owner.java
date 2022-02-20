// Victor Lejon vile3398
import java.util.Arrays;

public class Owner {
    private String name;
    private Dog[] dogsOwned = new Dog[0];

    public Owner(String name){
        this.name = name;
    }

    public String toString(){
        return String.format("%s", this.name);
    }

    public String getName(){
        return this.name;
    }

    public void addDog(Dog dog){
        Owner currentDogOwner = dog.getOwner();

        if (currentDogOwner != this && currentDogOwner != null) return;
        if (checkIfDogAdded(dog)) return;

        this.addDogToOwnerArray(dog);
        if (currentDogOwner == this) return;

        dog.setOwner(this);
        return;
    }

    private void addDogToOwnerArray(Dog dogToAdd){
        dogsOwned = Arrays.copyOf(dogsOwned, dogsOwned.length + 1);
        dogsOwned[dogsOwned.length-1] = dogToAdd;
    }

    public boolean checkIfDogAdded(Dog dog){
        if (dogsOwned.length > 0){
            for (Dog ownedDog : dogsOwned) {
                if (dog == ownedDog) return true;
            }
        }
        return false;
    }

    public String getDogs(){
        String output = "[";
        int arrayLength = dogsOwned.length;
        for (int i = 0; i < arrayLength; i++) {
            output += dogsOwned[i].getName();
            if (i != arrayLength - 1) output += ", ";
        }
        return output + "]";
    }

    public void removeDog(Dog dogToRemove){
        if(!checkIfDogAdded(dogToRemove)) return;

        Dog[] newArray = new Dog[dogsOwned.length-1];

        int newIndex = 0;
        for (int i = 0; i < dogsOwned.length; i++) {
            if (dogsOwned[i].equals(dogToRemove)) continue;
            newArray[newIndex++] = dogsOwned[i];
        }

        dogsOwned = newArray;

        if (dogToRemove.getOwner() != null)
            dogToRemove.removeOwner();
    }
}
