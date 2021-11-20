package com.wvsu.park;

import com.github.sarxos.webcam.Webcam;
import com.wvsu.park.controllers.MainFrameController;
import com.wvsu.park.global.AppCamera;
import com.wvsu.park.views.MainFrame;
import java.util.List;
import javax.swing.JOptionPane;


public class Start {
    public static void main(String[] args) {
        Start st = new Start();
        st.chooseCamera();
       // MainFrame mf = new MainFrame();
       // mf.setVisible(true);
       // mf.setExtendedState(JFrame.MAXIMIZED_BOTH);
       // new MainFrameController(mf);
        //camera starts recording...
    }
    
    private void chooseCamera() {
        try
        {
            List<Webcam> cameras = Webcam.getWebcams();
            Object[] cameraObjects = cameras.toArray();
            Object selectedValue = JOptionPane.showInputDialog(null,
            "Choose camera", "Camera Options",
            JOptionPane.INFORMATION_MESSAGE, null,
            cameraObjects, cameraObjects[0]);
            if(selectedValue == null)
            {
                System.exit(0);
            }
            Webcam CAMERA = (Webcam) selectedValue;
            //String cameraString = (String) selectedValue;
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            System.out.println("nagsulod");
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog (null, "Would You Like to "
                               + "Continue Without a camera?","Device Not Found",dialogButton);
            if(dialogResult == JOptionPane.YES_OPTION)
            {
                System.out.println("Yes");
            }
            else
            {
                System.exit(0);
            }  
        }
    }
}
