package com.ifttw.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.github.rtyley.android.sherlock.roboguice.fragment.RoboSherlockListFragment;
import com.ifttw.R;
import com.ifttw.model.AbstractAction;
import com.ifttw.model.Fence;
import com.ifttw.model.SMSAction;

import java.util.ArrayList;
import java.util.List;

import static com.ifttw.R.layout;

public class ActionFragment extends RoboSherlockListFragment {

    List<AbstractAction> actions = new ArrayList<AbstractAction>() {{
       add(new SMSAction());
    }};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(layout.action_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ListView list = (ListView) getActivity().findViewById(android.R.id.list);
        ArrayAdapter<AbstractAction> adapter = new ArrayAdapter<AbstractAction>(getActivity(),
                android.R.layout.simple_list_item_1, actions);
        list.setAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        AbstractAction action = actions.get(position);
        Fence fence = ((CreateFenceActivity) getActivity()).fence;
        fence.setAction(action);
        // TODO: Save the action and fence and whatever else to parse.
    }
}
