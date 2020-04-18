package com.sunshine.reptile.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年04月06日 4:15
 */
@NoArgsConstructor
@Data
public class DrgList {

    /**
     * data : [{"adrg":"RU1","age":[],"code":"RU10","drg_models":{"model_field":[],"model_info":"无条件限制"},"id":715,"in_days":[],"info_rule":[{"code":"RU10","name":"编码"},{"code":"恶性增生性疾病的支持性治疗（30天≤住院时间＜61天）","name":"名称"},{"code":"无条件限制","name":"计算模型"}],"inserted_at":"2019-06-15T23:50:31Z","is_cc":null,"is_mcc":null,"logs":[],"mdc":"R","model":"drg_m07","model_info":{"model_field":[],"model_info":"无条件限制"},"name":"恶性增生性疾病的支持性治疗（30天≤住院时间＜61天）","order_at":736,"out_result":[],"query_table":"rule_bj2019_drg","tab_rule":[],"updated_at":"2019-12-25T02:09:52Z","versions":[{"code":"RU10","model_info":"无条件限制","name":"恶性增生性疾病的支持性治疗（30天≤住院时间＜61天）","version":"整合开发版(2019)"},{"code":"RU10","model_info":"无条件限制","name":"恶性增生性疾病的支持性治疗（30天≤住院时间＜61天）","version":"公立医院考核版(2019)"},{"code":"RU10","model_info":"无条件限制","name":"恶性增生性疾病的支持性治疗（30天≤住院时间＜61天）","version":"医院绩效考核版(2019)"},{"code":"RU10","model_info":"无条件限制","name":"恶性增生性疾病的支持性治疗（30天≤住院时间＜61天）","version":"CN-B-DRG(2018)"},{"code":"RU10","model_info":"无条件限制","name":"恶性增生性疾病的支持性治疗（30天≤住院时间＜61天）","version":"CN-T-DRG(2018)"},{"code":"RU10","model_info":"无条件限制","name":"恶性增生性疾病的支持性治疗（30天≤住院时间＜61天）","version":"BJ-DRG(2017)"},{"code":"RU10","model_info":"无条件限制","name":"恶性增生性疾病的支持性治疗（30天≤住院时间＜61天）","version":"BJ-DRG(2016)"},{"code":"RU10","model_info":"无条件限制","name":"恶性增生性疾病的支持性治疗（30天≤住院时间＜61天）","version":"BJ-DRG(2015)"},{"code":"RU10","model_info":"无条件限制","name":"恶性增生性疾病的支持性治疗（30天≤住院时间＜61天）","version":"CC-DRG(2015)"}]},{"adrg":"RU1","age":[],"code":"RU12","drg_models":{"model_field":["in_days"],"model_info":"住院时间满足"},"id":716,"in_days":[7,29],"info_rule":[{"code":"RU12","name":"编码"},{"code":"恶性增生性疾病的支持性治疗（7天≤住院时间＜30天）","name":"名称"},{"code":"住院时间满足","name":"计算模型"},{"code":"7天≤住院天数≤29天","name":"住院天数"}],"inserted_at":"2019-06-15T23:50:31Z","is_cc":false,"is_mcc":true,"logs":[],"mdc":"R","model":"drg_m10","model_info":{"model_field":["in_days"],"model_info":"住院时间满足"},"name":"恶性增生性疾病的支持性治疗（7天≤住院时间＜30天）","order_at":735,"out_result":[],"query_table":"rule_bj2019_drg","tab_rule":[],"updated_at":"2019-12-25T02:09:52Z","versions":[{"code":"RU12","model_info":"住院时间满足","name":"恶性增生性疾病的支持性治疗（7天≤住院时间＜30天）","version":"整合开发版(2019)"},{"code":"RU12","model_info":"住院时间满足","name":"恶性增生性疾病的支持性治疗（7天≤住院时间＜30天）","version":"公立医院考核版(2019)"},{"code":"RU12","model_info":"住院时间满足","name":"恶性增生性疾病的支持性治疗（7天≤住院时间＜30天）","version":"医院绩效考核版(2019)"},{"code":"RU12","model_info":"住院时间满足","name":"恶性增生性疾病的支持性治疗（7天≤住院时间＜30天）","version":"CN-B-DRG(2018)"},{"code":"RU12","model_info":"住院时间满足","name":"恶性增生性疾病的支持性治疗（7天≤住院时间＜30天）","version":"CN-T-DRG(2018)"},{"code":"RU12","model_info":"住院时间满足","name":"恶性增生性疾病的支持性治疗（7天≤住院时间＜30天）","version":"BJ-DRG(2017)"},{"code":"RU12","model_info":"住院时间满足","name":"恶性增生性疾病的支持性治疗（7天≤住院时间＜30天）","version":"BJ-DRG(2016)"},{"code":"RU12","model_info":"住院时间满足","name":"恶性增生性疾病的支持性治疗（7天≤住院时间＜30天）","version":"BJ-DRG(2015)"},{"code":"RU12","model_info":"住院时间满足","name":"恶性增生性疾病的支持性治疗（7天≤住院时间＜30天）","version":"CC-DRG(2015)"}]},{"adrg":"RU1","age":[],"code":"RU14","drg_models":{"model_field":["in_days"],"model_info":"住院时间满足"},"id":717,"in_days":[0,7],"info_rule":[{"code":"RU14","name":"编码"},{"code":"恶性增生性疾病的支持性治疗（住院时间＜7天）","name":"名称"},{"code":"住院时间满足","name":"计算模型"},{"code":"0天≤住院天数≤7天","name":"住院天数"}],"inserted_at":"2019-06-15T23:50:31Z","is_cc":true,"is_mcc":false,"logs":[],"mdc":"R","model":"drg_m10","model_info":{"model_field":["in_days"],"model_info":"住院时间满足"},"name":"恶性增生性疾病的支持性治疗（住院时间＜7天）","order_at":734,"out_result":[],"query_table":"rule_bj2019_drg","tab_rule":[],"updated_at":"2019-12-25T02:09:52Z","versions":[{"code":"RU14","model_info":"住院时间满足","name":"恶性增生性疾病的支持性治疗（住院时间＜7天）","version":"整合开发版(2019)"},{"code":"RU14","model_info":"住院时间满足","name":"恶性增生性疾病的支持性治疗（住院时间＜7天）","version":"公立医院考核版(2019)"},{"code":"RU14","model_info":"住院时间满足","name":"恶性增生性疾病的支持性治疗（住院时间＜7天）","version":"医院绩效考核版(2019)"},{"code":"RU14","model_info":"住院时间满足","name":"恶性增生性疾病的支持性治疗（住院时间＜7天）","version":"CN-B-DRG(2018)"},{"code":"RU14","model_info":"住院时间满足","name":"恶性增生性疾病的支持性治疗（住院时间＜7天）","version":"CN-T-DRG(2018)"},{"code":"RU14","model_info":"住院时间满足","name":"恶性增生性疾病的支持性治疗（住院时间＜7天）","version":"BJ-DRG(2017)"},{"code":"RU14","model_info":"住院时间满足","name":"恶性增生性疾病的支持性治疗（住院时间＜7天）","version":"BJ-DRG(2016)"},{"code":"RU14","model_info":"住院时间满足","name":"恶性增生性疾病的支持性治疗（住院时间＜7天）","version":"BJ-DRG(2015)"},{"code":"RU14","model_info":"住院时间满足","name":"恶性增生性疾病的支持性治疗（住院时间＜7天）","version":"CC-DRG(2015)"}]}]
     * page : 0
     */

    private String page;
    private List<Drg> data;

}
