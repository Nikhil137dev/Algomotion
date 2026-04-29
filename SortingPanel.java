import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class SortingPanel extends JPanel {

    public int[] arr = new int[40];

    public int c1=-1, c2=-1;
    int tempX1=-1, tempX2=-1;

    public boolean paused = false;
    public boolean stepMode = false;
    public boolean nextStep = false;

    JComboBox<String> algoBox;
    JSlider speed;

    public SortingPanel(){

        setBackground(new Color(18,18,28));
        generate();

        algoBox = new JComboBox<>(new String[]{
            "Bubble","Selection","Insertion",
            "Merge","Quick","Heap","Counting","Radix"
        });

        JButton start = new JButton("Start");
        JButton pause = new JButton("Pause");
        JButton step = new JButton("Step");
        JButton gen = new JButton("Generate");

        speed = new JSlider(1,100,40);

        start.addActionListener(e -> new Thread(this::run).start());
        pause.addActionListener(e -> paused = !paused);
        step.addActionListener(e -> nextStep = true);
        gen.addActionListener(e -> { generate(); repaint(); });

        add(algoBox); add(start); add(pause); add(step); add(gen); add(speed);
    }

    //  MAIN RUN METHOD (IMPORTANT)
    void run(){
        String algo = (String)algoBox.getSelectedItem();

        if(algo.equals("Bubble"))
            bubbleSort();

        else if(algo.equals("Selection"))
            selectionSort();

        else if(algo.equals("Insertion"))
            insertionSort();
    }

    //  BUBBLE SORT
    void bubbleSort(){
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr.length-i-1;j++){

                highlight(j,j+1);
                sleep(getDelay());

                if(arr[j]>arr[j+1])
                    animateSwap(j,j+1);
            }
        }
    }

    //  SELECTION SORT
    void selectionSort(){
        for(int i=0;i<arr.length;i++){
            int min=i;

            for(int j=i+1;j<arr.length;j++){
                highlight(min,j);
                sleep(getDelay());

                if(arr[j]<arr[min])
                    min=j;
            }

            animateSwap(i,min);
        }
    }

    //  INSERTION SORT
    void insertionSort(){
        for(int i=1;i<arr.length;i++){

            int key = arr[i];
            int j = i-1;

            while(j>=0 && arr[j]>key){
                arr[j+1]=arr[j];
                highlight(j,j+1);
                repaint();
                sleep(getDelay());
                j--;
            }

            arr[j+1]=key;
        }
    }

    //  SMOOTH SWAP
    public void animateSwap(int i,int j){

        int frames=20;
        int x1=i*barWidth();
        int x2=j*barWidth();

        for(int f=0;f<=frames;f++){

            double t=(1-Math.cos(Math.PI*f/frames))/2;

            tempX1=(int)(x1+(x2-x1)*t);
            tempX2=(int)(x2+(x1-x2)*t);

            repaint();
            sleep(5);
        }

        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;

        tempX1=tempX2=-1;
        repaint();
    }

    //  DRAWING
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        int w = barWidth();

        for(int i=0;i<arr.length;i++){

            int x = i*w;

            if(i==c1 && tempX1!=-1) x=tempX1;
            if(i==c2 && tempX2!=-1) x=tempX2;

            if(i==c1 || i==c2)
                g.setColor(Color.RED);
            else
                g.setColor(new Color(0,200,255));

            g.fillRoundRect(x,getHeight()-arr[i],w-2,arr[i],10,10);

            //  VALUE DISPLAY
            g.setColor(Color.WHITE);
            g.drawString(""+arr[i],x,getHeight()-arr[i]-5);
        }
    }

    void generate(){
        Random r = new Random();
        for(int i=0;i<arr.length;i++)
            arr[i] = r.nextInt(300)+20;
    }

    public void sleep(int d){
        try{
            while(paused) Thread.sleep(100);

            if(stepMode){
                while(!nextStep) Thread.sleep(100);
                nextStep=false;
            }

            Thread.sleep(d);
        }catch(Exception e){}
    }

    int getDelay(){
        return 101 - speed.getValue(); // slider control
    }

    int barWidth(){
        return getWidth()/arr.length;
    }

    public void highlight(int i,int j){
        c1=i; c2=j;
        repaint();
    }
}