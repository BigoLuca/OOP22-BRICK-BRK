package brickbreaker.controllers.session;

import brickbreaker.model.user.User;

public interface Session {

    public void setUser(final User user);

    public User getUser();

    public void addData(final Object o);

    public Object getData(final Integer index);

    public Object removeData(final Integer index);
}
