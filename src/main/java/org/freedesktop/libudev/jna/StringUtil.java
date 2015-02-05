package org.freedesktop.libudev.jna;


import com.sun.jna.Memory;
import com.sun.jna.Pointer;

public class StringUtil {

    private StringUtil() {
    }

    public static Pointer asPointer(String charArray) {
        Pointer chars = new Memory(charArray.length() + 1); //FIXME assumes ascii-only string
        chars.setString(0, charArray);
        return chars;
    }

    public static String fromPointer(Pointer charP){
        if(charP !=null){
            return charP.getString(0);
        }else{
            return null;
        }
    }
}
