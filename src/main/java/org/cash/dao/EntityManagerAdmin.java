package org.cash.dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerAdmin {

    private static final String PERSISTENCE = "java_persistence_relationships";

    private static EntityManager instance;

    public EntityManagerAdmin() {
    }

    public static EntityManager getInstance() {
        if (instance == null || !instance.isOpen()) {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE);
            instance = entityManagerFactory.createEntityManager();
        }
        return instance;
    }

}