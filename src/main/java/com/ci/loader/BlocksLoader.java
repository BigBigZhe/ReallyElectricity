package com.ci.loader;

import com.ci.blocks.*;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;

public class BlocksLoader {
	public static final List<Block> BLOCKS = new ArrayList<>();

	public static final BandaBlock BANDA_BLOCK = new BandaBlock("bandablock");
	public static final BandaCore BANDA_CORE = new BandaCore("bandacore");
	public static final BandaUnderBlock BANDA_UNDER_BLOCK = new BandaUnderBlock("bandaunderblock");
}
