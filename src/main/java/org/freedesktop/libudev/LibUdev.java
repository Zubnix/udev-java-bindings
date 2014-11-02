package org.freedesktop.libudev;

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
        return new LibUdev(LibUdevJNI.udevNew());
    }

    private final long pointer;

    public LibUdev(final long pointer) {
        this.pointer = LibUdevJNI.udevRef(pointer);
    }

    @Override
    public long getPointer() {
        return this.pointer;
    }

    /**
     * The built-in logging writes to stderr.
     * It can be overridden by a custom function, to plug log messages into the users' logging functionality.
     *
     * @param logger function to be called for logging messages
     */
    public void setLogger(Logger logger) {
        LibUdevJNI.udevSetLogFn(getPointer(),
                                logger);
    }

    /**
     * The initial logging priority is read from the udev config file at startup.
     *
     * @return the current logging priority
     */
    public int getLogPriority() {
        return LibUdevJNI.udevGetLogPriority(getPointer());
    }


    /**
     * Set the current logging priority. The value controls which messages are logged.
     *
     * @param priority the new logging priority
     */
    public void setLogPriority(int priority) {
        LibUdevJNI.udevSetLogPriority(getPointer(),
                                      priority);
    }

    /**
     * Retrieve stored data pointer from library context. This might be useful to access from callbacks like a custom logging function.
     *
     * @return stored userdata
     */
    public Object getUserdata() {
        return LibUdevJNI.udevGetUserData(getPointer());
    }

    public void setUserdata(Object userdata) {
        LibUdevJNI.udevSetUserdata(getPointer(),
                                   userdata);
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

    @Override
    protected void finalize() throws Throwable {
        LibUdevJNI.udevUnref(getPointer());
        super.finalize();
    }
}
