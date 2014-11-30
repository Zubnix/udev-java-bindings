import org.freedesktop.libudev.Device;
import org.freedesktop.libudev.Enumerate;
import org.freedesktop.libudev.LibUdev;
import org.freedesktop.libudev.ListEntry;

public class Example {
    public static void main(String[] args) {

	/* Create the udev object */
        LibUdev udev = LibUdev.create();

	/* Create a list of the devices in the 'hidraw' subsystem. */
        Enumerate enumerate = Enumerate.create(udev);
        if(enumerate.addMatchSubsystem("input") < 0){
            System.err.printf("Error when adding hidraw subsystem match.");
            System.exit(1);
        }
        if(enumerate.scanDevices() < 0){
            System.err.printf("Error when scanning devices.");
            System.exit(1);
        }
        ListEntry devices = enumerate.getListEntry();
    /* For each item enumerated, print out its information.
       udev_list_entry_foreach is a macro which expands to
	   a loop. The loop will be executed for each member in
	   devices, setting dev_list_entry to a list entry
	   which contains the device's path in /sys. */

        for (ListEntry devListEntry = devices;
             devListEntry != null;
             devListEntry = devListEntry.getNext()) {

		/* Get the filename of the /sys entry for the device
           and create a udev_device object (dev) representing it */
            String path = devListEntry.getName();
            Device dev = Device.newFromSyspath(udev,
                                               path);
            if(dev == null){
                System.out.println("No more devices matching subsystem 'input' found.");
                continue;
            }

		/* usb_device_get_devnode() returns the path to the device node
		   itself in /dev. */
            System.out.printf("Device Node Path: %s\n",
                              dev.getDevnode());

		/* From here, we can call get_sysattr_value() for each file
		   in the device's /sys entry. The strings passed into these
		   functions (idProduct, idVendor, serial, etc.) correspond
		   directly to the files in the directory which represents
		   the USB device. Note that USB strings are Unicode, UCS2
		   encoded, but the strings returned from
		   udev_device_get_sysattr_value() are UTF-8 encoded. */
            System.out.printf("  VID/PID: %s %s\n",
                              dev.getSysattrValue(
                                      "idVendor"),
                              dev.getSysattrValue(
                                      "idProduct"));
            System.out.printf("  %s\n  %s\n",
                              dev.getSysattrValue(
                                      "manufacturer"),
                              dev.getSysattrValue(
                                      "product"));
            System.out.printf("  serial: %s\n",
                              dev.getSysattrValue(
                                      "serial"));
        }
    }
}
