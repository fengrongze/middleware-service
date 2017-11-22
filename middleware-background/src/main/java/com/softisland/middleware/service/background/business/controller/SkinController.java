package com.softisland.middleware.service.background.business.controller;

import com.github.junrar.Archive;
import com.github.junrar.rarfile.FileHeader;
import com.softisland.middleware.domain.bean.db.SysSiteSkin;
import com.softisland.middleware.service.background.business.service.SkinService;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * Created by softisland on 2017/11/1.
 */
@Controller
public class SkinController {

    private static Logger logger = LoggerFactory.getLogger(SkinController.class);

    @Value("${skin.path}")
    private String SKIN_PATH;

    @Autowired
    private SkinService skinService;

    @RequestMapping(value = "/skin/list",method = RequestMethod.GET)
    public ModelAndView siteSkinList(HttpServletRequest request, HttpServletResponse response)
    {
        //int siteId = Integer.parseInt(request.getSession().getAttribute("siteId").toString());
        int siteId = 1;
        List<SysSiteSkin> siteSkinsList = skinService.getSiteSkinList(siteId);
        ModelAndView modelAndView = new ModelAndView("/skinlist");
        modelAndView.addObject("siteSkinsList", siteSkinsList);
        return modelAndView;
    }

    @RequestMapping(value = "/skin/add",method = RequestMethod.GET)
    public ModelAndView siteSkinAdd(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView modelAndView = new ModelAndView("/addskin");
        return modelAndView;
    }

    @RequestMapping(value = "/skin/save",method = RequestMethod.POST)
    @ResponseBody
    public int siteSkinSave(HttpServletRequest request, HttpServletResponse response)
    {
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile myfile = multipartRequest.getFile("myfile");
            String originalFilename = myfile.getOriginalFilename();
            String endName = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());
            if (".zip".endsWith(endName)) {
                ZipInputStream zs = new ZipInputStream(myfile.getInputStream());
                BufferedInputStream bs = new BufferedInputStream(zs);
                ZipEntry ze;
                while((ze = zs.getNextEntry()) != null){                    //获取zip包中的每一个zip file entry
                    String fileName = ze.getName();                            //pictureName
                    File unpackfile = new File(SKIN_PATH + File.separator + fileName);
                    if(fileName.endsWith("/"))
                    {
                        unpackfile.mkdirs();
                    }
                    else
                    {
                        FileOutputStream out = new FileOutputStream(unpackfile);
                        int c;
                        byte[] by = new byte[1024];
                        while ((c = zs.read(by)) != -1) {
                            out.write(by, 0, c);
                        }
                        out.flush();
                    }

                }
                bs.close();
                zs.close();
            }

            return 1;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return -1;
        }
    }

  //  public int siteSkinOperator()
}
