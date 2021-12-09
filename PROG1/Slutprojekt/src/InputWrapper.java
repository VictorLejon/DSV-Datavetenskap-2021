import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

@UnderTest(id="U6.3")
public class InputWrapper{

    
    private static ArrayList<InputStream> inputStreamsUsed = new ArrayList<>();
    private Scanner scanner;

    public InputWrapper(){
        this(System.in);
    }

    public InputWrapper(InputStream inputStream){
        if(inputStreamsUsed.contains(inputStream)){
            throw new IllegalStateException("Error: InputStream already in use");
        }

        else{
            this.scanner = new Scanner(inputStream);
            inputStreamsUsed.add(inputStream);
        } 
    }

    public String readString(String text){
        String userInput;
        do{
            System.out.print(text + "?>");
            userInput = this.scanner.nextLine().trim();
        }while(!checkIfValid(userInput));
        return userInput;
    }

    public int readInt(String text){
        System.out.print(text + "?>");
        do{
            if (this.scanner.hasNextInt()) return this.scanner.nextInt();
            else {
                this.scanner.nextLine();
                System.out.print(text + "?>");
            }
        }while (this.scanner.hasNext());
        return 0;
    }

    public double readDouble(String text){
        System.out.print(text + "?>");
        do{
            if (this.scanner.hasNextDouble()) return this.scanner.nextDouble();
            else {
                this.scanner.nextLine();
                System.out.print(text + "?>");
            }
        }while (this.scanner.hasNext());
        return 0;
    }

    /*
    private boolean checkIfValid(double userInput){
        if (userInput == 0) return false;
        return true;
    }

    private boolean checkIfValid(int userInput){
        if (userInput == 0) return false;
        return true;
    }
    */
    
    private boolean checkIfValid(String userInput){
        if (userInput.equals("")){
            System.out.println("Error: Cannot enter empty string");
            return false;
        } 
        return true;
    }
}
