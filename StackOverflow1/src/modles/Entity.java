package modles;

import javax.xml.stream.events.Comment;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Entity {
    private Date createdAt;
    private int entityId;
    private Member creator;
    private List<Comment> comments;
    private Map<VoteType,Integer> votes;
}
