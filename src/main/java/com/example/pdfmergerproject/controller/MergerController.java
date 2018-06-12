package com.example.pdfmergerproject.controller;


import com.example.pdfmergerproject.Model.MergerUser;
import com.example.pdfmergerproject.service.*;

import com.flock.FlockEventHandlerClient;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.util.PDFMergerUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

@Controller
public class MergerController {

    @Autowired
    private MergerEventListener mergerEventListener;

    @Autowired
    private MergerService mergerService;

    @Value("${appId}")
    private String appId;

    @Value("${appSecret}")
    private String appSecret;

    @Value("${baseUrl}")
    private String baseUrl;



    private FlockEventHandlerClient flockEventHandlerClient;

    @PostConstruct
    public void init() {
        this.flockEventHandlerClient = new FlockEventHandlerClient(mergerEventListener, appId, appSecret);
    }

    @RequestMapping(value = "/event", method = RequestMethod.POST)
    public void event(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            flockEventHandlerClient.handle(httpServletRequest, httpServletResponse);
        } catch (IOException e) {
            System.out.println("Error while handling event");
        }
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    @ResponseBody
    public void HandlePostRequest(@RequestParam HashMap<String, String> params, @RequestPart("firstPdf") MultipartFile firstPdf , @RequestPart("secondPdf") MultipartFile secondPdf) throws IOException, COSVisitorException {
        mergerService.MergePdfFiles(firstPdf, secondPdf, params.get("userId"));
    }

}
