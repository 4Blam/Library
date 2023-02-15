package repository;

/**
 * This class implements a book entity in database
 */
public class BookEntity {
    /**
     * ID of a book that is represented by this entity
     */
    private int id;
    /**
     * Author of a book that is represented by this entity
     */
    private String author;
    /**
     * Title of a book that is represented by this entity
     */
    private String title;
    /**
     * Year when book that is represented by this entity was published
     */
    private int published_in;
    public BookEntity() {
        this.id = 0;
        this.author = "";
        this.title = "";
        this.published_in = 0;
    }
    public BookEntity(int id, String author, String title, int published_in) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.published_in = published_in;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
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

    public int getPublished_in() {
        return published_in;
    }

    public void setPublished_in(int published_in) {
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
