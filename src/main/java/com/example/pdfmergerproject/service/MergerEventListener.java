package com.example.pdfmergerproject.service;

import com.flock.FlockEventListener;
import com.flock.model.event.*;
import com.flock.model.message.Attachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MergerEventListener implements FlockEventListener {

    @Autowired
    private UserService userService;

    @Override
    public void onAppInstall(AppInstall appInstall) {
        System.out.println("App install event occurred for user: " + appInstall.getUserId());
        userService.addUserToken(appInstall.getUserId(), appInstall.getUserToken());
    }

    @Override
    public void onAppUnInstall(AppUnInstall appUnInstall) {
        System.out.println("App uninstall event occurred for user: " + appUnInstall.getUserId());
        userService.deleteUserToken(appUnInstall.getUserId());
    }

    @Override
    public ToasterMessage onSlashCommand(SlashCommand slashCommand) {
        return null;
    }

    @Override
    public Attachment onGenerateUrlPreview(ChatGenerateUrlPreview chatGenerateUrlPreview) {
        return null;
    }

    @Override
    public ToasterMessage onWidgetAction(WidgetAction widgetAction) {
        return null;
    }

    @Override
    public void onGroupUpdate(GroupUpdate group) {

    }

    @Override
    public void onTeamProfileAdded(TeamAddProfile teamAddProfile) {

    }

    @Override
    public void onTeamCreated(TeamCreated teamCreated) {

    }

    @Override
    public ToasterMessage onMessageAction(MessageAction messageAction) {
        return null;
    }

    @Override
    public void onProfileOauth(ProfileOauth profileOauth) {

    }

    @Override
    public void onChatMessageReceived(ChatMessageReceived chatMessageReceived) {

    }

    @Override
    public ToasterMessage onFlockMLAction(FlockMLAction flockMLAction) {
        return null;
    }

    @Override
    public ToasterMessage onOpenAttachmentWidget(OpenAttachmentWidget openAttachmentWidget) {
        return null;
    }

    @Override
    public ToasterMessage onPressButton(PressButton pressButton) {
        return null;
    }
}
