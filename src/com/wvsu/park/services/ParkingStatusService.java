package com.wvsu.park.services;

public interface ParkingStatusService {
    boolean isDeviceConnected(int index);
    String getDeviceStatus(int index); 
}
