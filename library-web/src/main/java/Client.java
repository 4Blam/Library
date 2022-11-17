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
        client.actionHandler.handleAction(client.actionHandler.scanner.nextInt());
    }
}
