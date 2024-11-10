package com.trip.enjoy_trip.repository;

import com.trip.enjoy_trip.dto.ReviewDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ReviewRepository {
    void insertReview(ReviewDto reviewDto);
    void insertReviewImages(Integer reviewId, List<String> imageUrls);
    ReviewDto findById(Integer reviewId);        // 특정 리뷰 조회
    List<ReviewDto> findAll();                   // 전체 리뷰 목록 조회

    void updateReview(ReviewDto reviewDto);      // 리뷰 수정
    void deleteReviewImages(Integer reviewId);       // 기존 이미지 삭제
    void deleteReview(Integer reviewId);         // 리뷰 삭제

    //좋아요 기능
    void insertLike(@Param("reviewId") Integer reviewId, @Param("userId") Integer userId);
    int selectLikeCount(@Param("reviewId") Integer reviewId);
    int deleteLike(@Param("reviewId") Integer reviewId, @Param("userId") Integer userId);
    int isUserLikedReview(@Param("reviewId") Integer reviewId, @Param("userId") Integer userId);

    //북마크 기능
    void insertBookmark(@Param("reviewId") Integer reviewId, @Param("userId") Integer userId); //북마크 추가
    int checkBookmark(@Param("reviewId") Integer reviewId, @Param("userId") Integer userId); //북마크 체크 확인
    int deleteBookmark(@Param("reviewId") Integer reviewId, @Param("userId") Integer userId); //북마크 취소

    //해시태그 기능
    // 해시태그 기능
    Integer findHashtagId(String hashtag);  // 해시태그 ID 찾기
    void createHashtag(String hashtag);     // 해시태그 생성
    void addHashtagToReview(@Param("reviewId") Integer reviewId, @Param("hashtagId") Integer hashtagId);  // 리뷰에 해시태그 추가
    List<String> findHashtagsByReviewId(Integer reviewId);  // 특정 리뷰에 등록된 해시태그 조회
    Integer findHashtagIdByName(String hashtag);  // 해시태그 이름으로 ID 찾기(삭제 용도)
    void deleteHashtagFromReview(@Param("reviewId") Integer reviewId, @Param("hashtagId") Integer hashtagId);  // 특정 리뷰에서 해시태그 삭제

}
