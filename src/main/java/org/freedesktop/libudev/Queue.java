package org.freedesktop.libudev;

/**
 * udev_queue — access to currently active events
 * <p>
 * This exports the current state of the udev processing queue.
 */
public class Queue implements HasPointer {

    /**
     * @param udev udev library context
     * @return the udev queue context, or NULL on error.
     */
    public static Queue create(final LibUdev udev) {
        final long queuePointer = LibUdevJNI.queueNew(udev.getPointer());
        if (queuePointer == 0) {
            return null;
        } else {
            return new Queue(queuePointer);
        }
    }

    private final long pointer;

    public Queue(final long pointer) {
        this.pointer = LibUdevJNI.queueRef(pointer);
    }

    @Override
    public long getPointer() {
        return pointer;
    }

    /**
     * Retrieve the udev library context the queue context was created with.
     *
     * @return the udev library context.
     */
    public LibUdev getUdev() {
        return new LibUdev(LibUdevJNI.queueGetUdev(getPointer()));
    }

    /**
     * Check if udev is active on the system.
     *
     * @return a flag indicating if udev is active.
     */
    public boolean getUdevIsActive() {
        return LibUdevJNI.queueGetUdevIsActive(getPointer());
    }

    /**
     * Check if udev is currently processing any events.
     *
     * @return a flag indicating if udev is currently handling events.
     */
    public boolean getQueueIsEmpty() {
        return LibUdevJNI.queueGetQueueIsEmpty(getPointer());
    }

    public int getFd() {
        return LibUdevJNI.queueGetFd(getPointer());
    }

    public int flush() {
        return LibUdevJNI.queueFlush(getPointer());
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

    @Override
    protected void finalize() throws Throwable {
        LibUdevJNI.queueUnref(getPointer());
        super.finalize();
    }
}
