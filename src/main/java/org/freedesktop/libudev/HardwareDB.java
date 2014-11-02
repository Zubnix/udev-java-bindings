package org.freedesktop.libudev;

/**
 * udev_hwdb
 * <p/>
 * access to the static hardware properties database
 */
public class HardwareDB implements HasPointer{

    public static HardwareDB create(){

    }

    private final long pointer;

    public HardwareDB(final long pointer) {
        this.pointer = pointer;
    }

    @Override
    public long getPointer() {
        return pointer;
    }

    public ListEntry getProperties(String modalias, int flags){

    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final HardwareDB that = (HardwareDB) o;

        return pointer == that.pointer;

    }

    @Override
    public int hashCode() {
        return (int) (pointer);
    }
}
