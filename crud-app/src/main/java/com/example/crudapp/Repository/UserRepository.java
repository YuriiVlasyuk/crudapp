package com.example.crudapp.Repository;

import com.example.crudapp.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/* Взаємодія з БД */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
