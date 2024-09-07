package br.com.erikpereira.EncurtadorURL.Links;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class LinkService {

    private final LinkRepository linkRepository;

    public LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    // Generate random url
    public String genUrl() {
        return RandomStringUtils.randomAlphanumeric(5, 10);
    }

    public Link shortenUrl(String originalUrl) {
        Link link = new Link();
        link.setUrlLong(originalUrl);
        link.setUrlShort(genUrl());
        link.setUrlCreatedAt(LocalDateTime.now());
        link.setUrlQrCode("INDISPONIVEL");
        return linkRepository.save(link);
    }

    public String getOriginalUrl(String shortUrl) {

    }
}
