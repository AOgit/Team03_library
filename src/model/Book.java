package model;

import java.util.Objects;

public class Book {
    private int id;
    private String title;
    private String author;
    private int year;
    private int pages;
    private String genre;
    private boolean isBorrowed;
    private User reader;

    public Book(int id, String title, String author, int year, int pages, String genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.pages = pages;
        this.genre =genre;


    }

    @Override
    public String toString() {
        return "Book {" +
                "id = " + id +
                ", title = '" + title + '\'' +
                ", author = '" + author + '\'' +
                ", year = " + year +
                ", pages = " + pages +
                ", isBorrowed = " + isBorrowed +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return id == book.id
                && year == book.year
                && pages == book.pages
                && isBorrowed == book.isBorrowed
                && Objects.equals(title, book.title)
                && Objects.equals(author, book.author)
                && Objects.equals(genre, book.genre);
    }
//    @Override
//    public boolean equals(Object o) {
//        if (!(o instanceof Book book)) return false;
//        return getId() == book.getId() && getYear() == book.getYear() && getPages() == book.getPages() && isBusy() == book.isBusy() && Objects.equals(getTitle(), book.getTitle()) && Objects.equals(getAuthor(), book.getAuthor());
//    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }


    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    public void setAvailable(boolean available) {
        isBorrowed = available;
    }

    public User getReader() {
        return reader;
    }

    public void setReader(User reader) {
        this.reader = reader;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
