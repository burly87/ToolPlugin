package Editor;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ExtendMinesweeper extends Application
{
	//Panes
	private GridPane root;
	private Pane r_Pane;
	private GridPane l_Pane;
	private VBox r_VBox, r_tileVBox;
	
	//Menubar
	private MenuBar menuBar;
	private Menu file;
	private MenuItem save, exit;
	private Label menuTitle;
	
	//Grid
	private static final int TILE_SIZE = 40;
    private static final int W = 800;
    private static final int H = 600;

    private static final int X_TILES = W / TILE_SIZE;
    private static final int Y_TILES = H / TILE_SIZE;

    private Tile[][] grid = new Tile[X_TILES][Y_TILES];
    
    //Images 
    Image gridTile0 = new Image("Editor/Tiles/placeholder.gif");
 

    //Others
    private Scene scene;

    private Parent createContent()
    {
    	/* Panes */
    	root = new GridPane();
    	root.setHgap(15.0f);
    	root.setPrefSize(W+200, H+25); 
    	
    	//left
    	l_Pane = new GridPane();
    	//right
    	r_Pane = new Pane();
    	r_VBox = new VBox(25.0f);
    	r_tileVBox = new VBox(5.0f);
    	
    	/* MenueBar top */
		menuBar = new MenuBar();
		file = new Menu("File");		
		save = new MenuItem("Save");
		save.setOnAction(e->saveMap()); // IMPLEMENT METHODE
		
		exit = new MenuItem("Exit");
		exit.setOnAction(e->System.exit(0));
		
		file.getItems().addAll(save, exit);
		menuBar.getMenus().add(file);
		
    	/* Menu on the right*/		
		menuTitle = new Label("Tilemenue to draw with         ");
		r_VBox.getChildren().add(menuTitle);
		r_VBox.getChildren().add(r_tileVBox);
		
		r_Pane.getChildren().add(r_VBox);
		
		// Tile Buttons to draw later
		for (int i = 0; i < 16; i++) 
		{
			Image tile = new Image("Editor/Tiles/Walkway/"+i+".gif");
			ImageView  ivMenue = new ImageView();
			ivMenue.setImage(tile);
			r_tileVBox.getChildren().add(ivMenue);
		}
    	
		fillGrid();
    
        /* Sort everything */
		root.add(menuBar, 0, 0);
		root.add(l_Pane, 0, 1);
//		root.add(r_Pane, 1, 1);
		
        return root;
    }

    private void fillGrid() 
    {
    	
    	for (int y = 0; y < Y_TILES; y++)
        {
            for (int x = 0; x < X_TILES; x++)
            {
                Tile tile = new Tile(x, y, gridTile0);

                grid[x][y] = tile;
//                l_Pane.add(tile, x, y);
                l_Pane.getChildren().add(tile);
            }
        }

    	/*
        for (int y = 0; y < Y_TILES; y++)
        {
            for (int x = 0; x < X_TILES; x++)
            {
                Tile tile = grid[x][y];

                if (tile.hasBomb)
                    continue;

                // list of Neightbors of tile; filter(if neightbor hastBomb) ->
                // count s
                long bombs = getNeightbors(tile).stream().filter(t -> t.hasBomb).count();

                if (bombs > 0)
                    tile.text.setText(String.valueOf(bombs));

            }
        }*/
	}

	private Object saveMap() {
		// TODO Auto-generated method stub
		return null;
	}

	private List<Tile> getNeightbors(Tile tile)
    {
        List<Tile> neightbors = new ArrayList<>();

        // ttt
        // tXt
        // ttt

        int[] points = new int[]
        { -1, -1, // topleft
            -1, 0, // left
            -1, 1, // bottomLeft
            0, -1, 0, 1, 1, -1, 1, 0, 1, 1 };

        for (int i = 0; i < points.length; i++)
        {
            int dx = points[i];
            int dy = points[++i]; // preIncrement!

            int newX = tile.x + dx;
            int newY = tile.y + dy;

            if (newX >= 0 && newX < X_TILES && newY >= 0 && newY < Y_TILES)
            {
                neightbors.add(grid[newX][newY]);
            }
        }

        return neightbors;

    }

    private class Tile extends StackPane
    {
        private int x, y;
       // private Image img;
 
        public Tile(int x, int y, Image img)
        {
            this.x = x;
            this.y = y;
            //this.img = img;

            ImageView imageView = new ImageView();
            imageView.setImage(img);
            
            getChildren().add(imageView);

            setTranslateX(x * TILE_SIZE);
            setTranslateY(y * TILE_SIZE);

//          setOnMouseClicked(e -> open());

        }

        public void open()
        {
        	getNeightbors(this).forEach(Tile::open);
        }
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        scene = new Scene(createContent());

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

}
