package asuHelloWorldJavaFX;

//MessageManager.java

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageManager {
 private static MessageManager instance;
 private Map<String, List<Message>> messageMap;

 private MessageManager() {
     messageMap = new HashMap<>();
 }

 public static MessageManager getInstance() {
     if (instance == null) {
         instance = new MessageManager();
     }
     return instance;
 }

 public void addMessage(String patientId, Message message) {
     List<Message> messages = messageMap.getOrDefault(patientId, new ArrayList<>());
     messages.add(message);
     messageMap.put(patientId, messages);
 }

 public List<Message> getMessages(String patientId) {
     return messageMap.getOrDefault(patientId, new ArrayList<>());
 }
}

class Message {
 private String sender;
 private String content;

 public Message(String sender, String content) {
     this.sender = sender;
     this.content = content;
 }

 public String getSender() {
     return sender;
 }

 public String getContent() {
     return content;
 }
}