package com.example.geektext;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

//AUTO IMPLEMENTED by spring into a bean called Repository
//CRUD refers to Create, Read, Update, Delete
public interface CartRepository extends CrudRepository<Cart, Long>
{
    List<Cart> findBycartId (Long cartId);
}
