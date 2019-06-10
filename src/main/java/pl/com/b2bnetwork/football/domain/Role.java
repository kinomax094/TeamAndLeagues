package pl.com.b2bnetwork.football.domain;

public enum Role {

    ROLE_ADMIN("Admin"), ROLE_USER("User");

    private String roleDescription;

    Role(final String desc) {
        roleDescription = desc;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public static Role fromString(final String value) {
        for (Role role : Role.values()) {
            if (value.equalsIgnoreCase(role.name())) {
                return role;
            }
        }
        return null;
    }

    public static Role findRoleByDescription(final String roleDesc) {

        for (Role role : Role.values()) {
            if (role.getRoleDescription().equals(roleDesc)) {
                return role;
            }
        }
        return null;
    }
}
