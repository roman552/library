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
import java.util.ArrayList;
import java.util.List;
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
    private List<Book> listBooks = new ArrayList<>();
    private List<Reader> listReaders = new ArrayList<>();
    private List<History> listHistories = new ArrayList<>();
    private List<User> listUsers = new ArrayList<>();
    
    private ReaderManager readerManager = new ReaderManager();
    private BookManager bookManager = new BookManager();
    private HistoryManager historyManager = new HistoryManager();
    private SecureManager secureManager = new SecureManager();
    private User loginedUser;
    public App() {
        BookSaver bookSaver = new BookSaver();
        listBooks = bookSaver.loadFile();
        ReaderSaver readerSaver = new ReaderSaver();
        listReaders = readerSaver.loadFile();
        HistorySaver historySaver = new HistorySaver();
        listHistories = historySaver.loadFile();
        UserSaver userSaver = new UserSaver();
        listUsers = userSaver.loadFile();
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
                    bookManager.addBookToArray(book, listBooks);
                    bookManager.printListBooks(listBooks);
                    BookSaver bookSaver = new BookSaver();
                    bookSaver.saveBooks(listBooks);
                    break;
                case "2":
                    System.out.println("--- Cписок книг ---");
                    bookManager.printListBooks(listBooks);
                    break;
                case "3":
                    System.out.println("--- Зарегистрировать нового читателя ---");
                    Reader reader = readerManager.createReader();
                    readerManager.addReaderToArray(reader, listReaders);
                    readerManager.printListReaders(listReaders);
                    ReaderSaver readerSaver = new ReaderSaver();
                    readerSaver.saveReaders(listReaders);
                    break;
                case "4":
                    System.out.println("--- Список читателей ---");
                    readerManager.printListReaders(listReaders);
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
