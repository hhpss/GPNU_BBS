package com.gpnu.bbs.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: PurcellHuang
 * @Date: 2019-07-24 19:43
 */
@RestController
@RequestMapping("/upload")
public class FileUploadController {
    @PostMapping("/img")
    public Map picUpload(@RequestParam("file") MultipartFile file){
        Map<String, Object> map = new HashMap<>();
        //获取文件名
        String fileName = file.getOriginalFilename();
        //获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //重新生成文件名
        fileName = UUID.randomUUID()+suffixName;
        String fileDir = "D:/Temp/upload/pic/";
        String filePath = fileDir + fileName;
        try{
            file.transferTo(new File(filePath));
            map.put("success",true);
            map.put("url","/upload/pic/"+fileName);

        }catch (Exception e){
            map.put("success",false);
            e.printStackTrace();
        }
        return map;
    }

}
