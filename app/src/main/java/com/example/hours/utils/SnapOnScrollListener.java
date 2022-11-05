package com.example.hours.utils;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

public class SnapOnScrollListener extends RecyclerView.OnScrollListener {
    private SnapHelper mSnapHelper;
    private Behavior mBehavior = Behavior.NOTIFY_ON_SCROLL;
    private OnSnapPositionChangeListener mOnSnapPositionChangeListener = null;
    private int mSnapPosition = RecyclerView.NO_POSITION;

    public SnapOnScrollListener(SnapHelper snapHelper, OnSnapPositionChangeListener snapListener, Behavior behavior) {
        mSnapHelper = snapHelper;
        mBehavior = behavior;
        mOnSnapPositionChangeListener = snapListener;
    }

    public enum Behavior{
        NOTIFY_ON_SCROLL,
        NOTIFY_ON_SCROLL_STATE_IDLE
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (mBehavior == Behavior.NOTIFY_ON_SCROLL) {
            maybeNotifySnapPositionChange(recyclerView);
        }
    }

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if (mBehavior == Behavior.NOTIFY_ON_SCROLL_STATE_IDLE
                && newState == RecyclerView.SCROLL_STATE_IDLE) {
            maybeNotifySnapPositionChange(recyclerView);
        }
    }

    private void maybeNotifySnapPositionChange(RecyclerView recyclerView) {
        int snapPosition = getSnapPosition(recyclerView);
        if (mSnapPosition != snapPosition) {
            mSnapPosition = snapPosition;
            mOnSnapPositionChangeListener.onSnapPositionChange(mSnapPosition);
        }
    }

    private int getSnapPosition(RecyclerView recyclerView){
        if(recyclerView.getLayoutManager() == null)
            return RecyclerView.NO_POSITION;
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        View snapView = mSnapHelper.findSnapView(layoutManager);
        if(snapView == null)
            return RecyclerView.NO_POSITION;
        return layoutManager.getPosition(snapView);
    }

    public int getPosition()
    {
        return mSnapPosition;
    }
}
