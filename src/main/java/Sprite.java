import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Sprite extends Rectangle {

	public int speed = 6;
	public int weightX = 1;
	public int weightY = 0;
	
	Sprite (int w, int h, int x, int y, Color c) {
		super(w, h, c);
		
		this.setTranslateX(x);
		this.setTranslateY(y);
	}
	
	public void moveSprite() {
		this.setTranslateX(this.getTranslateX() + speed * weightX);
		this.setTranslateY(this.getTranslateY() + speed * weightY);
	}
	
	public void setPlayerDirection(int x, int y) {
		weightX = x;
		weightY = y;
	}
	
}
