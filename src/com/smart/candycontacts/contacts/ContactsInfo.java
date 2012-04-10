
package com.smart.candycontacts.contacts;

import android.graphics.Bitmap;

public class ContactsInfo {
    /*
     * contact name
     */
    private String name;

    /*
     * contact id
     */
    private Long contactId;

    /*
     * contact photo id
     */
    private Bitmap photo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhotoId(Bitmap photo) {
        this.photo = photo;
    }

}
