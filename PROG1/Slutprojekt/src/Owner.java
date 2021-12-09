import java.util.Arrays;

public class Owner {
    private String name;
    private Dog[] dogArray = new Dog[0];

    public Owner(String name){
        this.name = name;
    }

    public String toString(){
        return String.format("%s", this.name);
    }

    public String getName(){
        return this.name;
    }

    @UnderTest(id="U8.3")
    public void addDog(Dog dog){
        Owner currentDogOwner = dog.getOwner();

        if (currentDogOwner != this && currentDogOwner != null) return;
        if (checkIfDogAdded(dog)) return;

        this.addDogToArray(dog);
        if (currentDogOwner == this) return;

        dog.setOwner(this);
        return;
    }

    private void addDogToArray(Dog dogToAdd){
        dogArray = Arrays.copyOf(dogArray, dogArray.length + 1);
        dogArray[dogArray.length-1] = dogToAdd;
    }

    @UnderTest(id="U8.5")
    public boolean checkIfDogAdded(Dog dog){
        if (dogArray.length > 0){
            for (Dog ownedDog : dogArray) {
                if (dog == ownedDog) return true;
            }
        }
        return false;
    }

    public String getDogs(){
        String output = "[";
        int arrayLength = dogArray.length;
        for (int i = 0; i < arrayLength; i++) {
            output += dogArray[i].getName();
            if (i != arrayLength - 1) output += ", ";
        }
        return output + "]";
    }
}
