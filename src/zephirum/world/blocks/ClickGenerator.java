package zephirum.world.blocks;

import arc.*;
import arc.graphics.g2d.*;
import arc.struct.*;
import arc.util.io.*;
import mindustry.gen.*;
import mindustry.logic.*;
import mindustry.world.blocks.power.PowerBlock;
import mindustry.world.meta.*;

public class ClickGenerator extends PowerBlock {
    public float tapTime = 60f;
    public float powerProduction = 0.3f;
    public TextureRegion trigRegion;

    public ClickGenerator(String name){
        super(name);
        quickRotate = false;
        consumesPower = false;
        outputsPower = true;
        targetable = false;
        solid = true;
        sync = true;
        configurable = true;
        saveConfig = false;
        noUpdateDisabled = false;
        group = BlockGroup.power;

        flags = EnumSet.of(BlockFlag.generator);

        config(Boolean.class, (ClickGeneratorBuild build, Boolean b) -> {
            if(b && build.enabled) build.heat = tapTime;
        });
    }

    @Override
    public void load() {
        super.load();
        trigRegion = Core.atlas.find(name + "-trig");
    }

    public class ClickGeneratorBuild extends Building {
        public float heat = 0f;

        @Override
        public void draw(){
            Draw.rect(pressed() ? trigRegion : region, x, y);
        }

        public boolean pressed(){
            return heat > 0.001f;
        }

        @Override
        public void updateTile(){
            super.updateTile();

            if(heat >= 0f) heat -= delta();
        }

        @Override
        public float getPowerProduction(){
            return heat > 0.001f ? powerProduction : 0f;
        }

        @Override
        public boolean configTapped(){
            if(!enabled || pressed()) return false;

            configure(true);
            return false;
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);

            if(read.bool()) heat = tapTime;
        }

        @Override
        public void write(Writes write){
            super.write(write);

            write.bool(pressed());
        }

        @Override
        public double sense(LAccess sensor){
            return switch(sensor){
                case heat -> heat / tapTime;
                case enabled -> heat > 0.001f ? 1 : 0;
                default -> super.sense(sensor);
            };
        }
    }
}