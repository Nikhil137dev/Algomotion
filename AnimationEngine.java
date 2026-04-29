

public class AnimationEngine {

    private boolean paused = false;
    private boolean stepMode = false;
    private boolean nextStep = false;

    private int speed = 40;

    //  DELAY CONTROL
    public void sleep(){
        try{

            while(paused)
                Thread.sleep(100);

            if(stepMode){
                while(!nextStep)
                    Thread.sleep(100);
                nextStep = false;
            }

            Thread.sleep(getDelay());

        }catch(Exception e){}
    }

    //  SPEED CONTROL
    public int getDelay(){
        return 101 - speed;
    }

    public void setSpeed(int s){
        speed = s;
    }

    //  PAUSE / STEP CONTROL
    public void togglePause(){
        paused = !paused;
    }

    public void step(){
        nextStep = true;
    }

    public void setStepMode(boolean mode){
        stepMode = mode;
    }
}