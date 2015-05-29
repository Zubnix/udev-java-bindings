package org.freedesktop.libudev;

import com.sun.jna.Pointer;
import org.freedesktop.libudev.jna.UdevLibrary;

/**
 * udev - library context
 * <p/>
 * reads the udev config and system environment
 * allows custom logging
 */
public class LibUdev implements HasPointer {

    /**
     * Create udev library context. This reads the udev configuration file, and fills in the default values.
     *
     * @return a new udev library context
     */
    public static LibUdev create() {
        return new LibUdev(UdevLibrary.INSTANCE().udev_new());
    }

    private final Pointer pointer;

    public LibUdev(final Pointer pointer) {
        assert pointer != null;
        this.pointer = pointer;
    }

    @Override
    public Pointer getPointer() {
        return this.pointer;
    }

    /**
     * The built-in logging writes to stderr.
     * It can be overridden by a custom function, to plug log messages into the users' logging functionality.
     *
     * @param logger function to be called for logging messages
     */
    public void setLogger(final Logger logger) {
        UdevLibrary.INSTANCE().udev_set_log_fn(getPointer(),
                                             logger);
    }

    /**
     * The initial logging priority is read from the udev config file at startup.
     *
     * @return the current logging priority
     */
    public int getLogPriority() {
        return UdevLibrary.INSTANCE().udev_get_log_priority(getPointer());
    }


    /**
     * Set the current logging priority. The value controls which messages are logged.
     *
     * @param priority the new logging priority
     */
    public void setLogPriority(final int priority) {
        UdevLibrary.INSTANCE().udev_set_log_priority(getPointer(),
                                                   priority);
    }

    /**
     * Retrieve stored data pointer from library context. This might be useful to access from callbacks like a custom logging function.
     *
     * @return stored userdata
     */
    public Object getUserdata() {
        return UdevLibrary.INSTANCE().udev_get_userdata(getPointer());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final LibUdev that = (LibUdev) o;

        return pointer.equals(that.pointer);
    }

    @Override
    public int hashCode() {
        return pointer.hashCode();
    }

    @Override
    protected void finalize() throws Throwable {
        UdevLibrary.INSTANCE().udev_unref(getPointer());
        super.finalize();
    }


}
