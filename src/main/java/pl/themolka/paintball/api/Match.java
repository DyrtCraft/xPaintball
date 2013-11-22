package pl.themolka.paintball.api;

public interface Match {
	
	public void addScore(TeamType teamType, int amount);
	
	public void end();
	
	public int getScore(TeamType teamType);
	
	public void setScore(TeamType teamType, int amount);
	
	public void start();
	
}
