package util;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public final class Placeholders {
//    private static Placeholders instance;
    public String value;

    private final ObjectProperty<Image> small;
    private final ObjectProperty<Image> medium;
    private final ObjectProperty<Image> large;
    private final ObjectProperty<Image> original;
    public Placeholders() {
        try {
            this.small = new SimpleObjectProperty<>(new Image(new FileInputStream(buildFilePath(PhotoSize.SMALL))));
            this.medium = new SimpleObjectProperty<>(new Image(new FileInputStream(buildFilePath(PhotoSize.MEDIUM))));
            this.large = new SimpleObjectProperty<>(new Image(new FileInputStream(buildFilePath(PhotoSize.LARGE))));
            this.original = new SimpleObjectProperty<>(new Image(new FileInputStream(buildFilePath(PhotoSize.ORIGINAL))));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static String buildFilePath(PhotoSize size) {
        String basePath = "frontend/src/main/resources/assets/placeholder";
        String fileExtension = ".png";

        return switch (size) {
            case SMALL -> basePath + "_small" + fileExtension;
            case MEDIUM-> basePath + "_medium" + fileExtension;
            case LARGE -> basePath + "_large" + fileExtension;
            case ORIGINAL -> basePath + fileExtension;
        };
    }

    public ObjectProperty<Image> getPlaceholder(PhotoSize size) {
        return switch (size) {
            case SMALL -> this.small;
            case MEDIUM->this.medium;
            case LARGE -> this.large;
            case ORIGINAL -> this.original;
        };
    }

}