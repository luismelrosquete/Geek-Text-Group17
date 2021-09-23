package com.example.geektext;

import org.springframework.data.repository.CrudRepository;
import com.example.geektext.User;

//AUTO IMPLEMENTED by spring into a bean called Respository
//CRUD refers to Create, Read, Update, Delete
public interface UserRepository extends CrudRepository <User, Integer>
{ }
