package part2;

import java.util.Observable;
import java.util.Observer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author S3372771
 */
public class TableList //implements Observer
{

    private TableModel model;
    private JPanel main;
    private JList nameList;

    public TableList() {
    }

    public TableList(TableModel model) {
        this.model = model;
        Sorting result = new Sorting(this.model.tableName());
        DefaultListModel listModel = result.sortingList();
        JList myList = new JList(listModel);

        myList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        myList.setLayoutOrientation(JList.VERTICAL);
        myList.setVisibleRowCount(-1);
    }

//    @Override
//    public void update(Observable o, Object arg) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
}
