package com.sunshine.reptile.entity;

import lombok.Data;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年04月18日 12:36
 */
@Data
public class Order {
   private String code;
   private int id;
   private String mdc;
   private String name;
   private int order_at;
}
