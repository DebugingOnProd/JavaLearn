package org.lhq.design.adapter.impl;

import org.lhq.design.adapter.Socket;
import org.lhq.design.adapter.SocketAdapter;
import org.lhq.design.adapter.Volt;

public class SocketAdapterImpl implements SocketAdapter {
    private Socket sock = new Socket();

    @Override
    public Volt get220Volt() {
        return sock.getVolt();
    }

    @Override
    public Volt get12Volt() {
        return this.convertVolt(sock.getVolt(), 18);
    }

    @Override
    public Volt get3Volt() {
        return this.convertVolt(sock.getVolt(), 73);
    }

    private Volt convertVolt(Volt v, int i) {
        return new Volt(v.getVolts() / i);
    }
}
