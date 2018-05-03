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
import android.widget.Toast;

import com.example.zkelly3.doodlegs.game_logic.BasicRule;
import com.example.zkelly3.doodlegs.game_logic.Combiner;
import com.example.zkelly3.doodlegs.game_logic.Element;
import com.example.zkelly3.doodlegs.game_logic.Group;
import com.example.zkelly3.doodlegs.game_logic.ProbRule;
import com.example.zkelly3.doodlegs.game_logic.Rule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fragment2 extends Fragment {
    private ListView listView;
    private ListAdapter listAdapter;
    private Map<String, Group> createdGroup;
    private List<String> groupNames;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        List<Group> allGroups = ((MainActivity)getActivity()).getAllGroups();
        Map<String, Element> allElements = ((MainActivity)getActivity()).getAllElements();

        createdGroup = new HashMap<>();
        for(Group group: allGroups) {
            if(!group.isEmpty()) {
                createdGroup.put(group.getName(), group);
            }
        }
        groupNames = new ArrayList<>();
        for (Map.Entry<String, Group> entry :  createdGroup.entrySet()) {
            groupNames.add(entry.getKey());
        }

        View view = inflater.inflate(R.layout.fragment2, container, false);
        listView = (ListView) view.findViewById(R.id.listGroup);

        listAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, groupNames);

        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(onClickListView);
        return view;
    }
    private AdapterView.OnItemClickListener onClickListView = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            List<Element> elements = createdGroup.get(groupNames.get(position)).getCreated();
            ((MainActivity) getActivity()).replaceFragment3(elements);
        }
    };
}
