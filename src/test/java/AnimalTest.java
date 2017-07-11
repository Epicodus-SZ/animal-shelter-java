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
      String deleteAnimalsQuery = "DELETE FROM animals *;";
      // String deleteCustomersQuery = "DELETE FROM customers *;";
      con.createQuery(deleteAnimalsQuery).executeUpdate();
      // con.createQuery(deleteCustomersQuery).executeUpdate();
    }
  }

  @Test
  public void Animal_instantiatesCorrectly_true() {
    Animal testAnimal = new Animal("Ranger", Gender.MALE, Animal_Type.CAT, "American Shorthair");
    assertEquals(true, testAnimal instanceof Animal);
  }

  @Test
  public void all_returnsAllInstancesOfAnimal_true() {
    Animal testAnimal = new Animal("Ranger", Gender.MALE, Animal_Type.CAT, "American Shorthair");
    testAnimal.save();
    Animal testAnimal2 = new Animal("Roverita", Gender.FEMALE, Animal_Type.DOG, "Pitbull");
    testAnimal2.save();
    assertEquals(true, Animal.all().get(0).equals(testAnimal));
    assertEquals(true, Animal.all().get(1).equals(testAnimal2));
  }

  @Test
  public void save_assignsIdToObject() {
    Animal testAnimal = new Animal("Ranger", Gender.MALE, Animal_Type.CAT, "American Shorthair");
    testAnimal.save();
    Animal dbAnimal = Animal.all().get(0);
    assertEquals(testAnimal.getId(), dbAnimal.getId());
  }

} // end of AnimalTest class
