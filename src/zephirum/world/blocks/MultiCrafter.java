package zephirum.world.blocks;

import arc.util.Nullable;
import mindustry.gen.Building;
import mindustry.type.Item;
import mindustry.type.ItemStack;
import mindustry.world.Build;
import mindustry.world.blocks.distribution.Sorter.SorterBuild;
import mindustry.world.blocks.production.GenericCrafter;

public class MultiCrafter extends GenericCrafter {

    public MultiCrafter(String name) {
        super(name);
        configurable = true;
        clearOnDoubleTap = true;

        config(CraftPlan.class, (MultiCrafterBuild tile, CraftPlan plan) -> tile.craftItem = plan);
        configClear((MultiCrafterBuild tile) -> tile.craftItem = null);
    }

    public static class CraftPlan{
        public ItemStack outputItem;
        public ItemStack[] requirements;
        public float time;

        public CraftPlan(ItemStack outputItem, float time, ItemStack[] requirements){
            this.outputItem = outputItem;
            this.time = time;
            this.requirements = requirements;
        }

        CraftPlan(){}
    }

    public class MultiCrafterBuild extends Building {
        public @Nullable CraftPlan craftItem;
        
        public CraftPlan config(){
            return craftItem;
        }
    }
}