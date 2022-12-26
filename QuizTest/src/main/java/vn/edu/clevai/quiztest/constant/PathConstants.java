package vn.edu.clevai.quiztest.constant;

public final class PathConstants {

    private PathConstants(){
    }

    public static final String ALL_PATTERN = "/**";
    public static final String API_BASE_URL = "/api";
    public static final String PRIVATE_BASE_URL = "/private";

    public static final String API_LO_BASE_URL = API_BASE_URL + "/learning-object";

    public static final String API_QUIZ_BASE_URL = API_BASE_URL+"/quiz";
    public static final String API_QUIZ_RANDOM_URL = API_QUIZ_BASE_URL+"/random";
    public static final String API_QUIZ_VIEW_URL = API_QUIZ_BASE_URL +"/{id}";
    public static final String API_QUIZ_UPDATE_URL = API_QUIZ_BASE_URL+"/{id}";

    public  static final  String API_USER_BASE_URL = API_BASE_URL + "/user" ;
    public static final String API_USER_PROGRESS_BASE_URL = API_USER_BASE_URL + "/progress" ;
    public static  final String API_USER_PROGRESS_VIEW_URL = API_USER_PROGRESS_BASE_URL + "/{id}";

}
