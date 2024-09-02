package com.example.demo.controller;

import com.example.demo.entity.Blog;
import com.example.demo.model.SuccessResponse;
import com.example.demo.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/blog")
public class BlogController {
    private BlogService blogService;

    @PostMapping
    public SuccessResponse addBlog(@RequestBody final Blog blog) {

        return blogService.createBlog(blog);
    }

    @PutMapping("/{id}")
    public SuccessResponse updateBlog(@PathVariable("id") final String id,
                                      @RequestParam(value = "userId") final String userId,
                                                   @RequestBody final Blog blog) {
        return blogService.updateBlog(id,userId,blog);
    }

    @GetMapping
    public HashMap<String, Blog> getAllBlogs() {
        return blogService.getAllBlogs();
    }

    @GetMapping("/{id}")
    public Blog getBlogById(@PathVariable("id") final String id) {
        return blogService.getSpecificBlogById(id);
    }

    @DeleteMapping("/{id}")
    public SuccessResponse deleteSpecificBlogById(@PathVariable("id") final String id,
                                                  @RequestParam(value = "userId") final String userId) {
        return blogService.deleteSpecificBlogById(id,userId);
    }

}
