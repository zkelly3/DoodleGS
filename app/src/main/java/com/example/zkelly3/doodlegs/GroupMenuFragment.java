package com.example.zkelly3.doodlegs;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.zkelly3.doodlegs.game_logic.Element;
import com.example.zkelly3.doodlegs.game_logic.Group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupMenuFragment extends Fragment {
    private ListView listView;
    private ListAdapter listAdapter;
    private List<String> createdGroup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        List<Group> allGroups = new ArrayList<>(((MainActivity)getActivity()).getAllGroups().values());
        Map<String, Element> allElements = ((MainActivity)getActivity()).getAllElements();

        createdGroup = new ArrayList<>();
        for(Group group: allGroups) {
            if(!group.isEmpty()) {
                createdGroup.add(group.getName());
            }
        }

        View view = inflater.inflate(R.layout.fragment2, container, false);
        listView = view.findViewById(R.id.listGroup);

        listAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, createdGroup);

        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(onClickListView);
        return view;
    }
    private AdapterView.OnItemClickListener onClickListView = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            ((MainActivity) getActivity()).navigateToElementMenu(createdGroup.get(position));
        }
    };
}
