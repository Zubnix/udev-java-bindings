package org.freedesktop.libudev;

import com.sun.jna.Pointer;
import org.freedesktop.libudev.jna.StructUdevDevice;
import org.freedesktop.libudev.jna.StructUdevMonitor;
import org.freedesktop.libudev.jna.UdevLibrary;

/**
 * udev_monitor — device event source
 * <p/>
 * Connects to a device event source.
 */
public class Monitor implements HasPointer<StructUdevMonitor> {

    private final StructUdevMonitor pointer;

    public Monitor(final StructUdevMonitor pointer) {
        this.pointer = pointer;
    }

    @Override
    public StructUdevMonitor getPointer() {
        return pointer;
    }

    /**
     * * Create new udev monitor and connect to a specified event source. Valid sources identifiers are "udev" and "kernel".
     * <p/>
     * Applications should usually not connect directly to the "kernel" events,
     * because the devices might not be useable at that time, before udev has configured them,
     * and created device nodes. Accessing devices at the same time as udev, might result in unpredictable behavior.
     * The "udev" events are sent out after udev has finished its event processing, all rules have been processed,
     * and needed device nodes are created.
     *
     * @param udev udev library context
     * @param name name of event source
     * @return a new udev monitor, or NULL, in case of an error
     */
    public static Monitor newFromNetlink(final LibUdev udev,
                                         final String name) {
        final StructUdevMonitor monitorPointer = UdevLibrary.INSTANCE.udev_monitor_new_from_netlink(udev.getPointer(),
                                                                                                    name);
        if (monitorPointer == null) {
            return null;
        }
        else {
            return new Monitor(monitorPointer);
        }
    }

    /**
     * Retrieve the udev library context the monitor was created with.
     *
     * @return the udev library context
     */
    public LibUdev getUdev() {
        return new LibUdev(UdevLibrary.INSTANCE.udev_ref(UdevLibrary.INSTANCE.udev_monitor_get_udev(getPointer())));
    }

    /**
     * Binds the udev_monitor socket to the event source.
     *
     * @return 0 on success, otherwise a negative error value.
     */
    public int enableReceiving() {
        return UdevLibrary.INSTANCE.udev_monitor_enable_receiving(getPointer());
    }

    /**
     * Set the size of the kernel socket buffer. This call needs the appropriate privileges to succeed.
     *
     * @param size the size in bytes
     * @return 0 on success, otherwise -1 on error.
     */
    public int setReceiveBufferSize(final int size) {
        return UdevLibrary.INSTANCE.udev_monitor_set_receive_buffer_size(getPointer(),
                                                                         size);
    }

    /**
     * Retrieve the socket file descriptor associated with the monitor.
     *
     * @return the socket file descriptor
     */
    public int getFd() {
        return UdevLibrary.INSTANCE.udev_monitor_get_fd(getPointer());
    }

    /**
     * Receive data from the udev monitor socket, allocate a new udev device, fill in the received data, and return the device.
     * <p/>
     * Only socket connections with uid=0 are accepted.
     * <p/>
     * The monitor socket is by default set to NONBLOCK.
     * A variant of poll() on the file descriptor returned by {@link #getFd()} should to be used to wake up when
     * new devices arrive, or alternatively the file descriptor switched into blocking mode.
     *
     * @return a new udev device, or NULL, in case of an error
     */
    public Device receiveDevice() {
        final StructUdevDevice devicePointer = UdevLibrary.INSTANCE.udev_monitor_receive_device(getPointer());
        if (devicePointer == null) {
            return null;
        }
        else {
            return new Device(devicePointer);
        }
    }

    /**
     * This filter is efficiently executed inside the kernel, and libudev subscribers will usually not be woken up for devices which do not match.
     * <p/>
     * The filter must be installed before the monitor is switched to listening mode.
     *
     * @param subsystem the subsystem value to match the incoming devices against
     * @param devtype   the devtype value to match the incoming devices against
     * @return 0 on success, otherwise a negative error value.
     */
    public int addMatchSubsystemDevtype(final String subsystem,
                                        final String devtype) {
        return UdevLibrary.INSTANCE.udev_monitor_filter_add_match_subsystem_devtype(getPointer(),
                                                                                    subsystem,
                                                                                    devtype);
    }

    /**
     * This filter is efficiently executed inside the kernel, and libudev subscribers will usually not be woken up for devices which do not match.
     * <p/>
     * The filter must be installed before the monitor is switched to listening mode.
     *
     * @param tag the name of a tag
     * @return 0 on success, otherwise a negative error value.
     */
    public int addMatchTag(final String tag) {
        return UdevLibrary.INSTANCE.udev_monitor_filter_add_match_tag(getPointer(),
                                                                      tag);
    }

    /**
     * Update the installed socket filter. This is only needed, if the filter was removed or changed.
     *
     * @return 0 on success, otherwise a negative error value.
     */
    public int filterUpdate() {
        return UdevLibrary.INSTANCE.udev_monitor_filter_update(getPointer());
    }

    /**
     * Remove all filters from monitor.
     *
     * @return 0 on success, otherwise a negative error value.
     */
    public int filterRemove() {
        return UdevLibrary.INSTANCE.udev_monitor_filter_remove(getPointer());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Monitor that = (Monitor) o;

        return Pointer.nativeValue(pointer.getPointer()) == Pointer.nativeValue(that.pointer.getPointer());
    }

    @Override
    public int hashCode() {
        return pointer.hashCode();
    }

    @Override
    protected void finalize() throws Throwable {
        UdevLibrary.INSTANCE.udev_monitor_unref(getPointer());
        super.finalize();
    }
}
