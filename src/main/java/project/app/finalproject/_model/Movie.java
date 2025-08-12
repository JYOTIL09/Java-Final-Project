package project.app.finalproject._model;

import lombok.Getter;
import lombok.Setter;

public class Movie {

@Getter
    private Integer id;

    @Getter @Setter
    private String title;

    @Getter @Setter
    private String overview;

    @Getter @Setter
    private String releaseDate;

    @Getter @Setter
    private Double rating;

    @Getter @Setter
    private String posterUrl;

    // Constructor, getters, setters
    public Movie(Integer _id,String title, String overview, String releaseDate, Double rating, String posterUrl) {
        this.id = _id;
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.posterUrl = posterUrl;
    }

    public String getDay() {
        //Separating day from released date just for the better view at frontend
        //Used in view template only
        try {
            return releaseDate.substring(8, 10); // "01"
        } catch (Exception e) {
            return "--";
        }
    }

    public String getMonth() {
        //Separating month from released date just for the better view at frontend
        //Used in view template only
        try {
            String month = releaseDate.substring(5, 7); // "07"
            return switch (month) {
                case "01" -> "Jan";
                case "02" -> "Feb";
                case "03" -> "Mar";
                case "04" -> "Apr";
                case "05" -> "May";
                case "06" -> "Jun";
                case "07" -> "Jul";
                case "08" -> "Aug";
                case "09" -> "Sep";
                case "10" -> "Oct";
                case "11" -> "Nov";
                case "12" -> "Dec";
                default -> "---";
            };
        } catch (Exception e) {
            return "---";
        }
    }

    public String getYear() {
        //Separating year from released date just for the better view at frontend
        //Used in view template only
        try {
            return releaseDate.substring(2, 4); // "25"
        } catch (Exception e) {
            return "--";
        }
    }

    public String getRatings() {
        //Adjusting ratings decimal points
        //Used in view template only
        try {
            return String.format("%.1f", rating); // rounds to one decimal
        } catch (Exception e) {
            return "--";
        }
    }

    public int getStarCount() {
        //To represent ratings as stars
        //Used in view template only
        if (rating == null) return 0;
        if (rating >= 9) return 5;
        if (rating >= 8) return 4;
        if (rating >= 6) return 3;
        if (rating >= 4) return 2;
        if (rating >= 2) return 1;
        return 0;
    }


}
