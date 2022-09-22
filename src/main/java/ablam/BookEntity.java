package ablam;

public class BookEntity {
    private String author;
    private String title;
    private int published_in;

    public BookEntity(String author, String title, int published_in) {
        this.author = author;
        this.title = title;
        this.published_in = published_in;
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

    public int getPublished_in() {
        return published_in;
    }

    public void setPublished_in(int published_in) {
        this.published_in = published_in;
    }
}
