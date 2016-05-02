package part2;


import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

/**
 * @author Denis Rinfret implemented by Dang Kim Khanh S3372771
 */
public class TableModel extends Observable {

    private SQLiteQueryExecutor db;
    private String dbFileName;
    private String query;
    private JLabel status;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getDbFileName() {
        return dbFileName;
    }

    //Get the table name list
    public String[] tableName() {
        String [] list = null;
        db = new SQLiteQueryExecutor(new File(dbFileName));
        db.executeQuery("SELECT name FROM sqlite_master WHERE type = 'table'");
        for (int i = 0; i < db.getTableModel().getRowCount(); i++) {
           list[i] = db.getTableModel().getValueAt(i, 1).toString();
        }
        return list;
    }

    //Get new database name and connect to the new database
    public void setDbFileName(String dbFileName) {
        this.dbFileName = dbFileName;
        db = new SQLiteQueryExecutor(new File(dbFileName));
    }

    public TableModel(JLabel status) {
        this.status = status;
        dbFileName = null;
        db = new SQLiteQueryExecutor();
    }

    public DefaultTableModel getTableModel() {
        return db.getTableModel();
    }

    public void update() {
        if (dbFileName == null) {
            status.setText("No database is selected");
        } else {
            try {
                db.executeQuery(query);
                setChanged();
                notifyObservers(db.getTableModel());
                status.setText("");
            } catch (Exception e) {
                status.setText(e.getMessage());
            }
        }
    }
}
