package com.haoqiang.vpn.api;

import com.haoqiang.vpn.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.Principal;

/**
 * @author Haoqiang Lyu
 * @date 2019-07-23 11:26
 */
@RequestMapping(value = {"/api/misc"})
@Controller
public class MiscController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private StorageService storageService;

    @RequestMapping(value="/picture",method = RequestMethod.POST)
    public void uploadImage(@RequestParam("pic") MultipartFile file, Principal principal){
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String username = (String)auth.getPrincipal();
        String username = principal.getName();

        //TODO
        //1. concatenate file name original file name+UUID+extension(,jpg)
        //2. create a temporary file at your local server
        //3. multipart file transfer to temp file
        //4. upload to S3 bucket
        //5. store s3key into database entity
        //6. new controller to retrieve object url by object id

        File f = new File("/Users/haoqianglyu/Downloads/1234.png");
        try {
            file.transferTo(f);
            storageService.putObject(file.getOriginalFilename(),f);
            logger.info("check uploaded file name"+file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
