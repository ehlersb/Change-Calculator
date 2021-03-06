import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Benjamin Ehlers on 3/17/2019.
 */
public class main {

    public static void main(String[] args) {
        String input;
        System.out.println("Enter two lines specifying the change calculator I/O");
        System.out.println("Enter '!' to quit.");
        while(true) {
            Scanner scan = new Scanner(System.in);
            input = scan.nextLine();
            if (input.equals("!")) break;
            Scanner scanline = new Scanner(input);
            ArrayList firstLine = new ArrayList<Integer>();
            scanline.next();
            for(int i = 0; scanline.hasNext(); i++) {
                firstLine.add(Integer.parseInt(scanline.next()));
            }
            scanline.close();
            input = scan.nextLine();
            scanline = new Scanner(input);
            int secondLine = 0;
            scanline.next();
            scanline.next();
            String booleanOperator = scanline.next();
            secondLine = Integer.parseInt(scanline.next());
            ChangeCalculator cc = new ChangeCalculator(firstLine, booleanOperator, secondLine);
            cc.calculateChange();
        }
    }
}
