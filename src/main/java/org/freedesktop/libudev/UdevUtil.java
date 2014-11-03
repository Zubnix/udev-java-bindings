package org.freedesktop.libudev;

/**
 * udev_util — utils
 * <p>
 * Utilities useful when dealing with devices and device node names.
 */
public class UdevUtil {
    /**
     * Encode all potentially unsafe characters of a string to the corresponding 2 char hex value prefixed by '\x'.
     *
     * @param str    input string to be encoded
     * @param strEnc output string to store the encoded input string
     * @param len    maximum size of the output string, which may be four times as long as the input string
     * @return 0 if the entire string was copied, non-zero otherwise.
     */
    public static int encodeString(final String str, final String strEnc, final int len) {
        return LibUdevJNI.utilEncodeString(str, strEnc,len);
    }
}
