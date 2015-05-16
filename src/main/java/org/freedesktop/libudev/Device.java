package org.freedesktop.libudev;

import com.sun.jna.Pointer;

import org.freedesktop.libudev.jna.StringUtil;
import org.freedesktop.libudev.jna.UdevLibrary;

/**
 * udev_device
 * <p/>
 * access to sysfs/kernel devices.
 * Representation of kernel sys devices.
 * Devices are uniquely identified by their syspath,
 * every device has exactly one path in the kernel sys filesystem.
 * Devices usually belong to a kernel subsystem, and have a unique name inside that subsystem.
 */
public class Device implements HasPointer {

    /**
     * Create new udev device, and fill in information from the sys device and the udev database entry.
     * The syspath is the absolute path to the device, including the sys mount point.
     *
     * @param udev    udev library context
     * @param syspath sys device path including sys directory
     * @return a new udev device, or NULL, if it does not exist
     */
    public static Device newFromSyspath(final LibUdev udev,
                                        final String syspath) {

        final Pointer devicePointer = UdevLibrary.INSTANCE().udev_device_new_from_syspath(udev.getPointer(),
                                                                                          StringUtil.asPointer(syspath));
        if (devicePointer == null) {
            return null;
        }
        else {
            return new Device(devicePointer);
        }
    }

    /**
     * Create new udev device, and fill in information from the sys device and the udev database entry.
     * The device is looked-up by its major/minor number and type.
     * Character and block device numbers are not unique across the two types.
     *
     * @param udev   udev library context
     * @param type   char or block device
     * @param devnum device major/minor number
     * @return a new udev device, or NULL, if it does not exist
     */
    public static Device newFromDevnum(final LibUdev udev,
                                       final byte type,
                                       final int devnum) {
        final Pointer devicePointer = UdevLibrary.INSTANCE().udev_device_new_from_devnum(udev.getPointer(),
                                                                                                type,
                                                                                                devnum);
        if (devicePointer == null) {
            return null;
        }
        else {
            return new Device(devicePointer);
        }
    }

    /**
     * Create new udev device, and fill in information from the sys device and the udev database entry.
     * The device is looked up by the subsystem and name string of the device,
     * like "mem" / "zero", or "block" / "sda".
     *
     * @param udev      udev library context
     * @param subsystem the subsystem of the device
     * @param sysname   the name of the device
     * @return a new udev device, or NULL, if it does not exist
     */
    public static Device newFromSubsystemSysname(final LibUdev udev,
                                                 final String subsystem,
                                                 final String sysname) {
        final Pointer devicePointer = UdevLibrary.INSTANCE().udev_device_new_from_subsystem_sysname(udev.getPointer(),
                                                                                                    StringUtil.asPointer(subsystem),
                                                                                                    StringUtil.asPointer(sysname));
        if (devicePointer == null) {
            return null;
        }
        else {
            return new Device(devicePointer);
        }
    }

    /**
     * Create new udev device, and fill in information from the sys device and the udev database entry.
     * The device is looked-up by a special string:
     * b8:2 - block device major:minor c128:1 - char device major:minor n3 - network device ifindex +sound:card29 - kernel driver core subsystem:device name
     *
     * @param udev udev library context
     * @param id   text string identifying a kernel device
     * @return a new udev device, or NULL, if it does not exist
     */
    public static Device newFromDeviceId(final LibUdev udev,
                                         final String id) {
        final Pointer devicePointer = UdevLibrary.INSTANCE().udev_device_new_from_device_id(udev.getPointer(),
                                                                                            StringUtil.asPointer(id));
        if (devicePointer == null) {
            return null;
        }
        else {
            return new Device(devicePointer);
        }
    }

    /**
     * Create new udev device, and fill in information from the current process environment.
     * This only works reliable if the process is called from a udev rule.
     * It is usually used for tools executed from IMPORT= rules.
     *
     * @param udev udev library context
     * @return a new udev device, or NULL, if it does not exist
     */
    public static Device newFromEnvironment(final LibUdev udev) {
        final Pointer devicePointer = UdevLibrary.INSTANCE().udev_device_new_from_environment(udev.getPointer());
        if (devicePointer == null) {
            return null;
        }
        else {
            return new Device(devicePointer);
        }
    }

    private final Pointer pointer;

    public Device(final Pointer pointer) {
        this.pointer = pointer;
    }

    @Override
    public Pointer getPointer() {
        return this.pointer;
    }

    /**
     * Retrieve the udev library context the device was created with.
     *
     * @return the udev library context
     */
    public LibUdev getUdev() {
        return new LibUdev(UdevLibrary.INSTANCE().udev_ref(UdevLibrary.INSTANCE().udev_device_get_udev(getPointer())));
    }

    /**
     * Find the next parent device, and fill in information from the sys device and the udev database entry.
     * <p/>
     * It is not necessarily just the upper level directory, empty or not recognized sys directories are ignored.
     *
     * @return a new udev device, or NULL, if it no parent exist.
     */
    public Device getParent() {
        final Pointer devicePointer = UdevLibrary.INSTANCE().udev_device_get_parent(getPointer());
        if (devicePointer == null) {
            return null;
        }
        else {
            //FIXME should probably be stored in a field to match the native lib's memory management.
            return new Device(devicePointer);
        }
    }

    /**
     * Find the next parent device, with a matching subsystem and devtype value, and fill in information from the sys device and the udev database entry.
     * <p/>
     * If devtype is NULL, only subsystem is checked, and any devtype will match.
     *
     * @param subsystem the subsystem of the device
     * @param devtype   the type (DEVTYPE) of the device
     * @return a new udev device, or NULL if no matching parent exists.
     */
    public Device getParentWithSubsystemDevtype(final String subsystem,
                                                final String devtype) {
        final Pointer devicePointer = UdevLibrary.INSTANCE().udev_device_get_parent_with_subsystem_devtype(getPointer(),
                                                                                                           StringUtil.asPointer(subsystem),
                                                                                                           StringUtil.asPointer(devtype));
        if (devicePointer == null) {
            return null;
        }
        else {
            //FIXME should probably be stored in a field to match the native lib's memory management.
            return new Device(devicePointer);
        }
    }

    /**
     * Retrieve the kernel devpath value of the udev device.
     * The path does not contain the sys mount point, and starts with a '/'.
     *
     * @return the devpath of the udev device
     */
    public String getDevpath() {
        return StringUtil.fromPointer(UdevLibrary.INSTANCE().udev_device_get_devpath(getPointer()));
    }

    /**
     * Retrieve the subsystem string of the udev device. The string does not contain any "/".
     *
     * @return the subsystem name of the udev device, or NULL if it can not be determined
     */
    public String getSubsystem() {
        return StringUtil.fromPointer(UdevLibrary.INSTANCE().udev_device_get_subsystem(getPointer()));
    }

    /**
     * Retrieve the devtype string of the udev device.
     *
     * @return the devtype name of the udev device, or NULL if it can not be determined
     */
    public String getDevtype() {
        return StringUtil.fromPointer(UdevLibrary.INSTANCE().udev_device_get_devtype(getPointer()));
    }

    /**
     * Retrieve the sys path of the udev device. The path is an absolute path and starts with the sys mount point.
     *
     * @return the sys path of the udev device
     */
    public String getSyspath() {
        return StringUtil.fromPointer(UdevLibrary.INSTANCE().udev_device_get_syspath(getPointer()));
    }

    /**
     * Get the kernel device name in /sys.
     *
     * @return the name string of the device
     */
    public String getSysname() {
        return StringUtil.fromPointer(UdevLibrary.INSTANCE().udev_device_get_sysname(getPointer()));
    }

    /**
     * Get the instance number of the device.
     *
     * @return the trailing number string of the device name
     */
    public String getSysnum() {
        return StringUtil.fromPointer(UdevLibrary.INSTANCE().udev_device_get_sysnum(getPointer()));
    }

    /**
     * Retrieve the device node file name belonging to the udev device.
     * The path is an absolute path, and starts with the device directory.
     *
     * @return the device node file name of the udev device, or NULL if no device node exists
     */
    public String getDevnode() {
        return StringUtil.fromPointer(UdevLibrary.INSTANCE().udev_device_get_devnode(getPointer()));
    }

    /**
     * Check if udev has already handled the device and has set up device node permissions and context,
     * or has renamed a network device.
     * <p/>
     * This is only implemented for devices with a device node or network interfaces.
     * All other devices return true here.
     *
     * @return true if the device is set up. false otherwise.
     */
    public boolean isInitialized() {
        return UdevLibrary.INSTANCE().udev_device_get_is_initialized(getPointer());
    }

    /**
     * Retrieve the list of device links pointing to the device file of the udev device.
     * The next list entry can be retrieved with {@link ListEntry#getNext()},
     * which returns NULL if no more entries exist.
     * The devlink path can be retrieved from the list entry by {@link ListEntry#getName()}.
     * The path is an absolute path, and starts with the device directory.
     *
     * @return the first entry of the device node link list
     */
    public ListEntry getDevlinks() {
        final Pointer listPointer = getPointer();
        return listPointer == null ? null : new ListEntry(UdevLibrary.INSTANCE().udev_device_get_devlinks_list_entry(listPointer));
    }

    /**
     * Retrieve the list of key/value device properties of the udev device.
     * The next list entry can be retrieved with {@link ListEntry#getNext()},
     * which returns NULL if no more entries exist.
     * The property name can be retrieved from the list entry by {@link ListEntry#getName()},
     * the property value by {@link ListEntry#getValue()}
     *
     * @return the first entry of the property list
     */
    public ListEntry getProperties() {
        final Pointer listPointer = getPointer();
        return listPointer == null ? null : new ListEntry(UdevLibrary.INSTANCE().udev_device_get_properties_list_entry(listPointer));
    }

    /**
     * Retrieve the list of tags attached to the udev device.
     * The next list entry can be retrieved with {@link ListEntry#getNext()}, which returns NULL if no more entries exist.
     * The tag string can be retrieved from the list entry by {@link ListEntry#getName()}.
     *
     * @return the first entry of the tag list
     */
    public ListEntry getTags() {
        final Pointer listPointer = getPointer();
        return listPointer == null ? null : new ListEntry(UdevLibrary.INSTANCE().udev_device_get_tags_list_entry(listPointer));
    }

    /**
     * Retrieve the list of available sysattrs, with value being empty;
     * This just return all available sysfs attributes for a particular device without reading their values.
     *
     * @return the first entry of the property list
     */
    public ListEntry getSysattr() {
        final Pointer listPointer = getPointer();
        return listPointer == null ? null :  new ListEntry(UdevLibrary.INSTANCE().udev_device_get_sysattr_list_entry(listPointer));
    }

    /**
     * @param key property name
     * @return the value of a device property, or NULL if there is no such property.
     */
    public String getPropertyValue(final String key) {
        return StringUtil.fromPointer(UdevLibrary.INSTANCE().udev_device_get_property_value(getPointer(),
                                                                                            StringUtil.asPointer(key)));
    }

    /**
     * @return the driver string, or NULL if there is no driver attached.
     */
    public String getDriver() {
        return StringUtil.fromPointer(UdevLibrary.INSTANCE().udev_device_get_driver(getPointer()));
    }

    /**
     * @return the device major/minor number.
     */
    public long getDevnum() {
        return UdevLibrary.INSTANCE().udev_device_get_devnum(getPointer());
    }

    /**
     * This is only valid if the device was received through a monitor. Devices read from sys do not have an
     * action string. Usual actions are: add, remove, change, online, offline.
     *
     * @return the kernel action value, or NULL if there is no action value available.
     */
    public String getAction() {
        return StringUtil.fromPointer(UdevLibrary.INSTANCE().udev_device_get_action(getPointer()));
    }

    /**
     * This is only valid if the device was received through a monitor.
     * Devices read from sys do not have a sequence number.
     *
     * @return the kernel event sequence number, or 0 if there is no sequence number available.
     */
    public long getSegnum() {
        return UdevLibrary.INSTANCE().udev_device_get_seqnum(getPointer());
    }

    /**
     * Return the number of microseconds passed since udev set up the device for the first time.
     * <p/>
     * This is only implemented for devices with need to store properties in the udev database. All other devices return 0 here.
     *
     * @return he number of microseconds since the device was first seen.
     */
    public long getUsecSinceInitialized() {
        return UdevLibrary.INSTANCE().udev_device_get_usec_since_initialized(getPointer());
    }

    /**
     * The retrieved value is cached in the device.
     * Repeated calls will return the same value and not open the attribute again.
     *
     * @param sysattr attribute name
     * @return The content of a sys attribute file, or NULL if there is no sys attribute value.
     */
    public String getSysattrValue(final String sysattr) {
        return StringUtil.fromPointer(UdevLibrary.INSTANCE().udev_device_get_sysattr_value(getPointer(),
                                                                                           StringUtil
                                                                                                   .asPointer(sysattr)));
    }

    /**
     * Update the contents of the sys attribute and the cached value of the device.
     *
     * @param sysattr attribute name
     * @param value   new value to be set
     * @return Negative error code on failure or 0 on success.
     */
    public int setSysattrValue(final String sysattr,
                               final String value) {
        return UdevLibrary.INSTANCE().udev_device_set_sysattr_value(getPointer(),
                                                                    StringUtil.asPointer(sysattr),
                                                                    StringUtil.asPointer(value));
    }

    /**
     * Check if a given device has a certain tag associated.
     *
     * @param tag tag name
     * @return true if the tag is found. false otherwise.
     */
    public boolean hasTag(final String tag) {
        return UdevLibrary.INSTANCE().udev_device_has_tag(getPointer(),
                                                          StringUtil.asPointer(tag));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Device that = (Device) o;

        return pointer.equals(that.pointer);

    }

    @Override
    public int hashCode() {
        return pointer.hashCode();
    }

    @Override
    protected void finalize() throws Throwable {
        UdevLibrary.INSTANCE().udev_device_unref(getPointer());
        super.finalize();
    }
}
