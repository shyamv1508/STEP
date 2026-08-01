import java.util.Scanner;

class Palindrome {

    static boolean isPalindromeIterative(String text) {

        int i = 0;
        int j = text.length() - 1;

        while (i < j) {

            if (text.charAt(i) != text.charAt(j))
                return false;

            i++;
            j--;
        }

        return true;
    }

    static boolean isPalindromeRecursive(String text, int i, int j) {

        if (i >= j)
            return true;

        if (text.charAt(i) != text.charAt(j))
            return false;

        return isPalindromeRecursive(text, i + 1, j - 1);
    }

    static boolean isPalindromeArrayReversal(String text) {

        char arr[] = text.toCharArray();
        char rev[] = new char[arr.length];

        for (int i = 0; i < arr.length; i++) {
            rev[i] = arr[arr.length - 1 - i];
        }

        String reversed = new String(rev);

        return text.equals(reversed);
    }

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a word: ");
        String text = sc.next();

        if (isPalindromeIterative(text))
            System.out.println("Iterative: Palindrome");
        else
            System.out.println("Iterative: Not Palindrome");

        if (isPalindromeRecursive(text, 0, text.length() - 1))
            System.out.println("Recursive: Palindrome");
        else
            System.out.println("Recursive: Not Palindrome");

        if (isPalindromeArrayReversal(text))
            System.out.println("Array Reversal: Palindrome");
        else
            System.out.println("Array Reversal: Not Palindrome");

        sc.close();
    }
}