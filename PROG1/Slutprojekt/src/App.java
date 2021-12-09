public class App {
    public static void main(String[] args) throws Exception {
        AssignmentEightPointFour a = new AssignmentEightPointFour();

        Dog b = new Dog("b", "breed", 4, 4);
        Dog c = new Dog("c", "breed", 4, 4);
        Dog d = new Dog("d", "breed", 4, 4);

        Owner e = new Owner("e");
        Owner f = new Owner("f");
        Owner g = new Owner("g");

        a.dogList.add(b);
        a.dogList.add(c);
        a.dogList.add(d);

        a.ownerList.add(e);
        a.ownerList.add(f);
        a.ownerList.add(g);
        

        a.addDogToOwner();
        a.addDogToOwner();
        a.listOwnersWithDogs();
    }
}
