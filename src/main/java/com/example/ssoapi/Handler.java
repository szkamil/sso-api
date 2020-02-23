package com.example.ssoapi;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Component;

import java.net.*;
import java.io.*;
import java.net.Authenticator;
import java.util.List;

@Component
public class Handler {
    public String getHandler(String url) throws Exception {
        /***
         *
         * COOKIES
         */

        CookieManager manager = new CookieManager();
        manager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(manager);


        /***
         *
         * Open connection
         */

        URL targetURL = new URL( url );
        URLConnection urlConnection = targetURL.openConnection();

        CookieStore cookieJar =  manager.getCookieStore();
        System.out.println(cookieJar);
        List<HttpCookie> cookies =
                cookieJar.getCookies();
        System.out.println(cookies.size());
        for (HttpCookie cookie: cookies) {
            System.out.println("CookieHandler retrieved cookie: " + cookie);
        }

        /***
         *
         * AUTHENTICATION
         */
        Authenticator.setDefault(new EpvAimAuth());
/***
        urlConnection.setRequestProperty( https://stackoverflow.com/questions/496651/connecting-to-remote-url-which-requires-authentication-using-java );

 cookies https://docs.oracle.com/javase/tutorial/networking/cookies/index.html
 ***/

//
//        Authenticator.setDefault (new Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication ("username", "password".toCharArray());
//            }
//        });

        /***
         *
         * Read response
         */

        BufferedReader bf = new BufferedReader( new InputStreamReader( urlConnection.getInputStream() ) );
        StringBuffer sb = new StringBuffer();
        String str;
        while ((str = bf.readLine()) != null)
            sb.append( str );
        bf.close();
        return sb.toString();


    }
}