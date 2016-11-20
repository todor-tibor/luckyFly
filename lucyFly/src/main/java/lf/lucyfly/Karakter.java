/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lf.lucyfly;

import com.jme3.asset.AssetManager;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

/**
 *
 * @author Csilla
 */
public class Karakter {

    private boolean isRunning = true;
    Box kek = new Box(1, 1, 1);
    Geometry geom = new Geometry("kek", kek);
    private static final float jobbHatar = (float) 2.8;
    private static final float balHatar = (float) -2.8;
    //private Object inputManager;
    private AssetManager assetManager;
    private Node root = new Node("root");
    private InputManager inputManager;
   

    public Karakter(InputManager inputManager,AssetManager assetManager) {
        this.inputManager = inputManager;
        this.assetManager = assetManager;
        Create();
        
        initKeys();
    }

    private void initKeys() {
        inputManager.addMapping("right", new KeyTrigger(KeyInput.KEY_D));
        inputManager.addMapping("left", new KeyTrigger(KeyInput.KEY_A));
        inputManager.addListener(analogListener, "left", "right");
    }
    private AnalogListener analogListener = new AnalogListener() {
        public void onAnalog(String name, float value, float tpf) {
           
            if (isRunning) {
                if (name.equals("left")) {

                    if (geom.getWorldTranslation().x > balHatar) {
                         
                        geom.move(-0.2f, 0.0f, 0.0f);
                        
                    }
                }
                if (name.equals("right")) {

                    if (geom.getWorldTranslation().x < jobbHatar) {
                        geom.move(0.2f, 0.0f, 0.0f);
                    }
                }
            }
        }
    };

    private void Create() {
       // Box akadaly = new Box(1, 1, 1);
        //Geometry geom = new Geometry("kek", akadaly);
        Material kekMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        kekMat.setColor("Color", ColorRGBA.Blue);
        geom.setMaterial(kekMat);
        geom.setLocalTranslation(new Vector3f(0.0f, 0.0f, 0.0f));
        root.attachChild(geom);

    } 
     public Node getRootNode() {
        return this.root;
    }

    
}
