package bl.fpdual.user.repository;

import java.util.List;

import bl.fpdual.user.model.User;

public interface UserRepository {

	public abstract void addNewUser(User user);

	public abstract void modifyExistingUser(String nickName, User user);

	public abstract void removeUser(String nickName);

	public abstract List<User> getUserListByCountry(String country);

	public Boolean userExists(String nickName);

	public abstract List<User> getUserList();

}
