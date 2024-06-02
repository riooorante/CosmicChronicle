package com.example.cosmicchronicle.model;

import java.util.ArrayList;

public class RoverData {
    private int id;
    private int sol;
    private Camera camera;
    private String img_src;
    private String earth_date;
    private Rover rover;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSol() {
        return sol;
    }

    public void setSol(int sol) {
        this.sol = sol;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public String getImgSrc() {
        return img_src;
    }

    public void setImgSrc(String img_src) {
        this.img_src = img_src;
    }

    public String getEarthDate() {
        return earth_date;
    }

    public void setEarthDate(String earth_date) {
        this.earth_date = earth_date;
    }

    public Rover getRover() {
        return rover;
    }

    public void setRover(Rover rover) {
        this.rover = rover;
    }

    public static class Camera {
        private int id;
        private String name;
        private int rover_id;
        private String full_name;

        // Getters and Setters
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getRoverId() {
            return rover_id;
        }

        public void setRoverId(int rover_id) {
            this.rover_id = rover_id;
        }

        public String getFullName() {
            return full_name;
        }

        public void setFullName(String full_name) {
            this.full_name = full_name;
        }
    }

    public static class Rover {
        private int id;
        private String name;
        private String landing_date;
        private String launch_date;
        private String status;
        private int max_sol;
        private String max_date;
        private int total_photos;
        private ArrayList<RoverCamera> cameras;

        // Getters and Setters
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLandingDate() {
            return landing_date;
        }

        public void setLandingDate(String landing_date) {
            this.landing_date = landing_date;
        }

        public String getLaunchDate() {
            return launch_date;
        }

        public void setLaunchDate(String launch_date) {
            this.launch_date = launch_date;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getMaxSol() {
            return max_sol;
        }

        public void setMaxSol(int max_sol) {
            this.max_sol = max_sol;
        }

        public String getMaxDate() {
            return max_date;
        }

        public void setMaxDate(String max_date) {
            this.max_date = max_date;
        }

        public int getTotalPhotos() {
            return total_photos;
        }

        public void setTotalPhotos(int total_photos) {
            this.total_photos = total_photos;
        }

        public ArrayList<RoverCamera> getCameras() {
            return cameras;
        }

        public void setCameras(ArrayList<RoverCamera> cameras) {
            this.cameras = cameras;
        }
    }

    public static class RoverCamera {
        private String name;
        private String full_name;

        // Getters and Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFullName() {
            return full_name;
        }

        public void setFullName(String full_name) {
            this.full_name = full_name;
        }
    }
}
