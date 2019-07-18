import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Sprite extends Rectangle {

    public String type;
    public int speed = 8;
    public int weightX = 1;
    public int weightY = 0;
    private KeyCode lastInputOpposite = KeyCode.LEFT;

    Sprite (int w, int h, int x, int y, String t, Color c) {
        super(w, h, c);
        
        this.type = t;
        this.setTranslateX(x);
        this.setTranslateY(y);
    }
    
    public void moveSprite() {
        this.setTranslateX(this.getTranslateX() + speed * weightX);
        this.setTranslateY(this.getTranslateY() + speed * weightY);
    }
    
    public void setPlayerDirection(int x, int y, KeyCode k, KeyCode o) {
        	
        if (lastInputOpposite != k) {
            
            lastInputOpposite = o;
            weightX = x;
            weightY = y;
        
        }
    	
    }
	
}
