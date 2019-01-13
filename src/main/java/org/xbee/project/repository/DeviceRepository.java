package org.xbee.project.repository;

import org.xbee.project.model.MyRemoteXbeeDevice;

import java.util.List;

public interface DeviceRepository {

    List<MyRemoteXbeeDevice> getAll();

    MyRemoteXbeeDevice get(int id);

    MyRemoteXbeeDevice get(String adr64bit);

    MyRemoteXbeeDevice save(MyRemoteXbeeDevice device);

    boolean delete(int id);


}