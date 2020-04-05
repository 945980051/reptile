package com.sunshine.reptile.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年04月06日 5:08
 */
@NoArgsConstructor
@Data
public class OrderBean {

    public List<DataBean> data;
    public List<String> mdc_index;

    @NoArgsConstructor
    @Data
    public static class DataBean {
        /**
         * code : AA19
         * id : 1
         * mdc : A
         * name : 心脏移植
         * order_at : 1
         */

        public String code;
        public int id;
        public String mdc;
        public String name;
        public int order_at;
    }
}
