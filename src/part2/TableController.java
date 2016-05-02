package part2;



import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JEditorPane;

/**
 * 
 * @author Denis Rinfret
 */
public class TableController extends MouseAdapter {

    private TableModel model;
    private JEditorPane queryPanel;

    public TableController(TableModel model, JEditorPane queryPanel) {
        this.model = model;
        this.queryPanel = queryPanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        model.setQuery(queryPanel.getText());
        model.update();
    }

}