package com.baeldungtutorial.bookservice.service;

import com.baeldungtutorial.bookservice.model.Book;
import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookService {

    private static final Map<Long, Book> books = buildBookDb();

    public List<Book> findAllBooks() {
        return books.values().stream().toList();
    }

    public Book findBookById(Long bookId) {
        return books.get(bookId);
    }

    public Book createBook(Book book) {
        return book;
    }

    public void deleteBook(Long bookId) {
    }

    public Book updateBook(Book book, Long bookId) {
        return book;
    }

    public Book updateBook(Map<String, String> updates, Long bookId) {
        return null;
    }

    private static Book buildBook(Long id, String author, String title) {
        Book book = new Book();
        book.setId(id);
        book.setAuthor(author);
        book.setTitle(title);
        return book;
    }

    private static final Map<Long, Book> buildBookDb()
    {
        long id1 = 1L;
        Book book1 = buildBook(id1, "Brad Lajoie", "Happy Wife is a Happy Life");
        long id2 = 2L;
        Book book2 = buildBook(id2, "Allon Bell", "Allong Disagrees with Brad's Book Title");
        return ImmutableMap.of(id1, book1, id2, book2);
    }
}
