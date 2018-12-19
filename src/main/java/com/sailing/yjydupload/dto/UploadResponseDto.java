package com.sailing.yjydupload.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * 数据上传接口返回数据DTO
 * create by en
 * at 2018/12/12 10:49
 **/
@Data
public class UploadResponseDto {

    /**
     * 返回信息集合
     */
    @JSONField(name = "ResponseStatusList")
    private List<ResponseStatusDto> ResponseStatusList;

}
