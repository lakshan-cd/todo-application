package com.lakshancd.todo_application_backend.common.util;

import io.micrometer.common.util.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Utility class for obtaining environment-related information.
 */
public class EnvUtil {
    /**
     * Retrieves the name of the server machine.
     *
     * @return The machine name.
     * @throws UnknownHostException  If the machine name cannot be determined.
     * @throws IllegalStateException If the machine name is empty or inaccessible.
     */
    public static String getServerMachine() throws UnknownHostException {
        // Get the host name of the local machine
        String machineName = InetAddress.getLocalHost().getHostName();
        // Check if the machine name is empty
        if (StringUtils.isEmpty(machineName)) {
            throw new IllegalStateException(" Server Machine name cannot be accessed");
        } else {
            return machineName;
        }
    }
}
