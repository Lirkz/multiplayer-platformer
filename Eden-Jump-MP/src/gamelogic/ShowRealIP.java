package gamelogic;

import java.net.*;
import java.util.*;

public class ShowRealIP {
public static void main(String[] args) {
try {
Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

while (interfaces.hasMoreElements()) {
NetworkInterface ni = interfaces.nextElement();

// Skip inactive or loopback interfaces
if (!ni.isUp() || ni.isLoopback()) continue;

Enumeration<InetAddress> addresses = ni.getInetAddresses();
while (addresses.hasMoreElements()) {
InetAddress addr = addresses.nextElement();

if (addr instanceof Inet4Address) {
System.out.println("Found IP: " + addr.getHostAddress());
}
}
}
} catch (Exception e) {
e.printStackTrace();
}
}
}