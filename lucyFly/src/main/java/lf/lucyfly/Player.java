package lf.lucyfly;

import com.jme3.asset.AssetManager;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.InputListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Sphere;

/**
 *
 * @author Csilla
 */
public class Player {
    private static final float RIGHT_LIMIT = (float) 3;
    private static final float LEFT_LIMIT = (float) -3;

    private Sphere  player = new Sphere(32, 32, 2f);
    private Geometry playerGeom = new Geometry("playerGeom", player);

    private AssetManager assetManager;
    private Node root = new Node("root");
    private InputManager inputManager;

    /**
     * Constructor of the class.
     * 
     * @param inputManager
     * @param assetManager
     */
    public Player(InputManager inputManager, AssetManager assetManager) {
        this.inputManager = inputManager;
        this.assetManager = assetManager;
        create();
        initKeys();
    }

    /**
     * Get the root node
     * 
     * @return {@link Player#root}
     */
    public Node getRootNode() {
        return this.root;
    }

    /**
     * initialize the key handling
     */
    private void initKeys() {
        inputManager.addMapping("right", new KeyTrigger(KeyInput.KEY_D));
        inputManager.addMapping("left", new KeyTrigger(KeyInput.KEY_A));
        inputManager.addListener(createAnalogListener(), "left", "right");
    }

    /**
     * Creates the analog listener, handles the input keys
     * 
     * @return analog listener
     */
    private InputListener createAnalogListener() {
        return new AnalogListener() {
            public void onAnalog(String name, float value, float tpf) {
                if (name.equals("left")) {

                    if (playerGeom.getWorldTranslation().x > LEFT_LIMIT) {

                        playerGeom.move(-0.3f, 0.0f, 0.0f);

                    }
                }
                if (name.equals("right")) {

                    if (playerGeom.getWorldTranslation().x < RIGHT_LIMIT) {
                        playerGeom.move(0.3f, 0.0f, 0.0f);
                    }
                }
            }
        };
    }

    /**
     * Creates the character box
     */
    private void create() {
        Material kekMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
       
        kekMat.setColor("Color", ColorRGBA.Blue);
        playerGeom.scale(0.5f, 0.5f, 0.1f);
        playerGeom.setMaterial(kekMat);
        playerGeom.setLocalTranslation(new Vector3f(0.0f, 0.0f, 0.0f));
        root.attachChild(playerGeom);
    }

}
