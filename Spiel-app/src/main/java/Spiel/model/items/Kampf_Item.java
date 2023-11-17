package Spiel.model.items;

public abstract class Kampf_Item extends Item {

	public Kampf_Item(String itemName, String itemBeschreibung, int anzahl) {
		super(itemName, ItemKategorien.KAMPFITEM.toString(), itemBeschreibung, anzahl, 1, false, 100);

	}
	

	protected int calculateItemDamageBis5() {

		int max = 5;
		int min = 1;
		int range = max - min + 1;

		int rand = (int) (Math.random() * range) + min;

		System.out.println("Itemschaden zwischen 1 und 5: " + rand);

		return rand;

	}

	protected int calculateItemDamage5_10() {

		int max = 10;
		int min = 6;
		int range = max - min + 1;

		int rand = (int) (Math.random() * range) + min;

		System.out.println("Itemschaden zwischen 6 und 10: " + rand);

		return rand;

	}

	protected int calculateItemDamage10_15() {

		int max = 15;
		int min = 11;
		int range = max - min + 1;

		int rand = (int) (Math.random() * range) + min;

		System.out.println("Itemschaden zwischen 11 und 15: " + rand);

		return rand;

	}

	protected int calculateItemDamage15_20() {

		int max = 20;
		int min = 16;
		int range = max - min + 1;

		int rand = (int) (Math.random() * range) + min;

		System.out.println("Itemschaden zwischen 16 und 20: " + rand);

		return rand;

	}

	protected int calculateItemDamage20_25() {

		int max = 25;
		int min = 21;
		int range = max - min + 1;

		int rand = (int) (Math.random() * range) + min;

		System.out.println("Itemschaden zwischen 21 und 25: " + rand);

		return rand;

	}

	protected int calculateItemDamage25_30() {

		int max = 30;
		int min = 26;
		int range = max - min + 1;

		int rand = (int) (Math.random() * range) + min;

		System.out.println("Itemschaden zwischen 26 und 30: " + rand);

		return rand;

	}

}
