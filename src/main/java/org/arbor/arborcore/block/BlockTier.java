package org.arbor.arborcore.block;

import org.arbor.arborcore.api.block.ITier;

public enum BlockTier implements ITier {
    TIER0(0),
    TIER1(1),
    TIER2(2),
    TIER3(3),
    TIER4(4),
    TIER5(5),
    TIER6(6),
    TIER7(7);

    private final int tier;

    BlockTier(int tier) {
        this.tier = tier;
    }

    @Override
    public int tier() {
        return tier;
    }
}
