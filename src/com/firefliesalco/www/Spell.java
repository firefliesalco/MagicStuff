package com.firefliesalco.www;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.inventivetalent.particle.ParticleEffect;

public abstract class Spell {

	long life = 0;
	Location loc;
	Vector speed;
	ParticleEffect particle;
	Player owner;
	
	public Spell(Location loc, Vector speed, Player p){
		this.loc = loc;
		this.speed = speed;
		this.owner = p;
	}
	
	public abstract Vector particleOffset();
	
	public abstract void onIntersect();
	
	public void remove(){
		Main.spells.remove(this);
	}
	
	public abstract void hitEntity(ArrayList<Entity> e);
	
	public void tick(){
		//Best guess is loc, particle type, life
		particle.send(Bukkit.getOnlinePlayers(), loc.clone().add(particleOffset()), 0, 0, 0, 0, 1);
		if(loc.getBlock().getType() != Material.AIR){
			onIntersect();
		}
		ArrayList<Entity> ehs = new ArrayList<Entity>();
		ehs.addAll(loc.getWorld().getNearbyEntities(loc, 1, 1, 1));
		if(ehs.contains(owner))
			ehs.remove(owner);
		if(ehs.size() > 0)
			hitEntity(ehs);
		life++;
		loc = loc.add(speed);
	}
	
}
