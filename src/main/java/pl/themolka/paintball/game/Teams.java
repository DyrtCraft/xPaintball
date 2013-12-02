package pl.themolka.paintball.game;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.material.Wool;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.kitteh.tag.TagAPI;

import pl.themolka.paintball.Paintball;
import pl.themolka.paintball.PbPlugin;
import pl.themolka.paintball.api.TeamType;
import pl.themolka.paintball.api.events.PlayerJoinTeamEvent;
import pl.themolka.paintball.tasks.StartTask;

public class Teams implements pl.themolka.paintball.api.Teams {

	Paintball plugin;
	private static Teams instance;
	
	public static List<String> getBluePlayers;
	public static List<String> getRedPlayers;
	
	boolean joinable;
	boolean running;
	
	public Teams(Paintball plugin) {
		this.plugin  = plugin;
		Teams.instance = this;
		
		getBluePlayers = new ArrayList<String>();
		getRedPlayers = new ArrayList<String>();
	}
	
	public static Teams getInstance() {
		return instance;
	}
	
	@Override
	public List<String> getPlayers(TeamType teamType) {
		if(teamType == TeamType.BLUE) {
			return getBluePlayers;
		}
		if(teamType == TeamType.RED) {
			return getBluePlayers;
		} else {
			return null;
		}
	}
	
	@Override
	public TeamType getTeamType(Player player) {
		if(PbPlugin.getTeams().isTeam(player, TeamType.BLUE)) {
			return TeamType.BLUE;
		}
		if(PbPlugin.getTeams().isTeam(player, TeamType.OBSERVER)) {
			return TeamType.OBSERVER;
		}
		if(PbPlugin.getTeams().isTeam(player, TeamType.RED)) {
			return TeamType.RED;
		} else {
			return null;
		}
	}
	
	@Override
	public String getTeamName(TeamType teamType) {
		if(teamType == TeamType.BLUE) {
			return ChatColor.BLUE + "Blue team" + ChatColor.RESET;
		}
		if(teamType == TeamType.OBSERVER) {
			return ChatColor.AQUA + "Observers team" + ChatColor.RESET;
		}
		if(teamType == TeamType.RED) {
			return ChatColor.RED + "Red team" + ChatColor.RESET;
		} else {
			return null;
		}
	}
	
	@Override
	public boolean isJoinable() {
		return this.joinable;
	}
	
	@Override
	public boolean isRunning() {
		return this.running;
	}
	
	private boolean isTeamsFull() {
		Map map = PbPlugin.getCurrentMap();
		
		if(map.getMaxPlayers() < 0) {
			return false;
		}
		if(Teams.getBluePlayers.size() >= map.getMaxPlayers() || Teams.getRedPlayers.size() >= map.getMaxPlayers()) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean isTeam(Player player, TeamType teamType) {
		if(teamType == TeamType.BLUE && getBluePlayers.contains(player.getName())) {
			return true;
		}
		if(teamType == TeamType.RED && getRedPlayers.contains(player.getName())) {
			return true;
		}
		if(teamType == TeamType.OBSERVER) {
			if(getBluePlayers.contains(player.getName()) || getRedPlayers.contains(player.getName())) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}
	
	@Override
	public void setJoinable(boolean joinable) {
		this.joinable = joinable;
	}
	
	@Override
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	@Override
	public void setTeam(Player player, TeamType teamType) {
		 if(isTeamsFull() == true){
			if(!(player.hasPermission("pgm.vip.joinfull") || teamType == TeamType.OBSERVER)) {
				player.sendMessage(ChatColor.GOLD + "This match is full! Purcharse VIP to join full matches!");
				return;
			}
		}
		if(teamType == TeamType.RANDOM || teamType == null) {
			if(joinable = false) {
				player.sendMessage(ChatColor.RED + "You can not join to the team at this time, " + ChatColor.GOLD + "please wait!");
				return;
			}
			if(!(teamType == TeamType.OBSERVER) && Teams.getBluePlayers.contains(player.getName()) || Teams.getRedPlayers.contains(player.getName())) {
				player.sendMessage(ChatColor.RED + "You are already joined to the team!");
				return;
			}
			if(Teams.getBluePlayers.size() > Teams.getRedPlayers.size()) {
				// Red Team
				PlayerJoinTeamEvent event = new PlayerJoinTeamEvent(player, TeamType.RED);
				Bukkit.getPluginManager().callEvent(event);
				
				Teams.getRedPlayers.add(event.getPlayer().getName());
				TagAPI.refreshPlayer(player);
				player.setPlayerListName(ChatColor.RED + player.getName() + ChatColor.RESET);
				
				player.sendMessage(ChatColor.DARK_PURPLE + "You have joined to the " + getTeamName(TeamType.RED) + ChatColor.DARK_PURPLE + ".");
				check(player, TeamType.RED);
				return;
			}
			if(Teams.getBluePlayers.size() < Teams.getRedPlayers.size()) {
				// Blue Team
				PlayerJoinTeamEvent event = new PlayerJoinTeamEvent(player, TeamType.BLUE);
				Bukkit.getPluginManager().callEvent(event);
				
				Teams.getBluePlayers.add(event.getPlayer().getName());
				TagAPI.refreshPlayer(player);
				player.setPlayerListName(ChatColor.BLUE + player.getName() + ChatColor.RESET);
				
				player.sendMessage(ChatColor.DARK_PURPLE + "You have joined to the " + getTeamName(TeamType.BLUE) + ChatColor.DARK_PURPLE + ".");
				check(player, TeamType.BLUE);
				return;
			} else {
				// Hmm... random :)
				// Red Team
				PlayerJoinTeamEvent event = new PlayerJoinTeamEvent(player, TeamType.RED);
				Bukkit.getPluginManager().callEvent(event);
				
				Teams.getRedPlayers.add(event.getPlayer().getName());
				TagAPI.refreshPlayer(player);
				player.setPlayerListName(ChatColor.RED + player.getName() + ChatColor.RESET);
				
				player.sendMessage(ChatColor.DARK_PURPLE + "You have joined to the " + getTeamName(TeamType.RED) + ChatColor.DARK_PURPLE + ".");
				check(player, TeamType.RED);
				return;
			}
		}
		if(teamType == TeamType.OBSERVER) {
			if(PbPlugin.getTeams().isTeam(player, TeamType.OBSERVER)) {
				player.sendMessage(ChatColor.RED + "You are already in the " + getTeamName(TeamType.OBSERVER) + ChatColor.RED + "!");
				return;
			}
			PlayerJoinTeamEvent event = new PlayerJoinTeamEvent(player, TeamType.OBSERVER);
			Bukkit.getPluginManager().callEvent(event);
			
			Teams.getBluePlayers.remove(player.getName());
			Teams.getRedPlayers.remove(player.getName());
			
			player.damage(20.0);
			player.setGameMode(GameMode.CREATIVE);
			player.setDisplayName(ChatColor.AQUA + player.getName());
			
			/*for(Player players : Bukkit.getOnlinePlayers()) {
				if(!Teams.getBluePlayers.contains(player.getName()) && !Teams.getRedPlayers.contains(player.getName())) {
					players.hidePlayer(player);
				} else {
					players.showPlayer(player);
				}
			}*/
			
			player.getInventory().clear();
			player.getInventory().setHelmet(null);
			player.getInventory().setChestplate(null);
			player.getInventory().setLeggings(null);
			player.getInventory().setBoots(null);
			player.getInventory().setItem(0, PbPlugin.getNavigateItems().compass());
			if(PbPlugin.getTeams().isJoinable()) {
				player.getInventory().setItem(1, PbPlugin.getNavigateItems().teamChooser());
			}
			if(PbPlugin.getMatch().isVote()) {
				player.getInventory().setItem(2, PbPlugin.getNavigateItems().mapVoter());
			}
			
			TagAPI.refreshPlayer(player);
			player.setPlayerListName(ChatColor.AQUA + player.getName() + ChatColor.RESET);
			
			player.sendMessage(ChatColor.DARK_PURPLE + "You have joined to the " + getTeamName(TeamType.OBSERVER) + ChatColor.DARK_PURPLE + ".");
			return;
		}
		if(!player.hasPermission("pgm.vip.join")) {
			player.sendMessage(ChatColor.GOLD + "We sorry," + ChatColor.RED + " join to specify team is only for VIP members!");
			return;
		}
		if(joinable = false) {
			player.sendMessage(ChatColor.RED + "You can not join to the team at this time, " + ChatColor.GOLD + "please wait!");
			return;
		}
		if(PbPlugin.getTeams().isTeam(player, TeamType.BLUE) || PbPlugin.getTeams().isTeam(player, TeamType.RED)) {
			player.sendMessage(ChatColor.RED + "You are already joined to the team!");
			check(player, TeamType.OBSERVER);
			return;
		}
		if(teamType == TeamType.BLUE) {
			// Blue Team
			PlayerJoinTeamEvent event = new PlayerJoinTeamEvent(player, TeamType.BLUE);
			Bukkit.getPluginManager().callEvent(event);
			
			Teams.getBluePlayers.add(event.getPlayer().getName());
			TagAPI.refreshPlayer(player);
			player.setPlayerListName(ChatColor.BLUE + player.getName() + ChatColor.RESET);
			
			player.sendMessage(ChatColor.DARK_PURPLE + "You have joined to the " + getTeamName(TeamType.BLUE) + ChatColor.DARK_PURPLE + ".");
			check(player, TeamType.BLUE);
			return;
		}
		if(teamType == TeamType.RED) {
			// Red Team
			PlayerJoinTeamEvent event = new PlayerJoinTeamEvent(player, TeamType.RED);
			Bukkit.getPluginManager().callEvent(event);
			
			Teams.getRedPlayers.add(event.getPlayer().getName());
			TagAPI.refreshPlayer(player);
			player.setPlayerListName(ChatColor.RED + player.getName() + ChatColor.RESET);
			
			player.sendMessage(ChatColor.DARK_PURPLE + "You have joined to the " + getTeamName(TeamType.RED) + ChatColor.DARK_PURPLE + ".");
			check(player, TeamType.RED);
			return;
		} else {
			player.sendMessage(ChatColor.RED + "Team with name \"" + teamType + "\" not found!");
			return;
		}
	}
	
	public static void join(Player player, TeamType teamType) {
		player.getInventory().clear();
		player.setGameMode(GameMode.ADVENTURE);
		
		player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 1));
		
		player.getInventory().addItem(PbPlugin.getItems().defaultPistol());
		
		if(teamType == TeamType.BLUE) {
			Wool wool = new Wool(DyeColor.BLUE);
			player.getInventory().setHelmet(wool.toItemStack(1));
			player.getInventory().setChestplate(null);
			player.getInventory().setLeggings(null);
			player.getInventory().setBoots(null);
		}
		if(teamType == TeamType.RED) {
			Wool wool = new Wool(DyeColor.RED);
			player.getInventory().setHelmet(wool.toItemStack(1));
			player.getInventory().setChestplate(null);
			player.getInventory().setLeggings(null);
			player.getInventory().setBoots(null);
		}
		
		/*for(Player players : Bukkit.getOnlinePlayers()) {
			if(!Teams.getBluePlayers.contains(player.getName()) && !Teams.getRedPlayers.contains(player.getName())) {
				players.hidePlayer(player);
			} else {
				players.showPlayer(player);
			}
		}*/
	}
	
	public static void endGame(boolean allowVote) {
		for(Player player : Bukkit.getOnlinePlayers()) {
			if(player.isOnline()) {
				player.setGameMode(GameMode.CREATIVE);
				player.getInventory().setItem(0, PbPlugin.getNavigateItems().compass());
				player.getInventory().setItem(1, null);
				if(allowVote) {
					player.getInventory().setItem(2, PbPlugin.getNavigateItems().mapVoter());
				}
			}
		}
	}
	
	public void check(Player player, TeamType teamType) {
		if(Teams.getBluePlayers.size() > 0 && Teams.getRedPlayers.size() > 0) {
			if(isRunning() == true) {
				join(player, teamType);
			} else {
				new StartTask(plugin);
			}
		} else {
			player.sendMessage(ChatColor.RED + "There are no players in another team. Waiting for more players...");
		}
	}
	
}
