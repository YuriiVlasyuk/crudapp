package com.example.crudapp.Controller;


import com.example.crudapp.Exception.UserNotFoundException;
import com.example.crudapp.Model.User;
import com.example.crudapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {


    @Autowired
    UserRepository userRepository;

    // Отримати всі записи
    @GetMapping("/user")
    public List getAllNotes() {
        return userRepository.findAll();
    }

    // Створити запис
    @PostMapping("/user")
    public User createNote(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    // Знайти запис по ID
    @GetMapping("/user/{id}")
    public User getNoteById(@PathVariable(value = "id") Long userId) throws UserNotFoundException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    // Змінити запис
    @PutMapping("/user/{id}")
    public User updateNote(@PathVariable(value = "id") Long userId,
                           @Valid @RequestBody User userDetails) throws UserNotFoundException {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());


        User updatedUser = userRepository.save(user);
        return updatedUser;
    }

    // Видалити запис
    @DeleteMapping("/user/{id}")
    public ResponseEntity deleteBook(@PathVariable(value = "id") Long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }

}











/*import com.example.crudapp.Model.User;
import com.example.crudapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private  UserService userService;

    @GetMapping("/all" )
    public List<User> findAll(){
        return userService.findAll();
    }


    @PostMapping("/add")
    public void addUser(@RequestBody User user){
        userService.saveUser(user);
    }


    @PutMapping("/put")
    public void update( @RequestBody User user) {

        UserService.updateUser(user);
    }

    @DeleteMapping("/del/{id}")
    public void deleteById (@PathVariable Long id){
        userService.deleteById(id);
    }



}*/
