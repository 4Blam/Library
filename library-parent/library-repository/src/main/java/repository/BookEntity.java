package repository;

/**
 * This class implements a book entity in database
 */
public class BookEntity {
    /**
     * UUID of a book that is represented by this entity
     */
    private String id;
    /**
     * Author of a book that is represented by this entity
     */
    private String author;
    /**
     * Title of a book that is represented by this entity
     */
    private String title;
    /**
     * ID of a publishing house book was published in
     */
    private long published_in;
    public BookEntity() {
        this.id = null;
        this.author = null;
        this.title = null;
        this.published_in = -1;
    }
    public BookEntity(String id, String author, String title, long published_in) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.published_in = published_in;
    }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getPublished_in() {
        return published_in;
    }

    public void setPublished_in(long published_in) {
        this.published_in = published_in;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEntity entity = (BookEntity) o;
        return id == entity.id;
    }

}
