package org.freedesktop.libudev;

import com.sun.jna.Pointer;
import org.freedesktop.libudev.jna.UdevLibrary;

/**
 * udev_queue — access to currently active events
 * <p/>
 * This exports the current state of the udev processing queue.
 */
public class Queue implements HasPointer {

    /**
     * @param udev udev library context
     * @return the udev queue context, or NULL on error.
     */
    public static Queue create(final LibUdev udev) {
        final Pointer queuePointer = UdevLibrary.INSTANCE().udev_queue_new(udev.getPointer());
        return queuePointer == null ? null : new Queue(queuePointer);
    }

    private final Pointer pointer;

    public Queue(final Pointer pointer) {
        assert pointer != null;
        this.pointer = pointer;
    }

    @Override
    public Pointer getPointer() {
        return pointer;
    }

    /**
     * Retrieve the udev library context the queue context was created with.
     *
     * @return the udev library context.
     */
    public LibUdev getUdev() {
        return new LibUdev(UdevLibrary.INSTANCE().udev_ref(UdevLibrary.INSTANCE().udev_queue_get_udev(getPointer())));
    }

    /**
     * Check if udev is active on the system.
     *
     * @return a flag indicating if udev is active.
     */
    public boolean getUdevIsActive() {
        return UdevLibrary.INSTANCE().udev_queue_get_udev_is_active(getPointer());
    }

    /**
     * Check if udev is currently processing any events.
     *
     * @return a flag indicating if udev is currently handling events.
     */
    public boolean getQueueIsEmpty() {
        return UdevLibrary.INSTANCE().udev_queue_get_queue_is_empty(getPointer());
    }

    public int getFd() {
        return UdevLibrary.INSTANCE().udev_queue_get_fd(getPointer());
    }

    public int flush() {
        return UdevLibrary.INSTANCE().udev_queue_flush(getPointer());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Queue that = (Queue) o;

        return pointer.equals(that.pointer);

    }

    @Override
    public int hashCode() {
        return pointer.hashCode();
    }

    @Override
    protected void finalize() throws Throwable {
        UdevLibrary.INSTANCE().udev_queue_unref(getPointer());
        super.finalize();
    }
}
