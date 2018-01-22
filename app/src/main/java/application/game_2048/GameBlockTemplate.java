package application.game_2048;

/**
 * Created by manish on 1/22/18.
 */

        import android.content.Context;
        import android.widget.ImageView;

public abstract class GameBlockTemplate extends ImageView{

    public GameBlockTemplate(Context gbCTX){
        super(gbCTX);
    }

    public abstract boolean setDestination(GameLoop.eDir myDir);

    public abstract void move();

}