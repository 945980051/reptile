package com.sunshine.reptile.entity;

import lombok.Data;

import java.util.List;

/**
 * @author : zhengwenyao
 * @Description: 病案资料资料数组
 * @date Date : 2020年04月14日 0:41
 */
@Data
public class MedicalRecordInformationList {
    private List<MedicalRecordInformation> data;
}
