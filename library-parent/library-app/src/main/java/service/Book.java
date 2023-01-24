package service;

import java.util.Objects;

/**
 * This class implements a service.Book in library
 */
public class Book {
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
    public Book(){
        this.author = "";
        this.title = "";
        this.published_in = 0;
    }
    public Book(String author, String title, int published_in) {
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

    @Override
    public String toString() {
        return "Title: " + title +
                ", Author: " + author +
                ", Published in: " + published_in;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return published_in == book.published_in &&
                Objects.equals(author, book.author) &&
                Objects.equals(title, book.title);
    }
}
