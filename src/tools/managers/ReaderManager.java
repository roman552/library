/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.managers;

import entity.Book;
import entity.Reader;
import java.util.Scanner;

/**
 *
 * @author user
 */
public class ReaderManager {

    public Reader createReader() {
        Reader reader = new Reader();
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- Регистрация читателя ---");
        System.out.print("Имя: ");
        reader.setName(scanner.nextLine());
        System.out.print("Фамилия: ");
        reader.setLastname(scanner.nextLine());
        System.out.print("Телефон: ");
        reader.setPhone(scanner.nextLine());
        return reader;
    }

    public void addReaderToArray(Reader reader, Reader[] readers) {
        for (int i = 0; i < readers.length; i++) {
            if(readers[i] == null){
                readers[i]=reader;
                break;
            }
        }   
    }
    public void printListReaders(Reader[] readers) {
        for (int i = 0; i < readers.length; i++) {
            if(readers[i]!= null){
                System.out.printf("%3d. Добавлен читатель: %s %s%n"
                        ,i+1
                        ,readers[i].getName()
                        ,readers[i].getLastname()
                );
                System.out.println("--------------------------------");
            }
        }
    }
    
}
