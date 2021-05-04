package com.ws.database;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "shop_order")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//数据库自增，jpa
    private Integer oid;

    private Integer uid;
    private String username;

    private Integer pid;
    private String pname;
    private Double pprice;

    private Integer number;
}
