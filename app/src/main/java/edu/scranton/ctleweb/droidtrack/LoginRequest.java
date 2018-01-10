package edu.scranton.ctleweb.droidtrack;

/**
 * Created by sean on 1/10/18.
 */

class LoginRequest {
    final String username;
    final String password;

    LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
