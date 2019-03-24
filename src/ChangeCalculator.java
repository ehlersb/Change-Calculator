import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Benjamin Ehlers on 3/17/2019.
 */
public class ChangeCalculator {

    private ArrayList<Integer> changeArray;
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
            this.changeArray.add(firstLine.get(i));
        }
    }

    public void calculateChange() {
        int leftover;
        int addedup;
        int numCoins;
        String coins;
        HashSet<ArrayList<Integer>> changeArrayFinal = new HashSet<>();
        //get every permutation of power set of changearray
        Set<Integer> set = new HashSet<>(changeArray);
        HashSet<HashSet<Integer>> powerSetChangeArray = powerSet(set);
        HashSet<ArrayList<Integer>> powerPermutation = new HashSet<>();
        HashSet<ArrayList<Integer>> tempSetofALs = new HashSet<>();
        for(HashSet<Integer> hs : powerSetChangeArray) { //create new HashSet of ArrayLists of integers to permute FROM the powerset Set of Sets
            ArrayList<Integer> a = new ArrayList<>();
            for(Integer i : hs) {
                a.add(i);
            }
            tempSetofALs.add(a);
        }
        for(ArrayList<Integer> a :tempSetofALs) {
            permute(a, a.size(), changeArrayFinal);
        }



        for (ArrayList<Integer> a : changeArrayFinal) {
            numCoins = 0;
            coins = "";
            addedup = 0;
            leftover = amount;
            for (int i = 0; i < a.size(); i++) {
                int jCoinValue = changeArray.get(i);
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
            //System.out.println(java.util.Arrays.toString(arr.toArray()));
            powerPermutation.add(arr);
        }
    }

    private static HashSet<HashSet<Integer>> powerSet(Set<Integer> originalSet) { //credit to https://stackoverflow.com/a/1670871 (I did not come up with this!)
        HashSet<HashSet<Integer>> sets = new HashSet<HashSet<Integer>>();
        if (originalSet.isEmpty()) {
            sets.add(new HashSet<Integer>());
            return sets;
        }
        List<Integer> list = new ArrayList<Integer>(originalSet);
        Integer head = list.get(0);
        HashSet<Integer> rest = new HashSet<Integer>(list.subList(1, list.size()));
        for (HashSet<Integer> set : powerSet(rest)) {
            HashSet<Integer> newSet = new HashSet<Integer>();
            newSet.add(head);
            newSet.addAll(set);
            sets.add(newSet);
            sets.add(set);
        }
        return sets;
    }

}
