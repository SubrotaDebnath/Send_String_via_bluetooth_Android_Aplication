package subrota.shuvro.sendstringandroidaplication;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.IOException;
import java.util.UUID;

public class ControlBoard extends AppCompatActivity {
    private static final String TAG = "ControlBoard";
    private String id, name;
    private TextView title;
    private EditText messageET;
    private BluetoothAdapter myBluetooth = null;
    private BluetoothSocket btSocket = null;
    private static final UUID myUUID = UUID.fromString("f6332bbb-1f1d-4563-b548-175fca1dfb19");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_board);

        title = findViewById(R.id.controlBoardTitle);
        messageET = findViewById(R.id.controlBoardMessage);
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
        }
    }

    public void send(View view) {
        String message = messageET.getText().toString();
        if (message != null){

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
}