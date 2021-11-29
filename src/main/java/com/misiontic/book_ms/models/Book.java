package com.misiontic.book_ms.models;

import org.springframework.data.annotation.Id;

public class Book {

    @Id
    private String id;
    private String title;
    private String author;
    private String editorial;
    private String genre;
    private String year;
    private String physicalState;
    private String edition;
    private String state;
    private String language;
    private Integer idOwner;
    private int idAplicant;
    private boolean requested;

    public Book(String id, String title, String author, String editorial, String genre, String year, String physicalState, String edition, String language, int idOwner) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.editorial = editorial;
        this.genre = genre;
        this.year = year;
        this.physicalState = physicalState;
        this.edition = edition;
        this.state = "available";
        this.language = language;
        this.idOwner = idOwner;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPhysicalState() {
        return physicalState;
    }

    public void setPhysicalState(String physicalState) {
        this.physicalState = physicalState;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(int idOwner) {
        this.idOwner = idOwner;
    }

    public int getIdAplicant() {
        return idAplicant;
    }

    public void setIdAplicant(int idAplicant) {
        this.idAplicant = idAplicant;
    }

    public boolean isRequested() {
        return requested;
    }

    public void setRequested(boolean requested) {
        this.requested = requested;
    }
}
