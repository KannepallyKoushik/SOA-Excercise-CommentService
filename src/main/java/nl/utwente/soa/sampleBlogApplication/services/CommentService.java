package nl.utwente.soa.sampleBlogApplication.services;

import nl.utwente.soa.sampleBlogApplication.domain.Comment;
import nl.utwente.soa.sampleBlogApplication.domain.PostBody;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private static List<Comment> comments = new ArrayList<>();

    @PostConstruct
    public void commentSetup() throws URISyntaxException {
        comments.add(
                new Comment(1L,1L,"Koushik","First comment for Blog1")
        );
        comments.add(
                new Comment(2L,1L,"Jesse","second comment for Blog1")
        );
        comments.add(
                new Comment(3L,2L,"Koushik","First comment for Blog2")
        );
        comments.add(
                new Comment(4L,2L,"Jesse","second comment for Blog2")
        );
        comments.add(
                new Comment(5L,3L,"Koushik","First comment for Blog3")
        );
        comments.add(
                new Comment(6L,3L,"Jesse","second comment for Blog3")
        );


        String postUrl = "http://localhost:8080/subscribePlugin";
        RestTemplate restTemplate = new RestTemplate();
        URI uri = new URI(postUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);




        for (Comment comment: comments) {
            PostBody body = new PostBody();
            body.setName(comment.getAuthor());
            body.setUrl("http://localhost:8081/comments/"+comment.getBlogId());
            HttpEntity entity = new HttpEntity<PostBody>(body,headers);
            restTemplate.exchange(postUrl, HttpMethod.POST, entity, String.class);
        }
    }

    public List<Comment> getComments() {
        return comments;
    }

    public List<Comment> getCommentsForBlog(Long blogId) {
        List<Comment> commentsForBlog = new ArrayList<>();
        commentsForBlog.addAll(
                comments.stream().filter(comment -> comment.getBlogId().equals(blogId)).collect(Collectors.toList())
        );
        return commentsForBlog;
    }

}
