package org.freedesktop.libudev;


/**
 * udev_list
 * <p/>
 * access to libudev generated lists
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

    public ListEntry getNext() {

    }

    public ListEntry getByName(String name) {

    }

    public String getName() {

    }

    public String getValue() {

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
