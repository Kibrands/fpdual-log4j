package bl.fpdual.user.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import bl.fpdual.user.repository.UserRepository;
import lombok.extern.log4j.Log4j;
import bl.fpdual.user.model.User;
import bl.fpdual.user.model.UserException;

@Repository
@Log4j
public class UserRepositoryIntoList implements UserRepository {
	protected final List<User> userList = new ArrayList<>();

	@Override
	public void addNewUser(User user) {
		userList.add(user);
		log.debug("User " + user.getNickName() + " added");
	}

	@Override
	public void modifyExistingUser(String nickName, User user) {
		final int position = userList.indexOf(User.builder().nickName(nickName).build());
		if (position == -1)
			throw new UserException("err.user.not.found");
		userList.set(position, user);
		log.debug("User " + user.getNickName() + " modified");
	}

	@Override
	public void removeUser(String nickName) {
		userList.remove(User.builder().nickName(nickName).build());
		log.debug("User " + nickName + " removed");
	}

	@Override
	public List<User> getUserListByCountry(String country) {
		return userList.stream().filter(u -> u.getCountry().equalsIgnoreCase(country)).collect(Collectors.toList());
	}

	@Override
	public Boolean userExists(String nickName) {
		return (userList.contains(User.builder().nickName(nickName).build()));
	}

	@Override
	public List<User> getUserList() {
		return userList.stream().collect(Collectors.toList());
	}

}
