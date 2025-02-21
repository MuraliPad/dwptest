package libmgmt.user.tests.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.ArrayList;

public class BookList {

    @JsonProperty("books")
    private List<Book> books;

    public BookList() {
        this.books = new ArrayList<>();
    }
    public BookList(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }
    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
