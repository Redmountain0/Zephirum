package zephirum.contents;

import mindustry.content.Blocks;
import mindustry.content.Items;

public class vanillaOverride {
    public static void load() {
        Blocks.sporePine.itemDrop = ZepItems.wood;
        Blocks.snowPine.itemDrop = ZepItems.wood;
        Blocks.pine.itemDrop = ZepItems.wood;
    }
}
