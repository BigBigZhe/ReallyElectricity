package com.ci.loader;

import com.ci.blocks.*;
import com.ci.blocks.wires.*;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;

public class BlocksLoader {
	public static final List<Block> BLOCKS = new ArrayList<>();

	public static final Wire_Copper WIRE_COPPER = new Wire_Copper("wire_copper");
	public static final ElectronicProcessingStation ELECTRONIC_PROCESSING_STATION
			= new ElectronicProcessingStation("electronicprocessingstation");
}
