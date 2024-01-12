package app.miniappspring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image extends java.awt.Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    byte[] bytes;


    @Override
    public int getWidth(ImageObserver imageObserver) {
        return 0;
    }

    @Override
    public int getHeight(ImageObserver imageObserver) {
        return 0;
    }

    @Override
    public ImageProducer getSource() {
        return null;
    }

    @Override
    public Graphics getGraphics() {
        return null;
    }

    @Override
    public Object getProperty(String s, ImageObserver imageObserver) {
        return null;
    }
}
