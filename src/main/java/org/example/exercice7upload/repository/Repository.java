package org.example.exercice7upload.repository;

import org.example.exercice7upload.model.Image;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Repository {

    protected StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

    protected SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    protected Session session;

    public Image save(Image image) {
         session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(image);
        session.getTransaction().commit();
        session.close();
        return image;
    }

    public Image findById(int id) {
        session = sessionFactory.openSession();
        Image image = session.get(Image.class, id);
        session.close();
        return image;
    }

    public void delete(Image image) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.remove(image);
        session.getTransaction().commit();
        session.close();
    }

    public List<Image> findAll() {
            session = sessionFactory.openSession();
            session.beginTransaction();
            List<Image> images = session.createQuery("from Image").list();
            session.getTransaction().commit();
            session.close();
            return images;

    }

}