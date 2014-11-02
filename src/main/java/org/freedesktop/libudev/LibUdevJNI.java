package org.freedesktop.libudev;

public class LibUdevJNI {
    public static native long udevRef(final long pointer);

    public static native void udevUnref(final long pointer);

    public static native long udevNew();

    public static native void udevSetLogFn(final long udevPointer,
                                           final Logger logger);

    public static native int udevGetLogPriority(final long udevPointer);

    public static native void udevSetLogPriority(final long udevPointer,
                                                 final int priority);

    public static native Object udevGetUserData(final long udevPointer);

    public static native void udevSetUserdata(final long udevPointer,
                                              final Object userdata);

    public static native long listEntryGetNext(final long listEntryPointer);

    public static native long listEntryGetByName(final long listEntryPointer,
                                                 final String name);

    public static native String listEntryGetName(final long listEntryPointer);

    public static native String listEntryGetValue(final long listEntryPointer);

    public static native long deviceRef(final long devicePointer);

    public static native void deviceUnref(final long devicePointer);

    public static native long deviceNewFromSyspath(final long devicePointer,
                                                   final String syspath);

    public static native long deviceNewFromDevnum(final long devicePointer,
                                                  final int type,
                                                  final long devnum);

    public static native long deviceNewFromSubsystemSysname(final long devicePointer,
                                                            final String subsystem,
                                                            final String sysname);

    public static native long deviceNewFromDeviceId(final long devicePointer,
                                                    final String id);

    public static native long deviceNewFromEnvironment(final long devicePointer);

    public static native long deviceGetUdev(final long devicePointer);

    public static native long deviceGetParent(final long devicePointer);

    public static native long deviceGetParentWithSubsystemDevtype(final long pointer,
                                                                  final String subsystem,
                                                                  final String devtype);

    public static native String deviceGetDevpath(final long devicePointer);

    public static native String deviceGetSubsystem(final long devicePointer);

    public static native String deviceGetDevtype(final long devicePointer);

    public static native String deviceGetSyspath(final long devicePointer);

    public static native String deviceGetSysname(final long devicePointer);

    public static native String deviceGetSysnum(final long devicePointer);

    public static native String deviceGetDevnode(final long devicePointer);

    public static native boolean deviceIsInitialized(final long devicePointer);

    public static native long deviceGetDevlinks(final long devicePointer);
}
