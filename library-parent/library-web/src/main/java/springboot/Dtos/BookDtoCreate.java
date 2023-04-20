package springboot.Dtos;

public class BookDtoCreate extends BookDto{
    /**
     * ID of a publishing house where book was published
     */
    private long phID;
    public BookDtoCreate(){}
    public BookDtoCreate(String author, String title, long phID) {
        super(author, title);
        this.phID = phID;
    }

    public long getPhID() {
        return phID;
    }

    public void setPhID(long phID) {
        this.phID = phID;
    }
}
