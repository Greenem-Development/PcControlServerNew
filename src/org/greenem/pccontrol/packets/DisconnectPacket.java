package org.greenem.pccontrol.packets;

import java.io.Serializable;

import org.greenem.pccontrol.data.References;

public class DisconnectPacket implements Packet, Serializable {
    private static final long serialVersionUID = 1L;
    private String disconnectText;
    private long recommendedThreshold = References.recommendedConnectionThreshold;
    
    public DisconnectPacket(String disconnectText) {
        this.disconnectText = disconnectText;
    }
    
    public String getText() {
        return disconnectText;
    }
    
    public long getThreshold() {
        return recommendedThreshold;
    }
    
    public void setTheshold(long threshold) {
    	this.recommendedThreshold = threshold;
    }
    
    public boolean isValid() {
        return true;
    }
    
    public boolean isVersionSupported() {
        return true;
    }
}
