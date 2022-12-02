import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "GetBooksServlet", urlPatterns = "/library-parent/getBooks")
public class GetBooksServlet extends HttpServlet {
    BookLibraryServiceImpl bookLibraryServiceImpl = new BookLibraryServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Book> books;

        String author = req.getParameter("author");

        PrintWriter writer = resp.getWriter();

        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e){
            writer.println(e.getMessage());
            writer.close();
        }

        try {
            books = bookLibraryServiceImpl.getAllBooks();
        } catch (RuntimeException e){
            writer.println("Couldn't complete your request, something went wrong");
            writer.close();
            return;
        }

        resp.setContentType("text/html");
        writer.println("<h1>Info about all books</h1>");

        writer.println("<table>" +
                "  <tr>\n" +
                "    <th>Title</th>" +
                "    <th>Author</th>"+
                "    <th>Published in</th>" +
                "  </tr>\n");
        for(Book b : books){
            writer.println
                    ("<tr>\n + " +
                    "    <td>"+ b.getTitle() +" </td>" +
                    "    <td>"+ b.getAuthor() +"</td>"+
                    "    <td>" + b.getPublished_in() + "</td>" +
                    "</tr>\n");
        }
        writer.println("</table");
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        super.doPost(req, resp);
    }

}