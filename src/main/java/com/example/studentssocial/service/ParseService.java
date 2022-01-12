package com.example.studentssocial.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParseService {
    @Value("${tag.delimitator}")
    private String tag;


    public List<String> parseText(String text) {
        List<String> users = new ArrayList<>();
        List<String> subStrings = List.of(text.split(tag));
        if (subStrings.size() > 1) {
            for(int i =1;i<subStrings.size();i++){
                List<String> words = List.of(subStrings.get(i).split(" "));
                users.add(words.get(0));
            }
        }
        return users;
    }

}
