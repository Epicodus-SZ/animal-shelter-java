import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class AnimalTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/shelter_test", null, null);
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String deleteTasksQuery = "DELETE FROM animals *;";
      String deleteCategoriesQuery = "DELETE FROM customers *;";
      con.createQuery(deleteTasksQuery).executeUpdate();
      con.createQuery(deleteCategoriesQuery).executeUpdate();
    }
  }

  @Test
  public void Animal_instantiatesCorrectly_true() {
    Animal testAnimal = new Animal("Ranger", "M", "American Shorthair");
    assertEquals(true, testAnimal instanceof Animal);
  }

  @Test
  public void all_returnsAllInstancesOfAnimal_true() {
    Animal testAnimal = new Animal("Ranger", "M", "American Shorthair");
    testAnimal.save();
    Animal testAnimal2 = new Animal("Dallas", "F", "American Shorthair");
    testAnimal2.save();
    assertEquals(true, Animal.all().get(0).equals(testAnimal));
    assertEquals(true, Animal.all().get(1).equals(testAnimal2));
  }

  // @Test
  // public void save_assignsIdToObject() {
  //   Animal testAnimal = new Animal("Ranger", "M", "American Shorthair");
  //   testAnimal.save();
  //   Task savedTask = Task.all().get(0);
  //   assertEquals(myTask.getId(), savedTask.getId());
  // }

} // end of AnimalTest class
