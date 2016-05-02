package part2;



import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * @author Denis Rinfret implemented by Dang Kim Khanh S3372771
 */
public class TableView extends JScrollPane implements Observer {
    private DefaultTableModel dataModel;
    private JTable table;
    
    public TableView() {
        table = new JTable();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        this.setViewportView(table);
    }
    
    @Override
    public void update(Observable o, Object o1) {
        dataModel = (DefaultTableModel) o1;
        table.setModel(dataModel);
    }
    
}
