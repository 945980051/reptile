package com.sunshine.reptile.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.List;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年04月12日 3:20
 */
@NoArgsConstructor
@Data
@Entity
public  class Gb2018AdrgTabRule implements Serializable {
    @Id
    @ManyToOne(targetEntity = Gb2018Adrg.class)
    private Integer id;
    public String name;
    @ElementCollection
    public List<String> code;
}