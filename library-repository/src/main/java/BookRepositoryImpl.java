import java.sql.*;

import java.util.ArrayList;

/**
 * Repository-level class, where we connect to our database, and get data by specified requests
 */
public class BookRepositoryImpl implements BookRepository{
    private Connection c;
    public BookRepositoryImpl(){
        DBConnector connector = new DBConnector();
        c = connector.connect();
    }
    public ArrayList<BookEntity> selectAllBooks() throws SQLException{
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(buildSelectStatement());
        c.close();

        ArrayList<BookEntity> entitiesList = new ArrayList<BookEntity>();
        insertResultSetIntoGivenArray(rs, entitiesList);
        return entitiesList;
    }
    public ArrayList<BookEntity> selectBooksByAuthor(BookEntity bookEntity) throws SQLException{
        Statement stmt = c.createStatement();
        ResultSet rs =
                stmt.executeQuery(buildSelectStatementByParameter(bookEntity.getAuthor(), "author"));
        c.close();

        ArrayList<BookEntity> entitiesList = new ArrayList<BookEntity>();
        insertResultSetIntoGivenArray(rs, entitiesList);
        return entitiesList;
    }
    public ArrayList<BookEntity> selectBookByTitle(BookEntity bookEntity) throws SQLException{
        Statement stmt = c.createStatement();
        ResultSet rs =
                stmt.executeQuery(buildSelectStatementByParameter(bookEntity.getTitle(), "title"));
        c.close();

        ArrayList<BookEntity> entitiesList = new ArrayList<BookEntity>();
        insertResultSetIntoGivenArray(rs, entitiesList);
        return entitiesList;
    }
    public BookEntity insertBook(BookEntity bookEntity) throws SQLException {
        Statement stmt = c.createStatement();
        stmt.executeUpdate(buildInsertStatementByGivenBook(bookEntity));

        c.close();
        return bookEntity;
    }

    /**
     * Creates Select * SQL statement
     * @return SQL statement that allows us to get info about all books
     */
    private String buildSelectStatement(){
        return "select title,author,published_in from devschema.library;";
    }
    /**
     * Creates Select SQL statement by given parameters
     * @param parameter value of parameter that has to be matched with data from database
     * @param type type of the given parameter(author/title)
     * @return SQL statement that allows us to get info from database
     */
    private String buildSelectStatementByParameter(String parameter, String type){
        return "select title,author,published_in from devschema.library " +
                "where " + type + "='" + parameter +"';";
    }
    /**
     * Creates Insert SQL statement with given parameters
     * @param book book that will be inserted
     * @return SQL statement that allows us to insert given info into database
     */
    private String buildInsertStatementByGivenBook(BookEntity book){
        return "Insert into devschema.library (title, author, published_in) "
                + "VALUES ('" + book.getTitle() + "','" + book.getAuthor() + "'," + book.getPublished_in() +");";
    }
    /**
     * Inserts answer from database into ArrayList which we will send into service level
     * @param rs ResultSet that we got after making a select request
     * @param books empty ArrayList, where we will hold books
     */
    private void insertResultSetIntoGivenArray(ResultSet rs, ArrayList<BookEntity> books){
        try{
            while(rs.next()){
                books.add(new BookEntity(
                        rs.getString(1).trim(),
                        rs.getString(2).trim(),
                        Integer.valueOf(rs.getString(3)))
                );
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    private class DBConnector implements Connector{
        private final String url = "jdbc:postgresql://localhost/ablam";
        private final String user = "ablam";
        private final String password = "delamland";

        public Connection connect() {
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return conn;
        }
    }
}


