package org.freedesktop.libudev;

public interface Logger {
    void log(LibUdev udev,
             int priority,
             String file,
             int line,
             String functionc,
             String format,
             Object[] args);
}
