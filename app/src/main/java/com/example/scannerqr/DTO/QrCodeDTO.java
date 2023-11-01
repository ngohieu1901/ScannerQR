package com.example.scannerqr.DTO;

public class QrCodeDTO {
    private int image;
    private boolean isSelected;


    public QrCodeDTO(int image, boolean isSelected) {
        this.image = image;
        this.isSelected = isSelected;
    }

    public QrCodeDTO() {
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
