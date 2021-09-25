package com.example.geektext;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//AUTO IMPLEMENTED by spring into a bean called Respository
//CRUD refers to Create, Read, Update, Delete
public interface UserRepository extends CrudRepository <User, String>
{
    List<User> findByuserName (String userName);
}
