package repository;

/**
 * This class implements a book entity in database
 */
public class BookEntity {
    /**
     * ID of a book that is represented by this entity
     */
    private long id;
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
        this.id = -1;
        this.author = null;
        this.title = null;
        this.published_in = -1;
    }
    public BookEntity(long id, String author, String title, long published_in) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.published_in = published_in;
    }
    public long getId() { return id; }
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

    public long getPublished_in() {
        return published_in;
    }

    public void setPublished_in(long published_in) {
        this.published_in = published_in;
    }
    public String buildingMultipleStatement(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select * from library where ");
        boolean added = false;
        if(id != -1){
            stringBuilder.append("bookid=" + id);
            added=true;
        }
        if(title!=null){
            if(added){
                stringBuilder.append(" AND ");
            }
            stringBuilder.append("title='" +title+"'");
            added=true;
        }
        if(author!=null){
            if(added){
                stringBuilder.append(" AND ");
            }
            added=true;
            stringBuilder.append("author='"+author+"'");
        }
        if(published_in!=-1){
            if(added){
                stringBuilder.append(" AND ");
            }
            stringBuilder.append("published_in="+published_in+"");
        }
        stringBuilder.append(";");
        return stringBuilder.toString();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEntity entity = (BookEntity) o;
        return id == entity.id;
    }

}
