package shop.main;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import shop.command.Command;
import shop.data.Data;
import shop.data.Record;
import shop.data.Video;
import shop.data.Inventory;
import java.util.Iterator;

import java.util.ArrayList;

// TODO:
// write an integration test that tests the data classes.
// add in some videos, check out, check in, delete videos, etc.
// check that errors are reported when necessary.
// check that things are going as expected.
public class TEST2{
  private Inventory _inventory = Data.newInventory();

  private void expect(Video v, String s) {
    assertEquals(s,_inventory.get(v).toString());
  }
  private void expect(Record r, String s) {
    assertEquals(s,r.toString());
  }

  @Test
  public void test() {
    Command clearCmd = Data.newClearCmd(_inventory);
    clearCmd.run();

    Video v4 = Data.newVideo("Star Wars: Episode IV - A New Hope", 1977, "George Lucas");
    Video v5 = Data.newVideo("Star Wars: Episode V - The Empire Strikes Back", 1980, "Irvin Kershner");
    Video v6 = Data.newVideo("Star Wars: Episode VI - Return of the Jedi", 1983, "Richard Marquand");
    Video v1 = Data.newVideo("Star Wars: Episode I - The Phantom Menace", 1999, "George Lucas");
    Video v2 = Data.newVideo("Star Wars: Episode II - Attack of the Clones", 2002, "George Lucas");
    Video v3 = Data.newVideo("Star Wars: Episode III - Revenge of the Sith", 2005, "George Lucas");
    Video v7 = Data.newVideo("Star Wars: Episode VII - The Force Awakens", 2015, "J.J. Abrams");
    Video v8 = Data.newVideo("Star Wars: Episode VIII - The Last Jedi", 2017, "Rian Johnson");
    Video v9 = Data.newVideo("Star Wars: Episode IX - The Rise of Skywalker", 2019, "J.J. Abrams");
    assertEquals(0, _inventory.size());

    Video[] StarWarsFilms = {v1,v2,v3,v4,v5,v6,v7,v8,v9};

    int videos_added = 0;
    for (Video video: StarWarsFilms) {
        assertEquals(videos_added, _inventory.size());
        assertTrue(Data.newAddCmd(_inventory, video, 10).run());
        videos_added++;
    }

    assertTrue(Data.newOutCmd(_inventory, v2).run());
    assertTrue(Data.newOutCmd(_inventory, v2).run());
    assertTrue(Data.newOutCmd(_inventory, v2).run());
    assertTrue(Data.newOutCmd(_inventory, v2).run());
    assertTrue(Data.newOutCmd(_inventory, v2).run());
    assertTrue(Data.newOutCmd(_inventory, v2).run());
    assertTrue(Data.newOutCmd(_inventory, v2).run());
    assertTrue(Data.newOutCmd(_inventory, v2).run());
    assertTrue(Data.newOutCmd(_inventory, v2).run());
    assertTrue(Data.newOutCmd(_inventory, v2).run());
    assertFalse(Data.newOutCmd(_inventory, v2).run()); // All checkedout

    expect(v2, "Star Wars: Episode II - Attack of the Clones (2002) : George Lucas [10,10,10]");

    Video v10 = Data.newVideo("The Lord of the Rings: The Fellowship of the Ring", 2001, "Peter Jackson");
    assertFalse(Data.newOutCmd(_inventory, v10).run()); // Have not added yet
    assertTrue(Data.newAddCmd(_inventory, v10, 5).run());

    expect(v10, "The Lord of the Rings: The Fellowship of the Ring (2001) : Peter Jackson [5,0,0]");

    assertFalse(Data.newInCmd(_inventory, v4).run());
    expect(v4, "Star Wars: Episode IV - A New Hope (1977) : George Lucas [10,0,0]");

    assertTrue(Data.newInCmd(_inventory, v2).run());
    assertTrue(Data.newInCmd(_inventory, v2).run());
    assertTrue(Data.newInCmd(_inventory, v2).run());
    assertTrue(Data.newInCmd(_inventory, v2).run());
    assertTrue(Data.newInCmd(_inventory, v2).run());
    assertTrue(Data.newInCmd(_inventory, v2).run());
    assertTrue(Data.newInCmd(_inventory, v2).run());

    assertTrue(Data.newOutCmd(_inventory, v2).run());
    assertTrue(Data.newOutCmd(_inventory, v2).run());
    assertTrue(Data.newOutCmd(_inventory, v2).run());

    assertTrue(Data.newInCmd(_inventory, v2).run());
    assertTrue(Data.newInCmd(_inventory, v2).run());
    assertTrue(Data.newInCmd(_inventory, v2).run());
    assertTrue(Data.newInCmd(_inventory, v2).run());
    assertTrue(Data.newInCmd(_inventory, v2).run());

    assertTrue(Data.newOutCmd(_inventory, v2).run());
    assertTrue(Data.newOutCmd(_inventory, v2).run());


    expect(v2, "Star Wars: Episode II - Attack of the Clones (2002) : George Lucas [10,3,15]");

    Iterator<Record> it = _inventory.iterator(new java.util.Comparator<Record>() {
        public int compare (Record r1, Record r2) {
          return r2.numOut() - r1.numOut();
        }
      });
    assertTrue(it.hasNext());
    
    assertEquals(it.next().toString(), "Star Wars: Episode II - Attack of the Clones (2002) : George Lucas [10,3,15]");

    
  } 
}
