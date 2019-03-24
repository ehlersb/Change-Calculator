import java.lang.reflect.Array;
import java.util.*;

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
        for (int i = 1; i < firstLine.size(); i++) {
            this.changeArray.add(new Change((firstLine.get(i))));
        }
    }

    public void calculateChange() {
        int leftover;
        int addedup;
        int numCoins;
        String coins;

        //get every permutation of power set of changearray
        Set<Change> set = new HashSet<>(changeArray);
        HashSet<HashSet<Integer>> powerSetChangeArray = powerSet(set);
        HashSet<ArrayList<Integer>> powerPermutation = new HashSet<>();


        for (int i = 0; i < changeArray.size(); i++) {
            numCoins = 0;
            coins = "";
            addedup = 0;
            leftover = amount;
            for (int j = i; j < changeArray.size(); j++) {
                int jCoinValue = changeArray.get(j).getCoinValue();
                if ((leftover - jCoinValue > -1)) {
                    leftover -= jCoinValue;
                    numCoins++;
                    addedup += jCoinValue;
                    coins += jCoinValue + " ";
                }
            }
            if (comparator(numCoins) && addedup == amount) {
                solutions.add(coins);
            }

        }
        for (int i = 0; i < solutions.size(); i++) {
            System.out.println(solutions.get(i));
        }
        if (solutions.size() == 0) {
            System.out.println("No solution");
        }
    }

    private boolean comparator(int numCoins) {
        if (booleanOperator.equals("<")) {
            return numCoins < bindingNumber;
        }
        if (booleanOperator.equals("<=")) {
            return numCoins <= bindingNumber;
        }
        if (booleanOperator.equals(">")) {
            return numCoins > bindingNumber;
        }
        if (booleanOperator.equals(">=")) {
            return numCoins >= bindingNumber;
        }
        if (booleanOperator.equals("=")) {
            return numCoins == bindingNumber;
        }
        if (booleanOperator.equals("!=")) {
            return numCoins != bindingNumber;
        }
        return false;
    }

    private static void permute(ArrayList<Integer> arr, int k, HashSet<ArrayList<Integer>> powerPermutation) { //credit to https://stackoverflow.com/a/14444037  (I did not come up with this!)
        for (int i = k; i < arr.size(); i++) {
            Collections.swap(arr, i, k);
            permute(arr, k + 1, powerPermutation);
            Collections.swap(arr, k, i);
        }
        if (k == arr.size() - 1) {
            System.out.println(java.util.Arrays.toString(arr.toArray()));
            powerPermutation.add(arr);
        }
    }

    private static <T> Set<Set<T>> powerSet(Set<T> originalSet) { //credit to https://stackoverflow.com/a/1670871 (I did not come up with this!)
        Set<Set<T>> sets = new HashSet<Set<T>>();
        if (originalSet.isEmpty()) {
            sets.add(new HashSet<T>());
            return sets;
        }
        List<T> list = new ArrayList<T>(originalSet);
        T head = list.get(0);
        Set<T> rest = new HashSet<T>(list.subList(1, list.size()));
        for (Set<T> set : powerSet(rest)) {
            Set<T> newSet = new HashSet<T>();
            newSet.add(head);
            newSet.addAll(set);
            sets.add(newSet);
            sets.add(set);
        }
        return sets;
    }

}
