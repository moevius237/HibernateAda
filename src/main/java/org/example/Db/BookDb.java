package org.example.Db;

import org.example.entity.Book;
import org.example.entity.Chapter;
import org.example.exceptions.SeveroException;

import java.util.List;
import java.util.Set;

public interface BookDb {

     List<Book>getBookList() throws SeveroException;

     void createListChapters(Book book , Set<Chapter> chapters) throws SeveroException;
}
