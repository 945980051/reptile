package com.sunshine.reptile.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年04月14日 12:33
 */
@NoArgsConstructor
@Data
public class DrugClassification {

    /**
     * records : 86154
     * total : 1724
     * rows : [{"approvalcode":"国药准字H44024254","unit":"盒","materialname":"铝塑包装","goodscode":"XA01ABD075A002010100594","registeredmedicinemodel":"片剂(含片)","registeredproductname":"地喹氯铵含片","goodsname":"无","registeredoutlook":"0.25mg","factor":12,"companynamesc":"珠海同源药业有限公司","goodsstandardcode":"86900594000342","minunit":"片"},{"approvalcode":"国药准字H44024254","unit":"盒","materialname":"铝塑包装","goodscode":"XA01ABD075A002010200594","registeredmedicinemodel":"片剂(含片)","registeredproductname":"地喹氯铵含片","goodsname":"无","registeredoutlook":"0.25mg","factor":24,"companynamesc":"珠海同源药业有限公司","goodsstandardcode":"86900594000342","minunit":"片"},{"approvalcode":"国药准字H44024254","unit":"盒","materialname":"铝塑包装","goodscode":"XA01ABD075A002020100594","registeredmedicinemodel":"片剂(含片)","registeredproductname":"地喹氯铵含片","goodsname":"无","registeredoutlook":"0.25mg","factor":6,"companynamesc":"珠海同源药业有限公司","goodsstandardcode":"86900594000335","minunit":"片"},{"approvalcode":"国药准字H44024254","unit":"盒","materialname":"铝塑包装","goodscode":"XA01ABD075A002020200594","registeredmedicinemodel":"片剂(含片)","registeredproductname":"地喹氯铵含片","goodsname":"无","registeredoutlook":"0.25mg","factor":12,"companynamesc":"珠海同源药业有限公司","goodsstandardcode":"86900594000335","minunit":"片"},{"approvalcode":"国药准字H44024254","unit":"盒","materialname":"无","goodscode":"XA01ABD075A002020300594","registeredmedicinemodel":"片剂(含片)","registeredproductname":"地喹氯铵含片","goodsname":"无","registeredoutlook":"0.25mg","factor":24,"companynamesc":"珠海同源药业有限公司","goodsstandardcode":"86900594000335","minunit":"片"},{"approvalcode":"国药准字H44025156","unit":"瓶","materialname":"塑料瓶","goodscode":"XA01ABD108A002010100372","registeredmedicinemodel":"片剂(含片)","registeredproductname":"碘化铵含片","goodsname":"无","registeredoutlook":"复方","factor":100,"companynamesc":"广州白云山光华制药股份有限公司","goodsstandardcode":"86900372002735","minunit":"片"},{"approvalcode":"国药准字H15021343","unit":"盒","materialname":"铝塑泡罩","goodscode":"XA01ABD108A002010103870","registeredmedicinemodel":"片剂(含片)","registeredproductname":"碘化铵含片","goodsname":"无","registeredoutlook":"碘化铵1.5mg、薄荷油4.4mg","factor":24,"companynamesc":"赤峰维康生化制药有限公司","goodsstandardcode":"86903870000555","minunit":"片"},{"approvalcode":"国药准字H20055147","unit":"盒","materialname":"复合软膏管","goodscode":"XA01ABD138F002010104557","registeredmedicinemodel":"乳膏剂","registeredproductname":"丁硼乳膏","goodsname":"雅皓","registeredoutlook":"每支装65克","factor":1,"companynamesc":"宁波立华制药有限公司","goodsstandardcode":"86904557000271","minunit":"支"},{"approvalcode":"国药准字H33022343","unit":"盒","materialname":"复合管包装","goodscode":"XA01ABD138F002010104745","registeredmedicinemodel":"软膏剂","registeredproductname":"丁硼乳膏","goodsname":"无","registeredoutlook":"65克","factor":1,"companynamesc":"浙江一新制药股份有限公司","goodsstandardcode":"86904745000922","minunit":"支"},{"approvalcode":"国药准字H20055147","unit":"盒","materialname":"复合软膏管","goodscode":"XA01ABD138F002020104557","registeredmedicinemodel":"乳膏剂","registeredproductname":"丁硼乳膏","goodsname":"雅皓","registeredoutlook":"每支装36克","factor":1,"companynamesc":"宁波立华制药有限公司","goodsstandardcode":"86904557000264","minunit":"支"},{"approvalcode":"国药准字H33022343","unit":"盒","materialname":"复合管包装","goodscode":"XA01ABD138F002020104745","registeredmedicinemodel":"软膏剂","registeredproductname":"丁硼乳膏","goodsname":"无","registeredoutlook":"65克","factor":1,"companynamesc":"浙江一新制药股份有限公司","goodsstandardcode":"86904745000922","minunit":"支"},{"approvalcode":"国药准字H20055147","unit":"盒","materialname":"复合软膏管","goodscode":"XA01ABD138F002030104557","registeredmedicinemodel":"乳膏剂","registeredproductname":"丁硼乳膏","goodsname":"雅皓","registeredoutlook":"每支装65克","factor":1,"companynamesc":"宁波立华制药有限公司","goodsstandardcode":"86904557000271","minunit":"支"},{"approvalcode":"国药准字H44024277","unit":"盒","materialname":"铝塑板","goodscode":"XA01ABD152A002010100322","registeredmedicinemodel":"片剂(含片)","registeredproductname":"度米芬含片","goodsname":"无","registeredoutlook":"0.5mg","factor":10,"companynamesc":"三才石岐制药股份有限公司","goodsstandardcode":"86900322001870","minunit":"片"},{"approvalcode":"国药准字H44024291","unit":"盒","materialname":"铝塑","goodscode":"XA01ABD152A002010100372","registeredmedicinemodel":"片剂(含片)","registeredproductname":"度米芬含片","goodsname":"无","registeredoutlook":"0.5mg","factor":20,"companynamesc":"广州白云山光华制药股份有限公司","goodsstandardcode":"86900372002698","minunit":"片"},{"approvalcode":"国药准字H42022612","unit":"瓶","materialname":"塑料瓶装","goodscode":"XA01ABD152A002010101871","registeredmedicinemodel":"片剂(含片)","registeredproductname":"度米芬含片","goodsname":"无","registeredoutlook":"0.5mg","factor":100,"companynamesc":"湖北科伦药业有限公司","goodsstandardcode":"86901871000468","minunit":"片"},{"approvalcode":"国药准字H20067091","unit":"盒","materialname":"药用PVC硬片、铝箔包装","goodscode":"XA01ABD152A002010102870","registeredmedicinemodel":"片剂","registeredproductname":"度米芬含片","goodsname":"无","registeredoutlook":"0.5mg","factor":24,"companynamesc":"山西澳迩药业有限公司","goodsstandardcode":"86902870000367","minunit":"片"},{"approvalcode":"国药准字H41024562","unit":"瓶","materialname":"玻璃瓶","goodscode":"XA01ABD152A002010103065","registeredmedicinemodel":"片剂","registeredproductname":"度米芬含片","goodsname":"无","registeredoutlook":"0.5mg","factor":100,"companynamesc":"河南省百泉制药有限公司","goodsstandardcode":"86903065000209","minunit":"片"},{"approvalcode":"国药准字H41024734","unit":"盒","materialname":"药品包装用铝箔、聚氯乙烯固体药用硬片","goodscode":"XA01ABD152A002010103087","registeredmedicinemodel":"片剂","registeredproductname":"度米芬含片","goodsname":"无","registeredoutlook":"0.5mg","factor":12,"companynamesc":"百正药业股份有限公司","goodsstandardcode":"86903087000331","minunit":"片"},{"approvalcode":"国药准字H41024961","unit":"盒","materialname":"铝箔装","goodscode":"XA01ABD152A002010103098","registeredmedicinemodel":"片剂","registeredproductname":"度米芬含片","goodsname":"无","registeredoutlook":"0.5mg","factor":18,"companynamesc":"天方药业有限公司","goodsstandardcode":"86903098001730","minunit":"片"},{"approvalcode":"国药准字H41024643","unit":"盒","materialname":"药品包装用复合膜、袋","goodscode":"XA01ABD152A002010103139","registeredmedicinemodel":"片剂","registeredproductname":"度米芬含片","goodsname":"无","registeredoutlook":"0.5mg","factor":12,"companynamesc":"开封制药(集团)有限公司","goodsstandardcode":"86903139002214","minunit":"片"},{"approvalcode":"国药准字H37023692","unit":"盒","materialname":"铝塑泡罩","goodscode":"XA01ABD152A002010104152","registeredmedicinemodel":"片剂","registeredproductname":"度米芬含片","goodsname":"无","registeredoutlook":"0.5mg","factor":12,"companynamesc":"瑞阳制药有限公司","goodsstandardcode":"86904152002632","minunit":"片"},{"approvalcode":"国药准字H53021773","unit":"盒","materialname":"铝塑包装","goodscode":"XA01ABD152A002010105725","registeredmedicinemodel":"片剂","registeredproductname":"度米芬含片","goodsname":"无","registeredoutlook":"0.5mg","factor":36,"companynamesc":"云南望子隆药业有限公司","goodsstandardcode":"86905725000086","minunit":"片"},{"approvalcode":"国药准字H44024277","unit":"盒","materialname":"铝塑板","goodscode":"XA01ABD152A002010200322","registeredmedicinemodel":"片剂(含片)","registeredproductname":"度米芬含片","goodsname":"无","registeredoutlook":"0.5mg","factor":24,"companynamesc":"三才石岐制药股份有限公司","goodsstandardcode":"86900322001870","minunit":"片"},{"approvalcode":"国药准字H44024291","unit":"盒","materialname":"塑料瓶","goodscode":"XA01ABD152A002010200372","registeredmedicinemodel":"片剂(含片)","registeredproductname":"度米芬含片","goodsname":"无","registeredoutlook":"0.5mg","factor":100,"companynamesc":"广州白云山光华制药股份有限公司","goodsstandardcode":"86900372002698","minunit":"片"},{"approvalcode":"国药准字H41024734","unit":"瓶","materialname":"口服固体药用高密度聚乙烯瓶","goodscode":"XA01ABD152A002010203087","registeredmedicinemodel":"片剂","registeredproductname":"度米芬含片","goodsname":"无","registeredoutlook":"0.5mg","factor":100,"companynamesc":"百正药业股份有限公司","goodsstandardcode":"86903087000331","minunit":"片"},{"approvalcode":"国药准字H37023692","unit":"瓶","materialname":"口服固体药用高密度聚乙烯瓶","goodscode":"XA01ABD152A002010204152","registeredmedicinemodel":"片剂","registeredproductname":"度米芬含片","goodsname":"无","registeredoutlook":"0.5mg","factor":100,"companynamesc":"瑞阳制药有限公司","goodsstandardcode":"86904152002632","minunit":"片"},{"approvalcode":"国药准字H44024277","unit":"瓶","materialname":"塑料瓶","goodscode":"XA01ABD152A002010300322","registeredmedicinemodel":"片剂(含片)","registeredproductname":"度米芬含片","goodsname":"无","registeredoutlook":"0.5mg","factor":1000,"companynamesc":"三才石岐制药股份有限公司","goodsstandardcode":"86900322001870","minunit":"片"},{"approvalcode":"国药准字H37023692","unit":"瓶","materialname":"口服固体药用高密度聚乙烯瓶","goodscode":"XA01ABD152A002010304152","registeredmedicinemodel":"片剂","registeredproductname":"度米芬含片","goodsname":"无","registeredoutlook":"0.5mg","factor":1000,"companynamesc":"瑞阳制药有限公司","goodsstandardcode":"86904152002632","minunit":"片"},{"approvalcode":"国药准字H20064218","unit":"盒","materialname":"塑料瓶装","goodscode":"XA01ABF391S006010101191","registeredmedicinemodel":"溶液剂(外用)","registeredproductname":"复方氯己定含漱液","goodsname":"无","registeredoutlook":"每毫升含葡萄糖酸氯己定1.2mg和甲硝唑0.2mg","factor":1,"companynamesc":"锦州本天药业有限公司","goodsstandardcode":"86901191000117","minunit":"瓶"},{"approvalcode":"国药准字H20058018","unit":"盒","materialname":"塑料瓶装","goodscode":"XA01ABF391S006010101427","registeredmedicinemodel":"含漱液","registeredproductname":"复方氯己定含漱液","goodsname":"无","registeredoutlook":"每毫升含葡萄酸氯己定1.2mg和甲硝唑0.2mg","factor":1,"companynamesc":"江苏晨牌邦德药业有限公司","goodsstandardcode":"86901427000867","minunit":"瓶"},{"approvalcode":"国药准字H32026694","unit":"瓶","materialname":"塑料瓶","goodscode":"XA01ABF391S006010101486","registeredmedicinemodel":"溶液剂(外用)","registeredproductname":"复方氯己定含漱液","goodsname":"无","registeredoutlook":"150ml","factor":1,"companynamesc":"江苏知原药业有限公司","goodsstandardcode":"86901486000082","minunit":"瓶"},{"approvalcode":"国药准字H20064218","unit":"盒","materialname":"塑料瓶装","goodscode":"XA01ABF391S006020101191","registeredmedicinemodel":"溶液剂(外用)","registeredproductname":"复方氯己定含漱液","goodsname":"无","registeredoutlook":"每毫升含葡萄糖酸氯己定1.2mg和甲硝唑0.2mg","factor":10,"companynamesc":"锦州本天药业有限公司","goodsstandardcode":"86901191000117","minunit":"支"},{"approvalcode":"国药准字H20058018","unit":"盒","materialname":"塑料瓶","goodscode":"XA01ABF391S006020101427","registeredmedicinemodel":"含漱液","registeredproductname":"复方氯己定含漱液","goodsname":"无","registeredoutlook":"每毫升含葡萄酸氯己定1.2mg和甲硝唑0.2mg","factor":1,"companynamesc":"江苏晨牌邦德药业有限公司","goodsstandardcode":"86901427000867","minunit":"瓶"},{"approvalcode":"国药准字H32026694","unit":"瓶","materialname":"塑料瓶","goodscode":"XA01ABF391S006020101486","registeredmedicinemodel":"溶液剂(外用)","registeredproductname":"复方氯己定含漱液","goodsname":"无","registeredoutlook":"150ml","factor":1,"companynamesc":"江苏知原药业有限公司","goodsstandardcode":"86901486000082","minunit":"瓶"},{"approvalcode":"国药准字H20064218","unit":"盒","materialname":"塑料瓶装","goodscode":"XA01ABF391S006020201191","registeredmedicinemodel":"溶液剂(外用)","registeredproductname":"复方氯己定含漱液","goodsname":"无","registeredoutlook":"每毫升含葡萄糖酸氯己定1.2mg和甲硝唑0.2mg","factor":15,"companynamesc":"锦州本天药业有限公司","goodsstandardcode":"86901191000117","minunit":"支"},{"approvalcode":"国药准字H20058018","unit":"盒","materialname":"塑料瓶","goodscode":"XA01ABF391S006020201427","registeredmedicinemodel":"含漱液","registeredproductname":"复方氯己定含漱液","goodsname":"无","registeredoutlook":"每毫升含葡萄酸氯己定1.2mg和甲硝唑0.2mg","factor":2,"companynamesc":"江苏晨牌邦德药业有限公司","goodsstandardcode":"86901427000867","minunit":"瓶"},{"approvalcode":"国药准字H20064218","unit":"盒","materialname":"塑料瓶装","goodscode":"XA01ABF391S006020301191","registeredmedicinemodel":"溶液剂(外用)","registeredproductname":"复方氯己定含漱液","goodsname":"无","registeredoutlook":"每毫升含葡萄糖酸氯己定1.2mg和甲硝唑0.2mg","factor":20,"companynamesc":"锦州本天药业有限公司","goodsstandardcode":"86901191000117","minunit":"支"},{"approvalcode":"国药准字H20064218","unit":"盒","materialname":"塑料瓶装","goodscode":"XA01ABF391S006020401191","registeredmedicinemodel":"溶液剂(外用)","registeredproductname":"复方氯己定含漱液","goodsname":"无","registeredoutlook":"每毫升含葡萄糖酸氯己定1.2mg和甲硝唑0.2mg","factor":30,"companynamesc":"锦州本天药业有限公司","goodsstandardcode":"86901191000117","minunit":"支"},{"approvalcode":"国药准字H20064218","unit":"盒","materialname":"塑料瓶装","goodscode":"XA01ABF391S006030101191","registeredmedicinemodel":"溶液剂(外用)","registeredproductname":"复方氯己定含漱液","goodsname":"无","registeredoutlook":"每毫升含葡萄糖酸氯己定1.2mg和甲硝唑0.2mg","factor":1,"companynamesc":"锦州本天药业有限公司","goodsstandardcode":"86901191000117","minunit":"瓶"},{"approvalcode":"国药准字H20058018","unit":"盒","materialname":"塑料瓶","goodscode":"XA01ABF391S006030101427","registeredmedicinemodel":"含漱液","registeredproductname":"复方氯己定含漱液","goodsname":"无","registeredoutlook":"每毫升含葡萄酸氯己定1.2mg和甲硝唑0.2mg","factor":1,"companynamesc":"江苏晨牌邦德药业有限公司","goodsstandardcode":"86901427000867","minunit":"瓶"},{"approvalcode":"国药准字H20064218","unit":"盒","materialname":"塑料瓶装","goodscode":"XA01ABF391S006040101191","registeredmedicinemodel":"溶液剂(外用)","registeredproductname":"复方氯己定含漱液","goodsname":"无","registeredoutlook":"每毫升含葡萄糖酸氯己定1.2mg和甲硝唑0.2mg","factor":1,"companynamesc":"锦州本天药业有限公司","goodsstandardcode":"86901191000117","minunit":"瓶"},{"approvalcode":"国药准字H20058018","unit":"盒","materialname":"塑料瓶","goodscode":"XA01ABF391S006040101427","registeredmedicinemodel":"含漱液","registeredproductname":"复方氯己定含漱液","goodsname":"无","registeredoutlook":"每毫升含葡萄酸氯己定1.2mg和甲硝唑0.2mg","factor":1,"companynamesc":"江苏晨牌邦德药业有限公司","goodsstandardcode":"86901427000867","minunit":"瓶"},{"approvalcode":"国药准字H20064218","unit":"盒","materialname":"塑料瓶装","goodscode":"XA01ABF391S006050101191","registeredmedicinemodel":"溶液剂(外用)","registeredproductname":"复方氯己定含漱液","goodsname":"无","registeredoutlook":"每毫升含葡萄糖酸氯己定1.2mg和甲硝唑0.2mg","factor":1,"companynamesc":"锦州本天药业有限公司","goodsstandardcode":"86901191000117","minunit":"瓶"},{"productcode":"1","materialname":"外用液体药用高密度聚乙烯瓶","goodscode":"XA01ABF422S006010100830","goodsname":"无","registeredoutlook":"复方","companynamesc":"上海运佳黄浦制药有限公司","goodsstandardcode":"86900830000365","minunit":"瓶","productmedicinemodel":"外用液体剂","approvalcode":"国药准字H31022772","unit":"盒","registeredmedicinemodel":"溶液剂","registeredproductname":"复方硼砂含漱液","productname":"复方硼砂","factor":1,"productinsurancetype":"甲"},{"approvalcode":"国药准字H35021423","unit":"盒","materialname":"复合膜","goodscode":"XA01ABF440M001010104828","registeredmedicinemodel":"膜剂","registeredproductname":"复方庆大霉素膜","goodsname":"无","registeredoutlook":"每片(10cm2)含硫酸庆大霉素1000单位","factor":4,"companynamesc":"福州海王金象中药制药有限公司","goodsstandardcode":"86904828001372","minunit":"袋"},{"approvalcode":"国药准字H35021422","unit":"盒","materialname":"铝塑复合膜","goodscode":"XA01ABF440M001020104828","registeredmedicinemodel":"膜剂","registeredproductname":"复方庆大霉素膜","goodsname":"无","registeredoutlook":"每片(10cm2)含硫酸庆大霉素500单位","factor":2,"companynamesc":"福州海王金象中药制药有限公司","goodsstandardcode":"86904828001365","minunit":"片"},{"approvalcode":"国药准字H35021422","unit":"盒","materialname":"铝塑复合膜","goodscode":"XA01ABF440M001020204828","registeredmedicinemodel":"膜剂","registeredproductname":"复方庆大霉素膜","goodsname":"无","registeredoutlook":"每片(10cm2)含硫酸庆大霉素500单位","factor":10,"companynamesc":"福州海王金象中药制药有限公司","goodsstandardcode":"86904828001365","minunit":"片"},{"approvalcode":"国药准字H20010492","unit":"盒","materialname":"无","goodscode":"XA01ABJ070A002010102699","registeredmedicinemodel":"片剂(含片)","registeredproductname":"甲硝唑含片","goodsname":"舒瑞特","registeredoutlook":"2.5mg","factor":24,"companynamesc":"华北制药股份有限公司","goodsstandardcode":"86902699000401","minunit":"片"},{"approvalcode":"国药准字H20010492","unit":"盒","materialname":"无","goodscode":"XA01ABJ070A002010202699","registeredmedicinemodel":"片剂(含片)","registeredproductname":"甲硝唑含片","goodsname":"舒瑞特","registeredoutlook":"2.5mg","factor":50,"companynamesc":"华北制药股份有限公司","goodsstandardcode":"86902699000401","minunit":"片"},{"approvalcode":"国药准字H20066651","unit":"盒","materialname":"药品包装用铝箔和聚氯乙烯固体药用硬片包装","goodscode":"XA01ABJ070A029010101984","registeredmedicinemodel":"片剂","registeredproductname":"甲硝唑口颊片","goodsname":"无","registeredoutlook":"3mg","factor":20,"companynamesc":"远大医药(中国)有限公司","goodsstandardcode":"86901984001543","minunit":"片"}]
     * page : 1
     * count : 50
     * firstResult : 0
     * maxResults : 50
     * success : false
     * result : null
     * conditions : {"versionId":"20200316","batchNumber":"20200316"}
     * msg : null
     * form : null
     * code : 0
     * operCount : 0
     * sord : asc
     * sidx : t.goods_code
     * orderby : t.goods_code asc
     */

    public int records;
    public int total;
    public int page;
    public int count;
    public int firstResult;
    public int maxResults;
    public boolean success;
    public String result;
    public ConditionsBean conditions;
    public String msg;
    public String form;
    public int code;
    public int operCount;
    public String sord;
    public String sidx;
    public String orderby;
    public List<RowsBean> rows;

    @NoArgsConstructor
    @Data
    public static class ConditionsBean {
        /**
         * versionId : 20200316
         * batchNumber : 20200316
         */

        public String versionId;
        public String batchNumber;
    }

    @NoArgsConstructor
    @Data
    public static class RowsBean {
        /**
         * approvalcode : 国药准字H44024254
         * unit : 盒
         * materialname : 铝塑包装
         * goodscode : XA01ABD075A002010100594
         * registeredmedicinemodel : 片剂(含片)
         * registeredproductname : 地喹氯铵含片
         * goodsname : 无
         * registeredoutlook : 0.25mg
         * factor : 12
         * companynamesc : 珠海同源药业有限公司
         * goodsstandardcode : 86900594000342
         * minunit : 片
         * productcode : 1
         * productmedicinemodel : 外用液体剂
         * productname : 复方硼砂
         * productinsurancetype : 甲
         */

        public String approvalcode;
        public String unit;
        public String materialname;
        public String goodscode;
        public String registeredmedicinemodel;
        public String registeredproductname;
        public String goodsname;
        public String registeredoutlook;
        public int factor;
        public String companynamesc;
        public String goodsstandardcode;
        public String minunit;
        public String productcode;
        public String productmedicinemodel;
        public String productname;
        public String productinsurancetype;
    }
}
