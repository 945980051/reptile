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
 * @date Date : 2020年04月12日 4:23
 */

@NoArgsConstructor
@Data
@Entity
public class Gb2018DrgInfoRule implements Serializable {
    /**
     * code : AA19
     * name : 编码
     */
    @Id
    @ManyToOne(targetEntity = Gb2018Drg.class)
    private Integer id;
    private String code;
    private String name;
}