package lf.lucyfly;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Csilla
 */
public class Obstacle {

    private int size;
    private AssetManager assetManager;
    private Node root = new Node("root");

    public Obstacle(AssetManager assetManager, int size) {
        this.size = size;
        this.assetManager = assetManager;
        create();
    }

    public void update(float tpf) {
        Vector3f currentTrans = root.getWorldTranslation();
        currentTrans.z = (float) (currentTrans.z + 2 * tpf);
        root.setLocalTranslation(currentTrans);
    }

    public Node getRootNode() {
        return this.root;
    }

    private void create() {
        Random rand = new Random();
        List<ColorRGBA> szinTomb = Arrays.asList(ColorRGBA.Blue, ColorRGBA.Brown, ColorRGBA.Green, ColorRGBA.Red,
                ColorRGBA.Yellow);
        
        for (int i = 0; i < size; i++) {
            Box box = new Box(1, 1, 1);
            Geometry geom = new Geometry("geometry", box);
            Material material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
            material.setColor("Color", szinTomb.get(rand.nextInt(szinTomb.size())));
            geom.setMaterial(material);

            
            root.attachChild(geom);
        }
    }

}
