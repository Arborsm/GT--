package org.arbor.gtnn.data.lang;

import org.arbor.gtnn.api.registry.CNLangProvider;
import org.arbor.gtnn.data.lang.cn.MachineLang;
import org.arbor.gtnn.data.lang.cn.MaterialLang;
import org.arbor.gtnn.data.lang.cn.MiscLang;

public class LangHandlerCN {
    public static void init(CNLangProvider provider){
        MachineLang.init(provider);
        MaterialLang.init(provider);
        MiscLang.init(provider);
    }
}
