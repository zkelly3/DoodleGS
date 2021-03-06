package com.example.zkelly3.doodlegs;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.widget.TextView;

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

public class MainActivity extends AppCompatActivity {
    private Map<String, Group> allGroups;
    private Map<String, Element> allElements;
    private WorkspaceFragment workspaceFragment;
    private Combiner combiner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allGroups = new HashMap<>();
        allElements = new HashMap<>();

        BufferedReader elementReader = null;
        try {
            elementReader = new BufferedReader(new InputStreamReader(getAssets().open("elements.txt")));
            String eLine;
            while((eLine = elementReader.readLine()) != null) {
                String[] tokens = eLine.split(" -> ");
                Group group = new Group(tokens[0]);
                allGroups.put(group.getName(), group);
                String[] elements = tokens[1].split(" ");
                for(String name: elements) {
                    Element element;
                    if(name.compareTo("baby") == 0 || name.compareTo("live") == 0) {
                        element = new Element(name, group, Boolean.TRUE);
                    }
                    else {
                        element = new Element(name, group, Boolean.FALSE);
                    }
                    allElements.put(name, element);
                    group.pushElement(element);
                }
            }
        } catch(IOException e) {
            //log the exception
        } finally {
            if (elementReader != null) {
                try {
                    elementReader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }

        combiner = new Combiner();
        BufferedReader ruleReader = null;
        try {
            ruleReader = new BufferedReader(new InputStreamReader(getAssets().open("rules.txt")));
            String rLine;
            while((rLine = ruleReader.readLine()) != null) {
                String[] tokens = rLine.split(" -> ");
                String[] pair = tokens[1].split(" ");
                String[] arr = tokens[2].split(" ");
                Element a = allElements.get(pair[0]);
                Element b = allElements.get(pair[1]);
                List<Element> result = new ArrayList<>();
                for(String option: arr) {
                    result.add(allElements.get(option));
                }

                Rule newRule;
                if(tokens[0].compareTo("BASIC") == 0) {
                    newRule = new BasicRule(a, b, result);
                }
                else {
                    newRule = new ProbRule(a, b, result);
                }
                combiner.addRule(newRule);
            }
        } catch(IOException e) {
            //log the exception
        } finally {
            if (ruleReader != null) {
                try {
                    ruleReader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }

        GroupMenuFragment groupMenuFragment = new GroupMenuFragment();
        getFragmentManager().beginTransaction()
                .replace(R.id.placeholder, groupMenuFragment,"group_menu")
                .commit();

        FragmentManager fragmentManager = getFragmentManager();
        workspaceFragment = (WorkspaceFragment) fragmentManager.findFragmentById(R.id.fragment1);
        TextView progrees_text = findViewById(R.id.progress_text);
        progrees_text.setText(String.format("progress: %d/%d", createdElementCnt(), allElements.size()));
    }
    public Map<String, Group> getAllGroups() {
        return this.allGroups;
    }
    public Map<String, Element> getAllElements() {
        return this.allElements;
    }

    public WorkspaceFragment getWorkspaceFragment() {
        return workspaceFragment;
    }

    public List<Pair<String, Boolean>> combine(String A, String B) {
        Element element_A = allElements.get(A);
        Element element_B = allElements.get(B);
        List<Element> created = combiner.combine(element_A, element_B);
        List<Pair<String, Boolean>> result = new ArrayList<>();
        for(Element element: created) {
            boolean newCreated = element.getGroup().createElement(element);
            result.add(new Pair<>(element.getName(), newCreated));
        }
        refresh();
        return result;
    }

    private int createdElementCnt() {
        int sum = 0;
        for(Group group: allGroups.values()) {
            sum += group.getCreated().size();
        }
        return sum;
    }

    private void refresh() {
        TextView progrees_text = findViewById(R.id.progress_text);
        progrees_text.setText(String.format("progress: %d/%d", createdElementCnt(), allElements.size()));
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.popBackStack();
    }

    public void navigateToElementMenu(String groupName) {
        ElementMenuFragment elementMenuFragment = null;
        try {
            elementMenuFragment = ElementMenuFragment.class.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Insert the fragment by replacing any existing fragment
        Bundle args = new Bundle();
        args.putString("group_name", groupName);
        elementMenuFragment.setArguments(args);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.placeholder, elementMenuFragment, "element_menu")
                .addToBackStack("element_menu")
                .commit();
    }
}
