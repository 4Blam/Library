package ablam;

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

        ActionExecutor actionExecutor = new ActionExecutor();

        if(action == 1){
            actionExecutor.getFullLibraryContent();
            scanner.close();
            return;
        }
        if(action == 2){
            System.out.println("Enter title");
            scanner.nextLine();

            String title = scanner.nextLine();
            scanner.close();

            actionExecutor.getBookByTitle(title);
            return;
        }
        if(action == 3){
            System.out.println("Enter author");
            scanner.nextLine();

            String author = scanner.nextLine();
            scanner.close();

            actionExecutor.getBooksByAuthor(author);
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

            actionExecutor.insertValues(title, author, year);
        }
    }
    }
