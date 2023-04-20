package repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

import java.util.List;
import java.util.UUID;

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
        //initDB();
        String sql = "SELECT * FROM library";
        return jdbcTemplate.query(
                sql,
                (rs, rowNum) ->
                        new BookEntity(
                                rs.getString("bookid"),
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
                        rs.getString("bookid"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getLong("published_in")
                ));
    }
    public BookEntity insertBook(BookEntity bookEntity) {
        String sql = "INSERT INTO library (bookid, title, author, published_in) VALUES (?, ?, ?, ?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, bookEntity.getId(), bookEntity.getTitle(), bookEntity.getAuthor(), bookEntity.getPublished_in());
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
                "(bookid varchar(36) NOT NULL UNIQUE PRIMARY KEY," +
                "title varchar(100) NOT NULL," +
                "author varchar(100) NOT NULL," +
                "published_in int);");
        jdbcTemplate.execute("INSERT INTO library (bookid, title, author, published_in) VALUES ('"+ UUID.randomUUID() +"','title1', 'author1', 1);");
        jdbcTemplate.execute("INSERT INTO library (bookid, title, author, published_in) VALUES ('"+ UUID.randomUUID() +"','title2', 'author2', 2);");
        jdbcTemplate.execute("INSERT INTO library (bookid, title, author, published_in) VALUES ('"+ UUID.randomUUID() +"','title4', 'author4', 4);");
        jdbcTemplate.execute("INSERT INTO library (bookid, title, author, published_in) VALUES ('"+ UUID.randomUUID() +"','title5', 'author5', 5);");
    }
}


