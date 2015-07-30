import org.apache.commons.lang.math.NumberUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.Serializable;

/**
* Ujwala Chintala
* 2015-07-29
/**

*/
//Implement Serializable to be able to ship objects over http.
public class SecretFunction implements Serializable{
 
    public static void main(String[] args) {
    	
    	final long serialVersionUID = 7526472295622776147L;
 
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a integer value greater than 1: ");
        int limit = 0;
        String input;
 
        input = scanner.nextLine();
        boolean isInputNum = NumberUtils.isDigits(input);
        if (isInputNum) { limit = Integer.parseInt(input);}
 
        if (limit < 2) {
            System.out.println("Error: " + input + " is invalid input. A single integer value greater than 1 must be entered.");
            System.exit(0);
        }
 
        Integer[] primes = returnPrimeNumbers(limit);
 
        System.out.println("\nPrimes under " + limit + ": " + Arrays.toString(primes));
 
        if (isAdditive(primes)) {
            System.out.println("The function is additive function for all prime number under " + limit + ".");
        } else {
            System.out.println("The function not an additive function for all prime number under " + limit + ".");
        }
    }
 
 //This function accepts single integer and returns a single integer
    public static int returnSecretNum(int number) {
        return number;
    }
 
 /* Returns all prime numbers less than given N */
    public static Integer[] returnPrimeNumbers(int limit) {
        int[] intList = new int[limit];
        ArrayList<Integer> primes = new ArrayList<>();
        Arrays.fill(intList, 1); 
        int i = 2;
 
        while (i < limit) {
            if (intList[i] == 1) {
                primes.add(i);
 
                int j = i;
                while (j < limit) {
                    intList[j] = 0; 
                    j += i;
                }
           }
            i++;
        }
        return primes.toArray(new Integer[primes.size()]);
    }
 
    //checks if the additive function [ secret(x+y) = secret(x) + secret(y) ]
    private static boolean isAdditive(Integer[] primes) {
        ArrayList<Integer> passed = new ArrayList<>();
 
        for (Integer x : primes) {
            for (Integer y : primes) {
                int sumOne = (x + y);
                int secretLeft = returnSecretNum(sumOne); // 1st - secret(x+y)
 
                int secretX = returnSecretNum(x);
                int secretY = returnSecretNum(y);
                int secretRight = (secretX + secretY); // 2nd - secret(x) + secret(y)
 
                if (secretLeft != secretRight) {
                    return false;
                }
            }
            passed.add(x);
        }
        return true;
    }
}