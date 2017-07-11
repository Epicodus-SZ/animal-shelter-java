import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class CustomerTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/shelter_test", null, null);
  }

  // @After
  // public void tearDown() {
  //   try(Connection con = DB.sql2o.open()) {
  //     //String deleteAnimalsQuery = "DELETE FROM animals *;";
  //     // con.createQuery(deleteAnimalsQuery).executeUpdate();
  //     String deleteCustomersQuery = "DELETE FROM customers *;";
  //     con.createQuery(deleteCustomersQuery).executeUpdate();
  //   }
  // }

  @Test
  public void Customer_instantiatesCorrectly_true() {
    Customer testCustomer = new Customer("Steve Z", "2068982579", Animal_Pref.DOG, "Golden Retriever");
    assertEquals(true, testCustomer instanceof Customer);
  }

  @Test
  public void all_returnsAllInstancesOfCustomer_Steve() {
    assertEquals("Steve Z", Customer.all().get(0).getName());
  }

}
