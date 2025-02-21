package libmgmt.user.tests.models;

public  class prBookBuilder {
    private String title;
    private String author;
    private String isbn;
    private String category;
    private int availableCopies;

    public BookBuilder() {

    }
    public BookBuilder title(String title) {
        this.title = title;
        return this;
    }
    public BookBuilder author(String author) {
        this.author = author;
        return this;
    }
    public BookBuilder isbn(String isbn) {
        this.isbn = isbn;
        return this;
    }
    public BookBuilder availableCopies(int availableCopies) {
        this.availableCopies= availableCopies;
        return this;
    }
    public BookBuilder category(String category) {
        this.category= category;
        return this;
    }
    public Book build() {
        return new Book(title,author,isbn,category,availableCopies);
    }


}
