package nl.utwente.soa.sampleBlogApplication.services;

import nl.utwente.soa.sampleBlogApplication.domain.Comment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    private static List<Comment> comments = new ArrayList<>();

    @PostConstruct
    public void commentSetup(){
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
    }

    public List<Comment> getComments() {
        return comments;
    }

    public List<Comment> getCommentsForBlog(Long blogId) {
        List<Comment> commentsForBlog = new ArrayList<>();
        commentsForBlog.add(
                comments.stream().filter(comment -> comment.getBlogId() == blogId).findAny().orElse(null)
        );
        return commentsForBlog;
    }

}
