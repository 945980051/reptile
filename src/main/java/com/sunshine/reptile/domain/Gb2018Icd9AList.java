package com.sunshine.reptile.domain;

import lombok.Data;

import java.util.List;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年04月12日 18:40
 */

@Data
public class Gb2018Icd9AList {

    private String page;
    private List<Gb2018Icd9A> data;

}
