package com.example.zkelly3.doodlegs;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.lang.reflect.Constructor;
import java.util.List;

public class WorkspaceFragment extends Fragment {
    private Button elementAButton;
    private Button elementBButton;
    private Button combineButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.workspace, container, false);
        elementAButton = view.findViewById(R.id.element_A);
        elementBButton = view.findViewById(R.id.element_B);
        combineButton = view.findViewById(R.id.combine_btn);
        elementAButton.setOnClickListener(new CancelListener(elementAButton.getText().toString()));
        elementBButton.setOnClickListener(new CancelListener(elementBButton.getText().toString()));
        combineButton.setOnClickListener(new CombineListener());
        return view;
    }
    public boolean addElement(String elementName) {
        if(addToElement(elementAButton, elementName)) {
            return true;
        }
        else if(addToElement(elementBButton, elementName)) {
            return true;
        }
        else return false;
    }
    private boolean addToElement(Button where, String elementName) {
        if(isOccupied(where)) {
            return false;
        }
        else {
            where.setText(elementName);
            where.setEnabled(true);
            return true;
        }
    }
    private boolean isOccupied(Button where) {
        return where.isEnabled();
    }
    private class CancelListener implements Button.OnClickListener{
        private final String defaultString;

        CancelListener(String defaultString) {
            this.defaultString = defaultString;
        }
        @Override
        public void onClick(View v) {
            Button btn = (Button) v;
            btn.setEnabled(false);
            btn.setText(this.defaultString);
        }
    }
    private class CombineListener implements Button.OnClickListener{

        @Override
        public void onClick(View v) {
            boolean full = isOccupied(elementAButton) && isOccupied(elementBButton);
            if(!full) {
                Toast.makeText(getActivity(), "Not full >:(", Toast.LENGTH_SHORT).show();
            }
            else {
                List<Pair<String, Boolean>> res = ((MainActivity)getActivity()).combine(elementAButton.getText().toString(), elementBButton.getText().toString());
                elementAButton.callOnClick();
                elementBButton.callOnClick();
                StringBuilder message = new StringBuilder();
                if(res.size() == 0) {
                    message = new StringBuilder("Invalid Combination");
                }
                else {
                    for(Pair<String, Boolean> res_pair: res) {
                        if(res_pair.second) {
                            message.append("You created a new element ").append(res_pair.first).append(" !\n");
                        }
                        else {
                            message.append("You created ").append(res_pair.first).append(" again !\n");
                        }
                    }
                }

                new AlertDialog.Builder(getActivity())
                        .setTitle("Result")
                        .setMessage(message.toString())
                        .show();
            }
        }
    }
}
