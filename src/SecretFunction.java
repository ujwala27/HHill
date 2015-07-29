import org.apache.commons.lang.math.NumberUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/**
* Ujwala Chintala
* 2015-07-29
/**

*/
 
public class SecretFunction {
 
    public static void main(String[] args) {
 
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter a single integer value greater than 1: ");
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
 
        if (testSecret(primes)) {
            System.out.println("The function secret() is an additive function for all prime number under " + limit + ".");
        } else {
            System.out.println("The function secret() is not an additive function for all prime number under " + limit + ".");
        }
    }
 
 
    public static int returnSecretNum(int number) {
        return number;
    }
 
 
    public static Integer[] returnPrimeNumbers(int limit) {
        int[] intList = new int[limit];
        ArrayList<Integer> primes = new ArrayList<>();
        Arrays.fill(intList, 1); // initially assume all are primes
        int i = 2;
 
        while (i < limit) {
            if (intList[i] == 1) {
                primes.add(i);
 
                int j = i;
                while (j < limit) {
                    intList[j] = 0; // number fails prime test
                    j += i;
                }
           }
            i++;
        }
        return primes.toArray(new Integer[primes.size()]);
    }
 
 
    private static boolean testSecret(Integer[] primes) {
        ArrayList<Integer> passed = new ArrayList<>();
 
        for (Integer x : primes) {
            for (Integer y : primes) {
                int sumLeft = (x + y);
                int secretLeft = returnSecretNum(sumLeft); // left side - secret(x+y)
 
                int secretX = returnSecretNum(x);
                int secretY = returnSecretNum(y);
                int secretRight = (secretX + secretY); // right side - secret(x) + secret(y)
 
                if (secretLeft != secretRight) {
                    return false;
                }
            }
            passed.add(x);
        }
        return true;
    }
}