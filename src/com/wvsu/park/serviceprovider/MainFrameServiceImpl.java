/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wvsu.park.serviceprovider;

import com.wvsu.park.services.MainFrameService;
import javax.swing.JDialog;

/**
 *
 * @author sernoi
 */
public class MainFrameServiceImpl implements MainFrameService {

    @Override
    public void openDialog(JDialog jDialog, String title) {
        jDialog.setTitle(title);
        jDialog.setResizable(true);
        jDialog.setModal(true);
        jDialog.pack();
        jDialog.setLocationRelativeTo(null);
        jDialog.setVisible(true);
    }
}
