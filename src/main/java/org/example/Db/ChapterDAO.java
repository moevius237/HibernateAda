package org.example.Db;


import org.example.entity.Book;
import org.example.entity.Chapter;

public interface ChapterDAO {
    void create(Chapter chapter);
    void deletebyId(Long id);
     void update(Chapter chapter);

}
