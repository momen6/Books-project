package org.example.dao;

import org.example.model.Book;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class BookDao {
    public List<Book> getAllBooks(){
        Transaction transaction = null;
        List<Book> books = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            books = session.createQuery("from Book").getResultList();
            session.close();
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return books;
    }

    public void saveBook(Book book){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
         transaction = session.beginTransaction();
         session.save(book);
         transaction.commit();
         System.out.println("1 book inserted!");
         session.close();
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public void updateBook(Book book){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.update(book);
            transaction.commit();
            session.close();
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteBookById(int id){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Book book = session.get(Book.class,id);
            session.delete(book);
            System.out.println("1 book Deleted!");
            transaction.commit();
            session.close();
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
