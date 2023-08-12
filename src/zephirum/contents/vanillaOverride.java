package zephirum.contents;

import mindustry.Vars;
import mindustry.content.*;
import mindustry.ctype.ContentType;

public class vanillaOverride {
    public static void load() {
        // Vars.content.getByName(ContentType.item, "zephirum-wood");
        Blocks.sporePine.itemDrop = ZepItems.wood;
        Blocks.snowPine.itemDrop = ZepItems.wood;
        Blocks.pine.itemDrop = ZepItems.wood;
        UnitTypes.alpha.mineWalls = true;
        UnitTypes.beta.mineWalls = true;
        UnitTypes.gamma.mineWalls = true;

        // EI Support
        Vars.content.getByName(ContentType.unit, "ei-piece").mineWalls = true;
        Vars.content.getByName(ContentType.unit, "ei-piece").mineTier = 0;
    }
}
