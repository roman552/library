/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.severs;

import entity.User;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author user
 */
public class UserSaver {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("SPTVR19LibraryPU");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();
    private final String fileName = "users";

    public void saveUsers(List<User> users) {
        tx.begin();
            for (int i = 0; i < users.size(); i++) {
                if(users.get(i) != null && users.get(i).getId()==null){
                    em.persist(users.get(i));
                    break;
                }else{
                    em.merge(users.get(i));
                }
            }
        tx.commit();
    }

    public List<User> loadFile() {
        try {
            return em.createQuery("SELECT user FROM User user")
                    .getResultList();
            
        } catch (Exception e) {
            System.out.println("Нет записей");
            return new ArrayList();
        }
    }
}

