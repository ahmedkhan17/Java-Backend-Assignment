package com.example.demo.util;

import com.example.demo.entity.Blog;
import com.example.demo.entity.User;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class DataSourceUtil {
    public final static HashMap<String, Blog> blogMap = new LinkedHashMap<>();
    public final static HashMap<String, User> userMap = new LinkedHashMap<>();

    public static Blog getBlog(final String blogId) {
        if(null != blogMap.get(blogId)) {
            return blogMap.get(blogId);
        }
        return null;
    }
    public static void addBlog(final Blog blog) {
        blogMap.put(blog.getId(),blog);
    }

    public static User getUser(final String userId) {
        if(null != userMap.get(userId)) {
            return userMap.get(userId);
        }
        return null;
    }
    public static void addUser(final User user) {
        userMap.put(user.getId(),user);
    }

    public static String generateBlogId() {
        final String blogId;
        while (true) {
            final String generatedId = RandomStringUtils.random(8, false, true);
            final Blog blog = getBlog(generatedId);
            if (null == blog) {
                blogId = generatedId;
                break;
            }
        }
        return blogId;
    }

    public static String generateUserId() {
        final String userId;
        while (true) {
            final String generatedId = RandomStringUtils.random(8, false, true);
            final User user = getUser(generatedId);
            if (null == user) {
                userId = generatedId;
                break;
            }
        }
        return userId;
    }
}
