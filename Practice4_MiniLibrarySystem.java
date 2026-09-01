import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Book {
    String title;
    String author;
    boolean issued;

    Book(String title, String author, boolean issued) {
        this.title = title;
        this.author = author;
        this.issued = issued;
    }

    String toFileFormat() {
        return title + "," + author + "," + issued;
    }

    static Book fromFileFormat(String line) {
        String[] parts = line.split(",");
        return new Book(parts[0], parts[1], Boolean.parseBoolean(parts[2]));
    }

    void display() {
        System.out.println("\"" + title + "\" by " + author +
                " - " + (issued ? "Issued" : "Available"));
    }
}

public class Practice4_MiniLibrarySystem {
    static final String FILE_NAME = "library_books.txt";
    static ArrayList<Book> books = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        loadBooks();
        int choice;

        do {
            System.out.println("\n===== Mini Library Management System =====");
            System.out.println("1. Add Book");
            System.out.println("2. Issue Book");
            System.out.println("3. Return Book");
            System.out.println("4. View All Books");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Enter title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter author: ");
                    String author = sc.nextLine();
                    books.add(new Book(title, author, false));
                    saveBooks();
                    System.out.println("Book added successfully!");
                    break;

                case 2:
                    System.out.print("Enter title to issue: ");
                    String issueTitle = sc.nextLine();
                    issueBook(issueTitle);
                    break;

                case 3:
                    System.out.print("Enter title to return: ");
                    String returnTitle = sc.nextLine();
                    returnBook(returnTitle);
                    break;

                case 4:
                    viewBooks();
                    break;

                case 5:
                    System.out.println("Exiting Library System. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);

        sc.close();
    }

    static void issueBook(String title) {
        for (Book b : books) {
            if (b.title.equalsIgnoreCase(title)) {
                if (b.issued) {
                    System.out.println("Book is already issued.");
                } else {
                    b.issued = true;
                    saveBooks();
                    System.out.println("Book issued successfully!");
                }
                return;
            }
        }
        System.out.println("Book not found.");
    }

    static void returnBook(String title) {
        for (Book b : books) {
            if (b.title.equalsIgnoreCase(title)) {
                if (!b.issued) {
                    System.out.println("Book was not issued.");
                } else {
                    b.issued = false;
                    saveBooks();
                    System.out.println("Book returned successfully!");
                }
                return;
            }
        }
        System.out.println("Book not found.");
    }

    static void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }
        System.out.println("\n----- Library Books -----");
        for (Book b : books) {
            b.display();
        }
    }

    static void saveBooks() {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            for (Book b : books) {
                writer.write(b.toFileFormat() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }

    static void loadBooks() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    books.add(Book.fromFileFormat(line));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading books: " + e.getMessage());
        }
    }
}
