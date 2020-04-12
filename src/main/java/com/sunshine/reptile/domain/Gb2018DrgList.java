package com.sunshine.reptile.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年04月12日 3:42
 */
@NoArgsConstructor
@Data
public class Gb2018DrgList {
    public String page;
    public List<Gb2018Drg> data;
}
