package com.example.geektext;

import org.springframework.data.repository.CrudRepository;

//AUTO IMPLEMENTED by spring into a bean called Repository
//CRUD refers to Create, Read, Update, Delete
public interface CartRepository extends CrudRepository<Cart, Integer>
{

}
