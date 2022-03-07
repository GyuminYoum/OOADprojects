package Factory;

import GuitarKitParts.*;
import Items.Items;

public interface GuitarKitFactory {
    public Bridge pickBridge();
    public Covers pickCovers();
    public knobSet pickKnobSet();
    public neck pickNeck();
    public pickGuard pickpG();
    public pickUps pickpU();
    public Items createGuitarKit();
}
