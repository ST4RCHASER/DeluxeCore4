package me.starchaser.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



















public class MySQL
  extends Database
{
  private final String user;
  private final String database;
  private final String password;
  private final String port;
  private final String hostname;
  
  public MySQL(String hostname, String port, String username, String password)
  {
    this(hostname, port, null, username, password);
  }
  














  public MySQL(String hostname, String port, String database, String username, String password)
  {
    this.hostname = hostname;
    this.port = port;
    this.database = database;
    user = username;
    this.password = password;
  }
  
  public Connection openConnection()
    throws SQLException, ClassNotFoundException
  {
    if (checkConnection()) {
      return connection;
    }
    
    String connectionURL = "jdbc:mysql://" + hostname + ":" + port;
    
    if (database != null) {
      connectionURL = connectionURL + "/" + database + "?useUnicode=true&characterEncoding=UTF-8";
    }
    
    Class.forName("com.mysql.jdbc.Driver");
    connection = DriverManager.getConnection(connectionURL, user, password);
    
    return connection;
  }
}
