package org.freedesktop.libudev;


/**
 * udev_list
 * <p/>
 * access to libudev generated lists.
 * Opaque object representing one entry in a list.
 * An entry contains a name, and optionally a value.
 */
public class ListEntry implements HasPointer {

    private final long pointer;

    public ListEntry(final long pointer) {
        this.pointer = pointer;
    }

    @Override
    public long getPointer() {
        return this.pointer;
    }

    /**
     * @return the next entry from the list, NULL is no more entries are found.
     */
    public ListEntry getNext() {
        final long listEntryPointer = LibUdevJNI.listEntryGetNext(getPointer());
        if (listEntryPointer == 0) {
            return null;
        }
        else {
            return new ListEntry(listEntryPointer);
        }
    }

    /**
     *
     * @param name name string to match
     * @return the entry where name matched, NULL if no matching entry is found.
     */
    public ListEntry getByName(String name) {
        final long listEntryPointer = LibUdevJNI.listEntryGetByName(getPointer(),name);
        if (listEntryPointer == 0) {
            return null;
        }
        else {
            return new ListEntry(listEntryPointer);
        }
    }

    /**
     *
     * @return the name string of this entry.
     */
    public String getName() {
        return LibUdevJNI.listEntryGetName(getPointer());
    }

    /**
     *
     * @return the value string of this entry.
     */
    public String getValue() {
        return LibUdevJNI.listEntryGetValue(getPointer());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final ListEntry listEntry = (ListEntry) o;

        return pointer == listEntry.pointer;
    }

    @Override
    public int hashCode() {
        return (int) (pointer);
    }
}
