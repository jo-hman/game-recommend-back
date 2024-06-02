package zti.ztiproject.controller.model;

/**
 * Represents a JWT response object.
 */
public class Jwt {

    private String jwt;

    /**
     * Constructs a JWT object with the provided JWT string.
     * @param jwt the JWT string
     */
    public Jwt(String jwt) {
        this.jwt = jwt;
    }

    /**
     * Gets the JWT string.
     * @return the JWT string
     */
    public String getJwt() {
        return jwt;
    }
}
