package lf.lucyfly;

import com.jme3.app.SimpleApplication;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.system.AppSettings;

/**
 * LookyFly game main class
 *
 * @author csilla
 */
public class LuckyFly extends SimpleApplication {

    private Obstacle obstacle;
    private Player player;

    public static void main(String[] args) {
        LuckyFly app = new LuckyFly();
        app.setShowSettings(false);
        AppSettings settings = new AppSettings(true);
        settings.put("Width", 1280);
        settings.put("Height", 720);
        settings.put("Title", "My awesome Game");
        settings.put("VSync", true);// Anti-Aliasing
        settings.put("Samples", 4);
        app.setSettings(settings);
        app.start();
    }

    @Override
    public void simpleInitApp() {
        obstacle = new Obstacle(assetManager, 4);
        player = new Player(inputManager, assetManager);

        rootNode.attachChild(obstacle.getRootNode());
        rootNode.attachChild(player.getRootNode());

        cam.setLocation(new Vector3f(0f, 5f, 20f));
        flyCam.setEnabled(false);
    }

    @Override
    public void simpleUpdate(float tpf) {
        obstacle.update(tpf);
    }

    @Override
    public void simpleRender(RenderManager rm) {
        viewPort.setBackgroundColor(new ColorRGBA(0.7f, 0.8f, 1f, 1f));
    }
}
