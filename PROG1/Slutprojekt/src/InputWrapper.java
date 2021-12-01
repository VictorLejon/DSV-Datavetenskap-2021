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

    public String readText(String text){
        System.out.print(text + "?>");
        return this.scanner.nextLine();
    }

    public int readInt(String text){
        System.out.print(text + "?>");
        int userInput = this.scanner.nextInt();
        this.scanner.nextLine();
        return userInput;
    }

    public double readDouble(String text){
        System.out.print(text + "?>");
        double userInput = this.scanner.nextDouble();
        this.scanner.nextLine();
        return userInput;
    }

}
