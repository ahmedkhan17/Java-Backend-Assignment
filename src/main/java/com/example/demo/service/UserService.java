package com.example.demo.service;

import com.example.demo.entity.Blog;
import com.example.demo.entity.User;
import com.example.demo.model.SuccessResponse;
import com.example.demo.util.DataSourceUtil;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @SneakyThrows
    public SuccessResponse createUser(final User user) {

        userValidation(user);
        final String id = DataSourceUtil.generateUserId();
        user.setId(id);
        DataSourceUtil.addUser(user);

        final SuccessResponse successResponse = new SuccessResponse();
        successResponse.setId(id);
        successResponse.setMessage("User Inserted Successfully");
        return successResponse;
    }

    @SneakyThrows
    public SuccessResponse updateUser(final String userId, final User user) {

        if(StringUtils.isBlank(userId)) {
            throw new Exception("userId is required");
        }
        getSpecificUserById(userId);
        userValidation(user);

        if(StringUtils.isNotBlank(user.getId()) && !userId.equals(user.getId())) {
            throw new Exception("user's Id not matched");
        }
        DataSourceUtil.addUser(user);
        final SuccessResponse successResponse = new SuccessResponse();
        successResponse.setId(userId);
        successResponse.setMessage("User Updated Successfully");
        return successResponse;
    }

    @SneakyThrows
    public User getSpecificUserById(final String userId) {

        if(StringUtils.isBlank(userId)) {
            throw new Exception("userId is required");
        }
        final User user = DataSourceUtil.getUser(userId);
        if(null == user) {
            throw new Exception("User with given Id is not present");
        }

        return user;
    }

    private void userValidation(final User user) throws Exception {
        if(null == user) {
            throw new Exception("user cannot be null");
        }

        if(StringUtils.isBlank(user.getName())) {
            throw new Exception("user's name is required");
        }
    }
}
