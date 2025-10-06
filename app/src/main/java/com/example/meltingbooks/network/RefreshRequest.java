// RefreshRequest.java
package com.example.meltingbooks.network;

public class RefreshRequest {
    private User user;

    public RefreshRequest(int userId) {
        this.user = new User(userId);
    }

    public static class User {
        private int id;

        public User(int id) {
            this.id = id;
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
