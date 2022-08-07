package nl.novi.eindopdracht.dtos;

import org.springframework.core.io.Resource;

public class FileInfoDto {
    private Resource resource;
    private String mimeType;

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
}
