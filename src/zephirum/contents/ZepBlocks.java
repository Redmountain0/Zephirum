package zephirum.contents;

import arc.*;
import arc.graphics.*;
import mindustry.content.*;
import mindustry.entities.bullet.SapBulletType;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.defense.turrets.*;

public class ZepBlocks {
    public static Block spiron;

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
    }
}
