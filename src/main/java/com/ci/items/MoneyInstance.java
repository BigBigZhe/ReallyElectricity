package com.ci.items;

import com.ci.loader.ItemsLoader;

public class MoneyInstance extends Money{

    private int value;

    public MoneyInstance(String name, int value){
        super(name, value);
        ItemsLoader.ITEMS.add(this);
    }

}
