package com.example.tieu_nt.vshop.Model.LoadMore;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * Created by tieu_nt on 4/23/2018.
 */

public class LoadMoreScroll extends RecyclerView.OnScrollListener{
    private RecyclerView.LayoutManager layoutManager;
    private ILoadMore iLoadMore;
    private int visibleItemCount, totalItemCount = 1;
    private int firstVisiblesItems = 0;
    private boolean isTrangCuoi;
    private String duongDan;

    public LoadMoreScroll(RecyclerView.LayoutManager layoutManager, ILoadMore iLoadMore){
        this.layoutManager = layoutManager;
        this.iLoadMore = iLoadMore;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (dy > 0){
            totalItemCount = layoutManager.getItemCount();

            if (layoutManager instanceof LinearLayoutManager){
                visibleItemCount = ((LinearLayoutManager) layoutManager).getChildCount();
                firstVisiblesItems = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
            }else if(layoutManager instanceof GridLayoutManager){
                visibleItemCount = ((GridLayoutManager) layoutManager).getChildCount();
                firstVisiblesItems = ((GridLayoutManager) layoutManager).findFirstVisibleItemPosition();
            }

            Log.d("DEMO", totalItemCount + " : " + firstVisiblesItems + " + " + visibleItemCount);

            if (!isTrangCuoi && (visibleItemCount + firstVisiblesItems) == totalItemCount) {
                Log.d("duongDan", duongDan);
                iLoadMore.loadMore(duongDan);
            }
        }
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }

    public void setTrangCuoi(boolean trangCuoi) {
        isTrangCuoi = trangCuoi;
    }

    public void setDuongDan(String duongDan) {
        this.duongDan = duongDan;
    }
}
