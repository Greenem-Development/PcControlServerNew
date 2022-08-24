package org.greenem.pccontrol.packets;

public interface Packet {
//    public static final int lowestSupportedVersion = 1;
//    public static final Class clazz = null;
    public boolean isValid();
    public boolean isVersionSupported();
}
