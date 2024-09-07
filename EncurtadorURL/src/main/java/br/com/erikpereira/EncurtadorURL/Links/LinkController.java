package br.com.erikpereira.EncurtadorURL.Links;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
public class LinkController {

    private final LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping("/encurta-url")
    public ResponseEntity<LinkResponse> generateUrlShort(@RequestBody Map<String, String> request) {
        String urlLong = request.get("urlLong");
        Link link = linkService.shortenUrl(urlLong);

        String generateUserRedirectUrl = "http://localhost:8080/r/" + link.getUrlShort();

        LinkResponse response = new LinkResponse(
                link.getId(),
                link.getUrlLong(),
                generateUserRedirectUrl,
                link.getUrlQrCode(),
                link.getUrlCreatedAt()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/r/{urlShort}")
    public void redirectUrl(@PathVariable String urlShort, HttpServletResponse response) throws IOException {
        Link link = linkService.getUrlOriginal(urlShort);

        if (link != null) {
            response.sendRedirect(link.getUrlLong());
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

}
