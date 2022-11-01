import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ActionHandler implements Handler{
    public Scanner scanner;
    public ActionHandler(){
        scanner = new Scanner(System.in);
    }

    public void handleAction(int action){
        BookLibraryServiceImpl bookLibraryServiceImpl = new BookLibraryServiceImpl();

        if(action == 1){
            scanner.close();
            List<Book> books;

            try {
                books = bookLibraryServiceImpl.getAllBooks();
            } catch (Exception e){
                System.out.println("Couldn't complete your request, something went wrong");
                return;
            }

            for (Book b : books){
                System.out.println(b);
            }

            scanner.close();
            return;
        }
        if(action == 2){
            System.out.println("Enter title");
            scanner.nextLine();

            String title = scanner.nextLine();
            scanner.close();

            List<Book> books;

            try {
                books = bookLibraryServiceImpl.getBookByTitle(title);
            } catch (Exception e){
                System.out.println("Couldn't complete your request, something went wrong");
                return;
            }

            for (Book b : books){
                System.out.println(b);
            }

            return;
        }
        if(action == 3){
            System.out.println("Enter author");
            scanner.nextLine();

            String author = scanner.nextLine();
            scanner.close();

            List<Book> books;

            try {
                books = bookLibraryServiceImpl.getBooksByAuthor(author);
            } catch (Exception e){
                System.out.println(e.getMessage());

                System.out.println("Couldn't complete your request, something went wrong");
                return;
            }

            for (Book b : books){
                System.out.println(b);
            }

            return;
        }
        if(action == 4){
            System.out.println("Enter title");
            scanner.nextLine();
            String title = scanner.nextLine();
            System.out.println("Enter author");
            String author = scanner.nextLine();
            System.out.println("Enter year when book was published: ");
            int year = scanner.nextInt();
            scanner.close();

            Book b = bookLibraryServiceImpl.insertBook(title, author, year);
            System.out.println("You've inserted this book: " + b);
        }
    }
    }
