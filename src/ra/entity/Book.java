package ra.entity;

import ra.interfaces.IBook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Book implements IBook, Serializable {
    private String bookId;
    private String bookName;
    private float importPrice;
    private float exportPrice;
    private float profit;
    private int quantity;
    private List<Author> listAuthor = new ArrayList<>();
    private String title;
    private String content;
    private String publishing;
    private boolean bookStatus;
    private static final float RATE = 1.2F;

    public Book() {
    }

    public Book(String bookId, String bookName, float importPrice, float exportPrice, float profit, int quantity, List<Author> listAuthor, String title, String content, String publishing, boolean bookStatus) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.profit = profit;
        this.quantity = quantity;
        this.listAuthor = listAuthor;
        this.title = title;
        this.content = content;
        this.publishing = publishing;
        this.bookStatus = bookStatus;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public float getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(float importPrice) {
        this.importPrice = importPrice;
    }

    public float getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(float exportPrice) {
        this.exportPrice = exportPrice;
    }

    public float getProfit() {
        return profit;
    }

    public void setProfit(float profit) {
        this.profit = profit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<Author> getListAuthor() {
        return listAuthor;
    }

    public void setListAuthor(List<Author> listAuthor) {
        this.listAuthor = listAuthor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublishing() {
        return publishing;
    }

    public void setPublishing(String publishing) {
        this.publishing = publishing;
    }

    public boolean isBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(boolean bookStatus) {
        this.bookStatus = bookStatus;
    }

    @Override
    public void inputData(Scanner scanner, List<Author> listAuthor, List<Book> listBook) {
        System.out.println("Enter Book ID:");
        boolean checkExit = false;
        do {
            this.bookId = scanner.nextLine();
            if (this.bookId.trim().length() == 5) {
                if (this.bookId.charAt(0) == 'B') {
                    for (Book book : listBook) {
                        if (book.getBookId().equals(this.bookId)) {
                            checkExit = true;
                            break;
                        }
                    }
                    if (!checkExit) {
                        break;
                    } else {
                        System.err.println("This ID has existed! Please try again");
                    }
                } else {
                    System.err.println("Please enter book ID start with B");
                }
            } else {
                System.err.println("Please enter book ID with 5 characters!");
            }
        } while (true);
        System.out.println("Enter Book Name: ");
        do {
            this.bookName = scanner.nextLine();
            if (this.bookName.trim().length() >= 10 && this.bookName.trim().length() <= 100) {
                break;
            } else {
                System.err.println("Please enter Book Name with 10-100 characters!");
            }
        } while (true);
        System.out.println("Enter Import price: ");
        do {
            try {
                this.importPrice = Float.parseFloat(scanner.nextLine());
                if (this.importPrice > 0) {
                    break;
                } else {
                    System.err.println("Please enter a number greater than 0");
                }
            } catch (NumberFormatException exception) {
                System.err.println("Please enter a number");
            }
        } while (true);
        System.out.println("Enter Export price");
        do {
            try {
                this.exportPrice = Float.parseFloat(scanner.nextLine());
                if (this.exportPrice > this.importPrice * RATE) {
                    break;
                } else {
                    System.err.println("Please enter export price 20% greater than import price");
                }
            } catch (NumberFormatException exception) {
                System.err.println("Please enter a number");
            }
        } while (true);
        System.out.println("Enter quantity Book");
        do {
            try {
                this.quantity = Integer.parseInt(scanner.nextLine());
                if (this.quantity >= 0) {
                    break;
                } else {
                    System.err.println("Please enter a number greater than 0");
                }
            } catch (NumberFormatException exception) {
                System.err.println("Please enter a number");
            }
        } while (true);
        System.out.println("Choose Author for Book: ");
        int count = 1;
        do {
            for (Author author : listAuthor) {
                System.out.printf("%d. %s\n", count, author.getAuthorName());
                count++;
            }
            count = 1;
            System.out.println("Your choice is: ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= listAuthor.size()) {
                    boolean checkAuthorExist = false;
                    for (Author authorExist : this.listAuthor) {
                        if (authorExist.getAuthorId() == listAuthor.get(choice - 1).getAuthorId()) {
                            checkAuthorExist = true;
                        }
                    }
                    if (!checkAuthorExist) {
                        this.listAuthor.add(listAuthor.get(choice - 1));
                    } else {
                        System.err.println("This Author has existed!");
                    }
                    System.out.println("Do you want to add new Author?");
                    System.out.println("1. Yes");
                    System.out.println("2. No");
                    System.out.println("Your choice is");
                    try {
                        int choiceExit = Integer.parseInt(scanner.nextLine());
                        if (choiceExit != 1) {
                            break;
                        }
                    } catch (NumberFormatException exception) {
                        System.err.println("Please enter a number");
                    }
                } else {
                    System.err.println("This Author was chose");
                }
            } catch (NumberFormatException exception) {
                System.err.println("Please enter a number");
            }
        } while (true);
        System.out.println("Enter Book Title");
        do {
            this.title = scanner.nextLine();
            if (this.title.trim().length() >= 30 && this.title.trim().length() <= 100) {
                break;
            } else {
                System.err.println("Please enter Book Title with 30-100 characters");
            }
        } while (true);
        System.out.println("Enter Book content:");
        this.content = scanner.nextLine();
        System.out.println("Enter Book Publishing:");
        this.publishing = scanner.nextLine();
        System.out.println("Choose Book Status: ");
        System.out.println("1. Selling");
        System.out.println("2. No more for sale");
        System.out.println("Your choice is: ");
        do {
            try {
                int choiceBookStatus = Integer.parseInt(scanner.nextLine());
                if (choiceBookStatus == 1) {
                    this.bookStatus = true;
                }
                break;
            } catch (NumberFormatException exception) {
                System.err.println("Please enter number");
            }
        } while (true);
    }

    @Override
    public void displayData() {
        String bookStatus = "Not sale";
        if (this.bookStatus) {
            bookStatus = "Selling";
        }
        String authorName = "";
        for (Author author : listAuthor) {
            authorName = author.getAuthorName();
        }
        System.out.printf("%-15s%-35s%-15f%-15f%-15f%-15d%-35s%-40s%-40s%-35s%-20s\n", this.bookId, this.bookName, this.importPrice, this.exportPrice, this.profit, this.quantity,
                authorName, this.title, this.content, this.publishing, bookStatus);
    }


    @Override
    public void getData(List<Author> listAuthor, List<Book> listBook) {
        File file = null;
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            file = new File(PATH_BOOK);
            if (file.exists()) {
                fileInputStream = new FileInputStream(file);
                objectInputStream = new ObjectInputStream(fileInputStream);
                List<Book> listRead = (List<Book>) objectInputStream.readObject();
                listBook.addAll(listRead);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
//            System.err.println("List Author is empty now! Please add new Author!");
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @Override
    public void insertData(Object object, boolean appended) {
        File file = null;
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            file = new File(PATH_BOOK);
            fileOutputStream = new FileOutputStream(file, appended);
            if (appended) {
                objectOutputStream = new ObjectOutputStream(fileOutputStream) {
                    protected void writeStreamHeader() throws IOException {
                        reset();
                    }
                };
                objectOutputStream.writeObject((Book) object);
            } else {
                objectOutputStream = new ObjectOutputStream(fileOutputStream);
            }
            if (appended) {
                objectOutputStream.writeObject((Book) object);
            } else {
                objectOutputStream.writeObject((List<Book>) object);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void calProfit() {
        this.profit = this.exportPrice - this.importPrice;
    }

    public void buyBook(int numberBook) {
        if (numberBook > this.quantity) {
            System.out.println("Not enough book to sell!");
        } else {
            this.quantity -= numberBook;
        }
    }
}
