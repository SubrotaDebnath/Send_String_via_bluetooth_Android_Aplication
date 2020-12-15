package subrota.shuvro.sendstringandroidaplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private BluetoothAdapter bluetoothAdapter;
    private static final int REQUEST_ENABLE_BT = 1;
    private View rootView;
    private Helper helper;
    private List<DeviceInfoDataSet> devices = new ArrayList<>();
    private String[] array = new String[50];
    private Set<BluetoothDevice> pairedDevices;
    //private ListView listView;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private DeviceAdapter adapter;
    private static final String TAG = "MainActivity";
    private TextView refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        rootView = getWindow().getDecorView().getRootView();
        pairedDevices = bluetoothAdapter.getBondedDevices();

        //listView = findViewById(R.id.lv_paired_devices);
        recyclerView = findViewById(R.id.paired_devices);
        linearLayoutManager = new LinearLayoutManager(this);
        refresh = findViewById(R.id.screenRefresh);

        if (bluetoothAdapter == null) {
            // Device doesn't support Bluetooth
            helper.showSnackBar(rootView.getRootView(), "Device doesn't support Bluetooth");
        }
        getDevices();
        enableBluetooth();
        setAdapter();


    }

    public void showDevices(){

    }

    ///////////////////////get paird devices/////////////////
    public void getDevices() {
        if (pairedDevices.size() > 0) {
            // Loop through paired devices
            for (BluetoothDevice device : pairedDevices) {
                DeviceInfoDataSet deviceInfoDataSet = new DeviceInfoDataSet(device.getName(), device.getAddress());
                Log.i(TAG, "device name: "+ device.getName());
                devices.add(deviceInfoDataSet);

            }
        }
    }


    ////////////////click event for acpt/ cancel bluetooth enable
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ENABLE_BT && resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "please turn on your bluetooth", Toast.LENGTH_SHORT).show();
            //helper.showSnackBar(rootView, "please turn on your bluetooth");
            enableBluetooth();

        } else {
            //Toast.makeText(this, "Bluetooth Turned on", Toast.LENGTH_LONG).show();
            helper.showSnackBar(rootView.getRootView(), "Bluetooth Turned on");
            getDevices();
            setAdapter();
        }
    }

    ////////////check bluetooth enable or not/////////////////////////
    public void enableBluetooth() {
        if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }else {
            getDevices();
            setAdapter();
        }
    }

    public void setAdapter(){
        if (devices.size()>0){
            recyclerView.setLayoutManager(linearLayoutManager);
            adapter = new DeviceAdapter(devices, this);
            recyclerView.setAdapter(adapter);
        }else {
            recyclerView.setVisibility(View.GONE);
            refresh.setVisibility(View.VISIBLE);
        }

    }


    public void refresh(View view) {
        Intent intent = new Intent(this, SplashScreen.class);
        intent.putExtra("msg", "Restarting....");
        startActivity(intent);
    }
}