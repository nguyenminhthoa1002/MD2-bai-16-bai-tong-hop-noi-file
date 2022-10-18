package ra.entity;

import ra.interfaces.IBook;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Author implements IBook, Serializable {
    private int authorId;
    private String authorName;
    private boolean authorStatus;

    public Author() {
    }

    public Author(int authorId, String authorName, boolean authorStatus) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.authorStatus = authorStatus;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public boolean isAuthorStatus() {
        return authorStatus;
    }

    public void setAuthorStatus(boolean authorStatus) {
        this.authorStatus = authorStatus;
    }


    @Override
    public void inputData(Scanner scanner, List<Author> listAuthor, List<Book> listBook) {
        if (listAuthor.size() == 0) {
            this.authorId = 1;
        } else {
            int max = 0;
            for (Author author : listAuthor) {
                if (max < author.getAuthorId()) {
                    max = author.getAuthorId();
                }
            }
            this.authorId = max + 1;
        }
        System.out.println("Enter Author Name: ");
        do {
            this.authorName = scanner.nextLine();
            boolean checkAuthorExist = false;
            if (this.authorName.trim().length() >= 6 && this.authorName.trim().length() <= 50) {
                for (Author author : listAuthor) {
                    if (!author.getAuthorName().toLowerCase().equals(this.authorName.toLowerCase())){
                        checkAuthorExist =true;
                        break;
                    } else {
                        checkAuthorExist = false;
                    }
                }
                if (!checkAuthorExist){
                    System.err.println("This Author has already existed in Author List!");
                }
                break;
            } else {
                System.err.println("Please enter Author Name with 6-50 characters!");
            }
        } while (true);
        System.out.println("Choose Author Status: ");
        System.out.println("1. This author's books are selling");
        System.out.println("2. This author's books are no longer for sale");
        System.out.println("Your choice is: ");
        int choiceAuthorStatus = 0;
        do {
            try {
                choiceAuthorStatus = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException exception) {
                System.err.println("Please enter number");
            }
        } while (true);
        switch (choiceAuthorStatus) {
            case 1:
                this.authorStatus = true;
                break;
            case 2:
                this.authorStatus = false;
                break;
            default:
                System.err.println("Please choose 1 or 2");
        }
    }

    @Override
    public void displayData() {
        String authorStatus = "Not sale";
        if (this.authorStatus) {
            authorStatus = "Selling";
        }
        System.out.printf("%-15d%-35s%-20s\n", this.authorId, this.authorName, authorStatus);
    }

    @Override
    public void getData(List<Author> listAuthor, List<Book> listBook) {
        File file = null;
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            file = new File(PATH_AUTHOR);
            if (file.exists()) {
                fileInputStream = new FileInputStream(file);
                objectInputStream = new ObjectInputStream(fileInputStream);
                List<Author> listRead = (List<Author>) objectInputStream.readObject();
                listAuthor.addAll(listRead);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
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
            file = new File(PATH_AUTHOR);
            fileOutputStream = new FileOutputStream(file, appended);
            if (appended) {
                objectOutputStream = new ObjectOutputStream(fileOutputStream) {
                    protected void writeStreamHeader() throws IOException {
                        reset();
                    }
                };
            } else {
                objectOutputStream = new ObjectOutputStream(fileOutputStream);
            }
            if (appended) {
                objectOutputStream.writeObject((Author) object);
            } else {
                objectOutputStream.writeObject((List<Author>) object);
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
}
