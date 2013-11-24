package pl.themolka.paintball.api;

public interface Match {
	
	public void addScore(TeamType teamType, int amount);
	
	public void end(boolean allowVote);
	
	public int getScore(TeamType teamType);
	
	public boolean isVote();
	
	public void setScore(TeamType teamType, int amount);
	
	public void setVote(boolean allowVote);
	
	public void start();
	
}
