package org.freedesktop.libudev;

/**
 * udev_queue
 * <p/>
 * access to the currently running udev events
 */
public class Queue implements HasPointer{

    public static Queue create(LibUdev udev){

    }

    private final long pointer;

    public Queue(final long pointer) {
        this.pointer = pointer;
    }

    @Override
    public long getPointer() {
        return pointer;
    }

    public LibUdev getUdev(){

    }

    @Deprecated
    public long getKernelSeqnum(){

    }

    @Deprecated
    public long getUdevSeqnum(){

    }

    public boolean getUdevIsActive(){

    }

    public boolean getQueueIsEmpty(){

    }

    @Deprecated
    public boolean getSeqnumIsFinished(long seqnum){

    }

    @Deprecated
    public boolean getSeqnumSequenceIsFinished(long start, long end){

    }

    public int getFd(){

    }

    public int flush(){

    }

    @Deprecated
    public ListEntry getQueuedListEntry(){

    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Queue queue = (Queue) o;

        return pointer == queue.pointer;

    }

    @Override
    public int hashCode() {
        return (int) (pointer);
    }
}
