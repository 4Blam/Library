package springboot;

public class CreatedPages {
    public static String mainPageHTML(){
        return " <!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n" +
                "\n" +
                "<h1>Welcome to our library!</h1>\n" +
                "\n" +
                "<p>Select what exactly you want to do</p>\n" +
                "\n" +
                "<label for=\"options\">Choose an option:</label>\n" +
                "<select id=\"options\" name=\"optionslist\" form=\"optionform\">\n" +
                "  <option value=\"getAllBooks\">Get All Books</    option>\n" +
                "  <option value=\"getBookById\">Get Book By Id</option>\n" +
                "  <option value=\"getBooksByAuthor\">Get Books by Author</option>\n" +
                "  <option value=\"getBookByTitle\">Get Book by Title</option>\n" +
                "  <option value=\"updateField\">Update Book's field by it's id</option>\n" +
                "  <option value=\"insertBook\">Insert New Book</option>\n" +
                "  <option value=\"deleteBook\">Delete Book by it's id</option>\n" +
                "</select>\n" +
                "\n" +
                "\n" +
                "<button type=\"button\" onclick= \"location.href=displayValue()\"> Submit</button>\n" +
                "      \n" +
                "    <script>\n" +
                "        function displayValue() {\n" +
                "            var ele = document.getElementsByName('optionslist');\n" +
                "            var link = ele[0].value\n" +
                "            return link\n" +
                "        }\n" +
                "    </script>\n" +
                "    \n" +
                "</body>\n" +
                "</html>\n";
    }
    public static String insertPageHTML(){
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n" +
                "\n" +
                "<h1>Insert a book into library</h1>\n" +
                "\n" +
                "<p>Please input information about a book</p>\n" +
                "\n" +
                " <form action=\"/insertBook/\">\n" +
                "  <label for=\"title\">Title :</label>\n" +
                "  <input type=\"text\" id=\"title\" name=\"title\" minlength=5><br><br>\n" +
                "  <label for=\"author\">Author:</label>\n" +
                "  <input type=\"text\" id=\"author\" name=\"author\" minlength=5><br><br>\n" +
                "  <label for=\"published_in\">Published in:</label>\n" +
                "  <input type=\"number\" id=\"published_in\" name=\"published_in\" min=1000><br><br>\n" +
                "  <input type=\"submit\" value=\"Submit\"> \n" +
                "</form> \n" +
                "\n" +
                "</body>\n" +
                "</html>";
    }

    public static String successInsert() {
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n" +
                "\n" +
                "<h1>You've succesfully inserted a book into library</h1>\n" +
                "<button onclick=\"location.href = 'http://localhost:8080/';\" id=\"myButton\" class=\"float-left submit-button\" >Return home</button>\n" +
                "\n" +
                "</body>\n" +
                "</html>";
    }
    public static String deletePageHTML(){
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n" +
                "\n" +
                "<h1>Delete book from library by it's id</h1>\n" +
                "\n" +
                "<p>Please input book's id</p>\n" +
                "\n" +
                "<form action=\"/deleteBook/\" id=\"carform\">\n" +
                "  <label for=\"id\">Id:</label>\n" +
                "  <input type=\"number\" id=\"id\" name=\"id\"><br><br>\n" +
                "  <input type=\"submit\" value =\"Submit\">\n" +
                "</form>\n" +
                "\t\n" +
                "</body>\n" +
                "</html>";
    }

    public static String successDelete() {
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n" +
                "\n" +
                "<h1>You've succesfully deleted a book from library</h1>\n" +
                "<button onclick=\"location.href = 'http://localhost:8080/';\" id=\"myButton\" class=\"float-left submit-button\" >Return home</button>\n" +
                "\n" +
                "</body>\n" +
                "</html>";
    }
    public static String errorHtml(){
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n" +
                "\n" +
                "<h1>Sorry, something went wrong</h1>\n" +
                "<button onclick=\"location.href = 'http://localhost:8080/';\" id=\"myButton\" class=\"float-left submit-button\" >Return home</button>\n" +
                "\n" +
                "</body>\n" +
                "</html>";
    }
    public static String updateHtml(){
        return " <!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n" +
                "\n" +
                "<h1>Welcome to update page</h1>\n" +
                "\n" +
                "<p>Select what exactly you want to update</p>\n" +
                "\n" +
                "<label for=\"options\">Choose field:</label>\n" +
                "<select id=\"options\" name=\"optionslist\" form=\"optionform\">\n" +
                "  <option value=\"updateTitle\">Title</    option>\n" +
                "  <option value=\"updateAuthor\">Author</option>\n" +
                "  <option value=\"updatePublished_in\">Published in</option>\n" +
                "</select>\n" +
                "\n" +
                "\n" +
                "<button type=\"button\" onclick= \"location.href=displayValue()\"> Submit</button>\n" +
                "      \n" +
                "    <script>\n" +
                "        function displayValue() {\n" +
                "            var ele = document.getElementsByName('optionslist');\n" +
                "            var link = ele[0].value\n" +
                "            return link\n" +
                "        }\n" +
                "    </script>\n" +
                "    \n" +
                "<br><br><button onclick=\"location.href = 'http://localhost:8080/';\" id=\"myButton\" class=\"float-left submit-button\" >Return home</button>\n" +
                "</body>\n" +
                "</html>\n";
    }
    public static String updateAuthorHtml(){
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n" +
                "\n"+
                "<p>Please input book's id and new author's name</p>\n" +
                "\n" +
                " <form action=\"/updateAuthor/\">\n" +
                "  <label for=\"id\">Id:</label>\n" +
                "  <input type=\"number\" id=\"id\" name=\"id\"><br><br>\n" +
                "  <label for=\"author\">Author:</label>\n" +
                "  <input type=\"text\" id=\"author\" name=\"author\" minlength=5><br><br>\n" +
                "  <input type=\"submit\" value=\"Submit\"> \n" +
                "</form> \n" +
                "\n" +
                "</body>\n" +
                "</html>";
    }
    public static String updateTitleHtml(){
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n" +
                "\n" +
                "<p>Please input book's id and new title</p>\n" +
                "\n" +
                " <form action=\"/updateTitle/\">\n" +
                "  <label for=\"id\">Id:</label>\n" +
                "  <input type=\"number\" id=\"id\" name=\"id\"><br><br>\n" +
                "  <label for=\"title\">Title :</label>\n" +
                "  <input type=\"text\" id=\"title\" name=\"title\" minlength=5><br><br>\n" +
                "  <input type=\"submit\" value=\"Submit\"> \n" +
                "</form> \n" +
                "\n" +
                "</body>\n" +
                "</html>";
    }
    public static String updatePublishedInHtml(){
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n" +
                "\n" +
                "<p>Please input book's id and year when book was published in about a book</p>\n" +
                "\n" +
                " <form action=\"/updatePublished_in/\">\n" +
                "  <label for=\"id\">Id:</label>\n" +
                "  <input type=\"number\" id=\"id\" name=\"id\"><br><br>\n" +
                "  <label for=\"published_in\">Published in:</label>\n" +
                "  <input type=\"number\" id=\"published_in\" name=\"published_in\" min=1000><br><br>\n" +
                "  <input type=\"submit\" value=\"Submit\"> \n" +
                "</form> \n" +
                "\n" +
                "</body>\n" +
                "</html>";
    }

    public static String successUpdate() {
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n" +
                "\n" +
                "<h1>You've succesfully updated info about a book in library</h1>\n" +
                "<button onclick=\"location.href = 'http://localhost:8080/';\" id=\"myButton\" class=\"float-left submit-button\" >Return home</button>\n" +
                "\n" +
                "</body>\n" +
                "</html>";
    }
    public static String startGettingIdHTML(){
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n" +
                "\n"+
                "<p>Please input book's id</p>\n" +
                "\n" +
                " <form action=\"/getBookById/\">\n" +
                "  <label for=\"id\">Id:</label>\n" +
                "  <input type=\"number\" id=\"id\" name=\"id\"><br><br>\n" +
                "  <input type=\"submit\" value=\"Submit\"> \n" +
                "</form> \n" +
                "\n" +
                "</body>\n" +
                "</html>";
    }
    public static String startGettingTitleHTML(){
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n" +
                "\n"+
                "<p>Please input book's title</p>\n" +
                "\n" +
                " <form action=\"/getBookByTitle/\">\n" +
                "  <label for=\"id\">Title:</label>\n" +
                "  <input type=\"text\" id=\"title\" name=\"title\"><br><br>\n" +
                "  <input type=\"submit\" value=\"Submit\"> \n" +
                "</form> \n" +
                "\n" +
                "</body>\n" +
                "</html>";
    }
    public static String startGettingAuthorHTML(){
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n" +
                "\n"+
                "<p>Please input book's author</p>\n" +
                "\n" +
                " <form action=\"/getBooksByAuthor/\">\n" +
                "  <label for=\"id\">Author:</label>\n" +
                "  <input type=\"text\" id=\"author\" name=\"author\"><br><br>\n" +
                "  <input type=\"submit\" value=\"Submit\"> \n" +
                "</form> \n" +
                "\n" +
                "</body>\n" +
                "</html>";
    }

}
