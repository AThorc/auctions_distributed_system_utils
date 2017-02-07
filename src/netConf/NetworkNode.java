package netConf;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nick F
 */
public class NetworkNode {
    private int id;
    private String name;
    private int port;
    private String ip;
    private boolean isReplica;
    private boolean isMe;

    public NetworkNode(int id, String name, int port, String ip, boolean isReplica, boolean isMe) {
        this.id = id;
        this.name = name;
        this.port = port;
        this.ip = ip;
        this.isReplica = isReplica;
        this.isMe = isMe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public boolean isIsReplica() {
        return isReplica;
    }

    public void setIsReplica(boolean isReplica) {
        this.isReplica = isReplica;
    }

    public boolean isIsMe() {
        return isMe;
    }

    public void setIsMe(boolean isMe) {
        this.isMe = isMe;
    }

    @Override
    public String toString() {
        return "NetworkNode{" + "id=" + id + ", name=" + name + ", port=" + port + ", ip=" + ip + ", isReplica=" + isReplica + ", isMe=" + isMe + '}';
    }
    
    
}
