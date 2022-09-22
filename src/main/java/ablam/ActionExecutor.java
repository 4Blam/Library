package ablam;

import java.sql.SQLException;

public class ActionExecutor implements Executor{
    private BookRepository bookRepository;
    public ActionExecutor(){
        this.bookRepository = new BookRepository();
    }
    public void getFullLibraryContent(){
        String selectSql = "select title,author,published_in from devschema.library";
        try {
            System.out.println(bookRepository.actualSelect(selectSql));
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void getBooksByAuthor(String author){
        String selectSql = "select title,author,published_in from devschema.library where author='" + author +"';";
        try {
            System.out.println(bookRepository.actualSelect(selectSql));
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void getBookByTitle(String title){
        String selectSql = "select title,author,published_in from devschema.library where title='" + title +"';";
        try {
            System.out.println(bookRepository.actualSelect(selectSql));
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void insertValues(String title, String author, int year){
        String insertSql = "Insert into devschema.library (title, author, published_in) "
                + "VALUES ('" + title + "','" + author + "'," + year +");";
            try {
                bookRepository.actualInsert(insertSql);
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
    }

}
