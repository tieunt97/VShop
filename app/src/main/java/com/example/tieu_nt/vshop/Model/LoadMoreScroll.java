package com.example.tieu_nt.vshop.Model;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by tieu_nt on 4/23/2018.
 */

public class LoadMoreScroll extends RecyclerView.OnScrollListener{
    RecyclerView.LayoutManager layoutManager;
    ILoadMore iLoadMore;

    public LoadMoreScroll(RecyclerView.LayoutManager layoutManager, ILoadMore iLoadMore){
        this.layoutManager = layoutManager;
        this.iLoadMore = iLoadMore;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int tongItem = layoutManager.getItemCount();
        int daLoad = 0;
        if (layoutManager instanceof LinearLayoutManager){
            daLoad = ((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
        }else if(layoutManager instanceof GridLayoutManager){
            daLoad = ((GridLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
        }
        if(daLoad == tongItem - 1){
            iLoadMore.loadMore("");
        }
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }
}
