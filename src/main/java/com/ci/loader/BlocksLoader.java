package com.ci.loader;

import com.ci.blocks.*;
import com.ci.blocks.components.*;
import com.ci.blocks.components.resistance.*;
import com.ci.blocks.wires.*;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;

public class BlocksLoader {
	public static final List<Block> BLOCKS = new ArrayList<>();

	public static final Wire_Copper WIRE_COPPER = new Wire_Copper("wire_copper");
	public static final Power POWER = new Power("power");
	//public static final Resistance RESISTANCE = new Resistance("resistance", 1);
	public static final Resistance RESISTANCE10 = new Resistance10("resistance10", 10);
	public static final Resistance RESISTANCE20 = new Resistance20("resistance20", 20);
	public static final Capacitor CAPACITOR = new Capacitor("capacitor");
	public static final Diode DIODE = new Diode("diode");
	public static final ElectronicProcessingStation ELECTRONIC_PROCESSING_STATION
			= new ElectronicProcessingStation("electronicprocessingstation");
}
