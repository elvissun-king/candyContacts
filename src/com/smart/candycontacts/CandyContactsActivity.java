package com.smart.candycontacts;

import com.smart.candycontacts.calllog.CallLogFragment;
import com.smart.candycontacts.contacts.ContactsFragment;
import com.smart.candycontacts.message.MessageFragment;
import com.smart.candycontacts.stats.StatsFragment;
import com.smart.candycontacts.R;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

public class CandyContactsActivity extends Activity {
    private static final String TAG = CandyContactsActivity.class.getSimpleName();

    private static final String CONTACT_TAG = "contact";

    private static final String CALLLOG_TAG = "calllog";

    private static final String MESSAGE_TAG = "message";

    private static final String STATS_TAG = "stats";

    private ContactsFragment mContactsFragment;

    private CallLogFragment mCallLogFragment;

    private MessageFragment mMessageFragment;

    private StatsFragment mStatsFragment;

    private Tab mCallLogTab;

    private Tab mContactsTab;

    private Tab mMessageTab;

    private Tab mStatsTab;

    // Container for Tabs
    // private LinearLayout container;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.candycontacts_layout);
        final ActionBar actionBar = getActionBar();
        Log.i(TAG, "actionBar = " + actionBar);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        // Remove the activity title to make space for tab-
        actionBar.setDisplayShowTitleEnabled(false);
        mContactsFragment = new ContactsFragment();
        mCallLogFragment = new CallLogFragment();
        // container = (LinearLayout)findViewById(R.id.container);
        // Initial status show Contacts fragment.
        // FragmentManager fragmentMgr = getFragmentManager();
        // FragmentTransaction fragmentTransaction =
        // fragmentMgr.beginTransaction();
        // fragmentTransaction.add(R.id.container, mContactsFragment);
        // fragmentTransaction.commit();
        mContactsTab = actionBar
                .newTab()
                .setText("Contacts")
                .setTabListener(
                        new TabListener<ContactsFragment>(this, CONTACT_TAG, ContactsFragment.class));
        mCallLogTab = actionBar
                .newTab()
                .setText("Calllog")
                .setTabListener(
                        new TabListener<CallLogFragment>(this, CALLLOG_TAG, CallLogFragment.class));
        mMessageTab = actionBar
                .newTab()
                .setText("Message")
                .setTabListener(
                        new TabListener<MessageFragment>(this, MESSAGE_TAG, MessageFragment.class));
        mStatsTab = actionBar
                .newTab()
                .setText("Stats")
                .setTabListener(
                        new TabListener<StatsFragment>(this, STATS_TAG, StatsFragment.class));
        actionBar.addTab(mContactsTab);
        actionBar.addTab(mCallLogTab);
        actionBar.addTab(mMessageTab);
        actionBar.addTab(mStatsTab);
    }

    public static class TabListener<T extends Fragment> implements ActionBar.TabListener {
        private Fragment mFragment;

        private final Activity mActivity;

        private final String mTag;

        private final Class<T> mClass;

        /**
         * Constructor used each time a new tab is created.
         * 
         * @param activity
         *            The host Activity, used to instantiate the fragment
         * @param tag
         *            The identifier tag for the fragment
         * @param clz
         *            The fragment's Class, used to instantiate the fragment
         */
        public TabListener(Activity activity, String tag, Class<T> clz) {
            mActivity = activity;
            mTag = tag;
            mClass = clz;
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * android.app.ActionBar.TabListener#onTabSelected(android.app.ActionBar
         * .Tab, android.app.FragmentTransaction)
         */
        public void onTabSelected(Tab tab, FragmentTransaction ft) {
            Log.i(TAG, "onTabSelected---" + "tab = " + tab.getText());
            // Check if the fragment is already initialized
            if (mFragment == null) {
                // If not, instantiate and add it to the activity
                mFragment = Fragment.instantiate(mActivity, mClass.getName());
                ft.add(R.id.container, mFragment, mTag);
            } else {
                // If it exists, simply attach it in order to show it
                ft.attach(mFragment);
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
            if (mFragment != null) {
                // Detach the fragment, because another one is being attached
                ft.detach(mFragment);
            }
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * android.app.ActionBar.TabListener#onTabReselected(android.app.ActionBar
         * .Tab, android.app.FragmentTransaction)
         */
        public void onTabReselected(Tab tab, FragmentTransaction ft) {
            // User selected the already selected tab. Usually do nothing.
        }

    }
}
