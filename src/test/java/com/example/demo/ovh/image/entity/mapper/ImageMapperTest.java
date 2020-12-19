package com.example.demo.ovh.image.entity.mapper;

import com.example.demo.ovh.image.entity.ImageEntity;
import com.example.demo.ovh.image.entity.model.Image;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class ImageMapperTest {

    @Test
    public void whenEntityIsNullThenReturnNull() {

        Assertions.assertNull(ImageMapper.map(null));
    }

    @Test
    public void whenEntityHasIdThenReturnId() {

        ImageEntity entity = new ImageEntity();
        entity.setId("id");

        Image image = ImageMapper.map(entity);

        Assertions.assertEquals("id", image.getId());
    }

    @Test
    public void whenEntityHasImageIdThenReturnImageId() {

        ImageEntity entity = new ImageEntity();
        entity.setOvhId("ovhId");

        Image image = ImageMapper.map(entity);

        Assertions.assertEquals("ovhId", image.getOvhId());
    }

    @Test
    public void whenEntityHasNameThenReturnName() {

        ImageEntity entity = new ImageEntity();
        entity.setName("name");

        Image image = ImageMapper.map(entity);

        Assertions.assertEquals("name", image.getName());
    }

    @Test
    public void whenEntityHasTypeThenReturnType() {

        ImageEntity entity = new ImageEntity();
        entity.setType("type");

        Image image = ImageMapper.map(entity);

        Assertions.assertEquals("type", image.getType());
    }

    @Test
    public void whenEntityHasImageCreateDateThenReturnImageCreateDate() {

        LocalDateTime createdDate = LocalDateTime.now();

        ImageEntity entity = new ImageEntity();
        entity.setImageCreatedDate(createdDate);

        Image image = ImageMapper.map(entity);

        Assertions.assertEquals(createdDate, image.getImageCreatedDate());
    }

    @Test
    public void whenEntityHasFlavorTypeThenReturnFlavorType() {

        ImageEntity entity = new ImageEntity();
        entity.setFlavorType("flavor-type");

        Image image = ImageMapper.map(entity);

        Assertions.assertEquals("flavor-type", image.getFlavorType());
    }

    @Test
    public void whenEntityHasHourlyThenReturnHourly() {

        ImageEntity entity = new ImageEntity();
        entity.setHourly("hourly");

        Image image = ImageMapper.map(entity);

        Assertions.assertEquals("hourly", image.getHourly());
    }

    @Test
    public void whenEntityHasMonthlyThenReturnMonthly() {

        ImageEntity entity = new ImageEntity();
        entity.setMonthly("monthly");

        Image image = ImageMapper.map(entity);

        Assertions.assertEquals("monthly", image.getMonthly());
    }

    @Test
    public void whenEntityHasSizeThenReturnSize() {

        ImageEntity entity = new ImageEntity();
        entity.setSize(1.0D);

        Image image = ImageMapper.map(entity);

        Assertions.assertEquals(1.0D, image.getSize());
    }

    @Test
    public void whenEntityHasMinRamThenReturnMinRam() {

        ImageEntity entity = new ImageEntity();
        entity.setMinRam(1);

        Image image = ImageMapper.map(entity);

        Assertions.assertEquals(1, image.getMinRam());
    }

    @Test
    public void whenEntityHasMinDiskThenReturnMinDisk() {

        ImageEntity entity = new ImageEntity();
        entity.setMinDisk(1);

        Image image = ImageMapper.map(entity);

        Assertions.assertEquals(1, image.getMinDisk());
    }

    @Test
    public void whenEntityHasUsernameThenReturnUsername() {

        ImageEntity entity = new ImageEntity();
        entity.setUsername("username");

        Image image = ImageMapper.map(entity);

        Assertions.assertEquals("username", image.getUsername());
    }

    @Test
    public void whenEntityHasStatusThenReturnStatus() {

        ImageEntity entity = new ImageEntity();
        entity.setStatus("status");

        Image image = ImageMapper.map(entity);

        Assertions.assertEquals("status", image.getStatus());
    }

    @Test
    public void whenEntityHasVisibilityThenReturnVisibility() {

        ImageEntity entity = new ImageEntity();
        entity.setVisibility("visibility");

        Image image = ImageMapper.map(entity);

        Assertions.assertEquals("visibility", image.getVisibility());
    }
}
