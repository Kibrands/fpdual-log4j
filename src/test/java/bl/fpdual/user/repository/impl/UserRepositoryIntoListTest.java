package bl.fpdual.user.repository.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bl.fpdual.user.model.User;
import bl.fpdual.user.model.UserException;
import bl.fpdual.user.repository.impl.UserRepositoryIntoList;

public class UserRepositoryIntoListTest {

	private static final String NICK_NAME = "nickName";
	private UserRepositoryIntoList userRepository;
	private final User user = User.builder().nickName(NICK_NAME).build();
	private final User user2 = User.builder().nickName("Otro").build();
	private final User user3 = mock(User.class);

	@BeforeEach
	public void intialize() {
		userRepository = new UserRepositoryIntoList();
	}

	@Test
	public void shouldExceptionWhileModifyingANonExistingUser() {
		assertThrows(UserException.class, () -> userRepository.modifyExistingUser(NICK_NAME, user));
	}

	@Test
	public void shouldModifyUser() {
		userRepository.userList.add(user);
		userRepository.userList.add(user2);
		userRepository.modifyExistingUser(NICK_NAME, user3);
		assertSame(user3, userRepository.userList.get(0));
	}

}
