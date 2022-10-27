public class BookMapper {
    public BookEntity bookToEntity(Book book){
        return new BookEntity(book.getAuthor(),
                book.getTitle(),
                book.getPublished_in());
    }
    public Book entityToBook(BookEntity bookEntity){
        return new Book(bookEntity.getAuthor(),
                bookEntity.getTitle(),
                bookEntity.getPublished_in());
    }
}
