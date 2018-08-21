package Editor;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Tile extends StackPane
{
	View view = new View();
	
	private int x, y;
	private ImageView img;
	
	public Tile(int x, int y, ImageView img)
	{
		this.x = x;
		this.y = y;
		this.img = img;
		
		setTranslateX(x * view.TILE_SIZE);
		setTranslateY(y * view.TILE_SIZE);
	}
	
	
	
	
	
	
}
