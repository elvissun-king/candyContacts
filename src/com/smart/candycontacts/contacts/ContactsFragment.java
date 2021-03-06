
package com.smart.candycontacts.contacts;

import com.smart.candycontacts.R;

import android.app.Activity;
import android.app.Fragment;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ContactsFragment extends Fragment {

    // Contacts List
    private ListView mContactsList;

    private List<ContactsInfo> mListData = new ArrayList<ContactsInfo>();

    private static final String[] PHONES_PROJECTION = new String[] {
            Phone.DISPLAY_NAME, Phone.NUMBER, Phone.PHOTO_ID, Phone.CONTACT_ID
    };

    // Contact name index
    private static final int PHONES_DISPLAY_NAME_INDEX = 0;

    // Contact photo id index
    private static final int PHONES_PHOTO_ID_INDEX = 2;

    // Contact ID index
    private static final int PHONES_CONTACT_ID_INDEX = 3;

    private ContactListAdapter mAdapter;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getContacts();
        mAdapter = new ContactListAdapter(getActivity(), mListData);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.contactsfragment_layout, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        mContactsList = (ListView)getActivity().findViewById(R.id.contactsList);
        mContactsList.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    // Add Search Menu and implement the call back method.
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Place an action bar item for searching.
        MenuItem item = menu.add("Search");
        item.setIcon(android.R.drawable.ic_menu_search);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        SearchView sv = new SearchView(getActivity());
        sv.setOnQueryTextListener(new OnQueryTextListener() {

            public boolean onQueryTextSubmit(String query) {
                // TODO Auto-generated method stub
                return false;
            }

            public boolean onQueryTextChange(String newText) {
                // TODO Auto-generated method stub
                return false;
            }
        });
        item.setActionView(sv);
    }

    private class ContactListAdapter extends BaseAdapter {
        private Context mContext;

        private List<ContactsInfo> mData;

        public ContactListAdapter(Context context, List<ContactsInfo> data) {
            mContext = context;
            mData = data;
        }

        /*
         * (non-Javadoc)
         *
         * @see android.widget.Adapter#getCount()
         */
        public int getCount() {
            return mData.size();
        }

        /*
         * (non-Javadoc)
         *
         * @see android.widget.Adapter#getItem(int)
         */
        public Object getItem(int position) {
            return mData.get(position);
        }

        /*
         * (non-Javadoc)
         *
         * @see android.widget.Adapter#getItemId(int)
         */
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        /*
         * (non-Javadoc)
         *
         * @see android.widget.Adapter#getView(int, android.view.View,
         * android.view.ViewGroup)
         */
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(mContext).inflate(R.layout.contact_list_item,
                        null);
                holder.mImage = (ImageView)convertView.findViewById(R.id.contactPhoto);
                holder.mText = (TextView)convertView.findViewById(R.id.contactName);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder)convertView.getTag();
            }
            // Set contact name
            holder.mText.setText(mData.get(position).getName());
            // Set contact photo
            holder.mImage.setImageBitmap(mData.get(position).getPhoto());
            return convertView;
        }
    }

    /**
     * get the contacts info, add contacts info into mListData.
     */
    private void getContacts() {
        ContentResolver resolver = getActivity().getContentResolver();

        Cursor phoneCursor = resolver.query(Phone.CONTENT_URI, PHONES_PROJECTION, null, null, null);
        if (phoneCursor != null) {
            while (phoneCursor.moveToNext()) {
                // Get contact name
                String contactName = phoneCursor.getString(PHONES_DISPLAY_NAME_INDEX);
                // Get Contact ID
                Long contactId = phoneCursor.getLong(PHONES_CONTACT_ID_INDEX);
                // Get Contact photo id
                Long photoId = phoneCursor.getLong(PHONES_PHOTO_ID_INDEX);
                // Get Contact photo Bitmap
                Bitmap contactPhoto = null;
                // photoId > 0, means contact has photo, else contact does not
                // have photo.
                if (photoId > 0) {
                    Uri uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI,
                            contactId);
                    InputStream input = ContactsContract.Contacts.openContactPhotoInputStream(
                            resolver, uri);
                    contactPhoto = BitmapFactory.decodeStream(input);
                } else {
                    contactPhoto = BitmapFactory.decodeResource(getActivity().getResources(),
                            R.drawable.default_contact_photo);
                }
                ContactsInfo info = new ContactsInfo();
                info.setContactId(contactId);
                info.setName(contactName);
                info.setPhotoId(contactPhoto);
                mListData.add(info);
            }
        }
    }

    public static class ViewHolder {

        private ImageView mImage;

        private TextView mText;
    }
}
