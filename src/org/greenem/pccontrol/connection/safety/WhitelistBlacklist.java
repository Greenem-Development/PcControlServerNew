package org.greenem.pccontrol.connection.safety;

import java.net.InetAddress;

import org.greenem.pccontrol.data.References;

public class WhitelistBlacklist {
	public static boolean isAdressAllowed(InetAddress adress) {
		return isAdressAllowed(adress.toString().split("/")[1]); // test this
	}
	
	public static boolean isAdressAllowed(String adress) {
		if(!References.adressWhitelistEnabled && !References.adressBlacklistEnabled) return true;
		if(References.blacklistPriorityAboveWhitelist) {
			if(References.adressBlacklist.contains(adress)) return false;
			else return References.adressWhitelist.contains(adress);
		} else {
			if(References.adressWhitelist.contains(adress)) return true;
			else return !References.adressBlacklist.contains(adress);
		}
	}
}
