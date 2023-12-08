package bg.softuni.water_app.config;

import bg.softuni.water_app.service.ReviewService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulerConfig {

    private final ReviewService reviewService;

    public SchedulerConfig(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @Scheduled(cron = "0 0 2 * * *")
    public void removeOldReviews(){
        reviewService.removeOldReviews();
    }
}
