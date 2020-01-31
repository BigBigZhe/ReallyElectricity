package com.ci.loader;

import java.util.ArrayList;
import java.util.List;

import com.ci.blocks.wires.Wire_Copper;
import com.ci.items.*;

import net.minecraft.item.Item;

public class ItemsLoader {
	public static List<Item> ITEMS = new ArrayList<>();

	public static final Wire_Copper WIRE_COPPER = new Wire_Copper("wire_copper");

	public static final Money MONEY1 = new MoneyInstance("money1", 1);
	/*public static final MoneyInstance MONEY5 = new MoneyInstance("money1", 5);
	public static final MoneyInstance MONEY10 = new MoneyInstance("money1", 10);
	public static final MoneyInstance MONEY50 = new MoneyInstance("money1", 50);
	public static final MoneyInstance MONEY100 = new MoneyInstance("money1", 100);
	public static final MoneyInstance MONEY500 = new MoneyInstance("money1", 500);
	public static final MoneyInstance MONEY1000 = new MoneyInstance("money1", 1000);*/

}
