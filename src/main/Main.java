package main;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	
	private ArrayList<Block> specialFurnaces = new ArrayList<Block>();
	
	
	public void onEnable() {
		
		getServer().getPluginManager().registerEvents(this, this);
		
	}
	
	
	public void onDisable() {
		
	}
	
	
	@EventHandler
	public void furnaceListener(FurnaceSmeltEvent event) {
		
		Block furnace = event.getBlock();
		if(specialFurnaces.contains(furnace)) {
			ItemStack result = event.getResult();
			result.setAmount(result.getAmount() * 2);
		}
		
		
	}
	
	@EventHandler
	public void furnaceListener2(FurnaceBurnEvent event) {
		
		Block furnace = event.getBlock();
		if(event.getFuel().getType() == Material.COAL_BLOCK) {
			event.setBurnTime(event.getBurnTime()/2);
			if (!specialFurnaces.contains(furnace)) {
				specialFurnaces.add(furnace);
			}
		}
		else {
			specialFurnaces.remove(furnace);
		}
	}
	
}
