package demo.websocketdemo;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TestComponent {

    private final SimpMessagingTemplate messagingTemplate;

    public TestComponent(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }


    @Scheduled(fixedRate = 1000L)
    public void getDate() {
        Date date = new Date();
        SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
        messagingTemplate.convertAndSend("/topic/date", "Bugun sana : " + day.format(date)+
                "\nHozirgi vaqt : " + time.format(date));
    }

    public void type(String message){
        messagingTemplate.convertAndSend("/topic/type", "Typing...");
    }

    public void keyDown(String message){
        messagingTemplate.convertAndSend("/topic/keyDown", "Message");
    }
}
