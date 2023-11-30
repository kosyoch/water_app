package bg.softuni.water_app.service;

import bg.softuni.water_app.model.dto.review.ReviewAddBindingModel;

public interface ReviewService {
    void add (ReviewAddBindingModel reviewAddBindingModel);

    void remove(Long id);
}
