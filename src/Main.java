import GUI.Game;
import GUIControls.Window;

public class Main {
    public static void main(String[] args) {
        new Window();
        Game game = new Game();
        Window.setContentPane(game);
        Window.setResizeable(false);
        game.startGame();
    }
}
