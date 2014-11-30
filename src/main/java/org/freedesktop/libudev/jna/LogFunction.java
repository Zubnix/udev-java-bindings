package org.freedesktop.libudev.jna;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;

public interface LogFunction extends Callback {
    void log(StructUdev udev,
             int priority,
             String file,
             int line,
             String fn,
             String format,
             Pointer args);
}
