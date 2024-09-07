package br.com.erikpereira.EncurtadorURL.Links;

import java.time.LocalDateTime;

public class LinkResponse {
    private Long id;
    private String urlLong;
    private String urlShort;
    private String urlQrCode;
    private LocalDateTime urlCreatedAt;

    public LinkResponse(Long id, String urlLong, String urlShort, String urlQrCode, LocalDateTime urlCreatedAt) {
        this.id = id;
        this.urlLong = urlLong;
        this.urlShort = urlShort;
        this.urlQrCode = urlQrCode;
        this.urlCreatedAt = urlCreatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrlLong() {
        return urlLong;
    }

    public void setUrlLong(String urlLong) {
        this.urlLong = urlLong;
    }

    public String getUrlShort() {
        return urlShort;
    }

    public void setUrlShort(String urlShort) {
        this.urlShort = urlShort;
    }

    public String getUrlQrCode() {
        return urlQrCode;
    }

    public void setUrlQrCode(String urlQrCode) {
        this.urlQrCode = urlQrCode;
    }

    public LocalDateTime getUrlCreatedAt() {
        return urlCreatedAt;
    }

    public void setUrlCreatedAt(LocalDateTime urlCreatedAt) {
        this.urlCreatedAt = urlCreatedAt;
    }
}
