package bl.fpdual.user.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import bl.fpdual.user.model.User;
import bl.fpdual.user.model.UserException;
import bl.fpdual.user.repository.UserRepository;
import bl.fpdual.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void addNewUser(User user) {
		if (userRepository.userExists(user.getNickName()))
			throw new UserException("err.user.already.exists");
		userRepository.addNewUser(user);
	}

	@Override
	public void modifyExistingUser(String nickName, User modifiedUser) {
		if (!userRepository.userExists(nickName))
			throw new UserException("err.user.not.found");
		if (isNickNameToModifyInUse(nickName, modifiedUser))
			throw new UserException("nick.name.already.in.use");

		userRepository.modifyExistingUser(nickName, modifiedUser);
	}

	private boolean isNickNameToModifyInUse(String nickName, User modifiedUser) {
		return !modifiedUser.getNickName().equalsIgnoreCase(nickName)
				&& userRepository.userExists(modifiedUser.getNickName());
	}

	@Override
	public void removeUser(String nickName) {
		userRepository.removeUser(nickName);
	}

	@Override
	public List<User> getUserListByCountry(String country) {
		return this.userRepository.getUserListByCountry(country);
	}

	@Override
	public List<User> getUserList() {
		return this.userRepository.getUserList();
	}

}
