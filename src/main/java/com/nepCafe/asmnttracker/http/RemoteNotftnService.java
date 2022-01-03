package com.nepCafe.asmnttracker.http;

import com.nepCafe.asmnttracker.models.NotificationReponse;
import org.nepCafe.kafka.client.models.Notification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RemoteNotftnService extends HttpService {

    @Value("${service.remote.url.notification-service}")
    private String url;
    private String findBuUserId = "/find/1023/user";


    public NotificationReponse findBuUserIdAndAction(Long id, String action) {
        Notification notification = new Notification();
        notification.setUserId(String.valueOf(id));
        notification.setAction(action);
        notification.setResolved(false);
        return (NotificationReponse) post(notification, url.concat("/find"), NotificationReponse.class );
    }

}
