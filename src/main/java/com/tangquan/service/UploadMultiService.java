package com.tangquan.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by bojieshen on 2017/5/17 0017.
 */
@Service
public class UploadMultiService {

    private Logger log = LoggerFactory.getLogger(UploadMultiService.class);

    @Autowired
    private PluginFileAliyunService pluginFileService;

    /**
     * 上传多文件，多方式
     *
     * @param file             文件
     * @param type             存储类型 1-上传图片到阿里云 2-本地  3-上传文件到里云
     * @param pak              目录
     * @param request          request
     * @return
     */
    public String upload(MultipartFile file, String type, String pak, HttpServletRequest request) {
        if (StringUtils.isEmpty(pak)) {
            pak = "attached";
        }

        String result = null;
        if (!file.isEmpty()) {
            String fileName = UUID.randomUUID().toString().replaceAll("-", "") + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            log.info(fileName);
            try {
                result = this.uploadByType(file, type, pak, fileName, request);
            } catch (Exception e) {
                log.error(e.getLocalizedMessage());
            }
        }
        return result;
    }

    private String uploadByType(MultipartFile file, String type, String pak, String fileName, HttpServletRequest request) throws IOException {
        String path;
        switch (type) {
//            case "1"://上传图片到阿里云并对图片压缩
//                path = pluginFileService.uploadImage(fileName, pak, file);
//                break;
            case "2"://上传到本地服务器
                path = this.uploadLocal(file, pak, fileName, request);
                break;
            case "3"://上传文件到阿里云
                path = pluginFileService.uploadFile(fileName, pak, file);
                break;
            default:
                path = "";
                break;
        }
        return path;
    }

    private String uploadLocal(MultipartFile file, String pak, String fileName, HttpServletRequest request) throws IOException {
        String filePath = request.getServletContext().getRealPath("/") + "attached" + File.separator + pak + File.separator;
        File uploadFile = new File(filePath + fileName);
        uploadFile.mkdirs();
        file.transferTo(uploadFile);
        return getAbsoluteResourceUrl(request, "/attached/" + pak + "/" + fileName);
    }

    private String getAbsoluteResourceUrl(HttpServletRequest request, String url) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + (request.getContextPath() + url).replaceAll("//", "/");
    }
}
