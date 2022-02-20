// Victor Lejon vile3398
public class Dog {

    private static final double DACHSHUND_TAIL_LENGTH = 3.7;
    private static final String[] DACHSHUND_NAMES = {"tax", "dachshund", "mäyräkoira", "teckel"};

    private String name;
    private String breed;
    private int age;
    private int weight;
    private Owner owner;

    public Dog(String name, String breed, int age, int weight){
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.weight = weight;
    }
    
    public String toString(){
        return String.format(
            "Name: %s Age: %s år Breed: %s Weight: %s kg TailLength: %s cm Owner: %s", this.name, this.age, this.breed, this.weight, getTailLength(), getOwner());
    }

    public String getName(){
        return this.name;
    }

    public String getBreed(){
        return this.breed;
    }

    public int getAge(){
        return this.age;
    }

    public int getWeight(){
        return this.weight;
    }

    public boolean increaseAge(){
        this.age += 1;
        return true;
    }

    public boolean increaseAge(int n){
        if (n > 0){
            this.age += n;
            return true;
        }
        else{
            return false;
        }
    }

    public Owner getOwner(){
        return this.owner;
    }

    public void setOwner(Owner newOwner){
        if (this.getOwner() != null) return;
        this.owner = newOwner;
        if (newOwner.checkIfDogAdded(this)) return;
        newOwner.addDog(this);
    }

    public double getTailLength(){
        return (checkIfDachshund()) ? DACHSHUND_TAIL_LENGTH : this.age*this.weight/10.0;
    }

    private boolean checkIfDachshund(){
        for (String name : DACHSHUND_NAMES) {
            if (name.equals(this.breed.toLowerCase())) return true;
        }
        return false;
    }

    public void removeOwner(){
        Owner owner = this.owner;
        if (this.getOwner() == null) return;
        this.owner = null;

        if(!owner.checkIfDogAdded(this)) return;
        owner.removeDog(this);
    }
}
