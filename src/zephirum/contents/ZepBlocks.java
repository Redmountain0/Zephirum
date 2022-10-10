package zephirum.contents;

import arc.*;
import arc.graphics.*;
import mindustry.content.*;
import mindustry.entities.bullet.SapBulletType;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.defense.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.blocks.storage.CoreBlock;

import zephirum.contents.ZepItems.*;

public class ZepBlocks {
    public static Block 
    
    // turret
    spiron,

    // drill
    drill,

    // wall
    iridiumWall, iridiumWallLarge,

    // core
    coreFragment;

    public static void load() {
        spiron = new ItemTurret("spiron"){{
            requirements(Category.turret, ItemStack.with(Items.lead, 80, Items.graphite, 65, Items.titanium, 50));

            reload = 20f;
            range = 100f;
            recoil = 4f;
            size = 2;
            maxAmmo = 20;
            rotateSpeed = 15f;

            health = 240 * size * size;
            shootSound = Sounds.sap;

            ammo(
                Items.sporePod, new SapBulletType(){{
                    sapStrength = 0;
                    damage = 8f;
                    buildingDamageMultiplier = 0.01f;
                    length = 100f;
                    width = 0.5f;
                    lifetime = 30f;
                    shootEffect = Fx.shootSmall;
                    status = StatusEffects.sapped;
                    statusDuration = 300;
                    knockback = -2f;
                    hitColor = Color.valueOf("bf92f9");
                    color = Color.valueOf("bf92f9");
                    ammoMultiplier = 3f;
                }}
            );
        }};

        int wallHealthMultiplier = 4;

        iridiumWall = new Wall("iridium-wall"){{
            requirements(Category.defense, ItemStack.with(ZepItems.iridium, 6));
            health = 280 * wallHealthMultiplier;
            armor = 36f;
        }};

        iridiumWallLarge = new Wall("iridium-wall-large"){{
            requirements(Category.defense, ItemStack.mult(iridiumWall.requirements, 4));
            health = 280 * wallHealthMultiplier * 4;
            armor = 36f;
            size = 2;
        }};

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
