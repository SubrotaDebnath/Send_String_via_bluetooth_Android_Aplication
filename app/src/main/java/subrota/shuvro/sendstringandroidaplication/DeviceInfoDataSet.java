package subrota.shuvro.sendstringandroidaplication;

import android.bluetooth.BluetoothDevice;

public class DeviceInfoDataSet {
    private String name;
    private String id;
    private BluetoothDevice device;

    public DeviceInfoDataSet(String name, String id, BluetoothDevice device) {
        this.name = name;
        this.id = id;
        this.device = device;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BluetoothDevice getDevice() {
        return device;
    }

    public void setDevice(BluetoothDevice device) {
        this.device = device;
    }
}
