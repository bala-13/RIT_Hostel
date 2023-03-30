package com.example.rithostel;

public class Users {
        public String username;
        public String email;

        public String password;

        public Users() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public Users(String username, String email,String password) {
            this.username = username;
            this.email = email;
            this.password=password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
}
