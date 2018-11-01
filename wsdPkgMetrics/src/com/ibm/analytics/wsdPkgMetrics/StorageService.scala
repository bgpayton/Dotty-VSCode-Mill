/* Provides storage services for WSD Package Metrics. */
package com.ibm.analytics.wsdPkgMetrics

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

object StorageService {

  def testConnection = {
    Class.forName("org.sqlite.JDBC")

    val connection = DriverManager.getConnection("jdbc:sqlite:sample.db")
    val statement = connection.createStatement();
    statement.setQueryTimeout(30)

    statement.executeUpdate("drop table if exists cats")
    statement.executeUpdate("create table cats (name string, breed string)")
    statement.executeUpdate("insert into cats values('Leo', 'Mau')")
    statement.executeUpdate("insert into cats values('Ginger', 'Orange tabby')")
    statement.executeUpdate("insert into cats values('Meemeow', 'Ragdoll')")
    statement.executeUpdate("insert into cats values('Shadow', 'Unknown')")
    val rs = statement.executeQuery("select * from cats")
    while(rs.next()) {
      // read the result set
      println("name = " + rs.getString("name") + ", breed = " + rs.getString("breed"));
    }

    connection.close()

  }
}