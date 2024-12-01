package org.example.restservice.repository;

import org.example.restservice.model.User;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    private final HibernateTemplate hibernateTemplate;

    public UserRepository(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public void save(User user) {
        hibernateTemplate.save(user);
    }

    public List<User> findAll() {
        return hibernateTemplate.loadAll(User.class);
    }

    public User findById(Long id) {
        return hibernateTemplate.get(User.class, id);
    }
}
