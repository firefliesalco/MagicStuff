package com.firefliesalco.www;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import org.inventivetalent.particle.ParticleEffect;

public class Freeze extends Spell {

	public Freeze(Location loc, Vector speed, Player owner) {
		super(loc, speed, owner);
		particle = ParticleEffect.SNOWBALL;
	}

	@Override
	public Vector particleOffset() {
		return new Vector(0, 0, 0);
	}

	@Override
	public void onIntersect() {
		remove();
		
	}

	@Override
	public void hitEntity(ArrayList<Entity> e) {
		for(Entity le : e){
		((LivingEntity)le).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 2000, 128));
		((LivingEntity)le).addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 2000, 128));
		
		}
		remove();
		
	}

	
	
}
