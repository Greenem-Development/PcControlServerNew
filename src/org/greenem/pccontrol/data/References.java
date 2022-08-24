package org.greenem.pccontrol.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.greenem.pccontrol.permissions.Group;
import org.greenem.pccontrol.util.AuthType;

public class References {
	public static long connectionThreshold = 100;
	public static long recommendedConnectionThreshold = 200;
	public static long authTimeout = 10000;
	public static AuthType authType = AuthType.USUAL_PWD;
	public static String pwd = null; // can't it bee seen outside via netbeans?
	public static List<Group> groups = new ArrayList<>();
	public static Map<Group, String> groupPasswords = new HashMap<>();
	public static boolean adressWhitelistEnabled = false;
	public static boolean adressBlacklistEnabled = false;
	public static List<String> adressWhitelist = new ArrayList<>();
	public static List<String> adressBlacklist = new ArrayList<>();
	public static boolean blacklistPriorityAboveWhitelist = true;
}
