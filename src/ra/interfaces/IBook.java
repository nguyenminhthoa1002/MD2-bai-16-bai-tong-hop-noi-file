package ra.interfaces;

import ra.entity.Author;
import ra.entity.Book;

import java.util.List;
import java.util.Scanner;

public interface IBook {
    String PATH_AUTHOR = "list-Author.txt";
    String PATH_BOOK = "list-Book.txt";
    void inputData(Scanner scanner,List<Author> listAuthor, List<Book> listBook);
    void displayData();
    void getData(List<Author> listAuthor, List<Book> listBook);
    void insertData(Object object, boolean appended);
}
