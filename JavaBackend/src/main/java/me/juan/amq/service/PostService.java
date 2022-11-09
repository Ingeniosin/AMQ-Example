package me.juan.amq.service;

import me.juan.amq.entity.Post;
import me.juan.amq.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public Post findById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public Iterable<Post> findAll() {
        return postRepository.findAll();
    }

    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

    public void deleteAll() {
        postRepository.deleteAll();
    }

    public Post update(Post post) {
        return postRepository.save(post);
    }

    public Post updateById(Long id, Post post) {
        Post postToUpdate = postRepository.findById(id).orElseThrow();
        postToUpdate.setTitle(post.getTitle());
        postToUpdate.setBody(post.getBody());
        return postRepository.save(postToUpdate);
    }

    public Post updateTitleById(Long id, String title) {
        Post postToUpdate = postRepository.findById(id).orElseThrow();
        postToUpdate.setTitle(title);
        return postRepository.save(postToUpdate);
    }

    public Post updateBodyById(Long id, String body) {
        Post postToUpdate = postRepository.findById(id).orElseThrow();
        postToUpdate.setBody(body);
        return postRepository.save(postToUpdate);
    }
}
