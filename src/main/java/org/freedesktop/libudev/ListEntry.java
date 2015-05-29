package org.freedesktop.libudev;


import com.sun.jna.Pointer;

import org.freedesktop.libudev.jna.StringUtil;
import org.freedesktop.libudev.jna.UdevLibrary;

/**
 * udev_list
 * <p/>
 * access to libudev generated lists.
 * Opaque object representing one entry in a list.
 * An entry contains a name, and optionally a value.
 */
public class ListEntry implements HasPointer {

    public static ListEntry create(final Pointer pointer) {
        return pointer == null ? null : new ListEntry(pointer);
    }

    private final Pointer pointer;

    /**
     * Use static {@link #create(Pointer)} factory method.
     */
    public ListEntry(final Pointer pointer) {
        assert pointer != null;
        this.pointer = pointer;
    }

    @Override
    public Pointer getPointer() {
        return this.pointer;
    }

    /**
     * @return the next entry from the list, NULL is no more entries are found.
     */
    public ListEntry getNext() {
        return ListEntry.create(UdevLibrary.INSTANCE().udev_list_entry_get_next(getPointer()));
    }

    /**
     * @param name name string to match
     * @return the entry where name matched, NULL if no matching entry is found.
     */
    public ListEntry getByName(String name) {
        return ListEntry.create(UdevLibrary.INSTANCE().udev_list_entry_get_by_name(getPointer(),
                                                                                   StringUtil.asPointer(name)));
    }

    /**
     * @return the name string of this entry.
     */
    public String getName() {
        return StringUtil.fromPointer(UdevLibrary.INSTANCE().udev_list_entry_get_name(getPointer()));
    }

    /**
     * @return the value string of this entry.
     */
    public String getValue() {
        return StringUtil.fromPointer(UdevLibrary.INSTANCE().udev_list_entry_get_value(getPointer()));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final ListEntry that = (ListEntry) o;

        return pointer.equals(that.pointer);
    }

    @Override
    public int hashCode() {
        return pointer.hashCode();
    }
}
