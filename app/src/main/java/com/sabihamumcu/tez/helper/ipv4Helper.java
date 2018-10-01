package com.sabihamumcu.tez.helper;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Created by sabis on 4/24/2018.
 */

public class ipv4Helper {

    private static ipv4Helper ipv4Helper;

    private ipv4Helper() {

    }

    public static ipv4Helper getInstance() {
        if (ipv4Helper == null) {
            ipv4Helper = new ipv4Helper();
        }
        return ipv4Helper;
    }

    public static String getLocalAddress() {
        Enumeration<NetworkInterface> ifaces = null;
        try {
            ifaces = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        while (ifaces.hasMoreElements()) {
            NetworkInterface iface = ifaces.nextElement();
            Enumeration<InetAddress> addresses = iface.getInetAddresses();

            while (addresses.hasMoreElements()) {
                InetAddress addr = addresses.nextElement();
                if (addr instanceof Inet4Address && !addr.isLoopbackAddress()) {
                    return addr.toString();
                }
            }
        }
        return null;
    }
}
