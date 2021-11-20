package com.wvsu.park;

import com.wvsu.park.controllers.MainFrameController;
import com.wvsu.park.views.MainFrame;


public class Start {
    public static void main(String[] args) {
        MainFrame mf = new MainFrame();
        mf.setVisible(true);
       // mf.setExtendedState(JFrame.MAXIMIZED_BOTH);
        new MainFrameController(mf);
        //camera starts recording...
    }
}
