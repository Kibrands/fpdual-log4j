package bl.fpdual.user.service;

import java.util.List;

import bl.fpdual.user.model.User;

public interface UserService {

	public abstract void addNewUser(User user);

	public abstract void modifyExistingUser(String nickName, User modifiedUser);

	public abstract void removeUser(String nickName);

	public abstract List<User> getUserListByCountry(String country);

	public abstract List<User> getUserList();
}
