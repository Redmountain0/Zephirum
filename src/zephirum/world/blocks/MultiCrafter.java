package zephirum.world.blocks;

import mindustry.type.Item;
import mindustry.world.blocks.distribution.Sorter.SorterBuild;
import mindustry.world.blocks.production.GenericCrafter;

public class MultiCrafter extends GenericCrafter {

    public MultiCrafter(String name) {
        super(name);
        configurable = true;
        clearOnDoubleTap = true;
        
        config(Item.class, (SorterBuild tile, Item item) -> tile.sortItem = item);
        configClear((SorterBuild tile) -> tile.sortItem = null);
    }
    
}
