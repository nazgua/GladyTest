package com.backEnd.gladyTest.repositoriesImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.backEnd.gladyTest.exceptions.UserNotFoundException;
import com.backEnd.gladyTest.model.User;
import com.backEnd.gladyTest.repositories.UserRepository;

@Service
public class UserRepositoryImpl implements UserRepository {

    private final Map<Long, User> userMap;

    public UserRepositoryImpl() {
        this.userMap = new HashMap<Long, User>() {{
            put(1L, new User(1L, "John", "Doe"));
        }};
    }

    @Override
    public User findUserById(Long id) {
        // Retrieve the user from the userMap based on the provided ID
        return userMap.get(id);
    }

    @Override
    public User saveUser(User user) {
        // Assign a new ID to the user if it doesn't have one
        if (user.getId() == null) {
            Long newId = generateNewId();
            user.setId(newId);
        }

        // Save the user in the userMap
        userMap.put(user.getId(), user);
        return user;
    }

    @Override
    public User updateUser(User user) throws UserNotFoundException {
        // Update the user in the userMap if it exists
        if (userMap.containsKey(user.getId())) {
            userMap.put(user.getId(), user);
            return user;
        }
        throw new UserNotFoundException("User not found with ID: " + user.getId()); 
    }

    private Long generateNewId() {
        // Generate a new ID for the user (you can use a custom logic based on your requirements)
        // Here's a simple example of generating an ID using a timestamp
        return System.currentTimeMillis();
    }
}
