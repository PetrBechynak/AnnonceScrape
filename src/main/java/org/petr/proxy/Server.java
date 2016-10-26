package org.petr.proxy;

/**
 * Created by petr on 20.9.16.
 */
public class Server {
    String ip;
    String port;
    String type;
    String anonymity;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAnonymity() {
        return anonymity;
    }

    public void setAnonymity(String anonymity) {
        this.anonymity = anonymity;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return Integer.parseInt(port.toString());
    }

    public void setPort(String port) {
        this.port = port;
    }
}
