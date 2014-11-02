package org.freedesktop.libudev;

public class Monitor implements HasPointer {


    private final long pointer;

    public Monitor(final long pointer) {
        this.pointer = pointer;
    }

    @Override
    public long getPointer() {
        return pointer;
    }

    /**
     * kernel and udev generated events over netlink
     */
    public static Monitor newFromNetlink(LibUdev udev,
                                         String name) {

    }

    public LibUdev getUdev() {

    }

    /**
     * bind socket
     */
    public int enableReceiving() {

    }


    public int setReceiveBufferSize(int size) {

    }

    public int getFd() {

    }

    public Device receiveDevice() {

    }

    /**
     * in-kernel socket filters to select messages that get delivered to a listener
     */
    public int addMatchSubsystemDevtype(String subsystem,
                                        String devtype) {

    }

    public int addMatchTag(String tag){

    }

    public int filterUpdate(){

    }

    public int filterRemoe(){

    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Monitor monitor = (Monitor) o;

        return pointer == monitor.pointer;
    }

    @Override
    public int hashCode() {
        return (int) (pointer);
    }
}
