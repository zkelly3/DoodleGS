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

import com.example.zkelly3.doodlegs.game_logic.Element;
import com.example.zkelly3.doodlegs.game_logic.Group;

import java.util.ArrayList;
import java.util.List;

public class ElementMenuFragment extends Fragment {
    private ListView listView;
    private ListAdapter listAdapter;
    private Group curGroup;
    private List<String> createdElement;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.element_menu, container, false);
        listView = view.findViewById(R.id.listGroup);

        Bundle args = getArguments();
        String groupName = args.getString("group_name");
        curGroup = ((MainActivity)getActivity()).getAllGroups().get(groupName);
        createdElement = new ArrayList<>();
        for(Element element: curGroup.getCreated()) {
            createdElement.add(element.getName());
        }
        listAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, createdElement);

        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(onClickListView);
        return view;
    }
    private AdapterView.OnItemClickListener onClickListView = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            WorkspaceFragment workspaceFragment = ((MainActivity) getActivity()).getWorkspaceFragment();
            if(!workspaceFragment.addElement(createdElement.get(position))) {

                Toast.makeText(getActivity(), "Full >:(", Toast.LENGTH_SHORT).show();
            }
        }
    };
}
