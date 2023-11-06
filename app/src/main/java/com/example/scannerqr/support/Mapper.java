package com.example.scannerqr.support;

import java.util.ArrayList;

public class Mapper {
    public static ArrayList<String> convertToValueLocation(String content) {
        String[] contents = content.split(",");
        String[] latitudes = contents[0].split("geo:");
        String latitude = "0.0";
        String longitude = "0.0";
        String altitude = "";
        if (contents.length > 1) {
            if (latitudes.length > 1) {
                latitude = latitudes[1];
            }
            longitude = contents[1];
        }
        if (contents.length > 2) {
            altitude = contents[2];
        }
        ArrayList<String> result = new ArrayList<>();
        result.add(latitude);
        result.add(longitude);
        result.add(altitude);
        return result;
    }

    public static ArrayList<String> convertToValueContact(String content) {
        String name = content.split("FN:")[1].split("ORG:")[0];
        String phone = content.split("TEL:")[1].split("EMAIL:")[0];
        String email = content.split("EMAIL:")[1].split("END:")[0];
        ArrayList<String> result = new ArrayList<>();
        result.add(name);
        result.add(phone);
        result.add(email);
        return result;
    }

    public static ArrayList<String> convertToValueUrl(String content) {
        String[] parts = content.split("MEBKM:TITLE:|;URL:|;;");
        String name = parts[1];
        String link = parts[2];
        ArrayList<String> result = new ArrayList<>();
        result.add(name);
        result.add(link);
        return result;
    }
    public static ArrayList<String> convertToValueEmail(String content) {
        String[] parts = content.split("mailto:|subject=");
        String email = parts[1];
        String subject = parts[2];
        ArrayList<String> result = new ArrayList<>();
        result.add(email);
        result.add(subject);
        return result;
    }

    public static ArrayList<String> convertToValueWifi(String content) {
        String security = getNthSplit(content, "WIFI:T:", 1).split(";S:")[0];
        String name = getNthSplit(content, ";S:", 1).split(";P:")[0];
        String password = getNthSplit(content, ";P:", 1);
        ArrayList<String> result = new ArrayList<>();
        result.add(name);
        result.add(security);
        result.add(password);
        return result;
    }

    private static String getNthSplit(String content, String delimiter, int index) {
        String[] parts = content.split(delimiter);
        if (parts.length > index) {
            return parts[index];
        } else {
            return "";
        }
    }
}
