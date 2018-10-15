package Editor;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Tile extends StackPane
{
	 private int x, y;
	 View view = new View();
     // private Image img;

      public Tile(int x, int y, Image img)
      {
          this.x = x;
          this.y = y;
          //this.img = img;

          ImageView imageView = new ImageView();
          imageView.setImage(img);
          
          getChildren().add(imageView);

          setTranslateX(x * view.TILE_SIZE);
          setTranslateY(y * view.TILE_SIZE);

//        setOnMouseClicked(e -> open());

      }

      public void open()
      {
      	//getNeightbors(this).forEach(Tile::open);
      }
	/*
	private int x, y;
	private Image img;
	
	public Tile(int x, int y, Image img)
	{
		this.x = x;
		this.y = y;
		this.img = img;
	} */
}
