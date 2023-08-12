package zephirum.contents;

import mindustry.content.Blocks;
import mindustry.content.Items;

public class vanillaOverride {
    public static void load() {
        Blocks.duo.requirements[Items.copper.id].amount = 50;
    }
}
