import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Practice3_SortNumbers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList<>();

        System.out.print("Enter number of elements: ");
        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            System.out.print("Enter number " + (i + 1) + ": ");
            numbers.add(Integer.parseInt(sc.nextLine()));
        }

        System.out.println("\nOriginal List: " + numbers);

        Collections.sort(numbers);
        System.out.println("Sorted Ascending: " + numbers);

        Collections.sort(numbers, Collections.reverseOrder());
        System.out.println("Sorted Descending: " + numbers);

        sc.close();
    }
}
