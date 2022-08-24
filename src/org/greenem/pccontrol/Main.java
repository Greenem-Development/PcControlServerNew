package org.greenem.pccontrol;

import org.greenem.pccontrol.packets.TextMessagePacket;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        TextMessagePacket p = new TextMessagePacket("a");
        System.out.println(p.getText());
    }
}
