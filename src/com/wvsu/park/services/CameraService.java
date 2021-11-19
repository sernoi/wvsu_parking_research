/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wvsu.park.services;

/**
 *
 * @author sernoi
 */
public interface CameraService {
    void openCamera(int camIndex, String deviceName);
    void startVideoRecording();
}
