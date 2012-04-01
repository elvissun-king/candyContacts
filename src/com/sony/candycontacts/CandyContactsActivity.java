
package com.sony.candycontacts;

import com.sony.candycontacts.contacts.ContactsFragment;
import com.sony.candycontacts.contacts.ContactsFragment.OnContactsListSelectedListener;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.LinearLayout;

public class CandyContactsActivity extends Activity implements OnContactsListSelectedListener,
        ActionBar.TabListener {
    private static final String TAG = CandyContactsActivity.class.getSimpleName();

    private ContactsFragment mContactsFragment;

    private CallLogFragment mCallLogFragment;

    private ActionBar.Tab mCallLogTab;

    private ActionBar.Tab mContactsTab;

    // Container for Tabs
    private LinearLayout container;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set ActionBar transparent
        requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        setContentView(R.layout.candycontacts_layout);
        final ActionBar actionBar = getActionBar();
        Log.i(TAG, "actionBar = " + actionBar);
        if (actionBar == null) {
            Log.i(TAG, "actionBar == null!");
        } else {
            Log.i(TAG, "actionBar != null");
        }
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        // Remove the activity title to make space for tab-
        actionBar.setDisplayShowTitleEnabled(false);
        mContactsFragment = new ContactsFragment();
        mCallLogFragment = new CallLogFragment();
        container = (LinearLayout)findViewById(R.id.container);
        // Initial status show Contacts fragment.
        FragmentManager fragmentMgr = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentMgr.beginTransaction();
        fragmentTransaction.add(R.id.container, mContactsFragment);
        fragmentTransaction.commit();
        actionBar.addTab(mContactsTab = actionBar.newTab().setText("Contacts-Tab")
                .setTabListener(this));
        actionBar.addTab(mCallLogTab = actionBar.newTab().setText("CallLog-Tab")
                .setTabListener(this));
    }

    /*
     * (non-Javadoc)
     *
     * @see com.sony.candycontacts.contacts.ContactsFragment.
     * OnContactsListSelectedListener#onContactsListSelected(android.net.Uri)
     */
    public void onContactsListSelected(Uri contactsUri) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * android.app.ActionBar.TabListener#onTabSelected(android.app.ActionBar
     * .Tab, android.app.FragmentTransaction)
     */
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub
        if (tab == mCallLogTab) {
            ft.add(R.id.container, mCallLogFragment);
        } else if (tab == mContactsTab) {
            ft.add(R.id.container, mContactsFragment);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * android.app.ActionBar.TabListener#onTabUnselected(android.app.ActionBar
     * .Tab, android.app.FragmentTransaction)
     */
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * android.app.ActionBar.TabListener#onTabReselected(android.app.ActionBar
     * .Tab, android.app.FragmentTransaction)
     */
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub

    }
}
