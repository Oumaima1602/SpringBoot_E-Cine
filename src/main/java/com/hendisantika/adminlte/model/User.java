package com.hendisantika.adminlte.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tab_user")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

   // @Email
    @Column(unique = true, nullable = false)
    private String email;

    private String password;
}
