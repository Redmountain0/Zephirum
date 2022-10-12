package zephirum.contents;

import arc.*;
import arc.graphics.*;
import arc.util.*;
import arc.math.*;
import arc.struct.Seq;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.consumers.*;
import mindustry.world.blocks.defense.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.storage.*;

import zephirum.contents.ZepItems.*;
import zephirum.world.blocks.*;

public class ZepBlocks {
    public static Block 
    
    // turret
    spiron,

    // drill
    drill,

    // ore
    oreIridium,
    
    // power generator
    clickGenerator,

    // factory
    powerCharger,

    // wall
    iridiumWall, iridiumWallLarge,

    // core
    coreFragment;

    public static void load() {
        // region turret
        spiron = new ItemTurret("spiron"){{
            requirements(Category.turret, ItemStack.with(Items.lead, 80, Items.graphite, 65, Items.titanium, 50));

            reload = 20f;
            range = 120f;
            // range +40 when using 
            recoil = 4f;
            size = 2;
            maxAmmo = 20;
            rotateSpeed = 15f;

            health = 240 * size * size;
            shootSound = Sounds.sap;
            // tmp option, will be support player control
            playerControllable = false;
            
            ammo(
                Items.sporePod, new DebuffBulletType(){{
                    sapStrength = 0;
                    damage = 8f;
                    buildingDamageMultiplier = 0.01f;
                    length = 100f;
                    width = 0.5f;
                    lifetime = 30f;
                    shootEffect = Fx.shootSmall;
                    status = StatusEffects.sapped;
                    statusDuration = 60;
                    knockback = -2f;
                    hitColor = Color.valueOf("bf92f9");
                    color = Color.valueOf("bf92f9");
                    ammoMultiplier = 3f;
                }}
            );
            
            unitSort = (u, x, y) -> u.isImmune(StatusEffects.sapped) ? 1000000f : (u.getDuration(StatusEffects.sapped) + Mathf.dst(u.x, u.y, x, y) / 6400000f);
            coolant = consume(new ConsumeLiquid(Liquids.water, 0.2f));
            coolantMultiplier = 5f;
        }};

        // region ore
        oreIridium = new OreBlock(ZepItems.iridium){{
            oreDefault = true;
            oreThreshold = 0.902f;
            oreScale = 25.880953f;
        }};

        // region power
        powerCharger = new MultiCrafter("power-charger"){{
            requirements(Category.crafting, ItemStack.with(Items.copper, 50, Items.lead, 120, Items.silicon, 80));
            plans = Seq.with(
                new CraftPlan(ItemStack.with(ZepItems.chargedIridium, 1), ItemStack.with(ZepItems.iridium, 1), null, null, 60f),
                new CraftPlan(ItemStack.with(Items.surgeAlloy, 1), ItemStack.with(ZepItems.surgium, 1), null, null, 120f)
            );
            size = 3;
            consumePower(1.2f);
        }};

        // region wall
        int wallHealthMultiplier = 4;

        iridiumWall = new Wall("iridium-wall"){{
            requirements(Category.defense, ItemStack.with(ZepItems.iridium, 6));
            health = 270 * wallHealthMultiplier;
            armor = 32f;
        }};
        iridiumWallLarge = new Wall("iridium-wall-large"){{
            requirements(Category.defense, ItemStack.mult(iridiumWall.requirements, 4));
            health = 270 * wallHealthMultiplier * 4;
            armor = 32f;
            size = 2;
        }};
        
        // region generator
        clickGenerator = new ClickGenerator("click-generator"){{
            requirements(Category.power, ItemStack.with(Items.graphite, 5));
            health = 40;
            powerProduction = 1f;
        }};
        
        // region core
        coreFragment = new CoreBlock("core-fragment"){{
            requirements(Category.effect, ItemStack.with(Items.copper, 1000, Items.lead, 800));
            alwaysUnlocked = true;
            isFirstTier = true;
            unitType = UnitTypes.alpha;
            health = 300;
            itemCapacity = 800;
            size = 2;

            unitCapModifier = 4;
        }};
    }
}
