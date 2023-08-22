package org.example.dao;

import org.example.model.Author;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Set;

public class AuthorDao {
    public void saveAuthor(Author author){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.save(author);
            transaction.commit();
            session.close();
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }
    }
    public List<Author> getAllAuthors(){
        Transaction transaction = null;
        List<Author> authors = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            authors = session.createQuery("from Author").getResultList();
            transaction.commit();
            session.close();
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }
        return authors;
    }

    public void deleteAuthor(int id){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            Author author = session.get(Author.class,id);
            if (author != null){
                session.delete(author);
                System.out.println("Author Deleted!");
            }
            transaction.commit();
            session.close();
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }
    }

    public void getAuthor(int id){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            Author author = session.get(Author.class,id);
            System.out.println("the Author is: " + author);
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
