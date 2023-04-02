package springboot.Dtos;

/**
 * This class implements a Book in library
 */
public abstract class BookDto {
    /**
     * Book's author
     */
    private String title;
    /**
     * Book's title
     */
    private String author;
    public BookDto(){};

    public BookDto(String title, String author) {
        this.title = title;
        this.author = author;
    }

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
}