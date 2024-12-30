package com.pizzashop.commands;

import com.pizzashop.models.Feedback;
import com.pizzashop.services.FeedbackService;

public class SubmitFeedbackCommand implements FeedbackCommand {
    private Feedback feedback;
    private FeedbackService feedbackService;

    public SubmitFeedbackCommand(Feedback feedback, FeedbackService feedbackService) {
        this.feedback = feedback;
        this.feedbackService = feedbackService;
    }

    @Override
    public void execute() {
        feedbackService.save(feedback); // Persist the feedback to DB
    }
}
