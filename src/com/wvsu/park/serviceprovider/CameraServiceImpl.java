/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wvsu.park.serviceprovider;

import com.github.sarxos.webcam.WebcamPanel;
import com.wvsu.park.services.CameraService;
import javax.swing.JFrame;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import com.wvsu.park.global.AppCamera;
import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.ICodec;
import com.xuggle.xuggler.IPixelFormat;
import com.xuggle.xuggler.IVideoPicture;
import com.xuggle.xuggler.video.ConverterFactory;
import com.xuggle.xuggler.video.IConverter;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sernoi
 */
public class CameraServiceImpl implements CameraService{

    Dimension size = WebcamResolution.VGA.getSize();
    Webcam webcam = openWebcam(size);
    
    @Override
    public void openCamera() {
        WebcamPanel panel = new WebcamPanel(AppCamera.CAMERA);
        panel.setFPSLimit(1);

        JFrame f = new JFrame(AppCamera.CAMERA.toString());
        f.add(panel);
        f.pack();
        f.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    @Override
    public void startVideoRecording() {
        Date date = new Date() ;
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH-mm-ss-ddMMyyyy") ;
        
        File saveFile = new File("clips/" + dateFormat.format(date) + ".mp4");

        System.out.println(saveFile.getName());
        //Initialize media writer
        IMediaWriter writer = ToolFactory.makeWriter(saveFile.getPath());
        //Set video recording size
        

        writer.addVideoStream(0, 0, ICodec.ID.CODEC_ID_H264, size.width, size.height);

        long start = System.currentTimeMillis();

        for (int i = 0; i < 50; i++) {
            BufferedImage image = ConverterFactory.convertToType(webcam.getImage(), BufferedImage.TYPE_3BYTE_BGR);
            IConverter converter = ConverterFactory.createConverter(image, IPixelFormat.Type.YUV420P);
            
            IVideoPicture frame = converter.toPicture(image, (System.currentTimeMillis() - start) * 1000);
            frame.setKeyFrame(i == 0);
            frame.setQuality(100);
            
            writer.encodeVideo(0, frame);
            
            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                Logger.getLogger(CameraServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        writer.close();
        System.out.println("Video recorded to the file: " + saveFile.getAbsolutePath());
    }

    private Webcam openWebcam(Dimension size) {
        Webcam webcam = AppCamera.CAMERA;
        webcam.setViewSize(size);
        webcam.open();
        return webcam;
    }
}
