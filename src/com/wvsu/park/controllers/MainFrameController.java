package com.wvsu.park.controllers;

import com.github.sarxos.webcam.Webcam;
import com.wvsu.park.serviceprovider.CameraServiceImpl;
import com.wvsu.park.serviceprovider.MainFrameServiceImpl;
import com.wvsu.park.serviceprovider.ParkingStatusServiceImpl;
import com.wvsu.park.services.CameraService;
import com.wvsu.park.services.MainFrameService;
import com.wvsu.park.services.ParkingStatusService;
import com.wvsu.park.views.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class MainFrameController {
    MainFrame mf;
    ParkingStatusService pss = new ParkingStatusServiceImpl();
    MainFrameService mfs = new MainFrameServiceImpl();
    CameraService cs = new CameraServiceImpl();
    
    public MainFrameController(MainFrame mf) {
        this.mf = mf;
        this.mf.buttonEvents(new ButtonEvents());
        this.cs.startVideoRecording();
    }
    
    class ButtonEvents implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == mf.viewCamera1Btn) {
                cs.openCamera(1, "Camera 2");
            }
            else if(e.getSource() == mf.liveJMenuItem) {
                //show webcam panel
            }
        }
    }
}