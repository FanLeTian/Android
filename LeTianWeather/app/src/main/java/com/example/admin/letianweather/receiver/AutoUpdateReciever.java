package com.example.admin.letianweather.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.admin.letianweather.service.AutoUpdateService;

/**
 * Created by admin on 2016/6/3.
 */
public class AutoUpdateReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, AutoUpdateService.class);
        context.startService(i);
    }
}
