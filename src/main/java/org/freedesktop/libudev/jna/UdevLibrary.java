package org.freedesktop.libudev.jna;

import com.sun.jna.Native;
import com.sun.jna.Pointer;

public class UdevLibrary implements UdevLibraryMapping{

    private static UdevLibraryMapping INSTANCE;

    public static UdevLibraryMapping INSTANCE() {
        if (INSTANCE == null) {
            Native.register(UdevLibraryMapping.JNA_LIBRARY_NAME);
            INSTANCE = new UdevLibrary();
        }
        return INSTANCE;
    }

    @Override
    public native Pointer udev_ref(final Pointer udev);

    @Override
    public native Pointer udev_unref(final Pointer udev);

    @Override
    public native Pointer udev_new();

    @Override
    public native void udev_set_log_fn(final Pointer udev, final LogFunction logFn);

    @Override
    public native int udev_get_log_priority(final Pointer udev);

    @Override
    public native void udev_set_log_priority(final Pointer udev, final int priority);

    @Override
    public native Pointer udev_get_userdata(final Pointer udev);

    @Override
    public native void udev_set_userdata(final Pointer udev, final Pointer userdata);

    @Override
    public native Pointer udev_list_entry_get_next(final Pointer list_entry);

    @Override
    public native Pointer udev_list_entry_get_by_name(final Pointer list_entry, final Pointer name);

    @Override
    public native Pointer udev_list_entry_get_name(final Pointer list_entry);

    @Override
    public native Pointer udev_list_entry_get_value(final Pointer list_entry);

    @Override
    public native Pointer udev_device_ref(final Pointer udev_device);

    @Override
    public native Pointer udev_device_unref(final Pointer udev_device);

    @Override
    public native Pointer udev_device_get_udev(final Pointer udev_device);

    @Override
    public native Pointer udev_device_new_from_syspath(final Pointer udev, final Pointer syspath);

    @Override
    public native Pointer udev_device_new_from_devnum(final Pointer udev, final byte type, final int devnum);

    @Override
    public native Pointer udev_device_new_from_subsystem_sysname(final Pointer udev, final Pointer subsystem,
                                                          final Pointer sysname);

    @Override
    public native Pointer udev_device_new_from_device_id(final Pointer udev, final Pointer id);

    @Override
    public native Pointer udev_device_new_from_environment(final Pointer udev);

    @Override
    public native Pointer udev_device_get_parent(final Pointer udev_device);

    @Override
    public native Pointer udev_device_get_parent_with_subsystem_devtype(final Pointer udev_device, final Pointer subsystem,
                                                                 final Pointer devtype);

    @Override
    public native Pointer udev_device_get_devpath(final Pointer udev_device);

    @Override
    public native Pointer udev_device_get_subsystem(final Pointer udev_device);

    @Override
    public native Pointer udev_device_get_devtype(final Pointer udev_device);

    @Override
    public native Pointer udev_device_get_syspath(final Pointer udev_device);

    @Override
    public native Pointer udev_device_get_sysname(final Pointer udev_device);

    @Override
    public native Pointer udev_device_get_sysnum(final Pointer udev_device);

    @Override
    public native Pointer udev_device_get_devnode(final Pointer udev_device);

    @Override
    public native boolean udev_device_get_is_initialized(final Pointer udev_device);

    @Override
    public native Pointer udev_device_get_devlinks_list_entry(final Pointer udev_device);

    @Override
    public native Pointer udev_device_get_properties_list_entry(final Pointer udev_device);

    @Override
    public native Pointer udev_device_get_tags_list_entry(final Pointer udev_device);

    @Override
    public native Pointer udev_device_get_sysattr_list_entry(final Pointer udev_device);

    @Override
    public native Pointer udev_device_get_property_value(final Pointer udev_device, final Pointer key);

    @Override
    public native Pointer udev_device_get_driver(final Pointer udev_device);

    @Override
    public native int udev_device_get_devnum(final Pointer udev_device);

    @Override
    public native Pointer udev_device_get_action(final Pointer udev_device);

    @Override
    public native long udev_device_get_seqnum(final Pointer udev_device);

    @Override
    public native long udev_device_get_usec_since_initialized(final Pointer udev_device);

    @Override
    public native Pointer udev_device_get_sysattr_value(final Pointer udev_device, final Pointer sysattr);

    @Override
    public native int udev_device_set_sysattr_value(final Pointer udev_device, final Pointer sysattr, final Pointer value);

    @Override
    public native boolean udev_device_has_tag(final Pointer udev_device, final Pointer tag);

    @Override
    public native Pointer udev_monitor_ref(final Pointer udev_monitor);

    @Override
    public native Pointer udev_monitor_unref(final Pointer udev_monitor);

    @Override
    public native Pointer udev_monitor_get_udev(final Pointer udev_monitor);

    @Override
    public native Pointer udev_monitor_new_from_netlink(final Pointer udev, final Pointer name);

    @Override
    public native int udev_monitor_enable_receiving(final Pointer udev_monitor);

    @Override
    public native int udev_monitor_set_receive_buffer_size(final Pointer udev_monitor, final int size);

    @Override
    public native int udev_monitor_get_fd(final Pointer udev_monitor);

    @Override
    public native Pointer udev_monitor_receive_device(final Pointer udev_monitor);

    @Override
    public native int udev_monitor_filter_add_match_subsystem_devtype(final Pointer udev_monitor, final Pointer subsystem,
                                                               final Pointer devtype);

    @Override
    public native int udev_monitor_filter_add_match_tag(final Pointer udev_monitor, final Pointer tag);

    @Override
    public native int udev_monitor_filter_update(final Pointer udev_monitor);

    @Override
    public native int udev_monitor_filter_remove(final Pointer udev_monitor);

    @Override
    public native Pointer udev_enumerate_ref(final Pointer udev_enumerate);

    @Override
    public native Pointer udev_enumerate_unref(final Pointer udev_enumerate);

    @Override
    public native Pointer udev_enumerate_get_udev(final Pointer udev_enumerate);

    @Override
    public native Pointer udev_enumerate_new(final Pointer udev);

    @Override
    public native int udev_enumerate_add_match_subsystem(final Pointer udev_enumerate, final Pointer subsystem);

    @Override
    public native int udev_enumerate_add_nomatch_subsystem(final Pointer udev_enumerate, final Pointer subsystem);

    @Override
    public native int udev_enumerate_add_match_sysattr(final Pointer udev_enumerate, final Pointer sysattr,
                                                final Pointer value);

    @Override
    public native int udev_enumerate_add_nomatch_sysattr(final Pointer udev_enumerate, final Pointer sysattr,
                                                  final Pointer value);

    @Override
    public native int udev_enumerate_add_match_property(final Pointer udev_enumerate, final Pointer property,
                                                 final Pointer value);

    @Override
    public native int udev_enumerate_add_match_sysname(final Pointer udev_enumerate, final Pointer sysname);

    @Override
    public native int udev_enumerate_add_match_tag(final Pointer udev_enumerate, final Pointer tag);

    @Override
    public native int udev_enumerate_add_match_parent(final Pointer udev_enumerate, final Pointer parent);

    @Override
    public native int udev_enumerate_add_match_is_initialized(final Pointer udev_enumerate);

    @Override
    public native int udev_enumerate_add_syspath(final Pointer udev_enumerate, final Pointer syspath);

    @Override
    public native int udev_enumerate_scan_devices(final Pointer udev_enumerate);

    @Override
    public native int udev_enumerate_scan_subsystems(final Pointer udev_enumerate);

    @Override
    public native Pointer udev_enumerate_get_list_entry(final Pointer udev_enumerate);

    @Override
    public native Pointer udev_queue_ref(final Pointer udev_queue);

    @Override
    public native Pointer udev_queue_unref(final Pointer udev_queue);

    @Override
    public native Pointer udev_queue_get_udev(final Pointer udev_queue);

    @Override
    public native Pointer udev_queue_new(final Pointer udev);

    @Override
    public native boolean udev_queue_get_udev_is_active(final Pointer udev_queue);

    @Override
    public native boolean udev_queue_get_queue_is_empty(final Pointer udev_queue);

    @Override
    public native int udev_queue_get_fd(final Pointer udev_queue);

    @Override
    public native int udev_queue_flush(final Pointer udev_queue);

    @Override
    public native Pointer udev_hwdb_new(final Pointer udev);

    @Override
    public native Pointer udev_hwdb_ref(final Pointer hwdb);

    @Override
    public native Pointer udev_hwdb_unref(final Pointer hwdb);

    @Override
    public native Pointer udev_hwdb_get_properties_list_entry(final Pointer hwdb, final Pointer modalias, final int flags);

    @Override
    public native int udev_util_encode_string(final Pointer str, final Pointer str_enc, final int len);
}
