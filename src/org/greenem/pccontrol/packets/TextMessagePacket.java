package org.greenem.pccontrol.packets;

import java.io.Serializable;

public class TextMessagePacket implements Packet, Serializable {
    private static final long serialVersionUID = 1L;
//    public int lowestSupportedVersion = 1;
    private String text;
//  public Class<?> clazz = this.getClass();
    
    public TextMessagePacket(String text) {
        this.text = text;
    }
    
    public String getText() {
        return text;
    }
    
    public boolean isValid() {
        return text!=null && serialVersionUID>0; // && clazz!=null // && lowestSupportedVersion>0
    }
    
    public boolean isVersionSupported() {
        return serialVersionUID > 0; // && serialVersionUID >= lowestSupportedVersion
    }
}
