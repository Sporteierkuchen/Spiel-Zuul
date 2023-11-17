package Spiel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Spiel.model.PlayerInventory;
import Spiel.model.items.Item;
import Spiel.model.items.ItemHealthPotion;
import Spiel.model.items.ItemIronSword;

class PLayerInventoryTest {

PlayerInventory testinventar1;
PlayerInventory testinventar2;
Item testitem1;
Item testitem2;

final String heiltrank="Heiltrank";
final String schadenstrank="Schadenstrank";
final String eisenschwert="Eisenschwert";

	@BeforeEach
	void setUp(){
		
	testinventar1=new PlayerInventory();
	testinventar2=new PlayerInventory();
	
	
	testitem1=new ItemHealthPotion(1);	
	testitem2=new ItemIronSword(1);	
	
	testinventar2.addItem(testitem1);
	}
	

	@Test
	@DisplayName("Test für Items dem Inventar hinzufügen")
	void AddItemTest() {

		testinventar1.addItem(testitem1);
		assertTrue(testinventar1.contains(heiltrank));
		assertFalse(testinventar1.isInventoryempty());
		
	}
	
	@Test
	@DisplayName("Test für NULL dem Inventar hinzufügen")
	void AddNullItemTest() {

		testinventar1.addItem(null);
		assertFalse(testinventar1.contains(null));
		assertTrue(testinventar1.isInventoryempty());
		
	}
	
	@Test
	@DisplayName("Test für Items die im Inventar vorhanden sind")
	void InventoryContainsTest() {

		assertFalse(testinventar2.contains(schadenstrank));
		assertTrue(testinventar2.contains(heiltrank));
		
	}
	
	@Test
	@DisplayName("Testet die contains-Methode mit mehreren Items im Inventar ")
	void InventoryWithMultipleItemsContainsTest() {

		testinventar1.addItem(testitem1);
		testinventar1.addItem(testitem2);
		
		assertTrue(testinventar1.contains(heiltrank));
		assertTrue(testinventar1.contains(eisenschwert));
		
	}
	
	
	
	@Test
	@DisplayName("Test für Items aus dem Inventar entfernen")
	void RemoveItemFromInventoryTest() {

		testinventar2.removeItems(testitem1);
		assertFalse(testinventar2.contains(heiltrank));
		assertTrue(testinventar2.isInventoryempty());
		
	}
	
	@Test
	@DisplayName("Test für falsches Item aus dem Inventar benutzen")
	void UseWrongItemFromInventoryTest() {

		assertEquals(Optional.empty(),testinventar2.useItemfromInventory(schadenstrank));
		
	}
	
	@Test
	@DisplayName("Test für Item aus dem Inventar benutzen")
	void UseItemFromInventoryTest() {

		assertEquals(Optional.ofNullable (testitem1),testinventar2.useItemfromInventory(heiltrank));
		
	}
	
	@Test
	@DisplayName("Prüft ob das gewünschte Item aus dem Inventar zurückgegeben wird, wenn mehere Items im Inventar sind ")
	void UseItemFromInventoryWithMultipleItemsTest() {

		testinventar1.addItem(testitem1);
		testinventar1.addItem(testitem2);
	
		assertEquals(Optional.ofNullable (testitem1),testinventar1.useItemfromInventory(heiltrank));
		assertEquals(Optional.ofNullable (testitem2),testinventar1.useItemfromInventory(eisenschwert));
		assertEquals(Optional.ofNullable (testitem2),testinventar1.useItemfromInventory(eisenschwert));
		
	}
	
}
