package subrota.shuvro.sendstringandroidaplication;

import android.bluetooth.BluetoothDevice;
import android.content.Context;

import java.util.UUID;

public interface MainActivityCommunicator {
    interface HandelConnection{
        void bluetoothConnection(BluetoothDevice device, UUID uuid);
        void progressDialog(Context context, int value);
    }

    interface HandelView{
        void changeActivity();
    }
}
