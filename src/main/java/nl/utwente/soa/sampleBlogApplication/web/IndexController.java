package nl.utwente.soa.sampleBlogApplication.web;

import nl.utwente.soa.sampleBlogApplication.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

    @Autowired private CommentService commentService;


    @GetMapping("/comments/{blogid}")
    public String showBlog(Model model, @PathVariable("blogid") Long blogId) {
        model.addAttribute("blog", commentService.getCommentsForBlog(blogId));
        return "detail";
    }
}
