/**
 * Created by cimo on 10/21/2016.
 */

import static spark.Spark.*;

public class Api {
    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello world!");
    }
}
