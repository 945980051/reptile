package com.sunshine.reptile.entity;

import lombok.Data;

import java.util.List;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年04月18日 12:36
 */
@Data
public class OrderList {
    private List<Order> data;
    private List<String> mdc_index;
}
