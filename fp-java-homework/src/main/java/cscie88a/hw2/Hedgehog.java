package cscie88a.hw2;

public class Hedgehog extends AbstractAnimal {

	public Hedgehog() {
	}

	public Hedgehog(String name, String eyeColor, String bodyColor) {
		super(name, eyeColor, bodyColor);
	}

	@Override
	public void sayHiToHuman(String humanName) {
		System.out.println(name + " says: Hi, " + humanName + "!!! I LOVE you!");
	}

	@Override
	public ActionResult playWithMe(AbstractAnimal aFriend) {
		// I am happy to play with anyone!
		System.out.println(name + " says: I'm not playing with " + aFriend.getName());
		return ActionResult.FAILURE;
	}

	@Override
	public ActionResult playWithToy(Toy toy) {
		System.out.println("I love the toy! Yeah!!!");
		toy.doFunStuff();
		return ActionResult.SUCCESS;
	}
}
