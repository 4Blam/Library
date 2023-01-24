package springboot;

import service.Book;
import service.BookLibraryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/getBooksByServlet", loadOnStartup = 1)
public class GetBooksServlet extends HttpServlet {
    BookLibraryServiceImpl bookLibraryServiceImpl = new BookLibraryServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        List<Book> books;

        try {
            books = bookLibraryServiceImpl.getAllBooks();
            StringBuilder result = new StringBuilder();
            for(Book book : books){
                result.append(book.toString()).append("\n");
            }
            out.println("<h3>" + result.toString() + "</h3>");
        } catch (RuntimeException e){
            //log
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        super.doPost(req, resp);
    }

}