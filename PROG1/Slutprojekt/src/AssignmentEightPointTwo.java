import java.util.ArrayList;

public class AssignmentEightPointTwo {
    
    @UnderTest(id="owners")
    private ArrayList<Owner> ownerList = new ArrayList<>();
    
    @UnderTest(id="U8.2")
    public Owner findOwnerByName(String ownerName){
        for (Owner owner : ownerList) {
            if (owner.getName().toLowerCase().equals(ownerName.toLowerCase())) return owner;
        }
        return null;
    }
}
