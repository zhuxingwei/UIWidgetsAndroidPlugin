package com.unity.uiwidgets.androidplugin;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.view.View;

import com.unity3d.player.UnityPlayerActivity;

public class UIWidgetsMainActivity extends UnityPlayerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        updateSystemUI();
        addSystemUIChangeListener();

        Log.i("UIWidgetsPlugin", "On UIWidgetsMainActivity Created !!");
    }

    private static int getLowProfileFlag() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
                ?       View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_FULLSCREEN
                :       View.SYSTEM_UI_FLAG_LOW_PROFILE;
    }

    private void updateSystemUI() {
        // Works from API level 11
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB)
            return;

        mUnityPlayer.setSystemUiVisibility(mUnityPlayer.getSystemUiVisibility() & ~getLowProfileFlag());
    }

    private void addSystemUIChangeListener()
    {
        // Works from API level 11
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            mUnityPlayer.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
                @Override
                public void onSystemUiVisibilityChange(final int visibility) {
                    updateSystemUI();
                }
            });
        }
    }
}
