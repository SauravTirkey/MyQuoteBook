package modles;

import java.util.List;

public class Question extends Entity{
    private List<Answer> answers;
    private List<EditHistory> editHistories;
    private List<Tag> tags;
    private  String title;
    private String description;
    private QuestionStatus questionStatus;
}
