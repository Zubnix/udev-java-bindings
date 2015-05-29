package org.freedesktop.libudev.jna;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

public class StringUtil {

    private StringUtil() {
    }

    public static Pointer asPointer(String charArray) {
        // The code is from com.sun.jna.NativeString.NativeString(java.lang.String, java.lang.String)
        byte[] data = Native.toByteArray(charArray);
        Pointer pointer = new Memory(data.length + 1);
        pointer.write(0, data, 0, data.length);
        pointer.setByte(data.length, (byte) 0);
        return pointer;
    }

    public static String fromPointer(Pointer charP) {
        if (charP != null) {
            return charP.getString(0);
        } else {
            return null;
        }
    }
}
