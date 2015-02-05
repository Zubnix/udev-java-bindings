package org.freedesktop.libudev.jna;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;

public interface LogFunction extends Callback {
    void log(Pointer udev,
             int priority,
             Pointer file,
             int line,
             Pointer fn,
             Pointer format,
             Pointer args);
}
