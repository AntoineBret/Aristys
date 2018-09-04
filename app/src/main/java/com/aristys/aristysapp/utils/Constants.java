package com.aristys.aristysapp.utils;

public class Constants {

    //Set content_type for json request header
    public static final String CONTENT_TYPE = "Content-Type: application/json;charset=UTF-8";

    //Set individual room size
    public static final Integer INDIVIDUAL_ROOM_SIZE = 2;

    //Set view type for RoomDetailsAdapter
    public static final int VIEW_TYPE_MESSAGE_SENT = 1;
    public static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;

    //Http codes list
    public interface httpcodes {
        int STATUS_OK = 200;
        String MESSAGE_STATUS_OK = "Query successfully processed : 200";

        int STATUS_NO_CONTENT = 204;
        String MESSAGE_NO_CONTENT = "";

        int STATUS_BAD_REQUEST = 400;
        String MESSAGE_BAD_REQUEST = "Bad request : 404 error";

        int STATUS_UNAUTHORIZED = 401;
        String MESSAGE_UNAUTHORIZED = "Unauthenticated user : 401 error";

        int STATUS_FORBITTEN = 403;
        String MESSAGE_FORBITTEN = "Access denied : 403 error";

        int STATUS_NOT_FOUND = 404;
        String MESSAGE_NOT_FOUND = " Page not found : 404 error";

        int STATUS_SERVER_ERROR = 500;
        String MESSAGE_SERVER_ERROR = "Server Error : 500 error";

        String ERROR_UNKNOWN = "Error not hold : unknown error";
    }

    //trello key
    public interface trello_url {
        String API_KEY = "262b4db409b7f8233f80ae9e51476dd3";
        String API_SECRET = "faa3fc38297a6419cbe6d3ac6c1e38626cfa9c6b0812552742824321748e2882";
        String RESOURCE_URL ="https://trello.com/1/members/me";
    }

    //drawer constants
    public interface menu_drawer {
        public static final long blog = 1;
        public static final long business = 2;
        public static final long work = 3;
        public static final long partnair = 4;
        public static final long contact = 5;
        public static final long twitter = 6;
        public static final long facebook = 7;
        public static final long linkedin = 8;
    }
}
