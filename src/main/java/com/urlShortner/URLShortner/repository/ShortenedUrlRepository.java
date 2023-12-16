package com.urlShortner.URLShortner.repository;

import com.urlShortner.URLShortner.domain.Shorturl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortenedUrlRepository extends JpaRepository<Shorturl, Long> {

    @Query("Select s from Shorturl s where s.longUrl = :longUrl")
    public Shorturl getShortUrl(String longUrl);

    @Query("Select longUrl from Shorturl s where s.shortUrl = :url")
    public String fetchLongUrl(String url);

}
