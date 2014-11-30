package org.freedesktop.libudev;


import com.sun.jna.Pointer;
import org.freedesktop.libudev.jna.StructUdevListEntry;
import org.freedesktop.libudev.jna.UdevLibrary;

/**
 * udev_list
 * <p/>
 * access to libudev generated lists.
 * Opaque object representing one entry in a list.
 * An entry contains a name, and optionally a value.
 */
public class ListEntry implements HasPointer<StructUdevListEntry> {

    private final StructUdevListEntry pointer;

    public ListEntry(final StructUdevListEntry pointer) {
        this.pointer = pointer;
    }

    @Override
    public StructUdevListEntry getPointer() {
        return this.pointer;
    }

    /**
     * @return the next entry from the list, NULL is no more entries are found.
     */
    public ListEntry getNext() {
        final StructUdevListEntry listEntryPointer = UdevLibrary.INSTANCE.udev_list_entry_get_next(getPointer());
        if (listEntryPointer == null) {
            return null;
        }
        else {
            return new ListEntry(listEntryPointer);
        }
    }

    /**
     * @param name name string to match
     * @return the entry where name matched, NULL if no matching entry is found.
     */
    public ListEntry getByName(String name) {
        final StructUdevListEntry listEntryPointer = UdevLibrary.INSTANCE.udev_list_entry_get_by_name(getPointer(),
                                                                                                      name);
        if (listEntryPointer == null) {
            return null;
        }
        else {
            return new ListEntry(listEntryPointer);
        }
    }

    /**
     * @return the name string of this entry.
     */
    public String getName() {
        return UdevLibrary.INSTANCE.udev_list_entry_get_name(getPointer());
    }

    /**
     * @return the value string of this entry.
     */
    public String getValue() {
        return UdevLibrary.INSTANCE.udev_list_entry_get_value(getPointer());
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

        return Pointer.nativeValue(pointer.getPointer()) == Pointer.nativeValue(that.pointer.getPointer());
    }

    @Override
    public int hashCode() {
        return pointer.hashCode();
    }
}
