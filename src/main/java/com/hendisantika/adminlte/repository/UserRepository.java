package com.hendisantika.adminlte.repository;

import com.hendisantika.adminlte.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
}
