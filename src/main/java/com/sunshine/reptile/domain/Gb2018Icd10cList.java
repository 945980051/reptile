package com.sunshine.reptile.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年04月12日 18:35
 */
@NoArgsConstructor
@Data
public class Gb2018Icd10cList {

    public List<Gb2018Icd10c> data;


}
