package org.freedesktop.libudev;

/**
 * udev_device
 * <p/>
 * access to sysfs/kernel devices
 */
public class Device implements HasPointer {

    public static Device newFromSyspath(LibUdev udev, String syspath){

    }

    public static Device newFromDevnum(LibUdev, int type, long devnum){

    }

    public static Device newFromSubsystemSysname(LibUdev udev, String subsystem, String sysname){

    }

    public static Device newFromDeviceId(LibUdev udev, String id){

    }

    public static Device newFromEnvironment(LibUdev udev){

    }

    private final long pointer;

    public Device(final long pointer) {
        this.pointer = pointer;
    }

    @Override
    public long getPointer() {
        return this.pointer;
    }

    public LibUdev getUdev(){

    }

    public Device getParent(){

    }

    public Device getParentWithSubsystemDevtype(String subsystem, String devtype){

    }

    public String getDevpath(){

    }

    public String getSubsystem(){

    }

    public String getDevtype(){

    }

    public String getSyspath(){

    }

    public String getSysname(){

    }

    public String getSysnum(){

    }

    public String getDevnode(){

    }

    public boolean isInitialized(){

    }

    public ListEntry getDevlinks(){

    }

    public ListEntry getProperties(){

    }

    public ListEntry getTags(){

    }

    public ListEntry getSysattr(){

    }

    public String getPropertyValue(String key){

    }

    public String getDriver(){

    }

    public long getDevnum(){

    }

    public String getAction(){

    }

    public long getSegnum(){

    }

    public long getUsecSinceInitialized(){

    }

    public String getSysattrValue(String sysattr){

    }

    public int setSysattrValue(String sysattr, String value){

    }

    public boolean hasTag(String tag){

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
}
