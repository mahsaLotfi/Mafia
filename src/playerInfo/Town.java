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
	public Town(String name, int position) {
		super(name, position);
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
		return "Townie";
	}

	/**
	 * returns the role name
	 */
	@Override
	public String getRole() {
		// TODO Auto-generated method stub
		return this.toString();
	}

	/**
	 * returns the role information
	 * for directions
	 */
	@Override
	public String getRoleInfo() {
		// TODO Auto-generated method stub
		return "Do nothing at night";
	}

	/**
	 * returns the goal of the role
	 */
	@Override
	public String getRoleGoal() {
		// TODO Auto-generated method stub
		return "Lynch all Mafia to win the game";
	}
	
}