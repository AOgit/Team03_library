package model;

import java.util.Objects;

public class Book {
    private int id;
    private String title;
    private String author;
    private int year;
    private int pages;
    // TODO написать реализацию жанра книги
    private String genre;
    private boolean isBusy;

    public Book(int id, String title, String author, int year, int pages) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.pages = pages;
    }

    public Book(){};

    @Override
    public String toString() {
        return String.format("Книга №%d. Название: %s, автор: %s, год издания: %s, кол-во страниц: %d,  доступна: %s"
                , this.id, this.title, this.author, this.year, this.pages, this.isBusy ? "Нет" : "Да");
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Book book)) return false;
        return getId() == book.getId() && getYear() == book.getYear() && getPages() == book.getPages() && isBusy() == book.isBusy() && Objects.equals(getTitle(), book.getTitle()) && Objects.equals(getAuthor(), book.getAuthor());
    }

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

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }
}
