package org.example.restservice.repository;

import org.example.restservice.model.Post;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostRepository {
    private final HibernateTemplate hibernateTemplate;

    public PostRepository(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public void save(Post post) {
        hibernateTemplate.save(post);
    }

    public List<Post> findAll() {
        return hibernateTemplate.loadAll(Post.class);
    }

    public Post findById(Long id) {
        return hibernateTemplate.get(Post.class, id);
    }
}
