// Victor Lejon vile3398
import java.util.ArrayList;

public class DogRegisterApp {

    private static final String COMMAND_ADD_DOG = "register new dog";
    private static final String COMMAND_LIST_DOGS = "list dogs";
    private static final String COMMAND_INCREASE_AGE = "increase age";
    private static final String COMMAND_REMOVE_DOG = "remove dog";
    private static final String COMMAND_ADD_OWNER = "register new owner";
    private static final String COMMAND_GIVE_DOG = "give dog";
    private static final String COMMAND_LIST_OWNERS = "list owners";
    private static final String COMMAND_REMOVE_OWNED_DOG = "remove owned dog";
    private static final String COMMAND_REMOVE_OWNER = "remove owner";
    private static final String COMMAND_EXIT = "exit";

    private InputHandler input = new InputHandler();

    private ArrayList<Owner> ownerList = new ArrayList<>();
    private ArrayList<Dog> dogList = new ArrayList<>();

    private void runCommand(String command){
        switch(command){
            case COMMAND_ADD_DOG: addDogToList(); break;
            case COMMAND_LIST_DOGS: listDogs(); break;
            case COMMAND_INCREASE_AGE: increaseDogAge(); break;
            case COMMAND_REMOVE_DOG: removeDog(); break;
            case COMMAND_GIVE_DOG: addDogToOwner(); break;
            case COMMAND_ADD_OWNER: registerOwner(); break;
            case COMMAND_LIST_OWNERS: listOwnersWithDogs(); break;
            case COMMAND_REMOVE_OWNED_DOG: removeDogFromOwner(); break;
            case COMMAND_REMOVE_OWNER: removeOwner(); break;
            case COMMAND_EXIT: break;
            default: printErrorMessage("Not a valid command");
        }
    }

    private void runLoop(){
        String command;
        do{
            command = input.readString("Command");
            runCommand(command);
        }while(!command.equals(COMMAND_EXIT));
    }

    private void shutDown(){
        System.out.println("Goodbye!");
    }

    private void setup(){
        printStartText();
    }

    private void printErrorMessage(String error){
        System.out.println("Error: " + error);
    }

    private void run(){
        setup();
        runLoop();
        shutDown();
    }

    private void printStartText(){
        System.out.println("Welcome!");
        System.out.println("Here is a list of available commands:");
        System.out.println("* register new dog");
        System.out.println("* list dogs");
        System.out.println("* increase age");
        System.out.println("* remove dog");
        System.out.println("* register new owner");
        System.out.println("* give dog");
        System.out.println("* list owners");
        System.out.println("* remove owned dog");
        System.out.println("* exit");
    }

    public static void main(String[] args){
        DogRegisterApp app = new DogRegisterApp();
        app.run();
    }

    private void removeOwner(){
        Owner ownerToRemove = getOwner();
        if (ownerToRemove != null){
            removeAllDogsOwnedByOwner(ownerToRemove);
            ownerList.remove(ownerToRemove);
            System.out.println(ownerToRemove.getName() + " removed");
        }
    }

    private void removeAllDogsOwnedByOwner(Owner owner){
        ArrayList<Dog> dogsToRemove = new ArrayList<Dog>();
        for (Dog dog : dogList){
            if (dog.getOwner() == owner)
                dogsToRemove.add(dog);    
        }
        dogList.removeAll(dogsToRemove);
    }

    private void removeDogFromOwner(){
        Dog dogToRemove = getDog();

        if (dogToRemove == null ||!checkIfDogHasOwner(dogToRemove)) return;

        Owner owner = dogToRemove.getOwner();
        owner.removeDog(dogToRemove);
        System.out.println(dogToRemove.getName() + " removed from " + owner.getName());
    }

    private void listOwnersWithDogs(){
        if (ownerList.size() == 0){
            printErrorMessage("No owners registered");
        }
        else{
            printOwners(ownerList);
        }
    }

    private void printOwners(ArrayList<Owner> ownersToPrint){
        for (Owner owner : ownerList) 
                System.out.println(owner + ", Dogs: " + owner.getDogs());
    }

    private void registerOwner(){
        String name = input.readString("Name");
        ownerList.add(new Owner(name));
        System.out.println(name + " added");
    }

    private void removeDog(){
        Dog dogToRemove = getDog();
        if(dogToRemove == null) return;

        if (checkIfDogHasOwner(dogToRemove)){
            Owner ownerOfDog = dogToRemove.getOwner();
            ownerOfDog.removeDog(dogToRemove);
        }
        dogList.remove(dogToRemove);
        System.out.println(dogToRemove.getName() + " removed");
    }

    private void addDogToOwner(){
        Dog dog = this.getDog();
        if (dog == null) return;
        if (checkIfDogHasOwner(dog)){
            printErrorMessage("dog already has an owner");
            return;
        } 

        Owner owner = this.getOwner();
        if (owner == null) return;
        owner.addDog(dog);
    }

    private boolean checkIfDogHasOwner(Dog dog){
        if (dog.getOwner() != null){
            return true;
        }
        return false;
    }

    private void addDogToList(){
        Dog dogToAdd = addDog();
        dogList.add(dogToAdd);
        System.out.println(dogToAdd.getName() + " added");
    }

    private void increaseDogAge(){
        Dog dog = getDog();
        if (dog != null){
            dog.increaseAge();
            System.out.println("Age increased of " + dog.getName());
        }
    }

    private void listDogs(){
        if(dogList.size() > 0){
            double smallestTailLength = input.readDouble("Smallest tail length to display");
            ArrayList<Dog> dogsToPrint = findDogsWithTailLength(smallestTailLength);
            if (dogsToPrint.size() > 0) printDogs(dogsToPrint);
            else printErrorMessage("No dogs meet tail length criteria");
            return;
        }
        printErrorMessage("No dogs added");
    }

    private ArrayList<Dog> findDogsWithTailLength(double smallestTailLength){
        ArrayList<Dog> dogsToPrint = new ArrayList<>();
        for (Dog dog : dogList) 
            if (dog.getTailLength() >= smallestTailLength) dogsToPrint.add(dog);

        return dogsToPrint;
    }
    
    private void printDogs(ArrayList<Dog> dogsToPrint){
        this.sortDogList(dogsToPrint);
        for (Dog dog : dogsToPrint) {
            System.out.println(dog);
        }
    }

    private Dog addDog(){    
        String name = this.input.readString("Vad heter hunden");
        String breed = this.input.readString("Av vilken ras är hunden");
        int age = this.input.readInt("Hur gammal är hunden (år)");
        int weight = this.input.readInt("Hur mycket väger hunden (kg)");
        return new Dog(name, breed, age, weight);
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
        printErrorMessage("no such dog");
        return null;
    }

    private Owner getOwnerByName(String ownerName){
        for (Owner owner : ownerList) {
            if (owner.getName().toLowerCase().equals(ownerName.toLowerCase())) return owner;
        }
        printErrorMessage("no such owner");
        return null;
    }


    // Sortering
    private void swapDogsManual(int i, int k, ArrayList<Dog> dogs){
        Dog tmpDog = dogs.get(i);
        dogs.set(i, dogs.get(k));
        dogs.set(k, tmpDog);
    }

    private boolean compareDogs(Dog first, Dog second){
        int tailComparisonResult = compareTailLength(first.getTailLength(), second.getTailLength());
        if (tailComparisonResult == 1) return true;
        else if (tailComparisonResult == -1) return false;

        if (compareNames(first.getName(), second.getName())){
            return true;
        }
        return false;
    }                                               

    private boolean compareNames(String s, String d){
        if (s.toLowerCase().compareTo(d.toLowerCase()) > 0){
            return true; 
        } 
        return false;
    }

    private int compareTailLength(double a, double b){
        if (a > b) return 1;
        if (a < b) return -1;
        return 0;
    }

    private int findSmallestDog(int startIndex, ArrayList<Dog> dogs){
        int smallestIndex = startIndex;
        for (int i = startIndex+1; i < dogs.size(); i++) {
            if(compareDogs(dogs.get(smallestIndex), dogs.get(i))){
                smallestIndex = i;
            }
        }
        return smallestIndex;
    }

    private int sortDogList(ArrayList<Dog> dogsToSort){
        int swaps = 0;
        for (int i = 0; i < dogsToSort.size(); i++) {
            int smallestIndex = findSmallestDog(i, dogsToSort);
            if(smallestIndex != i){
                swapDogsManual(i, smallestIndex, dogsToSort);
                swaps++;
            }
        }
        return swaps;
    }


}

