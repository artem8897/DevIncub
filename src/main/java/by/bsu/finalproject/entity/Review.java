package by.bsu.finalproject.entity;

import java.io.Serializable;

public class Review implements Serializable, Cloneable {

    private int rate;
    private int reviewId;
    private String review;

    public Review() {
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Review review1 = (Review) o;

        if (rate != review1.rate) return false;
        if (reviewId != review1.reviewId) return false;
        return review != null ? review.equals(review1.review) : review1.review == null;
    }

    @Override
    public int hashCode() {
        int result = rate;
        result = 31 * result + reviewId;
        result = 31 * result + (review != null ? review.hashCode() : 0);
        return result;
    }
}
