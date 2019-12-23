package com.workouts.workoutsbackend.wger;

import com.workouts.workoutsbackend.clients.YandexClient;
import com.workouts.workoutsbackend.controllers.wger.WgerController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class YandexTestSuite {

    @Autowired
    private YandexClient yandexClient;
    @Autowired
    private WgerController wgerController;

    @Test
    public void testTranslation() {
        //Given
        String simpleText = "Hello world";

        //When
        String translatedText = "";
        try {
            translatedText = yandexClient.translateText(simpleText);
        } catch (UnsupportedEncodingException e) {
            e.getMessage();
        }
        //Then
        Assert.assertEquals("Cześć świat", translatedText);
    }
}
