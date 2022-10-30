import java.sql.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository-level class, where we connect to our database, and get data by specified requests
 */
public class BookRepositoryImpl implements BookRepository{
    private final DBConnector dbConnector;
    private Connection c;
    public BookRepositoryImpl(){
        this.dbConnector = new DBConnector();
    }
    public List<BookEntity> selectAllBooks() throws IllegalAccessException, IllegalStateException, IllegalArgumentException {
        ResultSet rs;
        List<BookEntity> entitiesList = new ArrayList<>();

        c = dbConnector.connect();

        try {
            Statement stmt = c.createStatement();
            rs = stmt.executeQuery(buildSelectStatement());
        } catch (SQLException e){

            try{
                c.close();
            } catch (SQLException closingException){
                throw new IllegalAccessException(closingException.getMessage());
            }

            throw new IllegalStateException(e.getMessage());
        }

        try {
            insertResultSetIntoGivenArray(rs, entitiesList);
        } catch (IllegalArgumentException e){
            throw e;
        } finally {
            try {
                c.close();
            } catch (SQLException closingException){
                throw new IllegalAccessException(closingException.getMessage());
            }
        }

        return entitiesList;
    }
    public List<BookEntity> selectBooksByAuthor(BookEntity bookEntity) throws IllegalAccessException, IllegalStateException, IllegalArgumentException {
        ResultSet rs;
        List<BookEntity> entitiesList = new ArrayList<>();

        c = dbConnector.connect();

        try {
            Statement stmt = c.createStatement();
            rs = stmt.executeQuery(buildSelectStatementByParameter(bookEntity.getAuthor(), "author"));
        } catch (SQLException e){

            try{
                c.close();
            } catch (SQLException closingException){
                throw new IllegalAccessException(closingException.getMessage());
            }

            throw new IllegalStateException(e.getMessage());
        }

        try {
            insertResultSetIntoGivenArray(rs, entitiesList);
        } catch (IllegalArgumentException e){
            throw e;
        } finally {
            try {
                c.close();
            } catch (SQLException closingException){
                throw new IllegalAccessException(closingException.getMessage());
            }
        }

        return entitiesList;
    }
    public List<BookEntity> selectBookByTitle(BookEntity bookEntity) throws IllegalAccessException, IllegalStateException, IllegalArgumentException {
        ResultSet rs;
        List<BookEntity> entitiesList = new ArrayList<>();

        c = dbConnector.connect();

        try {
            Statement stmt = c.createStatement();
            rs = stmt.executeQuery(buildSelectStatementByParameter(bookEntity.getTitle(), "title"));
        } catch (SQLException e){

            try{
                c.close();
            } catch (SQLException closingException){
                throw new IllegalAccessException(closingException.getMessage());
            }

            throw new IllegalStateException(e.getMessage());
        }

        try {
            insertResultSetIntoGivenArray(rs, entitiesList);
        } catch (IllegalArgumentException e){
            throw e;
        } finally {
            try {
                c.close();
            } catch (SQLException closingException){
                throw new IllegalAccessException(closingException.getMessage());
            }
        }

        return entitiesList;
    }
    public BookEntity insertBook(BookEntity bookEntity) throws IllegalAccessException {
        c = dbConnector.connect();
        try {
            Statement stmt = c.createStatement();
            stmt.executeUpdate(buildInsertStatementByGivenBook(bookEntity));
        } catch (SQLException e){
            throw new IllegalStateException(e.getMessage());
        } finally {
            try {
                c.close();
            } catch (SQLException closingException){
                throw new IllegalAccessException(closingException.getMessage());
            }
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
    private void insertResultSetIntoGivenArray(ResultSet rs, List<BookEntity> books) throws IllegalArgumentException{
        try {
            while (rs.next()) {
                books.add(new BookEntity(
                        rs.getString(1).trim(),
                        rs.getString(2).trim(),
                        Integer.valueOf(rs.getString(3))));
            }
        } catch (SQLException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    private class DBConnector implements Connector{
        //private final String url = "jdbc:postgresql://localhost/ablam";
        private final String url = "jdbc:h2:./database/library";
        private final String user = "ablam";
        private final String password = "delamland";

        public Connection connect() throws IllegalAccessException {
            Connection conn;
            try {
                conn = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                throw new IllegalAccessException(e.getMessage());
            }
            return conn;
        }
    }
}


