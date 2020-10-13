package com.office.template.base.util;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public abstract class PaginationGridScrollListener extends RecyclerView.OnScrollListener {

    private GridLayoutManager gridLayoutManager;

    public PaginationGridScrollListener(GridLayoutManager gridLayoutManager) {
        this.gridLayoutManager = gridLayoutManager;
    }

    @Override
    public void onScrolled(@NotNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleItemCount = gridLayoutManager.getChildCount();
        int totalItemCount = gridLayoutManager.getItemCount();
        int firstVisibleItemPosition = gridLayoutManager.findFirstVisibleItemPosition();

        if (!isLoading() && !isLastPage()) {
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0) {
                loadMoreItems();
            }
        }
    }

    protected abstract void loadMoreItems();

    /*public abstract int getTotalPageCount();*/

    protected abstract boolean isLastPage();

    public abstract boolean isLoading();
}
