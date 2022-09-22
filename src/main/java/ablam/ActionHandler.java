package ablam;

import java.util.ArrayList;
import java.util.Scanner;

public class ActionHandler implements Handler{
    public Scanner scanner;
    public ActionHandler(){
        scanner = new Scanner(System.in);
    }

    public void handleAction(int action){

        if ((action < 1) || (action > 4)) {
            scanner.close();
            return;
        }

        Library library = new Library();

        if(action == 1){
            ArrayList<Book> books = library.getAllBooks();

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

            ArrayList<Book> books = library.getBookByTitle(title);

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

            ArrayList<Book> books = library.getBooksByAuthor(author);

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

            Book b = library.insertBook(title, author, year);
            System.out.println("You've inserted this book: " + b);
        }
    }
    }
