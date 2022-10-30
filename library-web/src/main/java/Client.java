import java.util.InputMismatchException;

public class Client {
    private final ActionHandler actionHandler;
    public Client(){
        this.actionHandler = new ActionHandler();
    }
    private final String instruction =
            "Choose what you want to do:\n" +
            "1: you want to see info about all books we have\n" +
            "2: you want to see info about a book with given title\n" +
            "3: you want to see info about all books with given author\n" +
            "4: you want to add info about a book\n";
    public static void main(String[] args){
        Client client = new Client();
        System.out.print(client.instruction);
        int action = 0;
        try {
            action = client.actionHandler.scanner.nextInt();
            if ((action < 1) || (action > 4)){
                throw new InputMismatchException();
            }
        } catch (InputMismatchException e){
            System.out.println("Wrong input, enter integer from 1 to 4");
            client.actionHandler.scanner.close();
            return;
        }
        client.actionHandler.handleAction(action);
    }
}
