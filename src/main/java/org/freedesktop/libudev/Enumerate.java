package org.freedesktop.libudev;

/**
 * udev_enumerate — lookup and sort sys devices
 * <p>
 * Lookup devices in the sys filesystem, filter devices by properties, and return a sorted list of devices.
 */
public class Enumerate implements HasPointer {

    /**
     * Create an enumeration context to scan /sys.
     *
     * @return an enumeration context.
     */
    public static Enumerate create() {
        return new Enumerate(LibUdevJNI.enumerateNew());
    }

    private final long pointer;

    public Enumerate(final long pointer) {
        this.pointer = LibUdevJNI.enumerateRef(pointer);
    }

    @Override
    public long getPointer() {
        return this.pointer;
    }

    /**
     * Get the udev library context.
     *
     * @return the context.
     */
    public LibUdev getUdev() {
        return new LibUdev(LibUdevJNI.enumerateGetUdev(getPointer()));
    }

    /**
     * Match only devices belonging to a certain kernel subsystem.
     *
     * @param subsystem filter for a subsystem of the device to include in the list
     * @return 0 on success, otherwise a negative error value.
     */
    public int addMatchSubsystem(final String subsystem) {
        return LibUdevJNI.enumerateAddMatchSubsystem(getPointer(), subsystem);
    }

    /**
     * Match only devices not belonging to a certain kernel subsystem.
     *
     * @param subsystem filter for a subsystem of the device to exclude from the list
     * @return 0 on success, otherwise a negative error value.
     */
    public int addNomatchSubsystem(final String subsystem) {
        return LibUdevJNI.enumerateAddNomatchSubsystem(getPointer(), subsystem);
    }

    /**
     * Match only devices with a certain /sys device attribute.
     *
     * @param sysattr filter for a sys attribute at the device to include in the list
     * @param value   optional value of the sys attribute
     * @return 0 on success, otherwise a negative error value.
     */
    public int addMatchSysattr(final String sysattr, final String value) {
        return LibUdevJNI.enumerateAddMatchSysattr(getPointer(), sysattr, value);
    }

    /**
     * Match only devices not having a certain /sys device attribute.
     *
     * @param sysattr filter for a sys attribute at the device to exclude from the list
     * @param value   optional value of the sys attribute
     * @return 0 on success, otherwise a negative error value.
     */
    public int addNomatchSysattr(final String sysattr, final String value) {
        return LibUdevJNI.enumerateAddNomatchSysattr(getPointer(), sysattr, value);
    }

    /**
     * Match only devices with a certain property.
     *
     * @param property filter for a property of the device to include in the list
     * @param value    value of the property
     * @return 0 on success, otherwise a negative error value.
     */
    public int addMatchProperty(final String property, final String value) {
        return LibUdevJNI.enumerateAddMatchProperty(getPointer(), property, value);
    }

    /**
     * Match only devices with a given /sys device name.
     *
     * @param sysname filter for the name of the device to include in the list
     * @return 0 on success, otherwise a negative error value.
     */
    public int addMatchSysname(final String sysname) {
        return LibUdevJNI.enumerateAddMatchSysname(getPointer(), sysname);
    }

    /**
     * Match only devices with a certain tag.
     *
     * @param tag filter for a tag of the device to include in the list
     * @return 0 on success, otherwise a negative error value.
     */
    public int addMatchTag(final String tag) {
        return LibUdevJNI.enumerateAddMatchTag(getPointer(), tag);
    }

    /**
     * Return the devices on the subtree of one given device. The parent itself is included in the list.
     *
     * @param parent parent device where to start searching
     * @return 0 on success, otherwise a negative error value.
     */
    public int addMatchParent(final Device parent) {
        return LibUdevJNI.enumerateAddMatchParent(getPointer(), parent.getPointer());
    }

    /**
     * Match only devices which udev has set up already. This makes sure, that the device node permissions and context
     * are properly set and that network devices are fully renamed.
     * <p>
     * Usually, devices which are found in the kernel but not already handled by udev, have still pending events.
     * Services should subscribe to monitor events and wait for these devices to become ready,
     * instead of using uninitialized devices.
     * <p>
     * For now, this will not affect devices which do not have a device node and are not network interfaces.
     *
     * @return 0 on success, otherwise a negative error value.
     */
    public int addMatchIsInitialized() {
        return LibUdevJNI.enumerateAddMatchIsInitialized(getPointer());
    }

    /**
     * Add a device to the list of devices, to retrieve it back sorted in dependency order.
     *
     * @param syspath path of a device
     * @return 0 on success, otherwise a negative error value.
     */
    public int addSyspath(final String syspath) {
        return LibUdevJNI.enumerateAddSyspath(getPointer(), syspath);
    }

    /**
     * Scan /sys for all devices which match the given filters. No matches will return all currently available devices.
     *
     * @return 0 on success, otherwise a negative error value.
     */
    public int scanDevices() {
        return LibUdevJNI.enumerateScanDevices(getPointer());
    }

    /**
     * Scan /sys for all kernel subsystems, including buses, classes, drivers.
     *
     * @return 0 on success, otherwise a negative error value.
     */
    public int scanSubsystems() {
        return LibUdevJNI.enumerateScanSubsystems(getPointer());
    }

    /**
     * Get the first entry of the sorted list of device paths.
     *
     * @return a list entry.
     */
    public ListEntry getListEntry() {
        return new ListEntry(LibUdevJNI.enumerateGetListEntry(getPointer()));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Enumerate enumerate = (Enumerate) o;

        return pointer == enumerate.pointer;

    }

    @Override
    public int hashCode() {
        return (int) (pointer);
    }

    @Override
    protected void finalize() throws Throwable {
        LibUdevJNI.enumerateUnref(getPointer());
        super.finalize();
    }
}
