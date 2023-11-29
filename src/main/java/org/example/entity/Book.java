package org.example.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "book")
public class Book {

    @Id
    private long id;
    private String titulo;
    private String autor;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private Set<Chapter> chapters;

    public Book(long id, String titulo, String autor, Set<Chapter> chapters) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.chapters = chapters;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                '}';
    }
    public Book(){

    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Set<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(Set<Chapter> chapters) {
        this.chapters = chapters;
    }

    public Book(long id, String titulo, String autor) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
    }
}
