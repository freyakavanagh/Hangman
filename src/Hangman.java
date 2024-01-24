import java.util.Random;
import java.util.Scanner;

public class Hangman {

    static Scanner scanner = new Scanner(System.in);

    public static String[] words = {"ant", "baboon", "badger", "bat", "bear", "beaver", "camel",
            "cat", "clam", "cobra", "cougar", "coyote", "crow", "deer",
            "dog", "donkey", "duck", "eagle", "ferret", "fox", "frog", "goat",
            "goose", "hawk", "lion", "lizard", "llama", "mole", "monkey", "moose",
            "mouse", "mule", "newt", "otter", "owl", "panda", "parrot", "pigeon",
            "python", "rabbit", "ram", "rat", "raven","rhino", "salmon", "seal",
            "shark", "sheep", "skunk", "sloth", "snake", "spider", "stork", "swan",
            "tiger", "toad", "trout", "turkey", "turtle", "weasel", "whale", "wolf",
            "wombat", "zebra"};

    public static String[] gallows = {"+---+\n" +
            "|   |\n" +
            "    |\n" +
            "    |\n" +
            "    |\n" +
            "    |\n" +
            "=========\n",

            "+---+\n" +
                    "|   |\n" +
                    "O   |\n" +
                    "    |\n" +
                    "    |\n" +
                    "    |\n" +
                    "=========\n",

            "+---+\n" +
                    "|   |\n" +
                    "O   |\n" +
                    "|   |\n" +
                    "    |\n" +
                    "    |\n" +
                    "=========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|   |\n" +
                    "     |\n" +
                    "     |\n" +
                    " =========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|\\  |\n" + 
                    "     |\n" +
                    "     |\n" +
                    " =========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|\\  |\n" +
                    "/    |\n" +
                    "     |\n" +
                    " =========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|\\  |\n" +
                    "/ \\  |\n" +
                    "     |\n" +
                    " =========\n"};

    public static String guessString(String word){
        StringBuilder guessBuilder = new StringBuilder();


        for (int i = 0; i < word.length(); i++) {
            guessBuilder.append("_");
        }

        return guessBuilder.toString();

    }

    public static void playHangman(){
        int misses = 0;
        int gallowsNumber = 0;
        int maxAttempts = 6;
        String word = randomWord();
        String guess =guessString(word);
        do {
            System.out.print(gallows[gallowsNumber] + wordPlaceholder(word, guess) + getMisses(misses) + "\nGuess:");
            guess = scanner.nextLine();

            if (guess.length() != word.length()) {
                do {
                    System.out.print("\nInvalid input! Please enter a guess with the same length as the word.\nGuess:");
                    guess = scanner.nextLine();
                } while(guess.length() != word.length());
            }
            if (guess.equals(word)) {
                System.out.print(gallows[gallowsNumber] + wordPlaceholder(word, guess) + getMisses(misses) + "\nCongratulations!");
                break;
            } else misses++;
            gallowsNumber++;
        } while (misses < 6);
        if(misses == maxAttempts){
            System.out.print(gallows[gallowsNumber]);
            System.out.println("\nSorry, you ran out of attempts.\nThe word was: " + word);
        }

    }

    public static String randomWord(){

        Random random = new Random();

        int randomNumber = random.nextInt(63) + 1;

        return words[randomNumber];

    }

    public static String wordPlaceholder(String word, String guess){
        int length = word.length();
        StringBuilder placeholder = new StringBuilder();

        placeholder.append("\nWord:   ");

        for (int i = 0; i < word.length(); i++) {
            if (guess.charAt(i) != word.charAt(i)) {
                placeholder.append("_ ");
            }else if (guess.charAt(i) == word.charAt(i)){
                placeholder.append(guess.charAt(i)+" ");
            }
        }

        placeholder.deleteCharAt(placeholder.length() - 1);

        return placeholder.toString();

    }

    public static String getMisses(int missesNumber){
        String stringMissesNumber = Integer.toString(missesNumber);
        String misses = "\nMisses: "+stringMissesNumber;
        return misses;
    }

    public static void main(String[] args) {
        do{
            System.out.println("\nWanna play hangman?");
            if (scanner.nextLine().equals("yes")) {
                System.out.println("Get ready!");
                playHangman();
                System.out.println("\nThanks for playing!");
            } else {
                System.out.println("Next time then!");
                break;
            }
        } while (true);

    }

}
