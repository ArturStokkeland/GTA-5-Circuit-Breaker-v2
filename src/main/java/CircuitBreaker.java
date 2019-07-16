
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CircuitBreaker extends Application {

	public int width = 1600;
	public int height = 900;
	
	public Pane pane;
	
	public Sprite player = new Sprite(12, 12, 800, 450, Color.GREEN);
	public Sprite tail;
	
	@Override
	public void start(Stage window) throws Exception {
		
				/*Model model = new Model();
				View view = new View();
				Controller controller = new Controller();
				
				// link MVC together
				model.view = view;
				model.controller = controller;
				
				view.model = model;
				view.controller = controller;
				
				controller.model = model;
				controller.view = view;*/
		
				pane = new Pane();
				pane.getChildren().add(player);
				Scene scene = new Scene(pane, width, height);
				window.setScene(scene);
				window.show();
				
				scene.setOnKeyPressed(e -> {
					switch(e.getCode()) {
					case UP:
						player.setPlayerDirection(0, -1);
						break;
					case RIGHT:
						player.setPlayerDirection(1, 0);
						break;
					case DOWN:
						player.setPlayerDirection(0, 1);
						break;
					case LEFT:
						player.setPlayerDirection(-1, 0);
						break;
					default:
						break;
					}
				});
				
				AnimationTimer timer = new AnimationTimer() {
					@Override
					public void handle(long now) {
						update();
					}
				};
				
				timer.start();
				
	}
	
	public void update() {
		tail = new Sprite((int) player.getWidth() / 2, (int) player.getHeight() / 2, (int) player.getTranslateX() + (int) player.getWidth() / 4, (int) player.getTranslateY() + (int) player.getHeight() / 4, Color.GREEN);
		pane.getChildren().add(tail);
		player.moveSprite();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
