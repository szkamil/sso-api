package com.example.ssoapi;

import org.springframework.stereotype.Component;

import java.net.*;
import java.io.*;
import java.net.Authenticator;

@Component
public class Handler {
    public String getHandler(String url) throws Exception {

        URL targetURL = new URL( url );

        URLConnection urlConnection = targetURL.openConnection();
/***
        urlConnection.setRequestProperty( https://stackoverflow.com/questions/496651/connecting-to-remote-url-which-requires-authentication-using-java );
***/


        Authenticator.setDefault (new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication ("username", "password".toCharArray());
            }
        });


        BufferedReader bf = new BufferedReader( new InputStreamReader( urlConnection.getInputStream() ) );
        StringBuffer sb = new StringBuffer();
        String str;
        while ((str = bf.readLine()) != null)
            sb.append( str );
        bf.close();
        return sb.toString();


    }
}