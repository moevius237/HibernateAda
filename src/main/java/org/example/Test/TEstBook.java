package org.example.Test;

import org.example.Db.BookDb;
import org.example.Db.BookJPA;
import org.example.entity.Book;
import org.example.entity.Chapter;
import org.example.exceptions.SeveroException;

import java.util.HashSet;
import java.util.Set;

public class TEstBook {
    public static void main(String[] args) throws SeveroException {
        BookJPA bookDb = new BookJPA();
        Book harrypoter = new Book(1,"Harry poter","J.kROwlinh");
        Set<Chapter> capitulos = new HashSet<>();
        capitulos.add(new Chapter("Introduciion",10,harrypoter));
        capitulos.add(new Chapter("Hogwarts",20,harrypoter));
        bookDb.createListChapters(harrypoter,capitulos);
        bookDb.getBookList();
    }
}
