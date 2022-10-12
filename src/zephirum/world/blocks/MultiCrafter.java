package zephirum.world.blocks;

import javax.swing.Icon;

import arc.Core;
import arc.graphics.Color;
import arc.struct.Seq;
import arc.util.Nullable;
import arc.util.Strings;
import mindustry.gen.Building;
import mindustry.graphics.Pal;
import mindustry.type.Item;
import mindustry.type.ItemStack;
import mindustry.ui.ItemDisplay;
import mindustry.ui.Styles;
import mindustry.world.Build;
import mindustry.world.blocks.distribution.Sorter.SorterBuild;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.meta.Stat;

public class MultiCrafter extends GenericCrafter {
    public int[] capacities = {};

    public Seq<CraftPlan> plans = new Seq<>(4);

    public MultiCrafter(String name) {
        super(name);
        configurable = true;
        clearOnDoubleTap = true;

        config(CraftPlan.class, (MultiCrafterBuild tile, CraftPlan plan) -> tile.craftItem = plan);
        configClear((MultiCrafterBuild tile) -> tile.craftItem = null);
    }

    public static class CraftPlan{
        public ItemStack[] outputItem;
        public ItemStack[] requirements;
        public float time;

        public CraftPlan(ItemStack[] outputItem, ItemStack[] requirements, float time){
            this.outputItem = outputItem;
            this.time = time;
            this.requirements = requirements;
        }

        CraftPlan(){}
    }

    /* 
    @Override
    public void setStats(){
        super.setStats();

        stats.remove(Stat.itemCapacity);

        stats.add(Stat.output, table -> {
            table.row();

            for(var plan : plans){
                table.table(Styles.grayPanel, t -> {

                    if(plan.unit.isBanned()){
                        t.image(Icon.cancel).color(Pal.remove).size(40);
                        return;
                    }

                    if(plan.unit.unlockedNow()){
                        t.image(plan.unit.uiIcon).size(40).pad(10f).left();
                        t.table(info -> {
                            info.add(plan.unit.localizedName).left();
                            info.row();
                            info.add(Strings.autoFixed(plan.time / 60f, 1) + " " + Core.bundle.get("unit.seconds")).color(Color.lightGray);
                        }).left();

                        t.table(req -> {
                            req.right();
                            for(int i = 0; i < plan.requirements.length; i++){
                                if(i % 6 == 0){
                                    req.row();
                                }

                                ItemStack stack = plan.requirements[i];
                                req.add(new ItemDisplay(stack.item, stack.amount, false)).pad(5);
                            }
                        }).right().grow().pad(10f);
                    }else{
                        t.image(Icon.lock).color(Pal.darkerGray).size(40);
                    }
                }).growX().pad(5);
                table.row();
            }
        });
    }
    */
    
    public class MultiCrafterBuild extends Building {
        public @Nullable CraftPlan craftItem;
        
        public CraftPlan config(){
            return craftItem;
        }
    }
}