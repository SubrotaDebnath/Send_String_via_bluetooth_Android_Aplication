package subrota.shuvro.sendstringandroidaplication;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.io.IOException;
import java.util.UUID;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;
import app.akexorcist.bluetotohspp.library.DeviceList;

public class MainActivityPresenter implements MainActivityCommunicator.HandelConnection{
    private Context context;
    private ProgressDialog progressDialog;
    private String name, address;
    private ConnectThread connectThread;
    private BluetoothDevice device;
    private BluetoothAdapter mBluetoothAdapter;
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;
    MainActivityCommunicator.HandelView view;

    private BluetoothSPP bt;

    private static final String TAG = "MainActivityPresenter";

    private static final UUID MY_UUID_INSECURE =
            UUID.fromString("8ce255c0-200a-11e0-ac64-0800200c9a66");

    public MainActivityPresenter(Context context, MainActivityCommunicator.HandelView view) {
        this.context = context;
        this.view = view;
        progressDialog = new ProgressDialog(context);
        bt = new BluetoothSPP(context);


    }

    @Override
    public void bluetoothConnection(BluetoothDevice device, UUID uuid) {

    }

    @Override
    public void progressDialog(Context context, int value) {

    }


    public void startConnection(int value, String address, String name, BluetoothDevice device) {
        this.name =name;
        this.address = address;
        this.device = device;


        if (value ==1){
            progressDialog = ProgressDialog.show(context,"Connecting Bluetooth"
                    ,"Please Wait...",true);
            //bt.startService(false);
            bt.startService(BluetoothState.DEVICE_OTHER);

            view.changeActivity();

            progressDialog.dismiss();


        }else {
            progressDialog.dismiss();
        }
    }


//    private class ConnectThread extends Thread {
//        private BluetoothSocket mmSocket;
//        private BluetoothDevice mmDevice;
//        private UUID deviceUUID;
//
//        public ConnectThread(BluetoothDevice device, UUID uuid) {
//            Log.i(TAG, "ConnectThread: started.");
//            mmDevice = device;
//            deviceUUID = uuid;
//        }
//
//        public void run(){
//            BluetoothSocket tmp = null;
//            Log.i(TAG, "RUN mConnectThread ");
//
//            // Get a BluetoothSocket for a connection with the
//            // given BluetoothDevice
//            try {
//                Log.i(TAG, "ConnectThread: Trying to create InsecureRfcommSocket using UUID: "
//                        +MY_UUID_INSECURE );
//                tmp = mmDevice.createRfcommSocketToServiceRecord(deviceUUID);
//            } catch (IOException e) {
//                Log.i(TAG, "ConnectThread: Could not create InsecureRfcommSocket " + e.getMessage());
//            }
//
//            mmSocket = tmp;
//
//            // Always cancel discovery because it will slow down a connection
//            mBluetoothAdapter.cancelDiscovery();
//
//            // Make a connection to the BluetoothSocket
//
//            try {
//                // This is a blocking call and will only return on a
//                // successful connection or an exception
//                mmSocket.connect();
//
//                Log.i(TAG, "run: ConnectThread connected.");
//            } catch (IOException e) {
//                // Close the socket
//                try {
//                    mmSocket.close();
//                    Log.i(TAG, "run: Closed Socket.");
//                } catch (IOException e1) {
//                    Log.i(TAG, "mConnectThread: run: Unable to close connection in socket " + e1.getMessage());
//                }
//                Log.i(TAG, "run: ConnectThread: Could not connect to UUID: " + MY_UUID_INSECURE );
//            }
//
//            //will talk about this in the 3rd video
//            //connected(mmSocket,mmDevice);
//        }
//        public void cancel() {
//            try {
//                Log.i(TAG, "cancel: Closing Client Socket.");
//                mmSocket.close();
//            } catch (IOException e) {
//                Log.i(TAG, "cancel: close() of mmSocket in Connectthread failed. " + e.getMessage());
//            }
//        }
//    }

}
