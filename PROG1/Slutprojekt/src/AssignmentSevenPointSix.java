import java.util.ArrayList;
import java.util.Collections;

public class AssignmentSevenPointSix {

    @UnderTest(id="dogs")
    private ArrayList<Dog> dogList = new ArrayList<>();
    
    
    @UnderTest(id="U7.6.1.1")
    private void swapManual(int i, int k){
        Dog tmpDog = dogList.get(i);
        dogList.set(i, dogList.get(k));
        dogList.set(k, tmpDog);
    }

    @UnderTest(id="U7.6.1.2")
    private void swapWithCollectionsSwap(int i, int k){
        Collections.swap(dogList, i, k);
    }

    @UnderTest(id="U7.6.2")
    private boolean compareDogs(Dog first, Dog second){
        int tailComparisonResult = compareTailLength(first.getTailLength(), second.getTailLength());
        if (tailComparisonResult == 1) return true;
        else if (tailComparisonResult == -1) return false;

        if (compareNames(first.getName().toLowerCase(), second.getName().toLowerCase())){
            return true;
        }
        return false;
    }

    private boolean compareNames(String s, String d){
        if (s.compareTo(d) > 0){
            return true; 
        } 
        return false;
    }

    private int compareTailLength(double a, double b){
        if (a > b) return 1;
        if (a < b) return -1;
        return 0;
    }

    @UnderTest(id="U7.6.3")
    private int findSmallestDog(int startIndex){
        int smallestIndex = startIndex;
        for (int i = startIndex+1; i < dogList.size(); i++) {
            if(compareDogs(dogList.get(smallestIndex), dogList.get(i))){
                smallestIndex = i;
            }
        }
        return smallestIndex;
    }

    @UnderTest(id="U7.6.4")
    public int selectionSort(){
        int swaps = 0;
        for (int i = 0; i < dogList.size(); i++) {
            int smallestIndex = findSmallestDog(i);
            if(smallestIndex != i){
                swapManual(i, smallestIndex);
                swaps++;
            }
        }
        return swaps;
    }
}