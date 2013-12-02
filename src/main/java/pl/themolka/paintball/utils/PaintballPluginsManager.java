package pl.themolka.paintball.utils;

public class PaintballPluginsManager implements pl.themolka.paintball.api.utils.PaintballPluginsManager {
	
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
