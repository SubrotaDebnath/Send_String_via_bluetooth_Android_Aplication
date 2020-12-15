package subrota.shuvro.sendstringandroidaplication;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.UUID;

public class ControlBoard extends AppCompatActivity {
    private static final String TAG = "ControlBoard";
    private String id, name;
    private TextView title;
    private EditText messageET;
    private BluetoothAdapter myBluetooth = null;
    private BluetoothSocket btSocket = null;
    private CheckBox checkBox;
    // This UUID is unique and fix id for this device
    private static final UUID myUUID = UUID.fromString("f6332bbb-1f1d-4563-b548-175fca1dfb19");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_board);

        title = findViewById(R.id.controlBoardTitle);
        messageET = findViewById(R.id.controlBoardMessage);
        checkBox = findViewById(R.id.controlBoardCB);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null){
            id =(String) bundle.getString("id");
            name =(String) bundle.getString("name");
            title.setText(name);
        }
        Log.i(TAG, "id: "+id+"  name: "+name);

        try {
            if (btSocket == null /*|| !isBtConnected*/) {
                myBluetooth = BluetoothAdapter.getDefaultAdapter();

                // This will connect the device with address as passed
                BluetoothDevice hc = myBluetooth.getRemoteDevice(id);
                btSocket = hc.createInsecureRfcommSocketToServiceRecord(myUUID);
                BluetoothAdapter.getDefaultAdapter().cancelDiscovery();

                //Now you will start the connection
                btSocket.connect();

            }
        }
        catch (IOException e) {
            e.printStackTrace();
            Log.i(TAG, "Exception: "+e.getMessage());
        }
    }

    public void send(View view) {
        String message = messageET.getText().toString();
        if (message != null){
            if (checkBox.isChecked()){
                message = message+" Developed By ECRC.";
            }

            if (btSocket != null) {
                try { // Converting the string to bytes for transferring
                    btSocket.getOutputStream().write(message.toString().getBytes());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }


//    private void connected(BluetoothSocket mmSocket, BluetoothDevice mmDevice) {
//        Log.i(TAG, "connected: Starting.");
//
//        // Start the thread to manage the connection and perform transmissions
//        mConnectedThread = new ConnectedThread(mmSocket);
//        mConnectedThread.start();
//    }
//
//    /**
//     * Write to the ConnectedThread in an unsynchronized manner
//     *
//     * @param out The bytes to write
//     * @see ConnectedThread#write(byte[])
//     */
//    public void write(byte[] out) {
//        // Create temporary object
//        ConnectedThread r;
//
//        // Synchronize a copy of the ConnectedThread
//        Log.i(TAG, "write: Write Called.");
//        //perform the write
//        mConnectedThread.write(out);
//    }
//
//    public ConnectedThread(BluetoothSocket socket) {
//        Log.i(TAG, "ConnectedThread: Starting.");
//
//        mmSocket = socket;
//        InputStream tmpIn = null;
//        OutputStream tmpOut = null;
//
//        //dismiss the progressdialog when connection is established
//        try{
//            mProgressDialog.dismiss();
//            //mProgressDiaLog.iismiss();
//        }catch (NullPointerException e){
//            e.printStackTrace();
//        }
//
//
//        try {
//            tmpIn = mmSocket.getInputStream();
//            tmpOut = mmSocket.getOutputStream();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        mmInStream = tmpIn;
//        mmOutStream = tmpOut;
//    }
//
//    public void run(){
//        byte[] buffer = new byte[1024];  // buffer store for the stream
//
//        int bytes; // bytes returned from read()
//
//        // Keep listening to the InputStream until an exception occurs
//        while (true) {
//            // Read from the InputStream
//            try {
//                bytes = mmInStream.read(buffer);
//                String incomingMessage = new String(buffer, 0, bytes);
//                Log.i(TAG, "InputStream: " + incomingMessage);
//            } catch (IOException e) {
//                Log.i(TAG, "write: Error reading Input Stream. " + e.getMessage() );
//                break;
//            }
//        }
//    }
//
//    //Call this from the main activity to send data to the remote device
//    public void write(byte[] bytes) {
//        String text = new String(bytes, Charset.defaultCharset());
//        Log.i(TAG, "write: Writing to outputstream: " + text);
//        try {
//            mmOutStream.write(bytes);
//        } catch (IOException e) {
//            Log.i(TAG, "write: Error writing to output stream. " + e.getMessage() );
//        }
//    }
//
//    /* Call this from the main activity to shutdown the connection */
//    public void cancel() {
//        try {
//            mmSocket.close();
//        } catch (IOException e) { }
//    }
//}
}