package com.example.tieu_nt.vshop.View.TrangChu;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.tieu_nt.vshop.R;
import com.squareup.picasso.Picasso;

/**
 * Created by tieu_nt on 5/11/2018.
 */

public class FragmentHinhSanPham extends Fragment{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hinhsanpham, container, false);

        Bundle bundle = getArguments();
        String linkHinh = bundle.getString("linkHinh");

        ImageView imageView = view.findViewById(R.id.imgHinhSlider);
        Picasso.get().load(linkHinh).into(imageView);
        return view;
    }
}
