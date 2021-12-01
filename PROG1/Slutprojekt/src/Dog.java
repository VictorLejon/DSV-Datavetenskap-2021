public class Dog {

    private String name;
    private String breed;
    private int age;
    private int weight;

    public Dog(String name, String breed, int age, int weight){
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.weight = weight;
    }
    
    public String toString(){
        return String.format(
            "Name: %s Age: %s år Breed: %s Weight: %s kg TailLength: %s dm", this.name, this.age, this.breed, this.weight, getTailLength());
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

    public double getTailLength(){
        return calculateTailLength();
    }

    public boolean increaseDogAge(int n){
        if (n > 0){
            this.age += n;
            return true;
        }
        else{
            return false;
        }
    }

    private double calculateTailLength(){
        final double taxTailLength = 3.7;
        String[] taxar = {"tax", "dachshund", "mäyräkoira", "teckel"};
        return (checkInStringArray(taxar, this.breed)) ? taxTailLength : this.age*this.weight/10.0;
    }

    private boolean checkInStringArray(String[] stringArray, String item){
        for (String element : stringArray) {
            if (element.equals(item.toLowerCase())) return true;
        }
        return false;
    }
}
