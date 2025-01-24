package com.mapping;




import javax.persistence.*;
import java.util.List;

public class TaskDAO {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("taskManagementUnit");

    public void createTask(Task task) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(task);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public List<Task> getAllTasks() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT t FROM Task t", Task.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void updateTask(Task task) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.merge(task);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void deleteTask(int taskId) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            Task task = em.find(Task.class, taskId);
            if (task != null) {
                em.remove(task);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void closeFactory() {
        emf.close();
    }
}
