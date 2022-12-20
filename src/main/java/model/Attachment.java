package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Attachment {
    private long id;
    private long size;
    private long attachmentId;
    private String contentType;
    private String fileName;
}
