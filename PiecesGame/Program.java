import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        Game currentGame;
        currentGame = new Game((byte) 2); // Game(numberOfPlayers)
        currentGame.defaultSettings(inputHorizontal(),inputVertical()); // applies default settings to game
        currentGame.run(); // start game with saved settings
        System.exit(0);
    }

    static byte inputHorizontal(){ // متد دریافت اندازه افقی صفحه
        byte horizontalLength;
        System.out.println("لطفا تعداد ستون های بازی راانتخاب کنید (حتما عددی فرد انتخاب کنید):");
        try {
            horizontalLength = input.nextByte();
            if (horizontalLength < 3) {
                System.out.println("مقدار وارد شده بسیار کوچک است، حداقل مقدار انتخاب شد.");
                horizontalLength = 3;
                return horizontalLength;
            }
            if (horizontalLength%2 == 0) {
                horizontalLength += 1;
                System.out.println("عدد وارد شده فرد نیست! عدد "+horizontalLength+" انتخاب شد.");
                return horizontalLength;
            }
        } catch (InputMismatchException e){
            System.out.println("ورودی نامعتبر! در حال آغاز بازی با تعداد پیش فرض....");
            input.nextLine();
            horizontalLength = 5;
            return horizontalLength;
        }
        return horizontalLength;
    }


    static byte inputVertical(){ // متد دریافت اندازه عمودی صفحه
        byte verticalLength;
        System.out.println("لطفا تعداد سطرهای بازی راانتخاب کنید:");
        try {
            verticalLength = input.nextByte();
            if (verticalLength < 5){
                verticalLength = 5;
                System.out.println("مقدار وارد شده بسیار کوچک است، حداقل مقدار انتخاب شد.");
                return verticalLength;
            }
        } catch (InputMismatchException e){
            System.out.println("ورودی نامعتبر! در حال آغاز بازی با تعداد پیش فرض....");
            input.nextLine();
            verticalLength = 7;
            return verticalLength;
        }
        return verticalLength;
    }
}
