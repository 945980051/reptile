package com.sunshine.reptile.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年04月12日 4:22
 */
@NoArgsConstructor
@Data
@Entity
public class Gb2018DrgModelInfo implements Serializable {
    /**
     * model_field : []
     * model_info : 无条件限制
     */
    @Id
    @GeneratedValue
    private Integer id;
    private String model_info;
    @ElementCollection
    private List<String> model_field;
}