import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetBooksServlet", urlPatterns = "/getBooks/*")
public class GetBooksServlet extends HttpServlet {
    BookLibraryServiceImpl bookLibraryServiceImpl = new BookLibraryServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Book> books;

        try {
            books = bookLibraryServiceImpl.getAllBooks();
        } catch (RuntimeException e){
            System.out.println("Couldn't complete your request, something went wrong");
            return;
        }

        req.setAttribute("book", books.get(0).getAuthor());
        req.getRequestDispatcher("mypage.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        super.doPost(req, resp);
    }

}