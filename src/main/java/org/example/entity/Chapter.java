package org.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "chapter")
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String titles;
    private int paginas;

    public Chapter(String titles, int paginas, Book book) {
        this.titles = titles;
        this.paginas = paginas;
        this.book = book;
    }

    @ManyToOne
    @JoinColumn(name = "book_id" , nullable = false)
    private Book book;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "id=" + id +
                ", titles='" + titles + '\'' +
                ", paginas=" + paginas +
                ", book=" + book +
                '}';
    }
}
