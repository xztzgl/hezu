package com.tangquan.service.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.tangquan.service.PluginFileAliyunService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created by gangsun on 2017/4/26.
 */

@Service
public class PluginFileAliyunAliyunServiceImpl implements PluginFileAliyunService {

    @Value("${aliyun.endpoint}")
    private String endpoint;

    @Value("${aliyun.key}")
    private String accessKeyId;

    @Value("${aliyun.secret}")
    private String accessKeySecret;

    @Value("${aliyun.bucketName}")
    private String bucketName;

    @Value("${aliyun.domain}")
    private String domain;


    /**
     * 上传文件到OSS
     *
     * @param fileBytes
     * @param pak       文件夹
     * @param fileName  文件名
     * @return
     */
    public String uploadFile(byte[] fileBytes, String pak, String fileName) {
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient("http://" + endpoint, accessKeyId, accessKeySecret);

        ossClient.putObject(bucketName, pak + "/" + fileName, new ByteArrayInputStream(fileBytes));
        // 关闭client
        ossClient.shutdown();

        return "http://" + domain + "/" + pak + "/" + fileName;
    }

    @Override
    public String uploadFile(String fileName, String pak, MultipartFile multipartFile) throws IOException {

        ObjectMetadata metaData = new ObjectMetadata();
        metaData.setContentDisposition("inline;fileName=" + multipartFile.getOriginalFilename());
        metaData.setContentType(multipartFile.getContentType());
        metaData.setContentLength(multipartFile.getSize());

        OSSClient ossClient = new OSSClient("http://" + endpoint, accessKeyId, accessKeySecret);

        ossClient.putObject(bucketName, pak + "/" + fileName, multipartFile.getInputStream(), metaData);
        // 关闭client
        ossClient.shutdown();

        return "http://" + domain + "/" + pak + "/" + fileName;
    }

//    public String uploadImage(String fileName, String pak, MultipartFile multipartFile) {
//        ObjectMetadata metaData = new ObjectMetadata();
//        metaData.setContentDisposition("inline;fileName=" + multipartFile.getOriginalFilename());
//        metaData.setContentType(multipartFile.getContentType());
//        metaData.setContentLength(multipartFile.getSize());
//
//        CommonsMultipartFile cf = (CommonsMultipartFile) multipartFile;
//        DiskFileItem fi = (DiskFileItem) cf.getFileItem();
//        File file = fi.getStoreLocation();
//
//        //图片压缩
//        ImgCompressUtil.ratioCompressImg(file,fileName, 600);
//        OSSClient ossClient = new OSSClient("http://" + endpoint, accessKeyId, accessKeySecret);
//
//        ossClient.putObject(bucketName, pak + "/" + fileName, file, metaData);
//        // 关闭client
//        ossClient.shutdown();
//
//        return "http://" + domain + "/" + pak + "/" + fileName;
//    }
}
