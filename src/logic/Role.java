package logic;
/**
 * 2/28/17
 *
 */
public enum Role {

    TOWNIE              ("Townie", "Do nothing at night", "To have only town members left"),
    DETECTIVE           ("Detective", "Reveals the team for one player per night", "To have only town members left"),
    MAFIA_HITMAN        ("Mafia: Hitman", "May kill someone each night", "To make the majority of the town mafia members"),
    DOCTOR              ("Doctor", "May heal one player each night", "To have only town members left"),
    SURVIVOR            ("Survivor", "Do nothing at night", "To be the last town member left alive"),
    MAFIA_BARMAN        ("Mafia: Barman", "May stop the action of another player each night", "To make the majority of the town mafia members"),
    BODYGUARD           ("Bodyguard", "May save another person by dying in their place", "To have only town members left"),
    LYNCHER             ("Lyncher", "Do nothing at night", "To Lynch a specific player to win solo win the game | "),
    MAFIABOSS_GODFATHER ("Mafiaboss: GodFather", "Hidden from the Detective. Can send a message to another Mafia memeber each night", "To make the majority of the town mafia members"),
    VIGILANTE           ("Vigilante", "May kill new person each night", "To have only town members left");

    private final String roleName;
    private final String roleInfo;
    private final String roleGoal;

    Role(String name, String role, String goal) {
        this.roleName = name;
        this.roleInfo = role;
        this.roleGoal = goal;
    }

    String getRoleName() {
        return roleName;
    }

    String getRoleInfo() {
        return roleInfo;
    }

    String getRoleGoal() {
        return roleGoal;
    }
}