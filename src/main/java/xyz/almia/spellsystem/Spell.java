package xyz.almia.spellsystem;

public class Spell {
	
	public int cost;
	public int id;
	public int cooldown;
	public String color;
	
	public int getCost(){
		return this.cost;
	}
	
	public void setCost(int cost){
		this.cost = cost;
	}
	
	public int getId(){
		return this.id;
	}
	
	public int getCooldown(){
		return this.cooldown;
	}
	
	public void setCooldown(int cooldown){
		this.cooldown = cooldown;
	}
	
	Spell(int id){
		this.id = id;
	}
}
