package springboot;

/**
 * This class implements a service.Book in library
 */
public class BookDto {
    /**
     * service.Book's id
     */
    private int id;
    /**
     * service.Book's author
     */
    private String author;
    /**
     * service.Book's title
     */
    private String title;
    /**
     * Year, when book was published
     */
    private int published_in;
    public BookDto(){
        this.id = 0;
        this.author = "";
        this.title = "";
        this.published_in = 0;
    }
    public BookDto(int id, String author, String title, int published_in) {
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
    public String toString() {
        return "ID: " + id +
                ", Title: " + title +
                ", Author: " + author +
                ", Published in: " + published_in;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDto book = (BookDto) o;
        return id == book.id;
    }
}
