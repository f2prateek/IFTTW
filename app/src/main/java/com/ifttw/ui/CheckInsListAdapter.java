package com.ifttw.ui;

import android.util.Log;
import android.view.LayoutInflater;

import com.ifttw.R;
import com.ifttw.core.CheckIn;
import com.ifttw.core.News;

import java.util.List;

import roboguice.util.Strings;

public class CheckInsListAdapter extends AlternatingColorListAdapter<CheckIn> {
    /**
     * @param inflater
     * @param items
     * @param selectable
     */
    public CheckInsListAdapter(LayoutInflater inflater, List<CheckIn> items,
                           boolean selectable) {
        super(R.layout.checkin_list_item, inflater, items, selectable);
    }

    /**
     * @param inflater
     * @param items
     */
    public CheckInsListAdapter(LayoutInflater inflater, List<CheckIn> items) {
        super(R.layout.checkin_list_item, inflater, items);
    }

    @Override
    protected int[] getChildViewIds() {
        return new int[] { R.id.tv_name, R.id.tv_date };
    }

    @Override
    protected void update(int position, CheckIn item) {
        super.update(position, item);

        setText(0, item.getName());
    }
}
