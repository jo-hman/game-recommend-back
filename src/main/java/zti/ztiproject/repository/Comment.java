package zti.ztiproject.repository;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Represents a comment entity.
 */
public class Comment {

    @Field
    private String author;
    @Field
    private String comment;

    /**
     * Constructs a Comment object with the provided author and comment content.
     * @param author the author of the comment
     * @param comment the content of the comment
     */
    public Comment(String author, String comment) {
        this.author = author;
        this.comment = comment;
    }

    /**
     * Retrieves the author of the comment.
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author of the comment.
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Retrieves the content of the comment.
     * @return the comment content
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the content of the comment.
     * @param comment the comment content to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
}
