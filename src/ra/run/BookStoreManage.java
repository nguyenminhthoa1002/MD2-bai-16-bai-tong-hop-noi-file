package ra.run;

import ra.entity.Author;
import ra.entity.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookStoreManage {
    static List<Author> listAuthor = new ArrayList<>();
    static List<Book> listBook = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static boolean authorManage = true;
    static boolean bookManage = true;

    public static void main(String[] args) {
        Book bookGet = new Book();
        bookGet.getData(null, listBook);
        Author authorGet = new Author();
        authorGet.getData(listAuthor, null);
        do {
            System.out.println("********************QUẢN LÝ CỬA HÀNG SÁCH***************");
            System.out.println("1. Quản lý tác giả");
            System.out.println("2. Quản lý sách");
            System.out.println("3. Thoát");
            System.out.println("Your choice is: ");
            int choiceBookStore = 0;
            try {
                choiceBookStore = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException exception) {
                System.err.println("Please enter a number");
            }
            switch (choiceBookStore) {
                case 1:
                    authorManage(scanner);
                    break;
                case 2:
                    bookManage(scanner);
                    break;
                case 3:
                    scanner.close();
                    System.exit(0);
                default:
                    System.err.println("Please choose 1-3");
            }
        } while (true);
    }

    public static void authorManage(Scanner scanner) {
        do {
            System.out.println("********************QUẢN LÝ TÁC GIẢ***********************");
            System.out.println("1. Danh sách tác giả");
            System.out.println("2. Thêm các tác giả");
            System.out.println("3. Cập nhật thông tin tác giả");
            System.out.println("4. Cập nhật trạng thái tác giả");
            System.out.println("5. Thoát");
            System.out.println("Your choice is: ");
            int choiceAuthorManage = 0;
            try {
                choiceAuthorManage = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException exception) {
                System.err.println("Please enter a number");
            }
            switch (choiceAuthorManage) {
                case 1:
                    displayAuthor();
                    break;
                case 2:
                    addNewAuthor(scanner);
                    break;
                case 3:
                    updateAuthorInformation(scanner);
                    break;
                case 4:
                    updateAuthorStatus(scanner);
                    break;
                case 5:
                    authorManage = false;
                    break;
                default:
                    System.err.println("Please choose 1-5");
            }
        } while (authorManage);
    }

    public static void bookManage(Scanner scanner) {
        do {
            System.out.println("********************QUẢN LÝ SÁCH*************************");
            System.out.println("1. Danh sách sách");
            System.out.println("2. Thêm các sách");
            System.out.println("3. Cập nhật thông tin sách");
            System.out.println("4. Cập nhật trạng thái sách");
            System.out.println("5. Tính lợi nhuận sách");
            System.out.println("6. Sắp xếp sách theo giá bán tăng dần");
            System.out.println("7. Tìm kiếm sách theo tên sách, tên tác giả");
            System.out.println("8. Bán sách");
            System.out.println("9. Thoát");
            System.out.println("Your choice is: ");
            int choiceBookManage = 0;
            try {
                choiceBookManage = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException exception) {
                System.err.println("Please enter a number");
            }
            switch (choiceBookManage) {
                case 1:
                    displayBook();
                    break;
                case 2:
                    inputListBook(scanner);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    bookManage = false;
                    break;
                default:
                    System.err.println("Please choose 1-9");
            }
        } while (bookManage);
    }

    public static void displayAuthor() {
        System.out.println("List Author is: ");
        System.out.printf("%-15s%-35s%-20s\n", "Author ID", "Author Name", "Status");
        for (Author author : listAuthor) {
            author.displayData();
        }
    }

    public static void addNewAuthor(Scanner scanner) {
        System.out.println("Enter the number Author you want to input: ");
        int choiceAddNewAuthor = 0;
        do {
            try {
                choiceAddNewAuthor = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException exception) {
                System.err.println("Please enter a number");
            }
        } while (true);
        int indexAuthorID = 0;
        for (int i = 0; i < choiceAddNewAuthor; i++) {
            System.out.printf("Enter Information Author %d\n", i + 1);
            Author author = new Author();
            author.setAuthorId(indexAuthorID + 1);
            author.inputData(scanner, listAuthor, null);
            listAuthor.add(author);
            author.insertData(author, true);
            indexAuthorID++;
        }
    }

    public static void updateAuthorInformation(Scanner scanner) {
        if (listAuthor.size() == 0) {
            System.err.println("List Author is empty now! Please add new Author");
        }
        System.out.println("Enter Author ID you want to update: ");
        int choiceIDUpdateAuthorInformation = 0;
        do {
            try {
                choiceIDUpdateAuthorInformation = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException exception) {
                System.err.println("Please enter a number");
            }
        } while (true);

        boolean checkIDUpdate = false;
        for (Author author : listAuthor) {
            if (author.getAuthorId() == choiceIDUpdateAuthorInformation) {
                checkIDUpdate = true;
                System.out.println("Enter Update Author Name: ");
                String updateAuthorName = "";
                do {
                    updateAuthorName = scanner.nextLine();
                    if (updateAuthorName != "" && updateAuthorName.trim().length() != 0) {
                        if (updateAuthorName.trim().length() >= 6 && updateAuthorName.trim().length() <= 50) {
                            author.setAuthorName(updateAuthorName);
                            break;
                        } else {
                            System.err.println("Please enter Author Name with 6-50 characters!");
                        }
                    } else {
                        break;
                    }
                } while (true);
                System.out.println("Choose Author Status: ");
                System.out.println("1. This author's books are selling");
                System.out.println("2. This author's books are no longer for sale");
                System.out.println("3. No Update");
                System.out.println("Your choice is: ");
                int choiceUpdateAuthorStatus = 0;
                do {
                    try {
                        choiceUpdateAuthorStatus = Integer.parseInt(scanner.nextLine());
                        break;
                    } catch (NumberFormatException exception) {
                        System.err.println("Please enter number");
                    }
                } while (true);
                switch (choiceUpdateAuthorStatus) {
                    case 1:
                        author.setAuthorStatus(true);
                        break;
                    case 2:
                        author.setAuthorStatus(false);
                        break;
                    case 3:
                        break;
                    default:
                        System.err.println("Please choose 1-3");
                }
                author.insertData(listAuthor, false);
                break;
            } else {
                checkIDUpdate = false;
            }
        }
        if (!checkIDUpdate) {
            System.err.println("This ID doesn't exist! Please try again!");
        }
    }

    public static void updateAuthorStatus(Scanner scanner) {
        System.out.println("Enter Author ID you want to change Status: ");
        int choiceIDChangeAuthorStatus = 0;
        do {
            try {
                choiceIDChangeAuthorStatus = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException exception) {
                System.err.println("Please enter a number");
            }
        } while (true);
        boolean checkAuthorStatus = false;
        for (Author author : listAuthor) {
            if (author.getAuthorId() == choiceIDChangeAuthorStatus) {
                checkAuthorStatus = true;
                author.setAuthorStatus(!author.isAuthorStatus());
                author.insertData(listAuthor, false);
                System.out.println("Change Status success!");
                break;
            } else {
                checkAuthorStatus = false;
            }
        }
        if (!checkAuthorStatus) {
            System.err.println("This ID doesn't exist! Please try again!");
        }
    }

    public static void displayBook() {
        System.out.println("List Book is: ");
        System.out.printf("%-15s%-35s%-15s%-15s%-15s%-15s%-35s%-40s%-40s%-35s%-20s\n", "Book ID", "Book Name", "Import price", "Export price", "Profit", "Quantity",
                "Author", "Title", "Content", "Publisher", "bookStatus");
        for (Book book : listBook) {
            book.displayData();
        }
    }

    public static void inputListBook(Scanner scanner) {
        System.out.println("Enter the number Book you want to input: ");
        int choiceAddNewBook = 0;
        do {
            try {
                choiceAddNewBook = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException exception) {
                System.err.println("Please enter a number");
            }
        } while (true);
        for (int i = 0; i < choiceAddNewBook; i++) {
            System.out.printf("Enter Information Author %d\n", i + 1);
            Book book = new Book();
            book.inputData(scanner, listAuthor, listBook);
            listBook.add(book);
            book.insertData(book, true);
        }
    }

    public static void updateBook(Scanner scanner) {
        System.out.println("Enter Book ID to update:");
        boolean checkBookExist = false;
        do {
            String updateBookID = scanner.nextLine();
            for (Book book : listBook) {
                if (book.getBookId().equals(updateBookID)) {
                    checkBookExist = true;
                    System.out.println("Enter update Book name: ");
                    String updateBookName = scanner.nextLine();
                    if (updateBookName!="" && updateBookName.trim().length()!=0) {
                        if (updateBookName.trim().length()>=10 && updateBookName.trim().length()<=100) {
                            book.setBookName(updateBookName);
                            break;
                        } else {
                            System.err.println("Please enter Book Name with 10-100 characters");
                        }
                    } else {
                        break;
                    }
                    System.out.println("Enter import price: ");
                    String updateImportPrice = scanner.nextLine();
                    if (updateImportPrice!="" && updateImportPrice.trim().length()!=0) {
                        if (Float.parseFloat(updateImportPrice)!= book.getImportPrice()){
                            book.setImportPrice(Float.parseFloat(updateImportPrice));
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                    System.out.println("Enter export price: ");
                    String updateExportPrice = scanner.nextLine();
                    if (updateExportPrice!="" && updateExportPrice.trim().length()!=0) {
                        if (Float.parseFloat(updateExportPrice)!= book.getExportPrice()){
                            book.setExportPrice(Float.parseFloat(updateExportPrice));
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                    System.out.println("Enter update Book quantity: ");
                    String updateQuantity = scanner.nextLine();
                    if (updateQuantity!="" && updateQuantity.trim().length()!=0) {
                        if (Integer.parseInt(updateQuantity)!= book.getQuantity()){
                            book.setQuantity(Integer.parseInt(updateQuantity));
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                } else {
                    checkBookExist = false;
                }

            }
            if (!checkBookExist){
                System.err.println("This book doesn't exist in list Book");
            }
        } while (true);
    }
}