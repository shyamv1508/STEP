import java.util.Scanner;

class NonRepeating {

    static char findFirstNonRepeatingChar(String text) {

        int count[] = new int[256];

        for (int i = 0; i < text.length(); i++) {
            count[text.charAt(i)]++;
        }

        for (int i = 0; i < text.length(); i++) {
            if (count[text.charAt(i)] == 1)
                return text.charAt(i);
        }

        return '#';
    }

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a word: ");
        String text = sc.next();

        char ch = findFirstNonRepeatingChar(text);

        if (ch == '#')
            System.out.println("No Non-Repeating Character Found");
        else
            System.out.println("First Non-Repeating Character: " + ch);

        sc.close();
    }
}