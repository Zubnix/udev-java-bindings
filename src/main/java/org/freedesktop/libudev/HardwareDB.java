package org.freedesktop.libudev;

/**
 * udev_hwdb — retrieve properties from the hardware database
 * <p>
 * Libudev hardware database interface.
 */
public class HardwareDB implements HasPointer {

    /**
     * Create a hardware database context to query properties for devices.
     *
     * @return a hwdb context.
     */
    public static HardwareDB create() {
        return new HardwareDB(LibUdevJNI.hwdbNew());
    }

    private final long pointer;

    public HardwareDB(final long pointer) {
        this.pointer = LibUdevJNI.hwdbRef(pointer);
    }

    @Override
    public long getPointer() {
        return pointer;
    }

    /**
     * Lookup a matching device in the hardware database.
     * The lookup key is a modalias string, whose formats are defined for the Linux kernel modules.
     * Examples are: pci:v00008086d00001C2D*, usb:v04F2pB221*.
     * The first entry of a list of retrieved properties is returned.
     *
     * @param modalias modalias string
     * @param flags (unused)
     * @return a list entry.
     */
    public ListEntry getProperties(final String modalias, final int flags) {
        return new ListEntry(LibUdevJNI.hwdbGetProperties(getPointer(),modalias,flags));
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

    @Override
    protected void finalize() throws Throwable {
        LibUdevJNI.hwdbUnref(getPointer());
        super.finalize();
    }
}
