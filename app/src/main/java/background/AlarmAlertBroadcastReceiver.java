package background;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import model.Alarm;
import ui.AlarmAlertActivity;

public class AlarmAlertBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("alert brd reveiver", "alarmAlertBroadcastReceiver");
        Intent nfcAlarmServiceIntent = new Intent(context, AlarmServiceBroadcastReceiver.class);
        context.sendBroadcast(nfcAlarmServiceIntent, null);

        StaticWakeLock.lockOn(context);
        Bundle bundle = intent.getExtras();
        final Alarm alarm = (Alarm) bundle.getSerializable("alarm");

        Intent nfcAlarmAlertActivityIntent;
        nfcAlarmAlertActivityIntent = new Intent(context, AlarmAlertActivity.class);
        nfcAlarmAlertActivityIntent.putExtra("alarm", alarm);
        nfcAlarmAlertActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(nfcAlarmAlertActivityIntent);
    }
}
