
import javax.swing.*;
import java.awt.*;

public class SearchPanel extends JPanel {

    int[] arr = {2,5,7,10,14,18,21,25};

    int current = -1;
    int left = -1, right = -1, mid = -1;
    int foundIndex = -1;

    JComboBox<String> algoBox;
    JTextField targetField;

    public SearchPanel(){

        setBackground(new Color(18,18,28));

        algoBox = new JComboBox<>(new String[]{"Linear","Binary"});
        targetField = new JTextField(5);

        JButton start = new JButton("Start");

        start.addActionListener(e -> new Thread(this::run).start());

        add(algoBox);
        add(new JLabel("Target:"));
        add(targetField);
        add(start);
    }

    //  MAIN RUN
    void run(){

        // RESET STATE
        current = -1;
        left = -1;
        right = -1;
        mid = -1;
        foundIndex = -1;

        int target;

        // INPUT VALIDATION
        try{
            target = Integer.parseInt(targetField.getText());
        }catch(Exception e){
            JOptionPane.showMessageDialog(this,"Enter valid number");
            return;
        }

        if(algoBox.getSelectedItem().equals("Linear"))
            linearSearch(target);
        else
            binarySearch(target);
    }
    //  LINEAR SEARCH
    void linearSearch(int target){

        for(int i=0;i<arr.length;i++){

            current = i;
            repaint();
            sleep(400);

            if(arr[i] == target){
                foundIndex = i;
                repaint();
                return;
            }
        }
    }

    //  BINARY SEARCH
    void binarySearch(int target){

        int l = 0, r = arr.length-1;

        while(l <= r){

            left = l;
            right = r;
            mid = (l+r)/2;

            repaint();
            sleep(600);

            if(arr[mid] == target){
                foundIndex = mid;
                repaint();
                return;
            }
            else if(arr[mid] < target)
                l = mid+1;
            else
                r = mid-1;
        }
    }

    void sleep(int d){
        try{Thread.sleep(d);}catch(Exception e){}
    }

    //  DRAWING
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        for(int i=0;i<arr.length;i++){

            int x = i * 80;

            // COLORS
            if(i == foundIndex)
                g.setColor(Color.GREEN);     // found
            else if(i == current || i == mid)
                g.setColor(Color.RED);       // active
            else if(i >= left && i <= right)
                g.setColor(Color.YELLOW);    // range
            else
                g.setColor(Color.GRAY);      // inactive

            g.fillRoundRect(x, 300, 50, arr[i]*5, 10, 10);

            // VALUE
            g.setColor(Color.WHITE);
            g.drawString(""+arr[i], x+10, 290);

            // POINTER LABELS
            if(i == current)
                g.drawString("i", x+15, 350);

            if(i == mid)
                g.drawString("M", x+15, 370);

            if(i == left)
                g.drawString("L", x+5, 390);

            if(i == right)
                g.drawString("R", x+25, 390);
        }
    }
}