package ablam;

public interface Executor {
    /**
     * Shows info about all books in library
     */
    void getFullLibraryContent();

    /**
     * Shows info about all books with the specified author
     * @param author author's name and surname
     */
    void getBooksByAuthor(String author);

    /**
     * Shows info about a book with given title
     * @param title book's title
     */
    void getBookByTitle(String title);

    /**
     * Insert given values into library
     * @param title book's title
     * @param author book's author
     * @param year year, when book was published
     */
    void insertValues(String title, String author, int year);
}
