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
            throw new IllegalStateException("Error: Input stream already in use");
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
        int userInput;
        do{
            System.out.print(text + "?>");
            userInput = this.scanner.nextInt();
            this.scanner.nextLine();
        }while(!checkIfValid(userInput));
        return userInput;
    }

    public double readDouble(String text){
        double userInput;
        do{
            System.out.print(text + "?>");
            userInput = this.scanner.nextDouble();
            this.scanner.nextLine();
        }while(!checkIfValid(userInput));
        return userInput;
    }

    private boolean checkIfValid(double userInput){
        if (userInput == 0) return false;
        return true;
    }

    private boolean checkIfValid(int userInput){
        if (userInput == 0) return false;
        return true;
    }

    private boolean checkIfValid(String userInput){
        if (userInput.equals("")){
            System.out.println("Error: Cannot enter empty string");
            return false;
        } 
        return true;
    }
}
