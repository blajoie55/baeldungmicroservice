package com.baeldungtutorial.bookservice.service;

import com.baeldungtutorial.bookservice.model.Book;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class BookService {

    public List<Book> findAllBooks()
    {
        return Collections.singletonList(buildBook());
    }

    public Book findBookById(Long bookId)
    {
        return buildBook();
    }

    public Book createBook(Book book)
    {
        return book;
    }

    public void deleteBook(Long bookId)
    {
    }

    public Book updateBook(Book book, Long bookId)
    {
        return book;
    }

    public Book updateBook(Map<String, String> updates, Long bookId)
    {
        return null;
    }

    private Book buildBook()
    {
        Book book = new Book();
        book.setId(1);
        book.setAuthor("Brad Lajoie");
        book.setTitle("Happy Wife is a Happy Life");
        return book;
    }
}
