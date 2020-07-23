package com.xuwx.pump.dto;

/**
 *@ClassName MinioUploadDto
 *@Description 文件上传返回结果
 *@Author wx.Xu
 *Date 2020/7/15 17:20
 *Version 1.0
 */
public class MinioUploadDto {
    private String url;
    private String name;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
