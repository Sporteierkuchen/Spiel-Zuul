package Spiel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Spiel.model.Chest;
import Spiel.model.items.Item;
import Spiel.model.items.ItemHealthPotion;


class ChestTest {

Chest testtruhe1;	
Chest testtruhe2;	
Item testitem1;
Item testitem2;
List<Item> items1;
List<Item> items2;

final String twoItemsinChest="Items in dieser Truhe: Heiltrank1 Heiltrank2";
final String noItemsinChest="Items in dieser Truhe: Keine";

	@BeforeEach
	void setUp() {
		
	testtruhe1=new Chest();	
	testtruhe2=new Chest();	
	testitem1=new ItemHealthPotion(1);
	testitem2=new ItemHealthPotion(1);
	testtruhe2.setItem(testitem1);
	testtruhe2.setItem(testitem2);
	items1=new ArrayList<>();
	items2=new ArrayList<>();
	items2.add(testitem1);
	items2.add(testitem2);

	}

	@Test
	@DisplayName("Test für leere Kiste")
	public void EmptyChestTest() {
			
	assertEquals(noItemsinChest,testtruhe1.getItemsInChest());
	
	}
	
	@Test
	@DisplayName("Test für Kiste mit Items")
	public void ChestWithItemsTest() {
			
	assertEquals(twoItemsinChest,testtruhe2.getItemsInChest());
	
	}
	
	@Test
	@DisplayName("Test für null als Item in Kiste")
	public void SetNullItemToChestTest() {
	
	testtruhe1.setItem(null);	
	assertEquals(noItemsinChest,testtruhe1.getItemsInChest());
	
	}
	
	@Test
	@DisplayName("Test für Schließen der Kiste")
	public void ChestClosedTest() {
	
	assertFalse(testtruhe1.isOpen());
	
	}
	
	@Test
	@DisplayName("Test für Öffnen der Kiste")
	public void ChestOpenTest() {
	
	testtruhe1.openChest();
	assertTrue(testtruhe1.isOpen());
	
	}
	
	@Test
	@DisplayName("Test für geöffnete Kiste")
	public void ChestAlreadyOpenTest() {
	
	testtruhe1.openChest();
	testtruhe1.openChest();
	assertTrue(testtruhe1.isOpen());
	
	}
	
	
	
	@Test
	@DisplayName("Test für Items aus der geschlossenen Kiste nehmen")
	public void TakeItemsFromClosedChest() {
	
	assertEquals(Optional.empty(),testtruhe1.removeItemsFromChest());
	
	}
	
	@Test
	@DisplayName("Test für Items aus der offenen leeren Kiste nehmen")
	public void TakeItemsFromOpenEmptyChest() {
		
	testtruhe1.openChest();
	assertEquals(Optional.ofNullable (items1),testtruhe1.removeItemsFromChest());
	
	}
	
	@Test
	@DisplayName("Test für Items aus der offenen Kiste nehmen")
	public void TakeItemsFromOpenChest() {
		
	testtruhe2.openChest();
	assertEquals(Optional.ofNullable (items2),testtruhe2.removeItemsFromChest());
	
	}
	
}
