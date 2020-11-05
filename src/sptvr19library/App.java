/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sptvr19library;


import security.SecureManager;
import entity.Reader;
import entity.Book;
import entity.History;
import entity.User;
import java.util.Scanner;
import tools.managers.BookManager;
import tools.severs.BookSaver;
import tools.managers.HistoryManager;
import tools.severs.HistorySaver;
import tools.managers.ReaderManager;
import tools.severs.ReaderSaver;
import tools.severs.UserSaver;

/**
 *
 * @author sillamae kutsekool
 */
class App {
    private Book[] books = new Book[100];
    private Reader[] readers = new Reader[100];
    private History[] histories = new History[100];
    private User[] users = new User[100];
    private ReaderManager readerManager = new ReaderManager();
    private BookManager bookManager = new BookManager();
    private HistoryManager historyManager = new HistoryManager();
    private SecureManager secureManager = new SecureManager();
    private User loginedUser;
    public App() {
        BookSaver bookSaver = new BookSaver();
        books = bookSaver.loadFile();
        ReaderSaver readerSaver = new ReaderSaver();
        readers = readerSaver.loadFile();
        HistorySaver historySaver = new HistorySaver();
        histories = historySaver.loadFile();
        UserSaver userSaver = new UserSaver();
        users = userSaver.loadFile();
    }
    
    public void run(){
        System.out.println("--- Библиотека ---");
        //this.loginedUser = secureManager.checkTask(users, readers);
        boolean repeat = true;
        do{
            System.out.println("Список задач: ");
            System.out.println("0. Выйти из программы");
            System.out.println("1. Добавить новую книгу");
            System.out.println("2. Посмотреть список книг");
            System.out.println("3. Зарегистрировать нового читателя");
            System.out.println("4. Список читателей");
            System.out.println("5. Выдать книгу читателю");
            System.out.println("6. Вернуть книгу в библиотеку");
            System.out.println("7. Список читаемыз книг");
            System.out.print("Выберите задачу: ");
            Scanner scanner = new Scanner(System.in);
            String task = scanner.nextLine();
            switch (task) {
                case "0":
                    System.out.println("---- Конец программы ----");
                    repeat = false;
                    break;
                case "1":
                    System.out.println("---- Добавить новую книгу ----");
                    Book book = bookManager.createBook();
                    bookManager.addBookToArray(book, books);
                    bookManager.printListBooks(books);
                    BookSaver bookSaver = new BookSaver();
                    bookSaver.saveBooks(books);
                    break;
                case "2":
                    System.out.println("--- Cписок книг ---");
                    bookManager.printListBooks(books);
                    break;
                case "3":
                    System.out.println("--- Зарегистрировать нового читателя ---");
                    Reader reader = readerManager.createReader();
                    readerManager.addReaderToArray(reader, readers);
                    readerManager.printListReaders(readers);
                    ReaderSaver readerSaver = new ReaderSaver();
                    readerSaver.saveReaders(readers);
                    break;
                case "4":
                    System.out.println("--- Список читателей ---");
                    readerManager.printListReaders(readers);
                    break;
                case "5":
                    System.out.println("--- Выдать книгу ---");
                    History history = historyManager.takeOnBookToReader(books, readers);
                    historyManager.addBookToArray(history, histories);
                    historyManager.printListHistories(histories);
                    HistorySaver historySaver = new HistorySaver();
                    historySaver.saveHistories(histories);
                    break;
                case "6":
                    System.out.println("--- Возврат книги ---");
                    historyManager.returnBook(histories);
                    historySaver = new HistorySaver();
                    historySaver.saveHistories(histories);
                    break;
                case "7":
                    System.out.println("--- Список читаемых книг ---");
                    historyManager.printListHistories(histories);
                    break;
                default:
                    System.out.println("Нет такой задачи.");
            }
        }while(repeat);
    }
}
