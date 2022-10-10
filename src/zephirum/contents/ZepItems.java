package zephirum.contents;

import arc.*;
import arc.graphics.*;
import mindustry.*;
import mindustry.game.*;
import mindustry.graphics.*;
import mindustry.type.*;

public class ZepItems {
    public static Item iridium;

    public static void load() {
        iridium = new Item("iridium", Color.valueOf("00ffff")){
            {
                charge = 0.5f;
                cost = 0.1f;
                hardness = 6;
            }
        };
    }
}
