package org.greenem.pccontrol.packets;

import java.io.Serializable;

import org.greenem.pccontrol.data.References;
import org.greenem.pccontrol.util.AuthType;

public class AuthPacket implements Packet, Serializable {
    private static final long serialVersionUID = 1L;
//    private AuthType authType;
    private String password;
    private String userGroup;
    private String groupPassword;
    
    public AuthPacket(String pwd) {
//        this.authType = authType;
        this.password = pwd;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(String userGroup) {
		this.userGroup = userGroup;
	}

	public String getGroupPassword() {
		return groupPassword;
	}

	public void setGroupPassword(String groupPassword) {
		this.groupPassword = groupPassword;
	}

	public boolean isValid() {
        return true;
    }
    
    public boolean isVersionSupported() {
        return true;
    }
}