package zti.ztiproject.repository;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document
public class Game {

    @Id
    private String id;

    @Field
    private String authorAccountId;

    @Field
    private String title;

    @Field
    private String description;

    @Field
    private List<Comment> comments;

    @Field
    private List<Integer> scores;

    public Game(String id, String authorAccountId, String title, String description, List<Comment> comments, List<Integer> scores) {
        this.id = id;
        this.authorAccountId = authorAccountId;
        this.title = title;
        this.description = description;
        this.comments = comments;
        this.scores = scores;
    }

    public void setScores(List<Integer> scores) {
        this.scores = scores;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthorAccountId() {
        return authorAccountId;
    }

    public void setAuthorAccountId(String authorAccountId) {
        this.authorAccountId = authorAccountId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Integer> getScores() {
        return scores;
    }
}
