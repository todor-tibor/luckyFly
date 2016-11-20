package lf.lucyfly;

import com.jme3.app.SimpleApplication;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.system.AppSettings;

/**
 * test
 *
 * @author normenhansen
 */
public class LookyFly extends SimpleApplication {

    private Akadalyok akadalyok;
    private Karakter karakter;

    public static void main(String[] args) {
        LookyFly app = new LookyFly();
        app.setShowSettings(false);
        AppSettings settings = new AppSettings(true);
        settings.put("Width", 1280);
        settings.put("Height", 720);
        settings.put("Title", "My awesome Game");
        settings.put("VSync", true);//Anti-Aliasing
        settings.put("Samples", 4);
        app.setSettings(settings);
        app.start();
    }

    @Override
    public void simpleInitApp() {
        akadalyok = new Akadalyok(assetManager, 100);
        karakter = new Karakter(inputManager, assetManager);

        rootNode.attachChild(akadalyok.getRootNode());
        rootNode.attachChild(karakter.getRootNode());


        cam.setLocation(new Vector3f(0f, 5f, 20f));
        flyCam.setEnabled(false);
        //initKeys();
        //CollisionResults results = new CollisionResults();
        //karakter.collideWith(akadalyok, results);
    }

    @Override
    public void simpleUpdate(float tpf) {
        akadalyok.update(tpf);
    }

    @Override
    public void simpleRender(RenderManager rm) {
        viewPort.setBackgroundColor(new ColorRGBA(0.7f, 0.8f, 1f, 1f));
    }
}
