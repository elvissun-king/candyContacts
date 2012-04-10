package com.smart.candycontacts.contacts;

import com.smart.candycontacts.R;

import android.app.Activity;
import android.app.Fragment;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ContactsFragment extends Fragment {

    // Contacts List
    private ListView contactsList;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        return inflater.inflate(R.layout.contactsfragment_layout, container, false);
    }

    @Override
    public void onStart() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 100; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("contact", i);
            list.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), list,
                android.R.layout.simple_list_item_1, new String[] {
                    "contact"
                }, new int[] {
                    android.R.id.text1
                });
        super.onStart();
        contactsList = (ListView)getActivity().findViewById(R.id.contactsList);
        // Cursor c =
        // getActivity().getContentResolver().query(People.CONTENT_URI, null,
        // null, null,
        // null);
        // getActivity().startManagingCursor(c);
        // ListAdapter adapter = new SimpleCursorAdapter(getActivity(),
        // android.R.layout.simple_list_item_1, c, new String[] {
        // People.NAME
        // }, new int[] {
        // android.R.id.text1
        // });
        contactsList.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        contactsList = null;

    }

    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        // TODO Auto-generated method stub
        super.onDetach();
    }

    /*
     * // Share events with the CandyContactsActivity public interface
     * OnContactsListSelectedListener { public void onContactsListSelected(Uri
     * contactsUri); }
     */

}
