package com.example.UserProject.Repository;


import com.example.UserProject.Model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {
}
