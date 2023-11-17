package Spiel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Spiel.model.Chest;
import Spiel.model.Hebel;
import Spiel.model.Room;
import Spiel.model.items.Item;
import Spiel.model.items.ItemHealthPotion;

class RoomTest {

Room room1;
Room room2;
Item testitem;
Hebel testschalter;
Chest testchest;

final String noItemsinChest="Items in dieser Kiste: Keine";
final String heiltrank="Heiltrank";
final String schadenstrank="Schadenstrank";
final String norden="norden";
final String sueden="süden";
final String westen="westen";
final String testraum="Testraum";
final String raumbeschreibung="Deine Position: Testraum"+ ".\n"+"Ausgänge: norden";

	@BeforeEach
	void setUp() {
		
	room1= new Room(testraum,1);
	room2 =new Room("Testraum2",1);
	room1.setExit("norden", room2);
	testitem=new ItemHealthPotion(1);
	room2.addItem(testitem);
	testschalter=new Hebel();
	room1.setHebel(testschalter);
	testchest=new Chest();
	room2.setChest(testchest);
	
	}

	@Test
	public void KonstrukturTest() {
		
	assertEquals(testraum, room1.getShortDescription());

	}	
	
	@Test

	public void getExitnachNordenTest() {
		
	assertEquals(Optional.ofNullable(room2),room1.getExit(norden));
	
	}	
	
	@Test

	public void getExitnachSuedenTest() {
		
	assertEquals(Optional.empty(),room1.getExit(sueden));
	
	}
	
	@Test
	@DisplayName("Test für die Ausgabe der erweiterten Raumbeschreibung")
	public void longDescriptionTest() {
	assertEquals(raumbeschreibung, room1.getLongDescription());
	
	}	

	@Test
	@DisplayName("Test für ein Item in einen Raum legen")
	public void SetItemTest() {
		
	room1.addItem(testitem);	
	assertFalse(room1.noItemsinRoom());

	assertEquals(Optional.ofNullable (testitem),room1.getItem(0));
	
	}
	
	@Test
	@DisplayName("Test für NULL in einen Raum legen")
	public void SetNullItemTest() {
	room1.addItem(null);	
	assertTrue(room1.noItemsinRoom());
	
	}
	
	@Test
	@DisplayName("Item aus Raum entfernen")
	void RemoveItemFromRoomTest() {
		room2.removeItem(testitem);
		assertTrue(room2.noItemsinRoom());
		assertEquals(Optional.empty(),room2.getItem(-1));
		
	}
	
	@Test
	void GetItemFromRoomTest() {
		
		assertEquals(Optional.ofNullable (testitem),room2.getItem(0));
		
	}
	
	@Test
	void GetWrongItemFromRoomTest() {

		assertEquals(Optional.empty(),room2.getItem(5));
		
	}
	
	@Test
	void GetItemInRoomTest() {

		assertEquals("Items in diesem Raum: Heiltrank",room2.getItemsinRoom());
		
	}
	
	@Test
	void GetNoItemInRoomTest() {

		assertEquals("Items in diesem Raum: Keine",room1.getItemsinRoom());
		
	}
	
	@Test
	void GetSchalterInRoomTest() {

		assertEquals(Optional.ofNullable (testschalter),room1.getHebel());
		
	}
	
	@Test
	void GetNoSchalterInRoomTest() {
		
		assertEquals(Optional.empty(),room2.getHebel());
		
	}
	
	@Test
	void leverAvailableInRoomTest() {

		assertTrue(room1.leverAvailable());
		
	}
	
	@Test
	void leverPushedAvailableInRoom() {

		room1.getHebel().get().einschalten();
		assertFalse(room1.leverAvailable());
		
	}
	
	@Test
	void NoLeverAvailableInRoom() {

		assertFalse(room2.leverAvailable());
		
	}
	
	@Test
	void SetLeverInRoom() {
		
		room2.setHebel(testschalter);
		assertEquals(Optional.ofNullable (testschalter),room2.getHebel());
		
	}
	
	@Test
	void SetNullLeverInRoom() {
		
		room2.setHebel(null);
		assertEquals(Optional.empty(),room2.getHebel());
		
	}
	
	@Test
	void GetChestInRoomTest() {

		assertEquals(Optional.ofNullable (testchest),room2.getChest());
		
	}
	
	@Test
	void GetNoChestInRoomTest() {

		assertEquals(Optional.empty(),room1.getChest());
		
	}
	
	@Test
	void SetChestInRoom() {
		
		room1.setChest(testchest);
		assertEquals(Optional.ofNullable (testchest),room1.getChest());
		
	}
	
	@Test
	void SetNullChestInRoom() {
		
		room1.setChest(null);
		assertEquals(Optional.empty(),room1.getChest());
		
	}
	
}
