package com.sunshine.reptile.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年04月12日 0:54
 */
@Entity
@Data
public class Gb2018MdcInfoRule implements Serializable {
    /**
     * code : MDCA
     * name : 编码
     */
    @Id
    @ManyToOne(targetEntity = Gb2018Mdc.class)
    private Integer id;
    private String code;
    private String name;
}