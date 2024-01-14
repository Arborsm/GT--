package org.arbor.gtnn.data;

import com.gregtechceu.gtceu.api.data.chemical.Element;
import com.gregtechceu.gtceu.common.data.GTElements;

@SuppressWarnings("unused")
public class GTNNElement {
    public static final Element IF = GTElements.createAndRegister(999, 9999, -1, null, "Infinity Catalyst", "If", false);
    public static final Element SpNt = GTElements.createAndRegister(9999, 99999, -1, null, "Space Neutronium", "SpNt", false);
    public static final Element IF2 = GTElements.createAndRegister(99999, 999999, -1, null, "Infinity", "If*", false);

    public static void init() {
    }
}
