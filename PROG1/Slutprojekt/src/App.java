public class App {
    public static void main(String[] args) throws Exception {
        AssignmentSevenPointSix a = new AssignmentSevenPointSix();

        
        
        Dog b = new Dog("Karo", "Breed", 5, 7);
        Dog c = new Dog("Ronja", "Breed", 9, 15);
        Dog d = new Dog("Lassie", "Breed", 1, 4);
        Dog e = new Dog("Charlie", "Breed", 14, 20);
        Dog f = new Dog("Ludde", "Breed", 1, 4);
        a.dogList.add(b);
        a.dogList.add(c);
        a.dogList.add(d);
        a.dogList.add(e);
        a.dogList.add(f);

        a.selectionSort();
    }
}
