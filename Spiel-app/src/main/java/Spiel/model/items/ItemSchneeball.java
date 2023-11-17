package Spiel.model.items;

import Spiel.model.Player;

public class ItemSchneeball extends Verbrauchs_Item{

	public ItemSchneeball(Integer anzahl) {
		super("Schneeball", "Damit kann der Gegner beworfen werden...", anzahl, 3,true);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void use(Player player) {
		// TODO Auto-generated method stub
		
		String text = "";

		if (player.getOptionalGegner().isPresent()) {

			player.getGegner().setAussetzen(1);
			text += "Du hast " + player.getGegner().getOpponentName() + " getroffen.";
		} else {
			text += "Du wirfst den Schneeball und es passiert nichts...";
		}

			if (getAnzahl() == 1) {
				player.removeItems(this);
			} else {
				this.setAnzahl(this.getAnzahl() - 1);
			}

		player.setUseItemText(text);

	}

	
	
}
