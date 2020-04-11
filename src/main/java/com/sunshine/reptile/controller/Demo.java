package com.sunshine.reptile.controller;

import com.sunshine.reptile.Reptile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年04月11日 17:54
 */
@RestController
public class Demo {

    @PostMapping(value = "/grouping")
    public String grouping(@RequestParam Map<String, Object> map) {
        return Reptile.fzcs(map);
    }
}
