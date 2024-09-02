package com.example.demo.service;

import com.example.demo.entity.Blog;
import com.example.demo.entity.User;
import com.example.demo.model.SuccessResponse;
import com.example.demo.util.DataSourceUtil;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class BlogService {
    @SneakyThrows
    public SuccessResponse createBlog(final Blog blog) {

        blogObjValidation(blog);
        final String id = DataSourceUtil.generateBlogId();
        blog.setId(id);
        DataSourceUtil.addBlog(blog);

        final SuccessResponse successResponse = new SuccessResponse();
        successResponse.setId(id);
        successResponse.setMessage("Blog Inserted Successfully");
        return successResponse;
    }

    @SneakyThrows
    public SuccessResponse updateBlog(final String blogId, final String userId, final Blog blog) {

        if(StringUtils.isBlank(blogId)) {
            throw new Exception("blogId is required");
        }
        Blog savedBlog = getSpecificBlogById(blogId);
        blogObjValidation(blog);

        if(StringUtils.isNotBlank(blog.getId()) && !blogId.equals(blog.getId())) {
            throw new Exception("blogId not matched");
        }
        blog.setId(blogId);
        validateUserAllowedToEditDeleteBlog(savedBlog,userId);
        DataSourceUtil.addBlog(blog);
        final SuccessResponse successResponse = new SuccessResponse();
        successResponse.setId(blogId);
        successResponse.setMessage("Blog Updated Successfully");
        return successResponse;
    }

    private void validateUserAllowedToEditDeleteBlog(final Blog savedBlog, final String currentUserId) throws Exception {
        if(!savedBlog.getUserId().equals(currentUserId)) {
            throw new Exception("only associated user of blog can edit/delete blog");
        }
    }

    public HashMap<String, Blog> getAllBlogs() {
        return DataSourceUtil.blogMap;
    }

    @SneakyThrows
    public Blog getSpecificBlogById(final String blogId) {

        if(StringUtils.isBlank(blogId)) {
            throw new Exception("BlogId is required");
        }
        final Blog savedBlog = DataSourceUtil.getBlog(blogId);
        if(null == savedBlog) {
            throw new Exception("Blog with given Id is not present");
        }

        return savedBlog;
    }

    @SneakyThrows
    public SuccessResponse deleteSpecificBlogById(final String blogId, final String userId) {

        if(StringUtils.isBlank(blogId)) {
            throw new Exception("BlogId is required");
        }
        final Blog savedBlog = DataSourceUtil.getBlog(blogId);
        if(null == savedBlog) {
            throw new Exception("Blog with given Id is not present");
        }
        validateUserAllowedToEditDeleteBlog(savedBlog,userId);
        DataSourceUtil.blogMap.remove(blogId);

        final SuccessResponse successResponse = new SuccessResponse();
        successResponse.setId(blogId);
        successResponse.setMessage("Blog Deleted Successfully");
        return successResponse;
    }

    private void blogObjValidation(final Blog blog) throws Exception {
        if(null == blog) {
            throw new Exception("blog Object cannot be null");
        }

        if(StringUtils.isBlank(blog.getUserId())) {
            throw new Exception("User must be associated with blog");
        }

        User user = DataSourceUtil.getUser(blog.getUserId());
        if(null == user) {
            throw new Exception("User with given Id is not present");
        }

        if(StringUtils.isBlank(blog.getData())) {
            throw new Exception("please add data in blog");
        }
    }
}
