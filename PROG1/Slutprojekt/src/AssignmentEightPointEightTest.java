// JUnit-testfallen i denna fil testar den uppdaterade funktionen för att ta bort en hund U8.8
// För mer information se README.txt-filen
// 
// import static org.junit.jupiter.api.Assertions.*;
// 
// import java.util.Arrays;
// import java.util.List;
// 
// import org.junit.jupiter.api.Test;
// 
// 
// public class AssignmentEightPointEightTest extends AssignmentEightIOBaseTest<AssignmentEightPointEight> {
// 
// 	private final Dog BELLA = new Dog("Bella", "Labrador", 3, 12);
// 	private final Dog RATATA = new Dog("Ratata", "Tax", 15, 7);
// 	private final Dog DORIS = new Dog("Doris", "Cocker spaniel", 5, 7);
// 	private final Dog LASSIE = new Dog("Lassie", "Mops", 11, 11);
// 	private final Dog CHARLIE = new Dog("Charlie", "Puli", 9, 12);
// 
// 	private final Dog[] PREPARED_DOGS = { BELLA, RATATA, DORIS, LASSIE, CHARLIE };
// 
// 	public AssignmentEightPointEightTest() {
// 		super(AssignmentEightPointEight.class, "U8.8");
// 	}
// 
// 	@Test
// 	public void noStaticFieldsExceptPossiblyConstants() {
// 		assertNoStaticFields(cut, true);
// 	}
// 
// 	@Test
// 	public void noStaticMethods() {
// 		assertNoStaticMethods(cut);
// 	}
// 
// 	@Test
// 	public void removingOnlyDog() {
// 		setIn("Lassie\n");
// 		createSut();
// 
// 		addDogs(LASSIE);
// 
// 		callMethodUnderTest(sut);
// 
// 		List<Dog> dogs = accessDogList();
// 		assertTrue(dogs.isEmpty(), String.format("Listan av hundar borde vara tom: %s", dogs));
// 		assertOutDoesNotContainErrorMessage();
// 	}
// 
// 	@Test
// 	public void removingFirstDog() {
// 		setIn("Bella\n");
// 		createSut();
// 
// 		addDogs(PREPARED_DOGS);
// 
// 		callMethodUnderTest(sut);
// 
// 		List<Dog> dogs = accessDogList();
// 		assertEquals(4, dogs.size(), "Storleken på hundlistan är fel");
// 		assertEquals(Arrays.asList(RATATA, DORIS, LASSIE, CHARLIE), dogs, "Hundlistan innehåller fel hundar");
// 		assertOutDoesNotContainErrorMessage();
// 	}
// 
// 	@Test
// 	public void removingMiddleDog() {
// 		setIn("Doris\n");
// 		createSut();
// 
// 		addDogs(PREPARED_DOGS);
// 
// 		callMethodUnderTest(sut);
// 
// 		List<Dog> dogs = accessDogList(sut);
// 		assertEquals(4, dogs.size(), "Storleken på hundlistan är fel");
// 		assertEquals(Arrays.asList(BELLA, RATATA, LASSIE, CHARLIE), dogs, "Hundlistan innehåller fel hundar");
// 		assertOutDoesNotContainErrorMessage();
// 	}
// 
// 	@Test
// 	public void removingLastDog() {
// 		setIn("Charlie\n");
// 		createSut();
// 
// 		addDogs(PREPARED_DOGS);
// 
// 		callMethodUnderTest(sut);
// 
// 		List<Dog> dogs = accessDogList(sut);
// 		assertEquals(4, dogs.size(), "Storleken på hundlistan är fel");
// 		assertEquals(Arrays.asList(BELLA, RATATA, DORIS, LASSIE), dogs, "Hundlistan innehåller fel hundar");
// 		assertOutDoesNotContainErrorMessage();
// 	}
// 
// 	@Test
// 	public void removingDogWithNameInDifferentCase() {
// 		setIn("RAtaTa\n");
// 		createSut();
// 
// 		addDogs(PREPARED_DOGS);
// 
// 		callMethodUnderTest(sut);
// 
// 		List<Dog> dogs = accessDogList(sut);
// 		assertEquals(4, dogs.size(), "Storleken på hundlistan är fel");
// 		assertEquals(Arrays.asList(BELLA, DORIS, LASSIE, CHARLIE), dogs, "Hundlistan innehåller fel hundar");
// 		assertOutDoesNotContainErrorMessage();
// 	}
// 
// 	@Test
// 	public void removingNonexistingDogGivesErrorMessage() {
// 		setIn("Ingen hund\n");
// 		createSut();
// 
// 		addDogs(PREPARED_DOGS);
// 
// 		callMethodUnderTest(sut);
// 
// 		List<Dog> dogs = accessDogList(sut);
// 		assertEquals(5, dogs.size(), "Storleken på hundlistan är fel");
// 		assertEquals(Arrays.asList(BELLA, RATATA, DORIS, LASSIE, CHARLIE), dogs, "Hundlistan innehåller fel hundar");
// 		assertOutContainErrorMessage();
// 	}
// 
// 	@Test
// 	public void removingDogWithNoDogsInListGivesErrorMessage() {
// 		setIn("Ingen hund\n");
// 		createSut();
// 
// 		callMethodUnderTest(sut);
// 
// 		List<Dog> dogs = accessDogList(sut);
// 		assertEquals(0, dogs.size(), "Storleken på hundlistan är fel");
// 		assertOutContainErrorMessage();
// 	}
// 
// 	// Nya test
// 	@Test
// 	public void removingOwnedDogAlsoRemovesDogFromOwner() {
// 		setIn("Doris\n");
// 		createSut();
// 
// 		addDogs(PREPARED_DOGS);
// 
// 		Owner owner = new Owner("Lillsten");
// 		addOwners(owner);
// 
// 		addDogToOwner(owner, DORIS);
// 		assertOwnDog(owner, DORIS);
// 
// 		callMethodUnderTest(sut);
// 
// 		assertDoesNotOwnDog(owner, DORIS);
// 	}
// 
// 	@Test
// 	public void removingOwnedDogLeavesOwner() {
// 		setIn("Doris\n");
// 		createSut();
// 
// 		addDogs(PREPARED_DOGS);
// 
// 		Owner owner = new Owner("Storsten");
// 		addOwners(owner);
// 
// 		addDogToOwner(owner, DORIS);
// 		assertOwnDog(owner, DORIS);
// 
// 		callMethodUnderTest(sut);
// 
// 		assertContains(accessOwnerList(), owner, "Ägare saknas");
// 	}
// 
// }
