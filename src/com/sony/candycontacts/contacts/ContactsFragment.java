/*********************************************************************
 *  ____                      _____      _                           *
 * / ___|  ___  _ __  _   _  | ____|_ __(_) ___ ___ ___  ___  _ __   *
 * \___ \ / _ \| '_ \| | | | |  _| | '__| |/ __/ __/ __|/ _ \| '_ \  *
 *  ___) | (_) | | | | |_| | | |___| |  | | (__\__ \__ \ (_) | | | | *
 * |____/ \___/|_| |_|\__, | |_____|_|  |_|\___|___/___/\___/|_| |_| *
 *                    |___/                                          *
 *                                                                   *
 *********************************************************************
 * Copyright 2012 Sony Ericsson Mobile Communications AB.         *
 * All rights, including trade secret rights, reserved.              *
 *********************************************************************/

/**
 * @file ContactsFragment.java
 *
 * @author Fei Sun (fei.sun@sonyericsson.com)
 */

package com.sony.candycontacts.contacts;

import com.sony.candycontacts.R;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * @author 28850470
 *
 */
public class ContactsFragment extends Fragment {

    // Contacts List
    private ListView contactsList;

    private OnContactsListSelectedListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnContactsListSelectedListener)activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnContactsListSelectedListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        contactsList = (ListView)getActivity().findViewById(R.id.contactsList);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        return inflater.inflate(R.layout.contactsfragment_layout, container, false);
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

    // Share events with the CandyContactsActivity
    public interface OnContactsListSelectedListener {
        public void onContactsListSelected(Uri contactsUri);
    }

}
