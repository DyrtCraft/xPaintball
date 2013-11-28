package pl.themolka.paintball;

public class PaintballPluginsManager implements pl.themolka.paintball.api.PaintballPluginsManager {
	
	private boolean chat = false;
	
	@Override
	public boolean isPaintballChat() {
		return chat;
	}
	
	@Override
	public void setPaintballChat(boolean paintballChat) {
		chat = paintballChat;
	}
	
}
