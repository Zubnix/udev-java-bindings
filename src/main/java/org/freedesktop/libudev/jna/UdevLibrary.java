package org.freedesktop.libudev.jna;


import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

public interface UdevLibrary extends Library {
    UdevLibrary INSTANCE = (UdevLibrary) Native.loadLibrary("udev",
                                                            UdevLibrary.class);

    StructUdev udev_ref(StructUdev udev);

    StructUdev udev_unref(StructUdev udev);

    StructUdev udev_new();

    void udev_set_log_fn(StructUdev udev,
                         LogFunction logFn);

    int udev_get_log_priority(StructUdev udev);

    void udev_set_log_priority(StructUdev udev,
                               int priority);

    Pointer udev_get_userdata(StructUdev udev);

    void udev_set_userdata(StructUdev udev,
                           Pointer userdata);

    StructUdevListEntry udev_list_entry_get_next(StructUdevListEntry list_entry);

    StructUdevListEntry udev_list_entry_get_by_name(StructUdevListEntry list_entry,
                                                    String name);

    String udev_list_entry_get_name(StructUdevListEntry list_entry);

    String udev_list_entry_get_value(StructUdevListEntry list_entry);

    StructUdevDevice udev_device_ref(StructUdevDevice udev_device);

    StructUdevDevice udev_device_unref(StructUdevDevice udev_device);

    StructUdev udev_device_get_udev(StructUdevDevice udev_device);

    StructUdevDevice udev_device_new_from_syspath(StructUdev udev,
                                                  String syspath);

    StructUdevDevice udev_device_new_from_devnum(StructUdev udev,
                                                 byte type,
                                                 int devnum);

    StructUdevDevice udev_device_new_from_subsystem_sysname(StructUdev udev,
                                                            String subsystem,
                                                            String sysname);

    StructUdevDevice udev_device_new_from_device_id(StructUdev udev,
                                                    String id);

    StructUdevDevice udev_device_new_from_environment(StructUdev udev);

    /* udev_device_get_parent_*() does not take a reference on the returned device, it is automatically unref'd with the parent */
    StructUdevDevice udev_device_get_parent(StructUdevDevice udev_device);

    StructUdevDevice udev_device_get_parent_with_subsystem_devtype(StructUdevDevice udev_device,
                                                                   String subsystem,
                                                                   String devtype);

    /* retrieve device properties */
    String udev_device_get_devpath(StructUdevDevice udev_device);

    String udev_device_get_subsystem(StructUdevDevice udev_device);

    String udev_device_get_devtype(StructUdevDevice udev_device);

    String udev_device_get_syspath(StructUdevDevice udev_device);

    String udev_device_get_sysname(StructUdevDevice udev_device);

    String udev_device_get_sysnum(StructUdevDevice udev_device);

    String udev_device_get_devnode(StructUdevDevice udev_device);

    boolean udev_device_get_is_initialized(StructUdevDevice udev_device);

    StructUdevListEntry udev_device_get_devlinks_list_entry(StructUdevDevice udev_device);

    StructUdevListEntry udev_device_get_properties_list_entry(StructUdevDevice udev_device);

    StructUdevListEntry udev_device_get_tags_list_entry(StructUdevDevice udev_device);

    StructUdevListEntry udev_device_get_sysattr_list_entry(StructUdevDevice udev_device);

    String udev_device_get_property_value(StructUdevDevice udev_device,
                                          String key);

    String udev_device_get_driver(StructUdevDevice udev_device);

    int udev_device_get_devnum(StructUdevDevice udev_device);

    String udev_device_get_action(StructUdevDevice udev_device);

    long udev_device_get_seqnum(StructUdevDevice udev_device);

    long udev_device_get_usec_since_initialized(StructUdevDevice udev_device);

    String udev_device_get_sysattr_value(StructUdevDevice udev_device,
                                         String sysattr);

    int udev_device_set_sysattr_value(StructUdevDevice udev_device,
                                      String sysattr,
                                      String value);

    boolean udev_device_has_tag(StructUdevDevice udev_device,
                                String tag);

    StructUdevMonitor udev_monitor_ref(StructUdevMonitor udev_monitor);

    StructUdevMonitor udev_monitor_unref(StructUdevMonitor udev_monitor);

    StructUdev udev_monitor_get_udev(StructUdevMonitor udev_monitor);

    StructUdevMonitor udev_monitor_new_from_netlink(StructUdev udev,
                                                    String name);

    int udev_monitor_enable_receiving(StructUdevMonitor udev_monitor);

    int udev_monitor_set_receive_buffer_size(StructUdevMonitor udev_monitor,
                                             int size);

    int udev_monitor_get_fd(StructUdevMonitor udev_monitor);

    StructUdevDevice udev_monitor_receive_device(StructUdevMonitor udev_monitor);

    int udev_monitor_filter_add_match_subsystem_devtype(StructUdevMonitor udev_monitor,
                                                        String subsystem,
                                                        String devtype);

    int udev_monitor_filter_add_match_tag(StructUdevMonitor udev_monitor,
                                          String tag);

    int udev_monitor_filter_update(StructUdevMonitor udev_monitor);

    int udev_monitor_filter_remove(StructUdevMonitor udev_monitor);

    StructUdevEnumerate udev_enumerate_ref(StructUdevEnumerate udev_enumerate);

    StructUdevEnumerate udev_enumerate_unref(StructUdevEnumerate udev_enumerate);

    StructUdev udev_enumerate_get_udev(StructUdevEnumerate udev_enumerate);

    StructUdevEnumerate udev_enumerate_new(StructUdev udev);

    int udev_enumerate_add_match_subsystem(StructUdevEnumerate udev_enumerate,
                                           String subsystem);

    int udev_enumerate_add_nomatch_subsystem(StructUdevEnumerate udev_enumerate,
                                             String subsystem);

    int udev_enumerate_add_match_sysattr(StructUdevEnumerate udev_enumerate,
                                         String sysattr,
                                         String value);

    int udev_enumerate_add_nomatch_sysattr(StructUdevEnumerate udev_enumerate,
                                           String sysattr,
                                           String value);

    int udev_enumerate_add_match_property(StructUdevEnumerate udev_enumerate,
                                          String property,
                                          String value);

    int udev_enumerate_add_match_sysname(StructUdevEnumerate udev_enumerate,
                                         String sysname);

    int udev_enumerate_add_match_tag(StructUdevEnumerate udev_enumerate,
                                     String tag);

    int udev_enumerate_add_match_parent(StructUdevEnumerate udev_enumerate,
                                        StructUdevDevice parent);

    int udev_enumerate_add_match_is_initialized(StructUdevEnumerate udev_enumerate);

    int udev_enumerate_add_syspath(StructUdevEnumerate udev_enumerate,
                                   String syspath);

    int udev_enumerate_scan_devices(StructUdevEnumerate udev_enumerate);

    int udev_enumerate_scan_subsystems(StructUdevEnumerate udev_enumerate);

    StructUdevListEntry udev_enumerate_get_list_entry(StructUdevEnumerate udev_enumerate);

    StructUdevQueue udev_queue_ref(StructUdevQueue udev_queue);

    StructUdevQueue udev_queue_unref(StructUdevQueue udev_queue);

    StructUdev udev_queue_get_udev(StructUdevQueue udev_queue);

    StructUdevQueue udev_queue_new(StructUdev udev);

    boolean udev_queue_get_udev_is_active(StructUdevQueue udev_queue);

    boolean udev_queue_get_queue_is_empty(StructUdevQueue udev_queue);

    int udev_queue_get_fd(StructUdevQueue udev_queue);

    int udev_queue_flush(StructUdevQueue udev_queue);

    StructUdevHwdb udev_hwdb_new(StructUdev udev);

    StructUdevHwdb udev_hwdb_ref(StructUdevHwdb hwdb);

    StructUdevHwdb udev_hwdb_unref(StructUdevHwdb hwdb);

    StructUdevListEntry udev_hwdb_get_properties_list_entry(StructUdevHwdb hwdb,
                                                            String modalias,
                                                            int flags);

    int udev_util_encode_string(String str,
                                String str_enc,
                                int len);

}