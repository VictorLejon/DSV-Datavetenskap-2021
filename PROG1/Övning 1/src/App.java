import java.util.Scanner;
import java.util.stream.IntStream;
public class App {
    static char[] alfabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'å', 'ä', 'ö'};
    static Scanner in = new Scanner(System.in, "UTF-8");
    public static void main(String[] args) throws Exception {
    
        while (true){
            System.out.println("1. Kryptera text");
            System.out.println("2. Avkryptera text");
            System.out.println("3. Avsluta");

            String text;
            String action = in.nextLine();
            switch(action){
                case "1":
                    text = cipher(true);
                    System.out.println(text);
                    break;
                case "2":
                    text = cipher(false);
                    System.out.println(text);
                    break;
                case "3":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Välj ett alternativ...");
                    break;
            }
        }
    }

    private static char[] getInput(){
        System.out.println("Skriv in textsträng: ");    
        String data = in.nextLine();
        return data.toCharArray();
    }

    private static String cipher(boolean encrypt){
        char[] letters = getInput();

        // Chiffer
        char[] cipheredChars = new char[letters.length];

        int i = 0;
        for (char letter : letters) {
            int newIndex = (encrypt) ? getEncryptedIndex(letter) : getDecryptedIndex(letter);

            // Check for uppercase
            letter = (Character.isLowerCase(letter)) ? alfabet[newIndex] : Character.toUpperCase(alfabet[newIndex]);
            cipheredChars[i] = letter;
            i++;
        }

        
        return new String(cipheredChars);
    }

    private static int getEncryptedIndex(char letter){
        int index = getIndex(letter);

        /*
        int index = IntStream.range(0, alfabet.length)
                        .filter(i -> Character.toLowerCase(letter) == alfabet[i])
                        .findFirst()
                        .orElse(-1);
        */
        return (index+3)%alfabet.length;
    }

    private static int getDecryptedIndex(char letter){
        int index = getIndex(letter);
        if (index-3 < 0) index += alfabet.length;
        return (index-3)%alfabet.length;
    }

    private static int getIndex(char letter){
        return new String(alfabet).indexOf(Character.toLowerCase(letter));
    }
}
