/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wvsu.park.serviceprovider;

import com.wvsu.park.services.ParkingStatusService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author John Rey Alipe
 */
public class ParkingStatusServiceImpl implements ParkingStatusService{
    String[] strURL = {"http://192.168.88.186",
                    "http://192.168.88.187",
                    "http://192.168.88.188"};
    
    
    @Override
    public boolean isDeviceConnected(int index) {
        try {
            
            URL url = new URL(strURL[index]);
            URLConnection con;
            try {
                con = url.openConnection();
                //           InputStream is =con.getInputStream();
//
//            BufferedReader br = new BufferedReader(new InputStreamReader(is));
//
//            String line = null;
//
//        while ((line = br.readLine()) != null) {
//            System.out.println(line);
//        }

            } catch (IOException ex) {
                System.err.println(ex);
                return false;
            }
            return true;
        } catch (MalformedURLException ex) {
            Logger.getLogger(ParkingStatusServiceImpl.class.getName()).log(Level.SEVERE, null,ex);
            return false;
        }
    }

    @Override
    public String getDeviceStatus(int index) {
        try {
            URL url = new URL(strURL[index]);
            
            String msg = "Normal";
            if(isDeviceConnected(index)) {
                URLConnection con;
                try {
                    con = url.openConnection();
                    InputStream is =con.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    String line;
                    while ((line = br.readLine()) != null) {
                        msg = line;
                    }
                } catch (IOException ex) {
                    System.err.println(ex);
                    return "Disconnected";
                }
                return msg;
            }
            else {
                return "Disconnected";
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(ParkingStatusServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return "Disconnected";
        }
    }
}
