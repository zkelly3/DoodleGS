package com.example.zkelly3.doodlegs;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class Fragment3 extends Fragment {
    private ListView listView;
    private ListAdapter listAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);
        listView = (ListView) view.findViewById(R.id.listGroup);

        String[] str = {"1", "2", "3"};
        listAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, str);

        listView.setAdapter(listAdapter);
        return view;
    }
}
