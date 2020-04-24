package com.sunshine.reptile.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年04月22日 13:37
 */

@NoArgsConstructor
@Data
public class Gb2018Icd9CList {

    public String page;
    public List<Gb2018Icd9C> data;
}
