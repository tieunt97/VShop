package com.example.tieu_nt.vshop;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;

        import com.example.tieu_nt.vshop.View.DangNhap.DangNhapActivity;

public class GioiThieuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                    Intent intent = new Intent(GioiThieuActivity.this, DangNhapActivity.class);
                    startActivity(intent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    Intent intent = new Intent(GioiThieuActivity.this, DangNhapActivity.class);
                    startActivity(intent);
                }
            }
        });

        thread.start();
    }
}
