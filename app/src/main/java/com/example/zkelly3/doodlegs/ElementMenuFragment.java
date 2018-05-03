package com.example.zkelly3.doodlegs;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.zkelly3.doodlegs.game_logic.Element;
import com.example.zkelly3.doodlegs.game_logic.Group;

import java.util.ArrayList;
import java.util.List;

public class ElementMenuFragment extends Fragment {
    private ListView listView;
    private ListAdapter listAdapter;
    private Group curGroup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);
        listView = view.findViewById(R.id.listGroup);

        Bundle args = getArguments();
        String groupName = args.getString("group_name");
        curGroup = ((MainActivity)getActivity()).getAllGroups().get(groupName);
        List<String> createdElement = new ArrayList<>();
        for(Element element: curGroup.getCreated()) {
            createdElement.add(element.getName());
        }
        listAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, createdElement);

        listView.setAdapter(listAdapter);
        return view;
    }
}
