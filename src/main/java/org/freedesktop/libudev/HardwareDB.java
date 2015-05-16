package org.freedesktop.libudev;

import com.sun.jna.Pointer;

import org.freedesktop.libudev.jna.StringUtil;
import org.freedesktop.libudev.jna.UdevLibrary;

/**
 * udev_hwdb — retrieve properties from the hardware database
 * <p/>
 * Libudev hardware database interface.
 */
public class HardwareDB implements HasPointer {

    /**
     * Create a hardware database context to query properties for devices.
     *
     * @return a hwdb context.
     */
    public static HardwareDB create(final LibUdev libUdev) {
        return new HardwareDB(UdevLibrary.INSTANCE().udev_hwdb_new(libUdev.getPointer()));
    }

    private final Pointer pointer;

    public HardwareDB(final Pointer pointer) {
        this.pointer = pointer;
    }

    @Override
    public Pointer getPointer() {
        return pointer;
    }

    /**
     * Lookup a matching device in the hardware database.
     * The lookup key is a modalias string, whose formats are defined for the Linux kernel modules.
     * Examples are: pci:v00008086d00001C2D*, usb:v04F2pB221*.
     * The first entry of a list of retrieved properties is returned.
     *
     * @param modalias modalias string
     * @param flags    (unused)
     * @return a list entry.
     */
    public ListEntry getProperties(final String modalias,
                                   final int flags) {
        final Pointer listPointer = getPointer();
        return listPointer == null ? null : new ListEntry(UdevLibrary.INSTANCE().udev_hwdb_get_properties_list_entry(listPointer,
                                                                                                                     StringUtil.asPointer(modalias),
                                                                                                                     flags));
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

        return pointer.equals(that.pointer);

    }

    @Override
    public int hashCode() {
        return pointer.hashCode();
    }

    @Override
    protected void finalize() throws Throwable {
        UdevLibrary.INSTANCE().udev_hwdb_unref(getPointer());
        super.finalize();
    }
}
