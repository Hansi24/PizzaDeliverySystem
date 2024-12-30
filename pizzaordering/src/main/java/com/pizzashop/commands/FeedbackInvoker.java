package com.pizzashop.commands;

public class FeedbackInvoker {
    private FeedbackCommand command;

    public void setCommand(FeedbackCommand command) {
        this.command = command;
    }

    public void submitFeedback() {
        command.execute();
    }
}
