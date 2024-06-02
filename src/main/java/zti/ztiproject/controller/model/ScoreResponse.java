package zti.ztiproject.controller.model;

/**
 * Represents a response object for scores, including average score and count.
 */
public record ScoreResponse(double average, int count) {
}
