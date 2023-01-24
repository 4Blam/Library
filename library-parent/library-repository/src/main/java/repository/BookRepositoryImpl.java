package repository;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository-level class, where we connect to our database, and get data by specified requests
 */
public class BookRepositoryImpl implements BookRepository{
    private final DBConnector dbConnector;
    //static Logger logger = LoggerFactory.getLogger(repository.BookRepositoryImpl.class);
    public BookRepositoryImpl(){
        this.dbConnector = new DBConnector();
    }
    public List<BookEntity> selectAllBooks() {
        List<BookEntity> entitiesList = new ArrayList<>();
        try (Connection c = dbConnector.connect();
             Statement stmt = c.createStatement()) {

            //stmt.executeUpdate("CREATE TABLE library (bookid serial, author varchar2(20),title varchar2(20), published_in number(10))");
            //stmt.executeUpdate("Insert into library (author,title,published_in) values ('author1', 'title1', 1111);");

            ResultSet rs = stmt.executeQuery(buildSelectStatement());
            mapResultSetToBookList(rs, entitiesList);

        } catch (SQLException e){
            System.out.println(e.getMessage());
            //logger.error(e.getMessage());
            throw new RuntimeException();
        }

        return entitiesList;
    }
    public List<BookEntity> selectBooksByAuthor(BookEntity bookEntity) {
        List<BookEntity> entitiesList = new ArrayList<>();

        try (Connection c = dbConnector.connect();
             Statement stmt = c.createStatement()) {

            ResultSet rs = stmt.executeQuery(buildSelectStatementByParameter(bookEntity.getAuthor(), "author"));
            mapResultSetToBookList(rs, entitiesList);

        } catch (SQLException e){
            //logger.error(e.getMessage());
            throw new RuntimeException();
        }

        return entitiesList;
    }
    public List<BookEntity> selectBookByTitle(BookEntity bookEntity) {
        List<BookEntity> entitiesList = new ArrayList<>();

        try (Connection c = dbConnector.connect();
             Statement stmt = c.createStatement()) {

            ResultSet rs = stmt.executeQuery(buildSelectStatementByParameter(bookEntity.getTitle(), "title"));
            mapResultSetToBookList(rs, entitiesList);

        } catch (SQLException e){
            //logger.error(e.getMessage());
            throw new RuntimeException();
        }

        return entitiesList;
    }
    public BookEntity insertBook(BookEntity bookEntity) {
        try (Connection c = dbConnector.connect();
             Statement stmt = c.createStatement()) {

            stmt.executeUpdate(buildInsertStatementByGivenBook(bookEntity));

        } catch (SQLException e){
            //logger.error(e.getMessage());
            throw new RuntimeException();
        }

        return bookEntity;
    }

    /**
     * Creates Select * SQL statement
     * @return SQL statement that allows us to get info about all books
     */
    private String buildSelectStatement(){
        return "select author,title,published_in from library;";
    }
    /**
     * Creates Select SQL statement by given parameters
     * @param parameter value of parameter that has to be matched with data from database
     * @param type type of the given parameter(author/title)
     * @return SQL statement that allows us to get info from database
     */
    private String buildSelectStatementByParameter(String parameter, String type){
        return "select author,title,published_in from library " +
                "where " + type + "='" + parameter +"';";
    }
    /**
     * Creates Insert SQL statement with given parameters
     * @param book book that will be inserted
     * @return SQL statement that allows us to insert given info into database
     */
    private String buildInsertStatementByGivenBook(BookEntity book){
        return "Insert into library (title, author, published_in) "
                + "VALUES ('" + book.getTitle() + "','" + book.getAuthor() + "'," + book.getPublished_in() +");";
    }
    /**
     * Inserts answer from database into ArrayList which we will send into service level
     * @param rs ResultSet that we got after making a select request
     * @param books empty ArrayList, where we will hold books
     * @throws IllegalArgumentException when there is something wrong with given ResultSet
     */
    private void mapResultSetToBookList(ResultSet rs, List<BookEntity> books) throws SQLException{
        while (rs.next()) {
            books.add(new BookEntity(
                    rs.getString(1).trim(),
                    rs.getString(2).trim(),
                    Integer.parseInt(rs.getString(3))));
        }
    }

   private static class DBConnector implements Connector{
        private final String url = "jdbc:h2:./library-parent/library-web/src/main/resources/database";
        //private final String url = "jdbc:h2:~/WEB-INF/classes/database";
        private final String user = "ablam";
        private final String password = "delamland";

        public Connection connect() throws SQLException{
            Connection conn;
            conn = DriverManager.getConnection(url, user, password);
            return conn;
        }
    }
}


