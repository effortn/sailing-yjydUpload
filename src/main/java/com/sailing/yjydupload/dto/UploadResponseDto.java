package com.sailing.yjydupload.dto;

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
    private List<ResponseStatusDto> ResponseStatusList;

}
