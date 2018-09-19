package com.tangquan.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by gangsun on 2017/4/26.
 */
public interface PluginFileAliyunService {
    /**
     * 字节数组上传
     *
     * @param fileBytes
     * @param pak       文件夹
     * @param fileName  文件名
     * @return
     */
    String uploadFile(byte[] fileBytes, String pak, String fileName);


    /**
     * 字节数组上传
     *
     * @param file     文件
     * @param pak      文件夹
     * @param fileName 文件名
     */
    String uploadFile(String fileName, String pak, MultipartFile file) throws IOException;


    /**
     * 上传图片，并进行等比压缩
     *
     * @param file     文件
     * @param pak      文件夹
     * @param fileName 文件名
     */
//    public String uploadImage(String fileName, String pak, MultipartFile file);
}
