import java.util.ArrayList;

public class AssignmentEightPointOne {
    
    private InputHandler input = new InputHandler();

    @UnderTest(id="owners")
    private ArrayList<Owner> ownerList = new ArrayList<>();

    @UnderTest(id="U8.1")
    public void registerOwner(){
        String name = input.readString("Name");
        ownerList.add(new Owner(name));
    }
}
