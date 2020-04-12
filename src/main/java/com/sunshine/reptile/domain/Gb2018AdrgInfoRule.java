package com.sunshine.reptile.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年04月12日 3:20
 */
@NoArgsConstructor
@Data
@Entity
public  class Gb2018AdrgInfoRule implements Serializable {
    /**
     * code : BB1
     * name : 编码
     */
    @Id
    @ManyToOne(targetEntity = Gb2018Adrg.class)
    private Integer id;
    public String code;
    public String name;
}