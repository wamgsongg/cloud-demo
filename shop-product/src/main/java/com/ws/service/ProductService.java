package com.ws.service;

import com.ws.database.Product;

public interface ProductService {

    Product findByPid(Integer pid);
}
