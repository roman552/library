/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import entity.Reader;
import entity.User;
import java.util.Scanner;
import tools.managers.ReaderManager;

/**
 *
 * @author user
 */
public class UserManager {
    private Scanner scanner = new Scanner(System.in);

    public User regUser() {
        ReaderManager readerManager = new ReaderManager();
        Reader reader = readerManager.createReader();
        return createUser(reader);
    }
    private User createUser(Reader reader){
        User user = new User();
        System.out.println("--- Регистрация пользователя ---");
        System.out.print("Логин: ");
        user.setLogin(scanner.nextLine());
        System.out.print("Пароль: ");
        user.setPassword(scanner.nextLine());
        user.setReader(reader);
        return user;
    }
            
    public User getAuthUser(User[] users) {
        //+получить от пользователя логин
        //+получиь от пользователя пароль
        //пройти по users циклом сравнить логины
        // --если логины совпадают 
        // --сравнить их пароли 
        // ---- если пароли совпадают то вернуть авторизованного пользователя и выйти из цикла
        // --если пароли не совпадают -> вторая попытка
        // --если пароли не совпадают -> третья попытка
        // --если пароли не совпадают -> закрыть программу.
        System.out.print("Введите логин:");
        String login = scanner.nextLine();
        System.out.print("Введите пароль:");
        String password = scanner.nextLine();
        for (int i = 0; i < users.length; i++) {
            User user = users[i];
            if(user==null) continue;
            if(login.equals(user.getLogin())){
                for (int j = 0; j < 2; j++) {
                    if(password.equals(user.getPassword())){
                        return user;
                    }else{
                        System.out.println("Неправильный пароль. Попробуйе еще раз");
                        System.out.print("Введите пароль:");
                        password = scanner.nextLine();
                    }
                }
                System.out.println("Вы не авторизовались. До свидания.");
                System.exit(0);
            }
        }
        
        return null;
    }
     public void addUserToArray(User user, User[] users){
        for (int i = 0; i < users.length; i++) {
            if(users[i] == null){
                users[i]=user;
                break;
            }
        }
    }
    
}
