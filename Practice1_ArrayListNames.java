import java.util.ArrayList;
import java.util.Scanner;

public class Practice1_ArrayListNames {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> names = new ArrayList<>();

        System.out.print("Enter number of students: ");
        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            System.out.print("Enter name of student " + (i + 1) + ": ");
            names.add(sc.nextLine());
        }

        System.out.println("\n----- Student Names -----");
        for (String name : names) {
            System.out.println(name);
        }

        sc.close();
    }
}
