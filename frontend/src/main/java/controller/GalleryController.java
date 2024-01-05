package controller;


import api.CommunicationHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import model.Gallery;
import model.Image;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import util.PhotoSize;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GalleryController {

    @FXML
    private TextField imageNameField;

    @FXML
    private ImageView imageView;

    @FXML
    private ListView<Image> imagesListView;

    @FXML
    private StackPane fileDropPane;

    @FXML
    private Label dropPaneLabel;

    private Gallery galleryModel;

    private PhotoSize size = PhotoSize.SMALL;

    @FXML
    private ListView<String> draggedFilesNamesList;

    private ObservableList<String> draggedFilesNames = FXCollections.observableArrayList();

    private List<File> filesToUpload = new ArrayList<>();




    @FXML
    public void initialize() {

        imagesListView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Image item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    StackPane stackPane = new StackPane();
                    ImageView imageView = new ImageView(item.getPhotoData());
                    imageView.setPreserveRatio(true);
                    imageView.setFitHeight(50);
                    stackPane.getChildren().add(imageView);

                    setGraphic(stackPane);
                }
            }
        });

        imagesListView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {

                    bindSelectedPhoto(newValue);
                }
        );
        draggedFilesNamesList.setItems(draggedFilesNames);
    }

    @FXML
    private void handleDragOver(DragEvent event) {
        if (event.getGestureSource() != fileDropPane && event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.COPY);
        }
        event.consume();
    }

    @FXML
    private void handleDragDropped(DragEvent event) {
        Dragboard db = event.getDragboard();
        boolean success = false;

        if (db.hasFiles()) {
            success = true;
            String filesText = "Dropped files:\n";

            for (java.io.File file : db.getFiles()) {
                filesText += file.getAbsolutePath() + "\n";
                filesToUpload.add(file);
                draggedFilesNames.add(file.getAbsolutePath());
            }

            System.out.println(filesText);
        }

        dropPaneLabel.setText(draggedFilesNames.size() + " files dropped");
        event.setDropCompleted(success);
        event.consume();
    }

    @FXML
    private void clearUploadList() {
        dropPaneLabel.setText("Drag your files here");
        filesToUpload.clear();
        draggedFilesNames.clear();
    }

    @FXML
    private void uploadPhotos() throws IOException {

        CommunicationHandler.uploadPhotos(filesToUpload);
        clearUploadList();
    }

    @FXML
    private void onSizeChangeSmall() throws IOException {
        size = PhotoSize.SMALL;
        fillGallery();
    }

    @FXML
    private void onSizeChangeMedium() throws IOException {
        size = PhotoSize.MEDIUM;
        fillGallery();

    }

    @FXML
    private void onSizeChangeLarge() throws IOException {
        size = PhotoSize.LARGE;
        fillGallery();
    }

    public void setModel(Gallery gallery) throws IOException {
        this.galleryModel = gallery;
        imagesListView.setItems(gallery.getPhotos());
        imagesListView.getSelectionModel().select(0);
        fillGallery();
    }

    private void bindSelectedPhoto(Image selectedPhoto) {
        imageView.imageProperty().bind(selectedPhoto.photoDataProperty());
    }

    // needs to be called every 1s before the socket works
    private void fillGallery() throws IOException {
        CommunicationHandler.getAllPhotos(galleryModel, size);
    }

}