package com.sunshine.reptile.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Entity;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import java.util.List;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年04月11日 23:49
 */
@NoArgsConstructor
@Data
public class Gb2018MdcList {

    public List<Gb2018Mdc> data;

    @Entity
    @NoArgsConstructor
    @Data
    public static class DataBean {

        public boolean is_qy;
        public String query_table;
        public String model;
        public String oper_type;
        public String model_info;
        public String name;
        public List<Integer> in_days;;
        @Id
        @GeneratedValue
        public Integer id;
        public List<Integer>       OutResult;
        public String              code;
        public String              No_DiseOperAdrg;
        public String              InsertedAt;
        public String              OperTypeAdrg;
        public String              mdc;
        public String                        UpdatedAt;
        public List<String>                 OperAdrgs;
        public List<String>                 gender;
        public List<String>                 logs;
        public List<String>                 z08;
        public List<String>                 z03;
        public List<String>                 PAge;
        public List<String>                 p01;
        public List<String>                 z07;
        public List<TabRuleBean>             TabRule;
        public List<String>              Icd10A;
        public List<String>              z06;
        public List<String>              z05;
        public List<String>              p03;
        public List<String>              y01;
        public List<String>              y02;
        public List<InfoRuleBean>           InfoRule;
        public List<String>                     z01;
        public List<String>                     b01;
        public List<Integer>                     PDays;
        public List<String>                     z02;
        public List<String>                     Icd9A;
        public List<String>                     z04;
        public List<String>                     DiagAdrgs;
        public List<String>                     p02;

        @NoArgsConstructor
        @Data
        public static class TabRuleBean {
            /**
             * code : []
             * name : 非QY小手术
             */

            public String name;
            public List<String> code;
        }


        @NoArgsConstructor
        @Data
        public static class InfoRuleBean {
            /**
             * code : MDCA
             * name : 编码
             */

            public String code;
            public String name;
        }
    }
}
