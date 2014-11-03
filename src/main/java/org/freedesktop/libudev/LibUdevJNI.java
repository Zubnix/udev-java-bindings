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

    public static native long deviceGetProperties(final long devicePointer);

    public static native long deviceGetTags(final long devicePointer);

    public static native long deviceGetSysattr(final long devicePointer);

    public static native String deviceGetPropertyValue(final long devicePointer,
                                                       final String key);

    public static native String deviceGetDriver(final long devicePointer);

    public static native long deviceGetDevnum(long devicePointer);

    public static native String deviceGetAction(long devicePointer);

    public static native long deviceGetSeqnum(long devicePointer);

    public static native long deviceGetUsecSinceUnitialized(long devicePointer);

    public static native String deviceGetSysattrValue(long devicePointer,
                                                      String sysattr);

    public static native int deviceSetSysattrValue(long pointer, String sysattr, String value);

    public static native boolean deviceHasTag(long devicePointer, String tag);

    public static native long monitRef(long monitorPointer);

    public static native void monitorUnref(long monitorPointer);

    public static native long monitorNewFromNetlink(long monitorPointer, String name);

    public static native long monitorGetUdev(long monitorPointer);

    public static native int monitorEnableReceiving(long monitorPointer);

    public static native int monitorSetReceiveBufferSize(long monitorPointer, int size);

    public static native int monitorGetFd(long monitorPointer);

    public static native long monitorReceiveDevice(long monitorPointer);

    public static native int monitorAddMatchSubsystemDevtype(long monitorPointer, String subsystem, String devtype);

    public static native int monitorAddMatchTag(long monitorPointer, String tag);

    public static native int monitorFilterUpdate(long monitorPointer);

    public static native int monitorFilterRemote(long monitorPointer);

    public static native long enumerateRef(long enumeratePointer);

    public static native void enumerateUnref(long enumeratePointer);

    public static native long enumerateGetUdev(long enumeratePointer);

    public static native long enumerateNew();

    public static native int enumerateAddMatchSubsystem(long enumeratePointer, String subsystem);

    public static native int enumerateAddNomatchSubsystem(long enumeratePointer, String subsystem);

    public static native int enumerateAddMatchSysattr(long enumeratePointer, String sysattr, String value);

    public static native int enumerateAddNomatchSysattr(long enumeratePointer, String sysattr, String value);

    public static native int enumerateAddMatchProperty(long enumeratePointer, String property, String value);

    public static native int enumerateAddMatchSysname(long enumeratePointer, String sysname);

    public static native int enumerateAddMatchTag(long enumeratePointer, String tag);

    public static native int enumerateAddMatchParent(long enumeratePointer, long parentDevicePointer);

    public static native int enumerateAddMatchIsInitialized(long enumeratePointer);

    public static native int enumerateAddSyspath(long enumeratePointer, String syspath);

    public static native int enumerateScanDevices(long enumeratePointer);

    public static native int enumerateScanSubsystems(long enumeratePointer);

    public static native long enumerateGetListEntry(long enumeratePointer);

    public static native long queueRef(long queuePointer);

    public static native void queueUnref(long queuePointer);

    public static native long queueGetUdev(long queuePointer);

    public static native long queueNew(long udevPointer) ;

    public static native boolean queueGetUdevIsActive(long queuePointer);

    public static native boolean queueGetQueueIsEmpty(long queuePointer);

    public static native int queueFlush(long queuePointer);

    public static native int queueGetFd(long queuePointer);

    public static native long hwdbRef(long hwdbPointer);

    public static native void hwdbUnref(long hwdbPointer);

    public static native long hwdbNew();

    public static native long hwdbGetProperties(long hwdbPointer, String modalias, int flags);

    public static native int utilEncodeString(String str, String strEnc, int len);
}
