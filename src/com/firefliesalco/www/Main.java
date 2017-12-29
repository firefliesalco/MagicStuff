package com.firefliesalco.www;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	
	public static ArrayList<Spell> spells = new ArrayList<Spell>();
	
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
	
		if(label.equalsIgnoreCase("wand")){
			if(args.length==0){
				
			}
			if(args.length==1){
				if(args[0].equalsIgnoreCase("get")){
					((Player)sender).getInventory().addItem(wand());
				}
			}
		}
		
		return true;
		
	}
	
	@Override
	public void onEnable(){
		getServer().getPluginManager().registerEvents(this, this);
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){
			public void run(){
				for(int i = 0; i < spells.size(); i++){
					spells.get(i).tick();
				}
			}
		}, 5, 1);
	}
	
	public ItemStack wand(){
		ItemStack is = new ItemStack(Material.BLAZE_ROD);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(ChatColor.DARK_PURPLE + "Magic Wand");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.BLUE + "There's great power radiating from it.");
		lore.add(ChatColor.BLUE + "You feel like you should be careful around this.");
		im.setLore(lore);
		is.setItemMeta(im);
		return is;
		
	}
	
	public boolean isWand(ItemStack is){
		return is != null && is.getType() == Material.BLAZE_ROD && is.getItemMeta() != null && is.getItemMeta().getDisplayName() != null && is.getItemMeta().getDisplayName().contains("Magic Wand");
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e){
		if(e.getAction() == Action.RIGHT_CLICK_AIR){
			if(isWand(e.getItem())){
				spells.add(new ExplosionHelix(e.getPlayer().getEyeLocation(), e.getPlayer().getLocation().getDirection().multiply(1), e.getPlayer()));
			}
		}
	}
	
	
	
	

}
