package com.example.pdfmergerproject.service;

import com.example.pdfmergerproject.Model.MergerUser;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.util.PDFMergerUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class MergerService {

    @Autowired
    private UserService userService;

    @Autowired
    private SendMessageService sendMessageService;

    @Autowired
    private AmazonClient amazonClient;

    @Value("${botId}")
    private String botId;

    @Value("${botToken}")
    private String botToken;

    public void MergePdfFiles(MultipartFile firstPdf, MultipartFile secondPdf, String userId) throws IOException, COSVisitorException {
        File firstFile = getFile(firstPdf);
        File secondFile = getFile(secondPdf);
        Long currentTimeMillis = System.currentTimeMillis();
        String fileName = "merged" + currentTimeMillis.toString() + ".pdf";
        PDFMergerUtility PDFmerger = new PDFMergerUtility();
        PDFmerger.setDestinationFileName("/Users/mithoonkumar/Documents/pdf-merger-files/" + fileName);
        PDFmerger.addSource(firstFile);
        PDFmerger.addSource(secondFile);
        PDFmerger.mergeDocuments();
        MergerUser mergerUser = userService.getUser(userId);
        String fileUrl = uploadFileToAws("/Users/mithoonkumar/Documents/pdf-merger-files/" + fileName);
        sendMessageService.sendMergedFile(mergerUser.getUserId(), botId, botToken, fileUrl);
        firstFile.delete();
        secondFile.delete();
    }


    private File getFile(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(multipartFile.getBytes());
        fos.close();
        return file;
    }

    private String uploadFileToAws(String path) {
        File file = new File(path);
        return amazonClient.uploadFile(file);
    }
}
