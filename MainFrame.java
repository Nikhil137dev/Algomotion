import javax.swing.*;
public class MainFrame extends JFrame {

    public MainFrame(){
        setTitle("AlgoMotion PRO");
        setSize(1200,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabs = new JTabbedPane();

        tabs.add("Sorting", new SortingPanel());
        tabs.add("Searching", new SearchPanel());
        tabs.add("Graph", new GraphPanel());

        add(tabs);
        setVisible(true);
    }

    public static void main(String[] args){
        new MainFrame();
    }
}