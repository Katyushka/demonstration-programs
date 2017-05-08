package com.demo.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;

/**
 * @author Ekaterina Pyataeva on 07.05.2017.
 */
public class ArticleUtils {
    public static final String PDF_MEDIA_TYPE = "application/pdf";

    public static ResponseEntity<byte[]> get404RedirectEntity(HttpHeaders headers) {
        headers.add("Location", "/404");
        return new ResponseEntity<byte[]>(null, headers, HttpStatus.FOUND);
    }


    public static HttpHeaders setHeaders(HttpHeaders headers, String mediaType, String fileName) {

        headers.setContentType(MediaType.parseMediaType(mediaType));
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + fileName);
        return headers;
    }

}
