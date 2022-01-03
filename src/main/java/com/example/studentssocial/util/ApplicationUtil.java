package com.example.studentssocial.util;

import java.util.List;
import java.util.stream.Collectors;

public class ApplicationUtil {

    public static String fromListToString(List<String> list){
        return String.join(",", list);
    }


}
