/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.severs;

import entity.Book;
import entity.Reader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author user
 */
public class ReaderSaver {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("SPTVR19LibraryPU");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();
    private String fileName = "readers";

    public void saveReaders(List<Reader> readers) {
        tx.begin();
            for (int i = 0; i < readers.size(); i++) {
                if(readers.get(i) != null && readers.get(i).getId()==null){
                    em.persist(readers.get(i));
                    break;
                }else{
                    em.merge(readers.get(i));
                }
            }
        tx.commit();
    }

    public List<Reader> loadFile() {
        try {
            return em.createQuery("SELECT reader FROM Reader reader")
                    .getResultList();
            
        } catch (Exception e) {
            System.out.println("Нет записей");
            return new ArrayList();
        }
    }
    
}
