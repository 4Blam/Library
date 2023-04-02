package repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

import java.util.List;

/**
 * Repository-level class, where we connect to our database, and get data by specified requests
 */
@Repository
public class BookRepositoryImpl implements BookRepository{
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public BookRepositoryImpl(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    public List<BookEntity> selectAllBooks(){
        String sql = "SELECT * FROM library";
        return jdbcTemplate.query(
                sql,
                (rs, rowNum) ->
                        new BookEntity(
                                rs.getLong("bookid"),
                                rs.getString("title"),
                                rs.getString("author"),
                                rs.getLong("published_in")
                        )
        );
    }
    public BookEntity selectBookById(BookEntity bookEntity) {
        String sql = "SELECT * FROM library WHERE bookid = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{bookEntity.getId()}, (rs, rowNum) ->
                new BookEntity(
                        rs.getLong("bookid"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getLong("published_in")
                ));
    }
    public BookEntity insertBook(BookEntity bookEntity) {
        String sql = "INSERT INTO library (title, author, published_in) VALUES (?, ?, ?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, bookEntity.getTitle());
            pst.setString(2, bookEntity.getAuthor());
            pst.setLong(3, bookEntity.getPublished_in());
            return pst;
        }, keyHolder);
        bookEntity.setId(keyHolder.getKey().intValue());
        return bookEntity;
    }

    public BookEntity deleteBook(BookEntity bookEntity){
        String sql = "DELETE FROM library WHERE bookid = ?";
        jdbcTemplate.update(sql, bookEntity.getId());
        return bookEntity;
    }

    public BookEntity updateBook(BookEntity bookEntity, String field){
        if(field.equals("author")){
            String sql = "UPDATE library SET author = ? WHERE bookid = ?";
            jdbcTemplate.update(sql, bookEntity.getAuthor(), bookEntity.getId());
        }
        if(field.equals("title")){
            String sql = "UPDATE library SET title = ? WHERE bookid = ?";
            jdbcTemplate.update(sql, bookEntity.getTitle(), bookEntity.getId());
        }
        if(field.equals("published_in")){
            String sql = "UPDATE library SET published_in = ? WHERE bookid = ?";
            jdbcTemplate.update(sql, bookEntity.getPublished_in(), bookEntity.getId());
        }
        return bookEntity;
    }
    public void initDB(){
        jdbcTemplate.execute("CREATE TABLE library " +
                "(bookid int NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY," +
                "title varchar(50) NOT NULL," +
                "author varchar(50) NOT NULL," +
                "published_in int);");
        jdbcTemplate.execute("INSERT INTO library (title, author, published_in) VALUES (title1, author1, 1);");
        jdbcTemplate.execute("INSERT INTO library (title, author, published_in) VALUES (title2, author2, 2);");
        jdbcTemplate.execute("INSERT INTO library (title, author, published_in) VALUES (title4, author4, 4);");
        jdbcTemplate.execute("INSERT INTO library (title, author, published_in) VALUES (title5, author5, 5);");
    }
}


