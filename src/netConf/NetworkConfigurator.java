package netConf;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nick F
 * 
 *  TRAMITE CONNETTORE JDBC SI INTERFACCIA AD UN DATABASE LOCALE MYSQL FORMATTATO NEL SEGUENTE MODO:
 *    ------------------------------------------------------------------------------------
 *      node_id   |  ip_address   |   port     |   node_name    |   isReplica    |   isMe
 *    ------------------------------------------------------------------------------------
 * 
 *  isReplica: booleano posto a true se il nodo è istanza di ReplicaManager
 *  isMe: booleano posto a true se la entry corrisponde al nodo che effettua la lettura
 *  node_id: id univoco del nodo nella rete per l'identificazione della sorgente nei messaggi di total ordering
 */
public class NetworkConfigurator {
    private static NetworkConfigurator istance; //classe singleton per la configurazione di rete del sistema distribuito
    private List<NetworkNode> nodes; //tutti i nodi registrati al sistema
    private List<NetworkNode> replicas; //nodi di tipo ReplicaManager
    private List<NetworkNode> frontends; //nodi di tipo FrontEnd
    private NetworkNode myself; //il nodo che coincide con la macchina su cui sta eseguendo questo codice
  
    
    private NetworkConfigurator(boolean amReplica){
        
        NetworkNode n;
        int port, id;
        String name, ip;
        boolean isReplica, isMe;
        
        this.nodes = new LinkedList<NetworkNode>();
        this.replicas = new LinkedList<NetworkNode>();
        this.frontends = new LinkedList<NetworkNode>();

        /*FETCH NETWORK CONFIGURATION FROM DATABASE..*/
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NetworkConfigurator.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.sql.Connection con;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tesinah_db", "root", "root");
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM nodes");
            if(rs!=null){
                while(rs.next()){
                    id = rs.getInt("id");
                    ip = rs.getString("ip_address");
                    port = rs.getInt("port");
                    name = rs.getString("node_name");
                    isReplica = rs.getBoolean("isReplica");
                    isMe = rs.getBoolean("isMe");
                    
                    n = new NetworkNode(id, name, port, ip, isReplica, isMe);
                    
                    if(isMe == true && amReplica && isReplica)this.myself = n;
                    if(isMe && !amReplica && !isReplica) this.myself = n;
                    
                    if(!isReplica)this.frontends.add(n);
                    else this.replicas.add(n);
                    
                    this.nodes.add(n);
                    System.out.println("Added to node list:  " + n.toString());
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(NetworkConfigurator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static NetworkConfigurator getInstance(boolean result) {
        if(istance == null) istance = new NetworkConfigurator(result);
        return istance;
    }

    public List<NetworkNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<NetworkNode> nodes) {
        this.nodes = nodes;
    }

    public List<NetworkNode> getReplicas() {
        return replicas;
    }

    public void setReplicas(List<NetworkNode> replicas) {
        this.replicas = replicas;
    }

    public List<NetworkNode> getFrontends() {
        return frontends;
    }

    public void setFrontends(List<NetworkNode> frontends) {
        this.frontends = frontends;
    }

    public NetworkNode getMyself() {
        return myself;
    }

    public void setMyself(NetworkNode myself) {
        this.myself = myself;
    }
    
    
}
