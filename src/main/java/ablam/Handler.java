package ablam;

public interface Handler {
    /**
     * This method allows us to handle request made by user. Requests are divided by specific codes.
     *
     * <ul>
     *  <li>1: user wants to see info about all books we have
     *  <li>2: user wants to see info about a book with given title
     *  <li>3: user wants to see info about all books with given author
     *  <li>4: user wants to add info about a book
     * </ul>
     *
     * @param action int parameter, which means specific action (from 1 to 4)
     */
    void handleAction(int action);
}
