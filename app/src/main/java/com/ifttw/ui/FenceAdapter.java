package com.ifttw.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.ifttw.R;
import com.ifttw.model.Fence;
import com.parse.ParseObject;

import java.util.List;

/**
 *
 */
public class FenceAdapter extends BaseAdapter {

    private static final String URL =
            "https://maps.googleapis.com/maps/api/staticmap?sensor=false&size=400x400&zoom=13&center=%s,%s";

    Context context;
    private List<ParseObject> data;

    public FenceAdapter(Context context, List<ParseObject> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            v = layoutInflater.inflate(R.layout.fence_row, parent, false);
        } else {
            v = convertView;
        }

        ParseObject object = (ParseObject) getItem(position);
        Fence fence = new Fence(object);

        TextView text = (TextView) v.findViewById(R.id.row_fence_name);
        text.setText(fence.getName());
        //TODO: set image
        return v;
    }
}
