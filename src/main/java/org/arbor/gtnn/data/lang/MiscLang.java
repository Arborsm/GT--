package org.arbor.gtnn.data.lang;

import com.tterrag.registrate.providers.RegistrateLangProvider;

import static com.gregtechceu.gtceu.data.lang.LangHandler.replace;

public class MiscLang {
    public static void init(RegistrateLangProvider provider) {
        replace(provider, "block.gtnn.high_speed_pipe_block", "高速管道方块");
        replace(provider, "block.gtnn.clean_machine_casing", "洁净机器方块");
    }
}
