package com.example.scannerqr.UI.qrcode;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.scannerqr.UI.qrcode.fragment.ContactFragment;
import com.example.scannerqr.UI.qrcode.fragment.EmailFragment;
import com.example.scannerqr.UI.qrcode.fragment.PositionFragment;
import com.example.scannerqr.UI.qrcode.fragment.ScheduleFragment;
import com.example.scannerqr.UI.qrcode.fragment.TextFragment;
import com.example.scannerqr.UI.qrcode.fragment.UrlFragment;
import com.example.scannerqr.UI.qrcode.fragment.WifiFragment;

public class PagerQrCodeAdapter extends FragmentStateAdapter {
    int soPage = 7;
    PositionFragment positionFragment = new PositionFragment();
    TextFragment textFragment = new TextFragment();
    UrlFragment urlFragment = new UrlFragment();
    ContactFragment contactFragment = new ContactFragment();
    WifiFragment wifiFragment = new WifiFragment();
    EmailFragment emailFragment = new EmailFragment();
    ScheduleFragment scheduleFragment = new ScheduleFragment();

    public PagerQrCodeAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return positionFragment;
            case 1:
                return textFragment;
            case 2:
                return urlFragment;
            case 3:
                return wifiFragment;
            case 4:
                return contactFragment;
            case 5:
                return emailFragment;
            case 6:
                return scheduleFragment;
            default:
                return positionFragment;
        }
    }

    @Override
    public int getItemCount() {
        return soPage;
    }
}
