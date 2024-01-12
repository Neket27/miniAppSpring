package app.miniappspring.entity;

import jakarta.persistence.*;
import lombok.*;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

@Entity
@Table(name = "images")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Image  {
    @Id
    private Long id;
    private String name;
    private String originalFileName;
    private Long size;
    private String contentType;
    @Lob
    private byte[] bytes;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;


}
