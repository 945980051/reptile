package com.sunshine.reptile;

import cn.wanghaomiao.seimi.core.Seimi;
import com.sunshine.reptile.utils.HttpClient;
import lombok.val;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.URL;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年03月28日 12:20
 */
@Component
public class Reptile {
    @PostConstruct
    public void cj() {
        Seimi s = new Seimi();
        s.start("basic");
    }
}
