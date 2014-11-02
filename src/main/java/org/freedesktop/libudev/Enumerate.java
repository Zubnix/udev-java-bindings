package org.freedesktop.libudev;

/**
 * udev_enumerate
 * <p/>
 * search sysfs for specific devices and provide a sorted list
 */
public class Enumerate implements HasPointer{

    public static Enumerate create(){

    }

    private final long pointer;

    public Enumerate(final long pointer) {
        this.pointer = pointer;
    }

    @Override
    public long getPointer() {
        return this.pointer;
    }

    public LibUdev getUdev(){

    }

    public int addMatchSubsystem(String subsystem){

    }

    public int addNomatchSubsystem(String subsystem){

    }

    public int addMatchSysattr(String sysattr, String value){

    }

    public int addNomatchSysattr(String sysattr, String value){

    }

    public int addMatchProperty(String property, String value){

    }

    public int addMatchSysname(String sysname){

    }

    public int addMatchTag(String tag){

    }

    public int addMatchParent(Device parent){

    }

    public int addMatchIsInitialized(){

    }

    public int addSyspath(String syspath){

    }

    public int scanDevices(){

    }

    public int scanSubsystems(){

    }

    public ListEntry getListEntry(){

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
}
