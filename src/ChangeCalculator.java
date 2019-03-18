import java.util.ArrayList;

/**
 * Created by Benjamin Ehlers on 3/17/2019.
 */
public class ChangeCalculator {

    private ArrayList<Change> changeArray;
    private int amount;
    private String booleanOperator;
    private int bindingNumber;
    private ArrayList<String> solutions;

    public ChangeCalculator(ArrayList<Integer> firstLine, String booleanOperator, int secondLine) {
        this.amount = firstLine.get(0);
        this.bindingNumber = secondLine;
        this.booleanOperator = booleanOperator;
        this.changeArray = new ArrayList<>();
        this.solutions = new ArrayList<>();
        for(int i = 1; i < firstLine.size(); i++) {
            this.changeArray.add(new Change((firstLine.get(i))));
        }
    }

    public void calculateChange() {
        int leftover;
        int addedup;
        int numCoins;
        String coins;

        for(int i = 0; i < changeArray.size(); i++)
        {
            numCoins = 0;
            coins = "";
            addedup = 0;
            leftover = amount;
            for(int j = i; j < changeArray.size(); j++)
            {
                int jCoinValue = changeArray.get(j).getCoinValue();
                if((leftover - jCoinValue > -1)) {
                    leftover -= jCoinValue;
                    numCoins++;
                    addedup += jCoinValue;
                    coins += jCoinValue + " ";
                }
            }
            if(comparator(numCoins) && addedup == amount) {
                solutions.add(coins);
            }

        }
        for(int i = 0; i < solutions.size(); i++) {
            System.out.println(solutions.get(i));
        }
        if(solutions.size() == 0) {
            System.out.println("No solution");
        }
    }

    private boolean comparator(int numCoins) {
        if(booleanOperator.equals("<")) {
            return numCoins < bindingNumber;
        }
        if(booleanOperator.equals("<=")) {
            return numCoins <= bindingNumber;
        }
        if(booleanOperator.equals(">")) {
            return numCoins > bindingNumber;
        }
        if(booleanOperator.equals(">=")) {
            return numCoins >= bindingNumber;
        }
        if(booleanOperator.equals("=")) {
            return numCoins == bindingNumber;
        }
        if(booleanOperator.equals("!=")) {
            return numCoins != bindingNumber;
        }
        return false;
    }

}
