package com.example.pdfmergerproject.service;

import com.flock.FlockApiClient;
import com.flock.auth.FlockAuth;
import com.flock.model.message.Attachment;
import com.flock.model.message.Download;
import com.flock.model.message.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SendMessageService {

    public void sendMergedFile(String to, String from, String botToken, String fileUrl){
        sendMessageWithAttachment("merged file", to, from, botToken, fileUrl);
    }

    private void sendMessageWithAttachment(String text, String to, String from, String token, String fileUrl) {
        Message message = new Message(from, to, text);
        List<Download> downloads =  new ArrayList<Download>();
        Download download = new Download();
        download.setSrc(fileUrl);
        downloads.add(download);
        Attachment attachment = new Attachment();
        attachment.setDownloads(downloads);
        message.setAttachments(Collections.singletonList(attachment));
        FlockAuth flockAuth = FlockAuth.forUserToken(false, token);
        try {
            FlockApiClient.callApi(flockAuth, "chat.sendMessage", message);
        } catch (Exception ex) {
            System.out.println("Error while sending message");
        }
    }

}
