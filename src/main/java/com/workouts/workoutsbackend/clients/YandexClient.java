package com.workouts.workoutsbackend.clients;

import com.workouts.workoutsbackend.domain.dto.YandexDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;

@Component
public class YandexClient {

    @Value("${yandex.api.endpoint.prod}")
    private String yandexEndopint;

    @Value("${yandex.api.key}")
    private String yandexKey;

    @Value("${yandex.api.lang}")
    private String yandexLang;

    @Autowired
    private RestTemplate restTemplate;

    public String translateText(String text) throws UnsupportedEncodingException{
        URI url = UriComponentsBuilder.fromHttpUrl(yandexEndopint)
                .queryParam("key", yandexKey)
                .queryParam("text", URLEncoder.encode(text, "UTF-8"))
                .queryParam("lang", yandexLang).build().encode().toUri();

        try {
            YandexDto yandexDto = restTemplate.getForObject(url, YandexDto.class);
            return yandexDto.getTranslatedText().get(0);
        } catch (RestClientException e) {
            return "";
        }
    }

    public String translationExceptionHandler(String textToTranslate) {
        try {
            return translateText(textToTranslate);
        } catch (UnsupportedEncodingException e) {
            return textToTranslate;
        }
    }
}
