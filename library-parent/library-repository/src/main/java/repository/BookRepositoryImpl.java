package repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository-level class, where we connect to our database, and get data by specified requests
 */
@Repository
public class BookRepositoryImpl implements BookRepository{
    private final DBConnector dbConnector;
    @Autowired
    public BookRepositoryImpl(DBConnector dbConnector){
        this.dbConnector = dbConnector;
    }

    public List<BookEntity> selectAllBooks() {
        List<BookEntity> entitiesList = new ArrayList<>();
        try (Connection c = dbConnector.connect();
             Statement stmt = c.createStatement()) {

            ResultSet rs = stmt.executeQuery(buildSelectStatement());
            mapResultSetToBookList(rs, entitiesList);

        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }

        return entitiesList;
    }
    public List<BookEntity> selectBookById(BookEntity bookEntity) {
        List<BookEntity> entitiesList = new ArrayList<>();

        try (Connection c = dbConnector.connect();
             Statement stmt = c.createStatement()) {

            ResultSet rs = stmt.executeQuery(buildSelectIdStatementByParameter(bookEntity.getId()));
            mapResultSetToBookList(rs, entitiesList);

        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
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
            throw new RuntimeException(e.getMessage());
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
            throw new RuntimeException(e.getMessage());
        }

        return entitiesList;
    }
    public BookEntity insertBook(BookEntity bookEntity) {
        try (Connection c = dbConnector.connect();
             Statement stmt = c.createStatement()) {

            stmt.executeUpdate(buildInsertStatementByGivenBook(bookEntity));

        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }

        return bookEntity;
    }
    public BookEntity deleteBook(BookEntity bookEntity){
        try (Connection c = dbConnector.connect();
             Statement stmt = c.createStatement()) {

            stmt.executeUpdate(buildDeleteStatementByGivenBook(bookEntity));

        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
        return bookEntity;
    }

    public BookEntity updateBook(BookEntity bookEntity, String field){
        try (Connection c = dbConnector.connect();
             Statement stmt = c.createStatement()) {
            if(field.equals("author")) {
                stmt.executeUpdate(buildUpdateAuthorStatementByGivenBook(bookEntity));
            }
            if(field.equals("title")) {
                stmt.executeUpdate(buildUpdateTitleStatementByGivenBook(bookEntity));
            }
            if(field.equals("published_in")){
                stmt.executeUpdate(buildUpdatePublishedInStatementByGivenBook(bookEntity));
            }

        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
        return bookEntity;
    }
    /**
     * Creates Select * SQL statement
     * @return SQL statement that allows us to get info about all books
     */
    private String buildSelectStatement(){
        return "select * from library;";
    }
    /**
     * Creates Select SQL statement by given parameters
     * @param parameter value of parameter that has to be matched with data from database
     * @param type type of the given parameter(author/title)
     * @return SQL statement that allows us to get info from database
     */
    private String buildSelectStatementByParameter(String parameter, String type){
        return "select * from library " +
                "where " + type + "='" + parameter +"';";
    }
    /**
     * Creates Select SQL statement by given id
     * @param id book's id
     * @return SQL statement that allows us to get info from database
     */
    private String buildSelectIdStatementByParameter(int id){
        return "select * from library " +
                "where bookid=" + id +";";
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
     * Creates Delete SQL statement with given parameters
     * @param bookEntity bookentity, that represents a book that will be deleted (only id filled)
     * @return SQL statement that allows us to insert given info into database
     */
    private String buildDeleteStatementByGivenBook(BookEntity bookEntity) {
        return "Delete from library where bookid="+bookEntity.getId();
    }
    /**
     * Creates Update author SQL statement with given parameters
     * @param bookEntity bookentity that will help us find book to update and update it (id - to find, author - will be updated)
     * @return SQL statement that allows us to update book's author in database
     */
    private String buildUpdateAuthorStatementByGivenBook(BookEntity bookEntity) {
        return "Update library set author='"+bookEntity.getAuthor() +"' where bookid=" + bookEntity.getId()+";";
    }
    /**
     * Creates Update title SQL statement with given parameters
     * @param bookEntity bookentity that will help us find book to update and update it (id - to find, title - will be updated)
     * @return SQL statement that allows us to update book's title in database
     */
    private String buildUpdateTitleStatementByGivenBook(BookEntity bookEntity) {
        return "Update library set title='"+bookEntity.getTitle() +"' where bookid=" + bookEntity.getId()+";";
    }
    /**
     * Creates Update published_in SQL statement with given parameters
     * @param bookEntity bookentity that will help us find book to update and update it (id - to find, published_in - will be updated)
     * @return SQL statement that allows us to update info about a book in database
     */
    private String buildUpdatePublishedInStatementByGivenBook(BookEntity bookEntity) {
        return "Update library set published_in="+bookEntity.getPublished_in() +" where bookid=" + bookEntity.getId()+";";
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
                    Integer.parseInt(rs.getString(1)),
                    rs.getString(2).trim(),
                    rs.getString(3).trim(),
                    Integer.parseInt(rs.getString(4))));
        }
    }
    @Component
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


