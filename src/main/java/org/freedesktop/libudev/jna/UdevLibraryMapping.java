package org.freedesktop.libudev.jna;

import com.sun.jna.Pointer;

public interface UdevLibraryMapping {

    String JNA_LIBRARY_NAME = "udev";

    Pointer udev_ref(Pointer udev);

    Pointer udev_unref(Pointer udev);

    Pointer udev_new();

    void udev_set_log_fn(Pointer udev,
                         LogFunction logFn);

    int udev_get_log_priority(Pointer udev);

    void udev_set_log_priority(Pointer udev,
                               int priority);

    Pointer udev_get_userdata(Pointer udev);

    void udev_set_userdata(Pointer udev,
                           Pointer userdata);

    Pointer udev_list_entry_get_next(Pointer list_entry);

    Pointer udev_list_entry_get_by_name(Pointer list_entry,
                                        Pointer name);

    Pointer udev_list_entry_get_name(Pointer list_entry);

    Pointer udev_list_entry_get_value(Pointer list_entry);

    Pointer udev_device_ref(Pointer udev_device);

    Pointer udev_device_unref(Pointer udev_device);

    Pointer udev_device_get_udev(Pointer udev_device);

    Pointer udev_device_new_from_syspath(Pointer udev,
                                         Pointer syspath);

    Pointer udev_device_new_from_devnum(Pointer udev,
                                        byte type,
                                        int devnum);

    Pointer udev_device_new_from_subsystem_sysname(Pointer udev,
                                                   Pointer subsystem,
                                                   Pointer sysname);

    Pointer udev_device_new_from_device_id(Pointer udev,
                                           Pointer id);

    Pointer udev_device_new_from_environment(Pointer udev);

    /* udev_device_get_parent_*() does not take a reference on the returned device, it is automatically unref'd with the parent */
    Pointer udev_device_get_parent(Pointer udev_device);

    Pointer udev_device_get_parent_with_subsystem_devtype(Pointer udev_device,
                                                          Pointer subsystem,
                                                          Pointer devtype);

    /* retrieve device properties */
    Pointer udev_device_get_devpath(Pointer udev_device);

    Pointer udev_device_get_subsystem(Pointer udev_device);

    Pointer udev_device_get_devtype(Pointer udev_device);

    Pointer udev_device_get_syspath(Pointer udev_device);

    Pointer udev_device_get_sysname(Pointer udev_device);

    Pointer udev_device_get_sysnum(Pointer udev_device);

    Pointer udev_device_get_devnode(Pointer udev_device);

    boolean udev_device_get_is_initialized(Pointer udev_device);

    Pointer udev_device_get_devlinks_list_entry(Pointer udev_device);

    Pointer udev_device_get_properties_list_entry(Pointer udev_device);

    Pointer udev_device_get_tags_list_entry(Pointer udev_device);

    Pointer udev_device_get_sysattr_list_entry(Pointer udev_device);

    Pointer udev_device_get_property_value(Pointer udev_device,
                                           Pointer key);

    Pointer udev_device_get_driver(Pointer udev_device);

    int udev_device_get_devnum(Pointer udev_device);

    Pointer udev_device_get_action(Pointer udev_device);

    long udev_device_get_seqnum(Pointer udev_device);

    long udev_device_get_usec_since_initialized(Pointer udev_device);

    Pointer udev_device_get_sysattr_value(Pointer udev_device,
                                          Pointer sysattr);

    int udev_device_set_sysattr_value(Pointer udev_device,
                                      Pointer sysattr,
                                      Pointer value);

    boolean udev_device_has_tag(Pointer udev_device,
                                Pointer tag);

    Pointer udev_monitor_ref(Pointer udev_monitor);

    Pointer udev_monitor_unref(Pointer udev_monitor);

    Pointer udev_monitor_get_udev(Pointer udev_monitor);

    Pointer udev_monitor_new_from_netlink(Pointer udev,
                                          Pointer name);

    int udev_monitor_enable_receiving(Pointer udev_monitor);

    int udev_monitor_set_receive_buffer_size(Pointer udev_monitor,
                                             int size);

    int udev_monitor_get_fd(Pointer udev_monitor);

    Pointer udev_monitor_receive_device(Pointer udev_monitor);

    int udev_monitor_filter_add_match_subsystem_devtype(Pointer udev_monitor,
                                                        Pointer subsystem,
                                                        Pointer devtype);

    int udev_monitor_filter_add_match_tag(Pointer udev_monitor,
                                          Pointer tag);

    int udev_monitor_filter_update(Pointer udev_monitor);

    int udev_monitor_filter_remove(Pointer udev_monitor);

    Pointer udev_enumerate_ref(Pointer udev_enumerate);

    Pointer udev_enumerate_unref(Pointer udev_enumerate);

    Pointer udev_enumerate_get_udev(Pointer udev_enumerate);

    Pointer udev_enumerate_new(Pointer udev);

    int udev_enumerate_add_match_subsystem(Pointer udev_enumerate,
                                           Pointer subsystem);

    int udev_enumerate_add_nomatch_subsystem(Pointer udev_enumerate,
                                             Pointer subsystem);

    int udev_enumerate_add_match_sysattr(Pointer udev_enumerate,
                                         Pointer sysattr,
                                         Pointer value);

    int udev_enumerate_add_nomatch_sysattr(Pointer udev_enumerate,
                                           Pointer sysattr,
                                           Pointer value);

    int udev_enumerate_add_match_property(Pointer udev_enumerate,
                                          Pointer property,
                                          Pointer value);

    int udev_enumerate_add_match_sysname(Pointer udev_enumerate,
                                         Pointer sysname);

    int udev_enumerate_add_match_tag(Pointer udev_enumerate,
                                     Pointer tag);

    int udev_enumerate_add_match_parent(Pointer udev_enumerate,
                                        Pointer parent);

    int udev_enumerate_add_match_is_initialized(Pointer udev_enumerate);

    int udev_enumerate_add_syspath(Pointer udev_enumerate,
                                   Pointer syspath);

    int udev_enumerate_scan_devices(Pointer udev_enumerate);

    int udev_enumerate_scan_subsystems(Pointer udev_enumerate);

    Pointer udev_enumerate_get_list_entry(Pointer udev_enumerate);

    Pointer udev_queue_ref(Pointer udev_queue);

    Pointer udev_queue_unref(Pointer udev_queue);

    Pointer udev_queue_get_udev(Pointer udev_queue);

    Pointer udev_queue_new(Pointer udev);

    boolean udev_queue_get_udev_is_active(Pointer udev_queue);

    boolean udev_queue_get_queue_is_empty(Pointer udev_queue);

    int udev_queue_get_fd(Pointer udev_queue);

    int udev_queue_flush(Pointer udev_queue);

    Pointer udev_hwdb_new(Pointer udev);

    Pointer udev_hwdb_ref(Pointer hwdb);

    Pointer udev_hwdb_unref(Pointer hwdb);

    Pointer udev_hwdb_get_properties_list_entry(Pointer hwdb,
                                                Pointer modalias,
                                                int flags);

    int udev_util_encode_string(Pointer str,
                                Pointer str_enc,
                                int len);

}