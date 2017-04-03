package playerInfo;
/**
 * Unlike the Mafia class, some Town Players are classified
 * only as town players.
 * @author nestr
 *
 */
public class Town extends Player {

	/**
	 * Used for inheriting different town subclasses
	 * @param name
	 * @param position
	 * @param role
	 */
	public Town(String name, int position, Role role) {
		super(name, position, role);
	}
	
	/**
	 * Copy constructor
	 * @param t
	 */
	public Town(Town t){
		super(t);
	}

	/**
	 * By default, Town Players do not have any night actions.
	 * This might be overriden by subclasses of Town
	 */
	@Override
	public int doAction(Player p) {
		// These players do nothing
		return p.getStatus();
	}
	
	public String toString() {
		return "Town " + role.getRoleName();
	}
	
}
