package org.freedesktop.libudev;

/*
 * udev - library context
 *
 * reads the udev config and system environment
 * allows custom logging
 */
public class LibUdev implements HasPointer {

    public static LibUdev create(){

    }

    private final long pointer;

    protected LibUdev(final long pointer) {
        this.pointer = pointer;
    }

    @Override
    public long getPointer() {
        return this.pointer;
    }

    public void setLogger(Logger logger){

    }

    public int getLogPriority(){

    }


    public void setLogPriority(int priority){

    }

    public Object getUserdata(){

    }

    public void setUserdata(Object userdata){

    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final LibUdev libUdev = (LibUdev) o;

        return pointer == libUdev.pointer;

    }

    @Override
    public int hashCode() {
        return (int) (pointer);
    }
}
