import java.util.ArrayList;
import java.util.List;

// Encapsulation: Book class with private fields and public getters/setters
class Book {
    private String title;
    private String author;
    private boolean isBorrowed;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.isBorrowed = borrowed;
    }
}

// Abstraction: abstract class defining a contract for library users
abstract class User {
    protected String name;

    public User(String name) {
        this.name = name;
    }

    // Abstract method - must be implemented by subclasses
    abstract void showRole();
}

// Inheritance: Member extends User
class Member extends User {
    public Member(String name) {
        super(name);
    }

    @Override
    void showRole() {
        System.out.println(name + " is a Library Member.");
    }
}

// Library class: manages the collection of books
class Library {
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: \"" + book.getTitle() + "\" by " + book.getAuthor());
    }

    public void borrowBook(String title, Member member) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                if (!book.isBorrowed()) {
                    book.setBorrowed(true);
                    System.out.println(member.name + " borrowed \"" + title + "\"");
                } else {
                    System.out.println("Sorry, \"" + title + "\" is already borrowed.");
                }
                return;
            }
        }
        System.out.println("Book \"" + title + "\" not found in library.");
    }

    public void returnBook(String title, Member member) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                if (book.isBorrowed()) {
                    book.setBorrowed(false);
                    System.out.println(member.name + " returned \"" + title + "\"");
                } else {
                    System.out.println("\"" + title + "\" was not borrowed.");
                }
                return;
            }
        }
        System.out.println("Book \"" + title + "\" not found in library.");
    }

    public void showAvailableBooks() {
        System.out.println("\n----- Available Books -----");
        for (Book book : books) {
            if (!book.isBorrowed()) {
                System.out.println("\"" + book.getTitle() + "\" by " + book.getAuthor());
            }
        }
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();

        // Adding books
        library.addBook(new Book("The Alchemist", "Paulo Coelho"));
        library.addBook(new Book("Clean Code", "Robert C. Martin"));
        library.addBook(new Book("Java Basics", "James Gosling"));

        // Creating a member (polymorphism via User reference possible too)
        Member member1 = new Member("Ajay Kumar");
        member1.showRole();

        // Borrowing and returning books
        library.borrowBook("Clean Code", member1);
        library.showAvailableBooks();

        library.returnBook("Clean Code", member1);
        library.showAvailableBooks();

        // Trying to borrow a book that doesn't exist
        library.borrowBook("Unknown Book", member1);
    }
}
