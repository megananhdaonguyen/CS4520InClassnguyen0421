package com.example.demo;


import com.example.demo.User8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

public class Utils {
    public static String generateUniqueID(ArrayList<String> chatEmails) {
//        Using Java UUID (Unique User8 ID) generator utility to create the IDs from the list of user emails......
        Collections.sort(chatEmails);
        String uuid = UUID.nameUUIDFromBytes(
                        chatEmails.toString().getBytes())
                .toString().substring(0,16);
        return uuid;
    }
    public static String generateChatName(ArrayList<User8> users){
        String name = "";
        for(int i=0; i< users.size(); i++){
            if(i==users.size()-1){
                name+= users.get(i).getFirstname();
            }else{
                name+= users.get(i).getFirstname()+", ";
            }
        }
        return name;
    }
}
