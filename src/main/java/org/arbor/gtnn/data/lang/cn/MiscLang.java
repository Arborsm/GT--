package org.arbor.gtnn.data.lang.cn;

import org.arbor.gtnn.api.registry.CNLangProvider;

public class MiscLang {
    public static void init(CNLangProvider provider) {
        provider.add("block.gtnn.high_speed_pipe_block", "高速管道方块");
        provider.add("block.gtnn.clean_machine_casing", "洁净机器方块");
        provider.add("item.gtnn.heavy_ingot_t1.tooltip", "§7用于制作T1重型合金板");
        provider.add("item.gtnn.heavy_ingot_t2.tooltip", "§7用于制作T2重型合金板");
        provider.add("item.gtnn.heavy_ingot_t3.tooltip", "§7用于制作T3重型合金板");
        provider.add("item.gtnn.heavy_ingot_t4.tooltip", "§7用于制作T4重型合金板");
        provider.add("item.gtnn.heavy_plate_t1.tooltip", "§71阶");
        provider.add("item.gtnn.heavy_plate_t2.tooltip", "§72阶");
        provider.add("item.gtnn.heavy_plate_t3.tooltip", "§73阶");
        provider.add("item.gtnn.heavy_plate_t4.tooltip", "§74阶");
        provider.add("item.gtnn.chip_t1.tooltip", "§7§o用于制作1阶火箭");
        provider.add("item.gtnn.chip_t2.tooltip", "§7§o用于制作2阶火箭");
        provider.add("item.gtnn.chip_t3.tooltip", "§7§o用于制作3阶火箭 ");
        provider.add("item.gtnn.chip_t4.tooltip", "§7§o用于制作4阶火箭");
    }
}
