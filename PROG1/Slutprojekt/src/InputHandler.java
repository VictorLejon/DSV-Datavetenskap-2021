// Victor Lejon vile3398
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class InputHandler{

    
    private static ArrayList<InputStream> inputStreamsUsed = new ArrayList<>();
    private Scanner scanner;

    public InputHandler(){
        this(System.in);
    }

    public InputHandler(InputStream inputStream){
        if(inputStreamsUsed.contains(inputStream)){
            throw new IllegalStateException("Error: InputStream already in use");
        }
        else{
            this.scanner = new Scanner(inputStream);
            inputStreamsUsed.add(inputStream);
        } 
    }

    public String readString(String promptText){
        String userInput;
        do{
            printPrompt(promptText);
            userInput = this.scanner.nextLine().trim();
        }while(!checkIfEmptyString(userInput));
        return userInput;
    }

    public int readInt(String promptText){
        printPrompt(promptText);
        do{
            if (this.scanner.hasNextInt()){
                int value = this.scanner.nextInt();
                this.scanner.nextLine();
                return value;
            }
            else {
                this.scanner.nextLine();
                printPrompt(promptText);
            }
        }while (this.scanner.hasNext());
        return 0;
    }

    public double readDouble(String promptText){
        printPrompt(promptText);
        do{
            if (this.scanner.hasNextDouble()){
                double value = this.scanner.nextDouble();
                this.scanner.nextLine();
                return value;
            }
            else {
                this.scanner.nextLine();
                printPrompt(promptText);
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
    
    private boolean checkIfEmptyString(String userInput){
        if (userInput.equals("")){
            //System.out.println("Error: Cannot enter empty string");
            return false;
        } 
        return true;
    }

    private void printPrompt(String text){
        System.out.print(text + "?> ");
    }
}
