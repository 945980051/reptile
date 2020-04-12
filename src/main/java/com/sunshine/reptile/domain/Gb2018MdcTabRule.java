package com.sunshine.reptile.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年04月12日 0:53
 */
@Entity
@Data
public class Gb2018MdcTabRule implements Serializable {
    /**
     * code : []
     * name : 非QY小手术
     */
    @Id
    @ManyToOne(targetEntity = Gb2018Mdc.class)
    private Integer id;
    private String name;
    @ElementCollection
    private List<String> code;
}
