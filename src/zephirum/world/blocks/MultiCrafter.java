package zephirum.world.blocks;

import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import arc.struct.Seq;
import arc.util.*;
import arc.util.Time;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.gen.Building;
import mindustry.type.Item;
import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;
import mindustry.world.blocks.production.GenericCrafter;

public class MultiCrafter extends GenericCrafter {
    public int[] capacities = {};

    public Seq<CraftPlan> plans = new Seq<>(4);

    public MultiCrafter(String name) {
        super(name);
        configurable = true;
        clearOnDoubleTap = true;

        config(CraftPlan.class, (MultiCrafterBuild tile, CraftPlan plan) -> tile.craftP = plan);
        configClear((MultiCrafterBuild tile) -> tile.craftP = null);
    }

    public static class CraftPlan{
        public ItemStack[] outputItems;
        public LiquidStack[] outputLiquids;
        public ItemStack[] inputItems;
        public LiquidStack[] inputLiquids;
        public float craftTime;
        public TextureRegion icon;

        public CraftPlan(ItemStack[] outputItems, ItemStack[] inputItems, LiquidStack[] outputLiquids, LiquidStack[] inputLiquids, float time){
            this.outputItems = outputItems;
            this.inputItems = inputItems;
            this.outputLiquids = outputLiquids;
            this.inputLiquids = inputLiquids;
            this.craftTime = time;
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
        public @Nullable CraftPlan craftP;
        
        public CraftPlan config(){
            return craftP;
        }

        public float progress;
        public float totalProgress;
        public float warmup;

        @Override
        public boolean shouldConsume(){
            if(craftP.outputItems != null){
                for(var output : craftP.outputItems){
                    if(items.get(output.item) + output.amount > itemCapacity){
                        return false;
                    }
                }
            }
            if(craftP.outputLiquids != null && !ignoreLiquidFullness){
                boolean allFull = true;
                for(var output : craftP.outputLiquids){
                    if(liquids.get(output.liquid) >= liquidCapacity - 0.001f){
                        if(!dumpExtraLiquid){
                            return false;
                        }
                    }else{
                        //if there's still space left, it's not full for all liquids
                        allFull = false;
                    }
                }

                //if there is no space left for any liquid, it can't reproduce
                if(allFull){
                    return false;
                }
            }

            return enabled;
        }

        @Override
        public void updateTile(){
            if(efficiency > 0){

                progress += getProgressIncrease(craftP.craftTime);
                warmup = Mathf.approachDelta(warmup, warmupTarget(), warmupSpeed);

                //continuously output based on efficiency
                if(craftP.outputLiquids != null){
                    float inc = getProgressIncrease(1f);
                    for(var output : craftP.outputLiquids){
                        handleLiquid(this, output.liquid, Math.min(output.amount * inc, liquidCapacity - liquids.get(output.liquid)));
                    }
                }

                if(wasVisible && Mathf.chanceDelta(updateEffectChance)){
                    updateEffect.at(x + Mathf.range(size * 4f), y + Mathf.range(size * 4));
                }
            }else{
                warmup = Mathf.approachDelta(warmup, 0f, warmupSpeed);
            }

            //TODO may look bad, revert to edelta() if so
            totalProgress += warmup * Time.delta;

            if(progress >= 1f){
                craft();
            }

            dumpOutputs();
        }

        @Override
        public float getProgressIncrease(float baseTime){
            //limit progress increase by maximum amount of liquid it can produce
            float scaling = 1f, max = 1f;
            if(outputLiquids != null){
                max = 0f;
                for(var s : craftP.outputLiquids){
                    float value = (liquidCapacity - liquids.get(s.liquid)) / (s.amount * edelta());
                    scaling = Math.min(scaling, value);
                    max = Math.max(max, value);
                }
            }

            //when dumping excess take the maximum value instead of the minimum.
            return super.getProgressIncrease(baseTime) * (dumpExtraLiquid ? Math.min(max, 1f) : scaling);
        }

        public float warmupTarget(){
            return 1f;
        }

        @Override
        public float warmup(){
            return warmup;
        }

        @Override
        public float totalProgress(){
            return totalProgress;
        }

        public void craft(){
            consume();

            if(outputItems != null){
                for(var output : craftP.outputItems){
                    for(int i = 0; i < output.amount; i++){
                        offload(output.item);
                    }
                }
            }

            if(wasVisible){
                craftEffect.at(x, y);
            }
            progress %= 1f;
        }

        public void dumpOutputs(){
            if(craftP.outputItems != null && timer(timerDump, dumpTime / timeScale)){
                for(ItemStack output : craftP.outputItems){
                    dump(output.item);
                }
            }

            if(craftP.outputLiquids != null){
                for(int i = 0; i < craftP.outputLiquids.length; i++){
                    int dir = liquidOutputDirections.length > i ? liquidOutputDirections[i] : -1;

                    dumpLiquid(craftP.outputLiquids[i].liquid, 2f, dir);
                }
            }
        }

        /* TODO
        @Override
        public double sense(LAccess sensor){
            if(sensor == LAccess.progress) return progress();
            //attempt to prevent wild total liquid fluctuation, at least for crafters
            if(sensor == LAccess.totalLiquids && craftP.outputLiquid != null) return liquids.get(craftP.outputLiquid.liquid);
            return super.sense(sensor);
        }*/

        @Override
        public float progress(){
            return Mathf.clamp(progress);
        }

        @Override
        public int getMaximumAccepted(Item item){
            return itemCapacity;
        }

        @Override
        public boolean shouldAmbientSound(){
            return efficiency > 0;
        }

        @Override
        public void write(Writes write){
            super.write(write);
            write.f(progress);
            write.f(warmup);
            if(legacyReadWarmup) write.f(0f);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            progress = read.f();
            warmup = read.f();
            if(legacyReadWarmup) read.f();
        }
    }
}