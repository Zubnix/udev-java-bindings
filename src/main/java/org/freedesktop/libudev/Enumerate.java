package org.freedesktop.libudev;

import com.sun.jna.Pointer;

import org.freedesktop.libudev.jna.StringUtil;
import org.freedesktop.libudev.jna.UdevLibrary;

/**
 * udev_enumerate — lookup and sort sys devices
 * <p/>
 * Lookup devices in the sys filesystem, filter devices by properties, and return a sorted list of devices.
 */
public class Enumerate implements HasPointer {

    /**
     * Create an enumeration context to scan /sys.
     *
     * @return an enumeration context.
     */
    public static Enumerate create(LibUdev libUdev) {
        return new Enumerate(UdevLibrary.INSTANCE().udev_enumerate_new(libUdev.getPointer()));
    }

    private final Pointer pointer;

    public Enumerate(final Pointer pointer) {
        this.pointer = pointer;
    }

    @Override
    public Pointer getPointer() {
        return this.pointer;
    }

    /**
     * Get the udev library context.
     *
     * @return the context.
     */
    public LibUdev getUdev() {
        return new LibUdev(UdevLibrary.INSTANCE().udev_ref(UdevLibrary.INSTANCE().udev_enumerate_get_udev(getPointer())));
    }

    /**
     * Match only devices belonging to a certain kernel subsystem.
     *
     * @param subsystem filter for a subsystem of the device to include in the list
     * @return 0 on success, otherwise a negative error value.
     */
    public int addMatchSubsystem(final String subsystem) {
        return UdevLibrary.INSTANCE().udev_enumerate_add_match_subsystem(getPointer(),
                                                                         StringUtil.asPointer(subsystem));
    }

    /**
     * Match only devices not belonging to a certain kernel subsystem.
     *
     * @param subsystem filter for a subsystem of the device to exclude from the list
     * @return 0 on success, otherwise a negative error value.
     */
    public int addNomatchSubsystem(final String subsystem) {
        return UdevLibrary.INSTANCE().udev_enumerate_add_nomatch_subsystem(getPointer(),
                                                                           StringUtil.asPointer(subsystem));
    }

    /**
     * Match only devices with a certain /sys device attribute.
     *
     * @param sysattr filter for a sys attribute at the device to include in the list
     * @param value   optional value of the sys attribute
     * @return 0 on success, otherwise a negative error value.
     */
    public int addMatchSysattr(final String sysattr,
                               final String value) {
        return UdevLibrary.INSTANCE().udev_enumerate_add_match_sysattr(getPointer(),
                                                                       StringUtil.asPointer(sysattr),
                                                                       StringUtil.asPointer(value));
    }

    /**
     * Match only devices not having a certain /sys device attribute.
     *
     * @param sysattr filter for a sys attribute at the device to exclude from the list
     * @param value   optional value of the sys attribute
     * @return 0 on success, otherwise a negative error value.
     */
    public int addNomatchSysattr(final String sysattr,
                                 final String value) {
        return UdevLibrary.INSTANCE().udev_enumerate_add_nomatch_sysattr(getPointer(),
                                                                         StringUtil.asPointer(sysattr),
                                                                         StringUtil.asPointer(value));
    }

    /**
     * Match only devices with a certain property.
     *
     * @param property filter for a property of the device to include in the list
     * @param value    value of the property
     * @return 0 on success, otherwise a negative error value.
     */
    public int addMatchProperty(final String property,
                                final String value) {
        return UdevLibrary.INSTANCE().udev_enumerate_add_match_property(getPointer(),
                                                                        StringUtil.asPointer(property),
                                                                        StringUtil.asPointer(value));
    }

    /**
     * Match only devices with a given /sys device name.
     *
     * @param sysname filter for the name of the device to include in the list
     * @return 0 on success, otherwise a negative error value.
     */
    public int addMatchSysname(final String sysname) {
        return UdevLibrary.INSTANCE().udev_enumerate_add_match_sysname(getPointer(),
                                                                       StringUtil.asPointer(sysname));
    }

    /**
     * Match only devices with a certain tag.
     *
     * @param tag filter for a tag of the device to include in the list
     * @return 0 on success, otherwise a negative error value.
     */
    public int addMatchTag(final String tag) {
        return UdevLibrary.INSTANCE().udev_enumerate_add_match_tag(getPointer(),
                                                                   StringUtil.asPointer(tag));
    }

    /**
     * Return the devices on the subtree of one given device. The parent itself is included in the list.
     *
     * @param parent parent device where to start searching
     * @return 0 on success, otherwise a negative error value.
     */
    public int addMatchParent(final Device parent) {
        return UdevLibrary.INSTANCE().udev_enumerate_add_match_parent(getPointer(),
                                                                      parent.getPointer());
    }

    /**
     * Match only devices which udev has set up already. This makes sure, that the device node permissions and context
     * are properly set and that network devices are fully renamed.
     * <p/>
     * Usually, devices which are found in the kernel but not already handled by udev, have still pending events.
     * Services should subscribe to monitor events and wait for these devices to become ready,
     * instead of using uninitialized devices.
     * <p/>
     * For now, this will not affect devices which do not have a device node and are not network interfaces.
     *
     * @return 0 on success, otherwise a negative error value.
     */
    public int addMatchIsInitialized() {
        return UdevLibrary.INSTANCE().udev_enumerate_add_match_is_initialized(getPointer());
    }

    /**
     * Add a device to the list of devices, to retrieve it back sorted in dependency order.
     *
     * @param syspath path of a device
     * @return 0 on success, otherwise a negative error value.
     */
    public int addSyspath(final String syspath) {
        return UdevLibrary.INSTANCE().udev_enumerate_add_syspath(getPointer(),
                                                                 StringUtil.asPointer(syspath));
    }

    /**
     * Scan /sys for all devices which match the given filters. No matches will return all currently available devices.
     *
     * @return 0 on success, otherwise a negative error value.
     */
    public int scanDevices() {
        return UdevLibrary.INSTANCE().udev_enumerate_scan_devices(getPointer());
    }

    /**
     * Scan /sys for all kernel subsystems, including buses, classes, drivers.
     *
     * @return 0 on success, otherwise a negative error value.
     */
    public int scanSubsystems() {
        return UdevLibrary.INSTANCE().udev_enumerate_scan_subsystems(getPointer());
    }

    /**
     * Get the first entry of the sorted list of device paths.
     *
     * @return a list entry.
     */
    public ListEntry getListEntry() {
        return new ListEntry(UdevLibrary.INSTANCE().udev_enumerate_get_list_entry(getPointer()));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Enumerate that = (Enumerate) o;

        return pointer.equals(that.pointer);

    }

    @Override
    public int hashCode() {
        return pointer.hashCode();
    }

    @Override
    protected void finalize() throws Throwable {
        UdevLibrary.INSTANCE().udev_enumerate_unref(getPointer());
        super.finalize();
    }
}
