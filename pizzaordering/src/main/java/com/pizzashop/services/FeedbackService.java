package com.pizzashop.services;

import com.pizzashop.models.Feedback;
import com.pizzashop.repositories.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;

    public void save(Feedback feedback) {
        feedbackRepository.save(feedback);
    }
}
