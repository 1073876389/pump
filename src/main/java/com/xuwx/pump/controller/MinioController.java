package com.xuwx.pump.controller;

import com.xuwx.pump.api.CommonResult;
import com.xuwx.pump.dto.MinioUploadDto;
import io.minio.MinioClient;
import io.minio.policy.PolicyType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *@ClassName MinioController
 *@Description Minio 上传 删除 文件
 *@Author wx.Xu
 *Date 2020/7/15 16:20
 *Version 1.0
 */
@Api(tags = "MinioController",description = "Minio对象存储管理")
@Controller
@RequestMapping("/minio")
public class MinioController {
    @Value("${minio.endpoint}")
    private String ENDPOINT;

    @Value("${minio.bucketName}")
    private String BUCKET_NAME;

    @Value("${minio.accessKey}")
    private String ACCESS_KEY;

    @Value("${minio.secretKey}")
    private String SECRET_KEY;

    @ApiOperation("文件上传")
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult upload(@RequestParam("file")MultipartFile file){
     try {
        //创建一个minio的java 客户端
        MinioClient minioClient = new MinioClient(ENDPOINT,ACCESS_KEY,SECRET_KEY);
        //判断桶是否存在
        boolean exists = minioClient.bucketExists(BUCKET_NAME);
        if (exists){
        System.out.println("存储桶已经存在了!");
        }else {
        //创建存储桶 设置只读权限
        minioClient.makeBucket(BUCKET_NAME);
        minioClient.setBucketPolicy(BUCKET_NAME,"*.*",PolicyType.READ_ONLY);
        }

        //设置存储参数 (时间 以及 文件名)
        String filename = file.getOriginalFilename();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        //设置存储对象名称
        String objectName = sdf.format(new Date()) + "/" + filename;
        //文件上我们的存储桶里面
        minioClient.putObject(BUCKET_NAME,objectName,file.getInputStream(),file.getContentType());
        System.out.println("文件上传成功");
         MinioUploadDto minioUploadDto = new MinioUploadDto();
         minioUploadDto.setName(filename);
         minioUploadDto.setUrl(ENDPOINT + "/" + BUCKET_NAME + "/" + objectName);
         return CommonResult.success(minioUploadDto);
     }catch (Exception e){
         System.out.println("文件上传发送错误 :"+e.getMessage());
     }
     return CommonResult.failed();
    }


    @ApiOperation("文件删除")
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@RequestParam("objectName")String objectName){
        try {
            //创建一个minio的java 客户端
            MinioClient minioClient = new MinioClient(ENDPOINT,ACCESS_KEY,SECRET_KEY);
            minioClient.removeObject(BUCKET_NAME,objectName);
            System.out.println("文件删除成功!");
            return CommonResult.success(null);
        }catch (Exception e){
            System.out.println("文件删除错误 :"+e.getMessage());
        }
        return CommonResult.failed();
    }
}
