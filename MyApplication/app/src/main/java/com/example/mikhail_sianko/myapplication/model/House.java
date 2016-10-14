package com.example.mikhail_sianko.myapplication.model;

public class House {

    private String doors;
    private String title;
    private int size;

    private House(final String pDoors, final String pTitle, final int pSize) {
        doors = pDoors;
        title = pTitle;
        size = pSize;
    }

    public String getDoors() {
        return doors;
    }

    public String getTitle() {
        return title;
    }

    public int getSize() {
        return size;
    }

    private House() {

    }

    public static class Builder {

        private String doors;
        private String title;
        private int size;

        public Builder setDoors(final String doors) {
            this.doors = doors;

            return this;
        }

        public Builder setTitle(final String title) {
            this.title = title;

            return this;
        }

        public Builder setSize(final int size) {
            this.size = size;

            return this;
        }

        public House build() {
            return new House(doors, title, size);
        }

    }

}
