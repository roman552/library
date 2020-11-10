/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.severs;

import entity.History;
import entity.Reader;
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
public class HistorySaver {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("SPTVR19LibraryPU");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();
    private final String fileName = "histories";

    public void saveHistories(History[] histories) {
        tx.begin();
            for (int i = 0; i < histories.length; i++) {
                if(histories[i] != null && histories[i].getId()==null){
                    em.persist(histories[i]);
                    break;
                }else{
                    em.merge(histories[i]);
                }
            }
        tx.commit();
    }

    public List<History> loadFile() {
        try {
            return em.createQuery("SELECT history FROM History history")
                    .getResultList();
            
        } catch (Exception e) {
            System.out.println("Нет записей");
            return new ArrayList();
        }
    }
    
}
