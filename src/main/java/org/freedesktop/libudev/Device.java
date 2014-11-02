package org.freedesktop.libudev;

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
    public static Device newFromSyspath(LibUdev udev,
                                        String syspath) {
        final long devicePointer = LibUdevJNI.deviceNewFromSyspath(udev.getPointer(),
                                                                   syspath);
        if (devicePointer == 0) {
            return null;
        }
        else {
            return new Device(devicePointer,
                              true);
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
    public static Device newFromDevnum(LibUdev udev,
                                       int type,
                                       long devnum) {
        final long devicePointer = LibUdevJNI.deviceNewFromDevnum(udev.getPointer(),
                                                                  type,
                                                                  devnum);
        if (devicePointer == 0) {
            return null;
        }
        else {
            return new Device(devicePointer,
                              true);
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
    public static Device newFromSubsystemSysname(LibUdev udev,
                                                 String subsystem,
                                                 String sysname) {
        final long devicePointer = LibUdevJNI.deviceNewFromSubsystemSysname(udev.getPointer(),
                                                                            subsystem,
                                                                            sysname);
        if (devicePointer == 0) {
            return null;
        }
        else {
            return new Device(devicePointer,
                              true);
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
    public static Device newFromDeviceId(LibUdev udev,
                                         String id) {
        final long devicePointer = LibUdevJNI.deviceNewFromDeviceId(udev.getPointer(),
                                                                    id);
        if (devicePointer == 0) {
            return null;
        }
        else {
            return new Device(devicePointer,
                              true);
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
    public static Device newFromEnvironment(LibUdev udev) {
        final long devicePointer = LibUdevJNI.deviceNewFromEnvironment(udev.getPointer());
        if (devicePointer == 0) {
            return null;
        }
        else {
            return new Device(devicePointer,
                              true);
        }
    }

    private final long    pointer;
    private final boolean refCount;

    public Device(final long pointer,
                  final boolean refCount) {
        this.refCount = refCount;
        if (refCount) {
            this.pointer = LibUdevJNI.deviceRef(pointer);
        }
        else {
            this.pointer = pointer;
        }
    }

    @Override
    public long getPointer() {
        return this.pointer;
    }

    /**
     * Retrieve the udev library context the device was created with.
     *
     * @return the udev library context
     */
    public LibUdev getUdev() {
        return new LibUdev(LibUdevJNI.deviceGetUdev(getPointer()));
    }

    /**
     * Find the next parent device, and fill in information from the sys device and the udev database entry.
     * <p/>
     * It is not necessarily just the upper level directory, empty or not recognized sys directories are ignored.
     *
     * @return a new udev device, or NULL, if it no parent exist.
     */
    public Device getParent() {
        final long devicePointer = LibUdevJNI.deviceGetParent(getPointer());
        if (devicePointer == 0) {
            return null;
        }
        else {
            return new Device(devicePointer,
                              false);
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
    public Device getParentWithSubsystemDevtype(String subsystem,
                                                String devtype) {
        final long devicePointer = LibUdevJNI.deviceGetParentWithSubsystemDevtype(getPointer(),
                                                                                  subsystem,
                                                                                  devtype);
        if (devicePointer == 0) {
            return null;
        }
        else {
            return new Device(devicePointer,
                              false);
        }
    }

    /**
     * Retrieve the kernel devpath value of the udev device.
     * The path does not contain the sys mount point, and starts with a '/'.
     *
     * @return the devpath of the udev device
     */
    public String getDevpath() {
        return LibUdevJNI.deviceGetDevpath(getPointer());
    }

    /**
     * Retrieve the subsystem string of the udev device. The string does not contain any "/".
     *
     * @return the subsystem name of the udev device, or NULL if it can not be determined
     */
    public String getSubsystem() {
        return LibUdevJNI.deviceGetSubsystem(getPointer());
    }

    /**
     * Retrieve the devtype string of the udev device.
     *
     * @return the devtype name of the udev device, or NULL if it can not be determined
     */
    public String getDevtype() {
        return LibUdevJNI.deviceGetDevtype(getPointer());
    }

    /**
     * Retrieve the sys path of the udev device. The path is an absolute path and starts with the sys mount point.
     *
     * @return the sys path of the udev device
     */
    public String getSyspath() {
        return LibUdevJNI.deviceGetSyspath(getPointer());
    }

    /**
     * Get the kernel device name in /sys.
     *
     * @return the name string of the device
     */
    public String getSysname() {
        return LibUdevJNI.deviceGetSysname(getPointer());
    }

    /**
     * Get the instance number of the device.
     *
     * @return the trailing number string of the device name
     */
    public String getSysnum() {
        return LibUdevJNI.deviceGetSysnum(getPointer());
    }

    /**
     * Retrieve the device node file name belonging to the udev device.
     * The path is an absolute path, and starts with the device directory.
     *
     * @return the device node file name of the udev device, or NULL if no device node exists
     */
    public String getDevnode() {
        return LibUdevJNI.deviceGetDevnode(getPointer());
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
        return LibUdevJNI.deviceIsInitialized(getPointer());
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
        return new ListEntry(LibUdevJNI.deviceGetDevlinks(getPointer()));
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

    }

    public ListEntry getTags() {

    }

    public ListEntry getSysattr() {

    }

    public String getPropertyValue(String key) {

    }

    public String getDriver() {

    }

    public long getDevnum() {

    }

    public String getAction() {

    }

    public long getSegnum() {

    }

    public long getUsecSinceInitialized() {

    }

    public String getSysattrValue(String sysattr) {

    }

    public int setSysattrValue(String sysattr,
                               String value) {

    }

    public boolean hasTag(String tag) {

    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Device device = (Device) o;

        return pointer == device.pointer;

    }

    @Override
    public int hashCode() {
        return (int) (pointer);
    }

    @Override
    protected void finalize() throws Throwable {
        if (refCount) {
            LibUdevJNI.deviceUnref(getPointer());
        }
        super.finalize();
    }
}
