package zephirum.contents;

import mindustry.Vars;
import mindustry.content.*;
import mindustry.ctype.ContentType;
import mindustry.gen.Unit;

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
        Vars.content.unit("ei-piece").mineWalls = true;
        
        Blocks.oreCopper.itemDrop = ZepItems.copperOre;
        Blocks.oreLead.itemDrop = ZepItems.leadOre;
        /* 
        Blocks.oreCoal.itemDrop = ZepItems.coalOre;
        Blocks.oreTitanium.itemDrop = ZepItems.titaniumOre;
        Blocks.oreThorium.itemDrop = ZepItems.thoriumOre;
        Blocks.oreCrystalThorium.itemDrop = ZepItems.thoriumOre;
        Blocks.wallOreThorium.itemDrop = ZepItems.thoriumOre;
        */
        /* 
        Blocks.oreBeryllium.itemDrop = ZepItems.berylliumOre;
        Blocks.wallOreBeryllium.itemDrop = ZepItems.berylliumOre;
        Blocks.oreTungsten.itemDrop = ZepItems.tungstenOre;
        Blocks.wallOreTungsten.itemDrop = ZepItems.tungstenOre;
        */
        

        // modpack-specific balance changes
        // will be seperated to another mod
        Vars.content.unit("ei-piece").mineSpeed = 1.0f;
        UnitTypes.alpha.mineSpeed = 2.0f;
        UnitTypes.beta.mineSpeed = 3.5f;
        UnitTypes.gamma.mineSpeed = 6.5f;
    }
}
