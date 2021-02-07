package com.example.demo.ovh.image.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class FetchAdminGameServerImagesResponseTest {

    @Test
    public void whenModelHasImagesThenReturnImages() {

        FetchAdminGameServerImagesResponse model = new FetchAdminGameServerImagesResponse(new ArrayList<>());

        Assertions.assertEquals(new ArrayList<>(), model.getImages());
    }

    @Test
    public void whenModelToString() {

        FetchAdminGameServerImagesResponse model = model();

        String expected = "FetchAdminGameServerImagesResponse(images=[])";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchAdminGameServerImagesResponse model = model();

        Assertions.assertEquals(60, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchAdminGameServerImagesResponse model1 = model();
        FetchAdminGameServerImagesResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchAdminGameServerImagesResponse model = model();

        Assertions.assertNotEquals(model , new FetchAdminGameServerImagesResponse(null));
    }

    private FetchAdminGameServerImagesResponse model() {

        return new FetchAdminGameServerImagesResponse(new ArrayList<>());
    }
}
