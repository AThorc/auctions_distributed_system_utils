/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package totalOrderReply;

/**
 *
 * @author alessandrotorcetta
 */
public class replyMsg {
    private String content; //contenuto di un messaggio di PROPOSED_TS per algoritmo ISIS
    private int port; //porta del destinatario di questo messaggio
    private String ip; //ip del destinatario di questo messaggio

    public replyMsg(String content, int port, String ip) {
        this.content = content;
        this.port = port;
        this.ip = ip;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
    
    
    
}
