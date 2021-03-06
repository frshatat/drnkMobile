package com.drnkmobile.drnkAndroid.drnk.Views;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import com.drnkmobile.drnkAndroid.app.R;
import com.drnkmobile.drnkAndroid.drnk.DomainModel.BusinessBuilder;
import com.drnkmobile.drnkAndroid.drnk.DomainModel.BusinessFormatter;
import com.drnkmobile.drnkAndroid.drnk.DomainModel.Parser;
import com.drnkmobile.drnkAndroid.drnk.Customize.ExpandableListAdapter;
import com.drnkmobile.drnkAndroid.drnk.Connection.URLReader;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WeekFragment extends Fragment {
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    public static List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    private ArrayList<DownloadJSONAsyncTask> tasks;
    private URLReader reader;
    private String typeOfBusiness;
    private List listOfSpecials;
    private int positionForCellSelected;
    private List<String> listSpecials;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_week, container, false);
        tasks = new ArrayList<DownloadJSONAsyncTask>();
        expListView = (ExpandableListView) rootView.findViewById(R.id.expandableListView);
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Sunday");
        listDataHeader.add("Monday");
        listDataHeader.add("Tuesday");
        listDataHeader.add("Wednesday");
        listDataHeader.add("Thursday");
        listDataHeader.add("Friday");
        listDataHeader.add("Saturday");
        // preparing list data
        reader = new URLReader();
        requestData();
        return rootView;
    }

    private void requestData() {
        positionForCellSelected = getActivity().getIntent().getExtras().getInt("index");
        DownloadJSONAsyncTask task = new DownloadJSONAsyncTask();
        task.execute();
    }

    /*
    * Preparing the list data
    */
    private void prepareListData() {
        List<List<String>> ar = new ArrayList<>();
        for(int i = 0;i<listOfSpecials.size();i++){
            ar.add(new ArrayList<String>());
            ar.get(i).add((String) listOfSpecials.get(i));
            listDataChild.put(listDataHeader.get(i),ar.get(i));
        }



    }

    protected void updateDisplay() {
        prepareListData();
        listAdapter = new ExpandableListAdapter(this.getContext(), listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);


    }

    private class DownloadJSONAsyncTask extends AsyncTask<String, String,
            String> {
        @Override
        protected void onPreExecute() {

            tasks.add(this);
        }

        @Override
        protected String doInBackground(String... input) {
            String content = null;
            content = reader.getJSON("bars",drnk.currentCity);
            return content;
        }

        @Override
        protected void onPostExecute(String result) {
            Parser parser = new Parser(result);
            BusinessBuilder schedule = null;
            try {
                parser.positionForItemSelectedinTableView(positionForCellSelected);
                schedule = parser.parse("week");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            BusinessFormatter formatter = new BusinessFormatter();
            listOfSpecials = formatter.getBusinessSpecials(schedule);

            updateDisplay();
            tasks.remove(this);

        }
    }

}


