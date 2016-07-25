package com.lector.util.tabular.manipulator;

import com.lector.util.tabular.document.TabularDocumentBuilder;
import com.lector.util.tabular.document.TabularDocument;
import com.lector.util.tabular.exception.InvalidCSVRowException;
import com.lector.util.tabular.model.Book;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Reza Mousavi reza.mousavi@lector.dk on 7/15/2016
 */
public class CSVDocumentManipulatorTest {

    private CSVDocumentManipulator manipulator;
    private TabularDocument<Book> tabularDocumentOfBook;

    @Before
    public void setUp() throws Exception {
        manipulator = new CSVDocumentManipulator();
        final TabularDocumentBuilder<Book> tabularDocumentBuilder = new TabularDocumentBuilder<>();
        tabularDocumentBuilder.setClass(Book.class);
        tabularDocumentOfBook = tabularDocumentBuilder.build();
    }

    @Test(expected = InvalidCSVRowException.class)
    public void testFromStringNull() {
        final Book book = manipulator.fromString(null, tabularDocumentOfBook);
    }

    @Test(expected = InvalidCSVRowException.class)
    public void testFromStringEmpty() {
        String line = "";
        final Book book = manipulator.fromString(line, tabularDocumentOfBook);
    }

    @Test(expected = InvalidCSVRowException.class)
    public void testFromStringWithThreeColumns() {
        String line = "1,2,3";
        final Book book = manipulator.fromString(line, tabularDocumentOfBook);

    }

    @Test
    public void testFromEmptyObject() {
        String result = manipulator.toString(tabularDocumentOfBook, new Book());
        Assert.assertNotEquals(null, result);
        Assert.assertEquals(",,,,,,,,", result);

    }

    @Test
    public void testFromObjectWithSingleValue() {
        Book book = new Book();
        book.setTitle("First");
        String result = manipulator.toString(tabularDocumentOfBook, book);
        Assert.assertNotEquals(null, result);
        Assert.assertEquals("First,,,,,,,,", result);
        Assert.assertNotEquals(",First,,,,,,,", result);
        Assert.assertNotEquals(",,,,,,,,First", result);

        book = new Book();
        book.setAuthor("Reza");
        result = manipulator.toString(tabularDocumentOfBook, book);
        Assert.assertNotEquals(null, result);
        Assert.assertEquals(",Reza,,,,,,,", result);
        Assert.assertNotEquals(",,,,,,,,", result);
        Assert.assertNotEquals(",,Reza,,,,,,", result);
        Assert.assertNotEquals(",,,N,,,,,", result);

    }

    @Test
    public void testFromStringWithSingleValue() {
        String line = "First,,,,,,,,";
        Book result =  manipulator.fromString(line, tabularDocumentOfBook);

        Book book = new Book();
        book.setTitle("First");
        Assert.assertNotEquals(null, result);
        Assert.assertNotEquals(null, result.getTitle());
        Assert.assertEquals(book.getTitle(), result.getTitle());
        Assert.assertEquals(book, result);

        line = ",First,,,,,,,,";
        result = manipulator.fromString(line, tabularDocumentOfBook);
        book = new Book();
        book.setAuthor("First");
        Assert.assertNotEquals(null, result);
        Assert.assertNotEquals(null, result.getAuthor());
        Assert.assertEquals(book.getTitle(), result.getTitle());
        Assert.assertEquals(book.getAuthor(), result.getAuthor());
        Assert.assertEquals(book, result);

    }

    @Test
    public void testToObjectWithSingleValue() {
        final Book result = manipulator.fromString("First,,,,,,,,", tabularDocumentOfBook);
        Book book = new Book();
        book.setTitle("First");
        Assert.assertNotEquals(null, result);
        Assert.assertNotEquals(null, result.getTitle());
        Assert.assertEquals(book.getTitle(), result.getTitle());
        Assert.assertEquals(book, result);

    }

    @Test
    public void testFromString() {
        String line = ",Reza Mousavi,12.5,2016-07-15,,Persian,,,1235432";
        final Book book = manipulator.fromString(line, tabularDocumentOfBook);

    }

}