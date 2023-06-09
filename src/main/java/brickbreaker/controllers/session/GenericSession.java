package brickbreaker.controllers.session;

import java.util.List;

import brickbreaker.model.user.User;

public abstract class GenericSession implements Session {

    private User u;
    private List<Object> additionalData;

    public GenericSession() {

    }

    public GenericSession(final User u) {
        this.u = u;
    }

    @Override
    public void setUser(User user) {
        this.u = user;
    }

    @Override
    public User getUser() {
        return this.u;
    }

    public void addData(final Object o) {
        this.additionalData.add(o);
    }

    public Object getData(final Integer index) {
        return this.additionalData.get(index);
    }

    public Object removeData(final Integer index) {
        return this.additionalData.remove(index);
    }
}
