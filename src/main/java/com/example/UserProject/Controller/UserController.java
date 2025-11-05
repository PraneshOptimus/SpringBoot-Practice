package com.example.UserProject.Controller;

import com.example.UserProject.Exceptions.ResourceNotFoundException;
import com.example.UserProject.Model.UserEntity;
import com.example.UserProject.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {



    @Autowired
    UserRepo userRepo;

    @GetMapping
    public List<UserEntity> showUser(){
        return  userRepo.findAll();
    }

    @GetMapping("/{id}")
    public UserEntity findById(@PathVariable long id){
            return userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Name with this id :"+id+" is not found"));
    }

    @PostMapping("/add")
    public UserEntity addUser(@RequestBody UserEntity userEntity){
        return userRepo.save(userEntity);
    }

    @PutMapping("/{id}")
    public UserEntity update(@PathVariable long id,@RequestBody UserEntity user){
        UserEntity userEn = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Name with this id :"+id+" is not found"));
        userEn.setEmail(user.getEmail());
        userEn.setName(user.getName());
        return userRepo.save(userEn);
    }

    @DeleteMapping("/all")
    public void removeAll(){
        userRepo.deleteAll();
    }

    @DeleteMapping("/{id}")
    public void removeById(@PathVariable long id){
        userRepo.deleteById(id);
    }
}
