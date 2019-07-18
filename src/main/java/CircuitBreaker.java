
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CircuitBreaker extends Application {

    public int width = 1600;
    public int height = 900;
    public int checkpointW = 25;
    public int checkpointH = 50;
    public int playerSize = 10;
    public int tailSize = 4;
    
    public Pane pane;
    
    public Sprite start;
    public Sprite end;
    public Sprite player;
    public Sprite tail;
    
    public AnimationTimer timer;
    
    @Override
    public void start(Stage window) throws Exception {
        
        pane = new Pane();
        Scene scene = new Scene(pane, width, height);
        window.setScene(scene);
        window.show();
        
        timer = new AnimationTimer() {
            
            @Override
            public void handle(long now) {
                
                update();
            
            }
            
        };
        
        generateGame();
        
        scene.setOnKeyPressed(e -> {
            
            switch(e.getCode()) {
            
                case UP:
                    player.setPlayerDirection(0, -1, e.getCode(), KeyCode.DOWN);
                    break;
                case RIGHT:
                    player.setPlayerDirection(1, 0, e.getCode(), KeyCode.LEFT);
                    break;
                case DOWN:
                    player.setPlayerDirection(0, 1, e.getCode(), KeyCode.UP);
                    break;
                case LEFT:
                    player.setPlayerDirection(-1, 0, e.getCode(), KeyCode.RIGHT);
                    break;
                case SPACE:
                    timer.start();
                    break;
                default:
                    break;
                
            }
            
        });
    			
    }
    
    public void update() {
        
        player.moveSprite();
        
        // I have no clue how this piece of code works >.<
        List<Sprite> sprites = pane.getChildren().stream().map(s -> (Sprite)s).collect(Collectors.toList());
        
        sprites.forEach(sprite -> {
            
            switch(sprite.type) {
            
                case "start":
                case "wall":
                case "tail":
                    if (sprite.getBoundsInParent().intersects(player.getBoundsInParent())) {
                        generateGame();
                    }
                    break;
                    
                case "end":
                    if (sprite.getBoundsInParent().intersects(player.getBoundsInParent())) {
                        generateGame();
                    }
                    break;
                    
                default:
                    break;
            
            }
            
                
        });
        
        tail = new Sprite(tailSize, tailSize, (int) player.getTranslateX() + playerSize / 2 - tailSize / 2, (int) player.getTranslateY() + (int) playerSize / 2 - tailSize / 2, "tail", Color.GREEN);
        pane.getChildren().add(tail);
    	
    }
    
    public void generateMap() {
        Random random = new Random();
        int amount = random.nextInt(6) + 3;               // 3-8
        
        for(int i = 0; i < amount; i++) {
            int width = random.nextInt(401) + 100;        // 100-500
            int height = random.nextInt(401) + 100;       // 100-500
            int x = random.nextInt(1501 - width) + 100;   //100-(1600 - width)
            int y = random.nextInt(701);                  //0-700
            Sprite wall = new Sprite(width, height, x, y, "wall", Color.RED);
            pane.getChildren().add(wall);
        }
        
    }
    
    public void generateGame() {
        timer.stop();
        pane.getChildren().clear();
        start = new Sprite(checkpointW, checkpointH, 20, 20, "start", Color.BLUE);
        end = new Sprite(checkpointW, checkpointH, width - checkpointW - 20, height - checkpointH - 20, "end", Color.BLUE);
        player = new Sprite(playerSize, playerSize, (int) start.getTranslateX() + checkpointW, (int) start.getTranslateY() + checkpointH / 2 - (int) playerSize / 2, "player", Color.GREEN);
        pane.getChildren().addAll(player, start, end);
        generateMap();
    }
    
    public static void main(String[] args) {
        
        launch(args);
        
    }
	
}
