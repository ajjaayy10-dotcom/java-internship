import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Practice2_HashMapRollNumbers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<Integer, String> rollToName = new HashMap<>();

        System.out.print("Enter number of students: ");
        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            System.out.print("Enter roll number: ");
            int roll = Integer.parseInt(sc.nextLine());
            System.out.print("Enter name: ");
            String name = sc.nextLine();
            rollToName.put(roll, name);
        }

        System.out.println("\n----- Roll Number : Student Name -----");
        for (Map.Entry<Integer, String> entry : rollToName.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        sc.close();
    }
}
