package com.example.tieu_nt.vshop.ConnectInternet;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by tieu_nt on 5/20/2018.
 */

public class DownloadHinhSanPham extends AsyncTask<String, Void, Bitmap>{
    private String duongDan;

    public DownloadHinhSanPham(String duongDan) {
        this.duongDan = duongDan;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        URL url;
        Bitmap bitmap = null;
        try {
            url = new URL(duongDan);
            bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }
}
