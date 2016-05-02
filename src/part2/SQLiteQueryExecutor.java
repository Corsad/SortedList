package part2;



import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 * @author Denis Rinfret implemented by Dang Kim Khanh S3372771
 */
public class SQLiteQueryExecutor {

    private Connection dbConn = null;
    ArrayList<Object[]> rows = null;
    String[] titles = null;

    public SQLiteQueryExecutor(File file) {
        this.connectTo(file);
    }

    public SQLiteQueryExecutor() {
    }

    public boolean connectTo(File file) {
        try {
            // load the JDBC driver for SQLite
            Class.forName("org.sqlite.JDBC");
            // get a connection to the DB
            dbConn = DriverManager.getConnection("jdbc:sqlite:" + file.getAbsolutePath());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLiteQueryExecutor.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(SQLiteQueryExecutor.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public boolean isConnected() {
        return dbConn != null;
    }

    public boolean executeQuery(String query) {

        Statement stat;
        ResultSet rs;
        ResultSetMetaData meta;

        // if no connection, cannot execute a query
        if (dbConn == null) {
            return false;
        }
        
        try {
            // start by creating an SQL statement
            stat = dbConn.createStatement();
            // then execute a query
            rs = stat.executeQuery(query);
            // the metadata is data about the resulting data (column count, 
            // column names, ...)
            meta = rs.getMetaData();

            rows = new ArrayList<Object[]>();
            titles = new String[meta.getColumnCount()];
            Object[] row;
            
            // put the column labels (or names) in the titles array
            for (int i = 0; i < meta.getColumnCount(); i++) {
                titles[i] = meta.getColumnLabel(i + 1);
            }

            // go through the result set row by row (like with an iterator)
            while (rs.next()) {
                row = new Object[meta.getColumnCount()];
                // extract the row from the result set
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    row[i - 1] = rs.getString(i);
                }
                // and add the row to the list of rows
                rows.add(row);
            }

            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(SQLiteQueryExecutor.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return true;
    }

    public String[] getTitles() {
        return titles;
    }

    public ArrayList<Object[]> getRows() {
        return rows;
    }

    // get the table model to be used by a JTable in a Swing application
    public DefaultTableModel getTableModel() {
        if (rows != null) {
            return new DefaultTableModel((Object[][]) rows.toArray(new Object[0][0]), titles);
        } else {
            return null;
        }
    }
}
