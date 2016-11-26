package lf.lucyfly;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.jme3.app.SimpleApplication;
import com.jme3.collision.CollisionResults;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.InputListener;
import com.jme3.input.controls.KeyTrigger;
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
    private boolean isRunning = false;
    List<Float> xTomb;

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
        Random rand = new Random();
        obstacle = new Obstacle(assetManager, 50);
        player = new Player(inputManager, assetManager);
        initKeyReplay();
        rootNode.attachChild(obstacle.getRootNode());
        rootNode.attachChild(player.getRootNode());

        List<Float> xTomb = Arrays.asList(-2.8f, -2.3f, -1.5f, 0f, 1.5f, 2.3f, 2.8f);
        for (int i = 0; i < obstacle.getRootNode().getChildren().size(); ++i) {
            float z = (float) (-10.0 - i * 6);
            float x = xTomb.get(rand.nextInt(xTomb.size()));
            obstacle.getRootNode().getChildren().get(i).setLocalTranslation(new Vector3f(x, 0.0f, z));
        }

        cam.setLocation(new Vector3f(0f, 5f, 20f));
        flyCam.setEnabled(false);
    }

    @Override
    public void simpleUpdate(float tpf) {

        for (int i = 0; i < obstacle.getRootNode().getChildren().size(); ++i) {
            CollisionResults result = new CollisionResults();
            player.getRootNode().collideWith(obstacle.getRootNode().getChildren().get(i).getWorldBound(), result);
            if (result.size() > 0) {
                isRunning = false;
            }
        }
        if (isRunning) {
            obstacle.update(tpf);

        }

    }

    @Override
    public void simpleRender(RenderManager rm) {
        viewPort.setBackgroundColor(new ColorRGBA(0.7f, 0.8f, 1f, 1f));
    }

    public void initKeyReplay() {
        inputManager.addMapping("replay", new KeyTrigger(KeyInput.KEY_R));
        inputManager.addMapping("start", new KeyTrigger(KeyInput.KEY_Q));
        inputManager.addListener(createAnalogListener(), "replay", "start");
    }

    private InputListener createAnalogListener() {
        return new AnalogListener() {
            public void onAnalog(String name, float value, float tpf) {
                if (name.equals("replay")) {
                    obstacle.getRootNode().move(rootNode.getWorldTranslation().x, rootNode.getWorldTranslation().y,
                            -5f);

                }
                if (name.equals("start")) {
                    isRunning = true;

                }
            }

        };
    }
}
