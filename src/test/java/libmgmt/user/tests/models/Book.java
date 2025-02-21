package libmgmt.user.tests.models;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Book {
    @JsonProperty("title")
    private String title;

    @JsonProperty("author")
    private String author;

    @JsonProperty("isbn")
    private String isbn;

    @JsonProperty("category")
    private String category;

    @JsonProperty("availableCopies")
    private int availableCopies;

    public Book() {

    }



    public Book(String title, String author, String isbn, String category, int availableCopies) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.category = category;
        this.availableCopies = availableCopies;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public int getAvailableCopies() {
        return availableCopies;
    }
    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

//    @Override
//    public String toString() {
//        return "Book{" +
//                "title='" + title + '\'' +
//                ", author='" + author + '\'' +
//                ", isbn='" + isbn + '\'' +
//                ", category='" + category + '\'' +
//                ", availableCopies=" + availableCopies +
//                '}';
//    }



}
