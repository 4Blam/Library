package service;

import java.util.Objects;
import java.util.UUID;

/**
 * This class implements a service.Book in library
 */
public class Book {
    /**
     * service.Book's id
     */
    private UUID id;
    /**
     * service.Book's author
     */
    private String author;
    /**
     * service.Book's title
     */
    private String title;
    /**
     * ID of a publishing house book was published in
     */
    private long published_in;
    public Book(){
        this.id = null;
        this.author = "";
        this.title = "";
        this.published_in = -1;
    }
    public Book(UUID id, String author, String title, long published_in) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.published_in = published_in;
    }

    public UUID getId() { return id; }

    public void setId(UUID id) { this.id = id; }

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
        Book book = (Book) o;
        return id == book.id;
    }
}
