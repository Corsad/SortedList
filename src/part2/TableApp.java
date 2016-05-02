package part2;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import jsyntaxpane.DefaultSyntaxKit;

/**
 * @author Denis Rinfret implemented by Dang Kim Khanh S3372771
 */
public class TableApp extends JFrame implements ActionListener {
    
    JLabel status = new JLabel();
    TableModel taModel = new TableModel(status);
    TableView taView = new TableView();
    JEditorPane scrPane = new JEditorPane();
    TableController taController = new TableController(taModel, scrPane);
    JButton updateButton = new JButton("Execute");
    JScrollPane scroll = new JScrollPane();
    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    JMenuItem fileOpen = new JMenuItem("Open File");
    JFileChooser fc = new JFileChooser();
    JPanel midPanel = new JPanel();
    JList tableList = new JList();
    JPanel topPanel = new JPanel();
    JPanel frame = new JPanel();
//  TableList listFrame = new TableList(taModel);
    
    public TableApp() {
        this.setJMenuBar(menuBar);
        menuBar.add(fileMenu);
        fileMenu.add(fileOpen);
        fileOpen.addActionListener(this);
        DefaultSyntaxKit.initKit();
        scroll.setViewportView(scrPane);
        scrPane.setContentType("text/sql");
        scrPane.setText("SELECT \nFROM \nWHERE ");
        topPanel.add(scroll);
        scrPane.setPreferredSize(new Dimension(330, 70));
        midPanel.add(updateButton);
        frame.add(topPanel);
        frame.add(midPanel);
        //updateButton.addMouseListener(new Execute(taModel,scrPane.getText()));
        updateButton.addMouseListener(taController);
        taModel.addObserver(taView);
        
        this.add(frame, BorderLayout.NORTH);
        this.add(taView, BorderLayout.CENTER);
        this.add(status,BorderLayout.SOUTH);
        
    }
    
    public static void main(String[] args) {
        TableApp app = new TableApp();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.pack();
        app.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        fc = new JFileChooser();
        fc.setFileFilter(new TypeOpen());
        int result = fc.showOpenDialog(this);;
        
        if (result == 0) {
            File file = fc.getSelectedFile();
            String name = file.getName();
            taModel.setDbFileName(name);
        }
    }
    
//    static class Execute extends MouseAdapter {
//        TableModel model;
//        String query;
//        
//        public Execute(TableModel model, String query) {
//            this.model = model;
//            this.query = query;
//        }
//        
//        @Override
//        public void mouseEntered(MouseEvent e) {
//            model.setQuery(query);
//            model.update();
//        }
//    }
}
