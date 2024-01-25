package app.miniappspring.entity;

import jakarta.persistence.*;
import lombok.*;


@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Image {
    @Column(name = "name_image")
    private String name;
//    private String originalFileName;
//    private Long size;
    private String contentType;
    @Lob
    private byte[] bytes;

}
